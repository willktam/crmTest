package modules.staff.StaffList;

import org.skyve.CORE;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.persistence.DocumentQuery;

import modules.admin.ModulesUtil;
import modules.admin.User.UserExtension;
import modules.staff.domain.StaffList;

public class StaffListBizlet extends Bizlet<StaffList> {

	@Override
	public StaffList newInstance(StaffList bean) throws Exception {
		// Create Document Query for Staff Document
		DocumentQuery dq = CORE.getPersistence().newDocumentQuery(StaffList.MODULE_NAME, StaffList.DOCUMENT_NAME);
				
		dq.getFilter().addEquals(StaffList.userPropertyName, ModulesUtil.currentAdminUser());
				
		// Create staff from dq bean result
		StaffList stafflist = dq.beanResult();
				
		// in case of staff is null, we will return an empty bean with User and Contact Information
		if (stafflist == null) {
			bean.setUser((UserExtension) ModulesUtil.currentAdminUser());
			return bean;
		}
		// else return staff
		return stafflist;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4675730439916051771L;

}
