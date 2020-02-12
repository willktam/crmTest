package modules.customers.ContactDetail.actions;

import org.skyve.CORE;
import org.skyve.domain.messages.Message;
import org.skyve.domain.messages.ValidationException;
import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.metadata.customer.Customer;
import org.skyve.metadata.model.document.Document;
import org.skyve.metadata.module.Module;
import org.skyve.metadata.user.User;
import org.skyve.util.Binder;
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
		
		User user = CORE.getUser();
		Customer customer = user.getCustomer();
		Module module = customer.getModule(ContactDetail.MODULE_NAME);
		Document document = module.getDocument(customer, ContactDetail.DOCUMENT_NAME);
		String collectionBinding = ContactDetail.interactionsPropertyName;
		Binder.sortCollectionByMetaData(bean, customer, module, document, collectionBinding);
		
//		int size = bean.getInteractions().size();
//		if (size > 25) {
//			bean.getInteractions().removeAll(bean.getInteractions().subList(fromIndex, toIndex))
//			bean.getInteractions().addAll(bean.getInteractions().subList(0, 25));
//		}

		return new ServerSideActionResult<>(bean);
	}

}
