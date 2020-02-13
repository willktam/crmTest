package modules.sales.Opportunity.actions;

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

import modules.customers.domain.Account;
import modules.sales.Opportunity.OpportunityExtension;

public class AddInteraction implements ServerSideAction<OpportunityExtension> {

	private static final long serialVersionUID = 2514028933547767942L;

	@Override
	public ServerSideActionResult<OpportunityExtension> execute(OpportunityExtension bean, WebContext webContext)
			throws Exception {
		// check for required fields
		if (bean.getAccount().getInteractionType() == null) {
			throw new ValidationException(new Message(Account.interactionTypePropertyName, "Type is required"));
		}
		if (bean.getAccount().getInteractionDescription() == null) {
			throw new ValidationException(new Message(Account.interactionDescriptionPropertyName, "Description is required"));
		}
				
		bean.createInteraction(bean.getAccount().getInteractionType(), bean.getAccount().getInteractionDescription());
					
		// clear the quick add form
		bean.getAccount().setInteractionDescription(null);
		bean.getAccount().setInteractionType(null);
		
		bean = CORE.getPersistence().save(bean);
		
		User user = CORE.getUser();
		Customer customer = user.getCustomer();
		Module module = customer.getModule(Account.MODULE_NAME);
		Document document = module.getDocument(customer, Account.DOCUMENT_NAME);
		String collectionBinding = Account.interactionsPropertyName;
		Binder.sortCollectionByMetaData(bean.getAccount(), customer, module, document, collectionBinding);
		
		if (bean.getAccount().getInteractions().size() > 30) {
			bean.getAccount().getInteractions().retainAll(bean.getAccount().getInteractions().subList(0, 30));
		}
		
		return new ServerSideActionResult<>(bean);
	}
}
