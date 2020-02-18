package modules.sales.Invoice;

import org.skyve.domain.Bean;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

import modules.admin.ModulesUtil;

public class InvoiceBizlet extends Bizlet<InvoiceExtension> {

	private static final long serialVersionUID = -3315242171599158721L;
	
	@Override
	public InvoiceExtension newInstance(InvoiceExtension bean) throws Exception {
		bean.setSelectedTab(0);
		// Create a new id on new instance
		bean.setInvoiceId(ModulesUtil.getNextDocumentNumber("INV", InvoiceExtension.MODULE_NAME, InvoiceExtension.DOCUMENT_NAME, InvoiceExtension.invoiceIdPropertyName, 8));
		return super.newInstance(bean);
	}
	
	@Override
	public InvoiceExtension preExecute(ImplicitActionName actionName, InvoiceExtension bean, Bean parentBean,
			WebContext webContext) throws Exception {
		if (ImplicitActionName.Save.equals(actionName) || ImplicitActionName.OK.equals(actionName)) {
			if (bean.isChanged()) {
				bean.updateInteraction();
			}
			if (bean.isNotPersisted()) {
				bean.createInteraction();
			}
		}
		if (bean.isPersisted()) {
			bean.sortInteractions();
		}
		return super.preExecute(actionName, bean, parentBean, webContext);
	}
	
	
	@Override
	public void preRerender(String source, InvoiceExtension bean, WebContext webContext) throws Exception {
		if (bean.getOrder() != null) {
			bean.setTotal(bean.getOrder().getTotal());
		}
		super.preRerender(source, bean, webContext);
	}
}
