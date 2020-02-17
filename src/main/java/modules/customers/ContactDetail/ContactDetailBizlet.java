package modules.customers.ContactDetail;

import org.skyve.domain.Bean;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

import modules.customers.ContactDetail.ContactDetailExtension;

public class ContactDetailBizlet extends Bizlet<ContactDetailExtension> {

	private static final long serialVersionUID = -840436736363283863L;
	
	@Override
	public ContactDetailExtension preExecute(ImplicitActionName actionName, ContactDetailExtension bean, Bean parentBean,
			WebContext webContext) throws Exception {
		if (ImplicitActionName.Save.equals(actionName) || ImplicitActionName.OK.equals(actionName)) {
			if (bean.isChanged()) {
				bean.updateInteraction();
			}
		}
		bean.getNewInteractions();
		return super.preExecute(actionName, bean, parentBean, webContext);
	}
	
	@Override
	public ContactDetailExtension newInstance(ContactDetailExtension bean) throws Exception {
		if (bean.isNotPersisted()) {
			bean.setSelectedTab(0);
		}
		bean.createInteraction();
		return super.newInstance(bean);
	}
	
}
