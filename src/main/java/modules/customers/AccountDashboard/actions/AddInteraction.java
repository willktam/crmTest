package modules.customers.AccountDashboard.actions;

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
import modules.customers.AccountDashboard.AccountDashboardExtension;
import modules.customers.domain.Account;

public class AddInteraction implements ServerSideAction<AccountDashboardExtension> {

	private static final long serialVersionUID = 7791001026504747096L;

	@Override
	public ServerSideActionResult<AccountDashboardExtension> execute(AccountDashboardExtension bean,
			WebContext webContext) throws Exception {
		// check for required fields
		if (bean.getAccount().getInteractionType() == null) {
			throw new ValidationException(new Message(Account.interactionTypePropertyName, "Type is required"));
		}
		if (bean.getAccount().getInteractionDescription() == null) {
			throw new ValidationException(new Message(Account.interactionDescriptionPropertyName, "Description is required"));
		}
						
		bean.createInteraction(bean.getAccount().getInteractionType(), bean.getAccount().getInteractionDescription(), bean.getAccount().getDocument());
							
		// clear the quick add form
		bean.getAccount().setInteractionDescription(null);
		bean.getAccount().setInteractionType(null);
				
		
		AccountExtension account = bean.getAccount();
		account = CORE.getPersistence().save(account);
		bean.setAccount(account);		
		
		// resort account interactions after adding
		User user = CORE.getUser();
		Customer customer = user.getCustomer();
		Module module = customer.getModule(Account.MODULE_NAME);
		Document document = module.getDocument(customer, Account.DOCUMENT_NAME);
		String collectionBinding = Account.interactionsPropertyName;
		Binder.sortCollectionByMetaData(bean.getAccount(), customer, module, document, collectionBinding);
		
		// clear current shown interactions then add resorted list
		bean.getInteractions().clear();
		
		if (bean.getAccount().getInteractions().size() < 4) {
			bean.getInteractions().addAll(bean.getAccount().getInteractions());
		}
		else {
			bean.getInteractions().addAll(bean.getAccount().getInteractions().subList(0, 4)); 
		}
		
		

		return new ServerSideActionResult<>(bean);
	}	

}
