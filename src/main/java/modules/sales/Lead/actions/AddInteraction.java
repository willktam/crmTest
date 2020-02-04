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
		
		bean.createInteraction(bean.getContactDetails().getInteractionType(), bean.getContactDetails().getInteractionDescription());
			
		// clear the quick add form
		bean.getContactDetails().setInteractionDescription(null);
		bean.getContactDetails().setInteractionType(null);
		
		bean = CORE.getPersistence().save(bean);
				
		User user = CORE.getUser();
		Customer customer = user.getCustomer();
		Module module = customer.getModule(LeadExtension.MODULE_NAME);
		Document document = module.getDocument(customer, LeadExtension.DOCUMENT_NAME);
		String collectionBinding = ContactDetail.interactionsPropertyName;
		
		int size = bean.getContactDetails().getInteractions().size();
		if (size < 50) {
			Binder.sortCollectionByMetaData(bean.getContactDetails(), customer, module, document,collectionBinding);
		}
		else {
			for (int i = 0; i < size-50; i++) {
				bean.getContactDetails().getInteractions().remove(i);
			}
			Binder.sortCollectionByMetaData(bean.getContactDetails(), customer, module, document, collectionBinding);
		}
		

		return new ServerSideActionResult<>(bean);
	}
}
