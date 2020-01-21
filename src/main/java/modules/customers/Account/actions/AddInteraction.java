package modules.customers.Account.actions;

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

import modules.customers.Account.AccountExtension;

public class AddInteraction implements ServerSideAction<AccountExtension> {

	private static final long serialVersionUID = 6648526293393253855L;

	@Override
	public ServerSideActionResult<AccountExtension> execute(AccountExtension bean, WebContext webContext)
			throws Exception {
		// check for required fields
		
		if(bean.getInteractionType() == null) {
			throw new ValidationException(new Message(AccountExtension.interactionTypePropertyName, "Type is required"));
		}
		if(bean.getInteractionDescription() == null) {
			throw new ValidationException(new Message(AccountExtension.interactionDescriptionPropertyName, "Description is required"));
		}
		
		bean.createInteraction(bean.getInteractionType(), bean.getInteractionDescription());
				
		// clear the quick add form
		bean.setInteractionDescription(null);
		bean.setInteractionType(null);
				
		bean = CORE.getPersistence().save(bean);
		
		User user = CORE.getUser();
		Customer customer = user.getCustomer();
		Module module = customer.getModule(AccountExtension.MODULE_NAME);
		Document document = module.getDocument(customer, AccountExtension.DOCUMENT_NAME);
		String collectionBinding = AccountExtension.interactionsPropertyName;
		
		int size = bean.getInteractions().size();
		if (size < 50) {
			Binder.sortCollectionByMetaData(bean, customer, module, document, collectionBinding);
		}
		else {
			for (int i = 0; i < size-50; i++) {
				bean.getInteractions().remove(i);
			}
			Binder.sortCollectionByMetaData(bean, customer, module, document, collectionBinding);
		}
		return new ServerSideActionResult<>(bean);
	}
}
