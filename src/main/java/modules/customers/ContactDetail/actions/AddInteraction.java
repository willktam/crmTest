package modules.customers.ContactDetail.actions;

import org.skyve.CORE;
import org.skyve.domain.messages.Message;
import org.skyve.domain.messages.ValidationException;
import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.util.BeanValidator;
import org.skyve.web.WebContext;

import modules.customers.ContactDetail.ContactDetailExtension;
import modules.customers.domain.ContactDetail;

public class AddInteraction implements ServerSideAction<ContactDetailExtension> {

	private static final long serialVersionUID = 3164811346643625845L;

	@Override
	public ServerSideActionResult<ContactDetailExtension> execute(ContactDetailExtension bean, WebContext webContext)
			throws Exception {

		// check for required fields
		if(bean.getInteractionType() == null) {
			throw new ValidationException(new Message(ContactDetail.interactionTypePropertyName, "Type is required"));
		}
		if(bean.getInteractionDescription() == null) {
			throw new ValidationException(new Message(ContactDetail.interactionDescriptionPropertyName, "Description is required"));
		}
		
		bean.createInteraction(bean.getInteractionType(), bean.getInteractionDescription());
		
		// clear the quick add form
		bean.setInteractionDescription(null);
		bean.setInteractionType(null);
		
		bean = CORE.getPersistence().save(bean);
		
		return new ServerSideActionResult<>(bean);
	}

}
