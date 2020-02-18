package modules.sales.Lead.actions;

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

import modules.customers.domain.ContactDetail;
import modules.sales.Lead.LeadExtension;

public class AddInteraction implements ServerSideAction<LeadExtension> {
	
	private static final long serialVersionUID = -1325718922332249285L;

	@Override
	public ServerSideActionResult<LeadExtension> execute(LeadExtension bean, WebContext webContext) 
			throws Exception {
		
		// check for required fields
		if (bean.getContactDetails().getInteractionType() == null) {
			throw new ValidationException(new Message(ContactDetail.interactionTypePropertyName, "Type is required"));
		}
		if (bean.getContactDetails().getInteractionDescription() == null) {
			throw new ValidationException(new Message(ContactDetail.interactionDescriptionPropertyName, "Description is required"));
		}
		
		bean.createInteraction(bean.getContactDetails().getInteractionType(), bean.getContactDetails().getInteractionDescription(), bean.getContactDetails().getDocument());
			
		// clear the quick add form
		bean.getContactDetails().setInteractionDescription(null);
		bean.getContactDetails().setInteractionType(null);
		bean.getContactDetails().setDocument(null);
		
		bean = CORE.getPersistence().save(bean);
				
		User user = CORE.getUser();
		Customer customer = user.getCustomer();
		Module module = customer.getModule(ContactDetail.MODULE_NAME);
		Document document = module.getDocument(customer, ContactDetail.DOCUMENT_NAME);
		String collectionBinding = ContactDetail.interactionsPropertyName;
		Binder.sortCollectionByMetaData(bean.getContactDetails(), customer, module, document, collectionBinding);
			
		// keep only the newest interactions
		if (bean.getContactDetails().getInteractions().size() > 50) {
			bean.getContactDetails().getInteractions().retainAll(bean.getContactDetails().getInteractions().subList(0, 50));
		}
		

		return new ServerSideActionResult<>(bean);
	}
}
