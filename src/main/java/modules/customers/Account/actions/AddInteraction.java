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
		
		bean.createInteraction(bean.getInteractionType(), bean.getInteractionDescription(), bean.getDocument());
				
		// clear the quick add form
		bean.setInteractionDescription(null);
		bean.setInteractionType(null);
		bean.setDocument(null);
				
		bean = CORE.getPersistence().save(bean);
		
		User user = CORE.getUser();
		Customer customer = user.getCustomer();
		Module module = customer.getModule(AccountExtension.MODULE_NAME);
		Document document = module.getDocument(customer, AccountExtension.DOCUMENT_NAME);
		String collectionBinding = AccountExtension.interactionsPropertyName;
		Binder.sortCollectionByMetaData(bean, customer, module, document, collectionBinding);
		
		if (bean.getInteractions().size() > 30) {
			bean.getInteractions().retainAll(bean.getInteractions().subList(0, 30));
		}
		
		return new ServerSideActionResult<>(bean);
	}
}
