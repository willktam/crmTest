package modules.admin.ImportExport.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.skyve.CORE;
import org.skyve.domain.PersistentBean;
import org.skyve.domain.messages.Message;
import org.skyve.domain.messages.MessageSeverity;
import org.skyve.domain.messages.OptimisticLockException;
import org.skyve.domain.messages.UploadException;
import org.skyve.domain.messages.ValidationException;
import org.skyve.impl.bizport.AbstractDataFileLoader.LoaderActivityType;
import org.skyve.impl.bizport.DataFileField;
import org.skyve.impl.bizport.DataFileField.LoadAction;
import org.skyve.impl.bizport.POISheetLoader;
import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.metadata.customer.Customer;
import org.skyve.metadata.model.document.Document;
import org.skyve.metadata.module.Module;
import org.skyve.persistence.Persistence;
import org.skyve.util.Util;
import org.skyve.web.WebContext;

import modules.admin.ImportExportColumn.ImportExportColumnBizlet;
import modules.admin.domain.ImportExport;
import modules.admin.domain.ImportExport.LoadType;
import modules.admin.domain.ImportExport.RollbackErrors;
import modules.admin.domain.ImportExportColumn;

public class RunImport implements ServerSideAction<ImportExport> {

	private static final long serialVersionUID = 7301976416286938546L;

	@Override
	public ServerSideActionResult<ImportExport> execute(ImportExport bean, WebContext webContext)
			throws Exception {

		if (bean.getImportFileAbsolutePath() != null) {

			File importFile = new File(bean.getImportFileAbsolutePath());
			UploadException exception = new UploadException();

			int loadedRows = 0;
			int created = 0;
			
			Persistence persistence = CORE.getPersistence();
			Customer customer = CORE.getCustomer();
			Module module = customer.getModule(bean.getModuleName());
			Document document = module.getDocument(customer, bean.getDocumentName());			
			
			try (InputStream poiStream = new FileInputStream(importFile)) {

				POISheetLoader loader = new POISheetLoader(poiStream, 0, bean.getModuleName(), bean.getDocumentName(), exception);
				loader.setDebugMode(true);
				if(LoadType.createAll.equals(bean.getLoadType())) {
					loader.setActivityType(LoaderActivityType.CREATE_ALL);
				} else {
					loader.setActivityType(LoaderActivityType.CREATE_FIND);
				}				loader.setDebugMode(true);

				// include headers
				if (Boolean.TRUE.equals(bean.getFileContainsHeaders())) {
					loader.setDataIndex(1);
				}

				// and field bindings to loader
				for (ImportExportColumn col : bean.getImportExportColumns()) {
					
					String resolvedBinding = col.getBindingName();
					if(ImportExportColumnBizlet.EXPRESSION.equals(col.getBindingName())){
						if(col.getBindingExpression()!=null ) {
							if(col.getBindingExpression().indexOf("{")>-1) {
								resolvedBinding = col.getBindingExpression().substring(col.getBindingExpression().indexOf("{")+1,col.getBindingExpression().lastIndexOf("}"));
							} else {
								resolvedBinding = col.getBindingExpression();
							}
						} else {
							StringBuilder msg = new StringBuilder();
							msg.append("You selected '").append(ImportExportColumnBizlet.EXPRESSION).append("' for column ").append(col.getColumnName());
							msg.append(" but have not provided a binding expression.");
							throw new ValidationException(new Message(msg.toString()));
						}
					}
					StringBuilder sb = new StringBuilder();
					sb.append("Adding field with binding ").append(resolvedBinding);
										
					//add field to loader configuration
					DataFileField f = new DataFileField(resolvedBinding);
					f.setLoadAction(null);  //default behaviour
					if(col.getLoadAction()!=null) {
						switch(col.getLoadAction()) {
						case confirmValue:
							f.setLoadAction(LoadAction.CONFIRM_VALUE);
							break;
						case lookupContains:
							f.setLoadAction(LoadAction.LOOKUP_CONTAINS);
							break;
						case lookupEquals:
							f.setLoadAction(LoadAction.LOOKUP_EQUALS);
							break;
						case lookupLike:
							f.setLoadAction(LoadAction.LOOKUP_LIKE);
							break;
						case setValue:
							f.setLoadAction(LoadAction.SET_VALUE);
							break;
						default:
							break;
						}
						sb.append(" using load action ").append(col.getLoadAction().toDescription());
					}
					
					if(loader.isDebugMode()) {
						Util.LOGGER.info(sb.toString());
					}
					loader.addField(f);
					Util.LOGGER.info("Field added at position " + f.getIndex().toString());
				}

				// save uploaded rows
				while (loader.hasNextData()) {
					loader.nextData();
					
					//stop at empty row
					if (loader.isNoData()) {
						Util.LOGGER.info("End of import found at " + loader.getWhere());
						break;
					}

					Util.LOGGER.info("------TRANSFORMING ------");

					PersistentBean b = loader.beanResult();
					Util.LOGGER.info("------LOAD RESULT ------");

					if (loader.isDebugMode()) {
						if (b == null) {
							Util.LOGGER.info("Loaded failed at " + loader.getWhere());
						} else {
							Util.LOGGER.info(b.getBizKey() + " - Loaded successfully");
						}
					}
					try {
						if (b != null && (b.getBizKey() == null || b.getBizKey().trim().length() == 0)) {
							String msg = "The new record has no value for bizKey at row " + created + ".";
							ValidationException ve = new ValidationException(new Message(msg));
							throw ve;
						}
						//Testing
						Util.LOGGER.info("------ATTEMPTING TO SAVE------");
						
						b = persistence.save(b);
						if (loader.isDebugMode()) {
							Util.LOGGER.info(b.getBizKey() + " - Saved successfully");
						}
						persistence.evictCached(b);

						//commit and start a new transaction if selected
						if(RollbackErrors.noRollbackErrors.equals(bean.getRollbackErrors())) {
							persistence.commit(false);
							persistence.begin();
						}
						created++;

					} catch (ValidationException ve) {
						StringBuilder msg = new StringBuilder();
						msg.append("The import succeeded but the imported record could not be saved because imported values were not valid:");
						msg.append("\nCheck upload values and try again.");
						msg.append("\n");
						for (Message m : ve.getMessages()) {
							msg.append("\n").append(m.getText());
						}

						throw new ValidationException(new Message(msg.toString()));
					} catch (OptimisticLockException ole) {
						StringBuilder msg = new StringBuilder();
						msg.append("The import succeeded but the save failed.");
						msg.append(
								"\nCheck that you don't have duplicates in your file, or multiple rows in your file are finding the same related record, or that other users are not changing related data.");
						throw new ValidationException(new Message(msg.toString()));
					} catch (Exception e) {
						StringBuilder msg = new StringBuilder();
						msg.append("The import succeeded but saving the records failed.");
						msg.append("\nCheck that you are uploading to the correct binding and that you have supplied enough information for the results to be saved.");
						throw new ValidationException(new Message(msg.toString()));
					}
					loadedRows++;
				}
			}

			// construct result message
			StringBuilder sb = new StringBuilder();
			if (loadedRows > 0) {
				sb.append("Successfully loaded ").append(loadedRows).append(" rows. ");
				sb.append(created).append(' ').append(document.getPluralAlias()).append(" created.");
			} else {
				sb.append("Import unsuccessful. Try again.");
			}
			bean.setResults(sb.toString());
			webContext.growl(MessageSeverity.info, sb.toString());
		}

		return new ServerSideActionResult<>(bean);
	}
}
