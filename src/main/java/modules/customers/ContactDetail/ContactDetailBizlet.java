package modules.customers.ContactDetail;

import org.skyve.domain.Bean;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

import modules.customers.ContactDetail.ContactDetailExtension;
import modules.customers.domain.Interaction;

public class ContactDetailBizlet extends Bizlet<ContactDetailExtension> {

	private static final long serialVersionUID = -840436736363283863L;
	
	@Override
	public ContactDetailExtension preExecute(ImplicitActionName actionName, ContactDetailExtension bean, Bean parentBean,
			WebContext webContext) throws Exception {
		//
		if (ImplicitActionName.Save.equals(actionName) || ImplicitActionName.OK.equals(actionName)) {
			if (bean.isChanged()) {
				Interaction interaction = bean.createInteraction();
				bean.setInteractionsElementById(bean.getBizId(), interaction);
				
			}
		}
		return super.preExecute(actionName, bean, parentBean, webContext);
	}
	
}
