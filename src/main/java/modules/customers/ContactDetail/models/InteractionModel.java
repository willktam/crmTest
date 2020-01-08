package modules.customers.ContactDetail.models;

import java.util.List;

import org.skyve.metadata.module.query.MetaDataQueryColumn;
import org.skyve.metadata.view.model.list.ReferenceListModel;

import modules.customers.domain.ContactDetail;

public class InteractionModel extends ReferenceListModel<ContactDetail> {

	private static final long serialVersionUID = 7487046903328437644L;

	public InteractionModel(String moduleName, String drivingDocumentName, String referenceBinding) throws Exception {
		super(moduleName, drivingDocumentName, referenceBinding);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MetaDataQueryColumn> getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

}
