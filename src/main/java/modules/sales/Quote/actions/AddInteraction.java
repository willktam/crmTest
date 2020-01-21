package modules.sales.Quote.actions;

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
import modules.sales.Quote.QuoteExtension;

public class AddInteraction implements ServerSideAction<QuoteExtension> {
	
	private static final long serialVersionUID = 6312613763531803627L;

	@Override
	public ServerSideActionResult<QuoteExtension> execute(QuoteExtension bean, WebContext webContext) 
			throws Exception {
		// check for required fields
		if (bean.getOpportunity().getAccount().getInteractionType() == null) {
			throw new ValidationException(new Message(Account.interactionTypePropertyName, "Type is required"));
		}
		if (bean.getOpportunity().getAccount().getInteractionDescription() == null) {
			throw new ValidationException(new Message(Account.interactionDescriptionPropertyName, "Description is required"));
		}
						
		bean.createInteraction(bean.getOpportunity().getAccount().getInteractionType(), bean.getOpportunity().getAccount().getInteractionDescription());
						
		// clear the quick add form
		bean.getOpportunity().getAccount().setInteractionDescription(null);
		bean.getOpportunity().getAccount().setInteractionType(null);
				
		bean = CORE.getPersistence().save(bean);
			
		User user = CORE.getUser();
		Customer customer = user.getCustomer();
		Module module = customer.getModule(QuoteExtension.MODULE_NAME);
		Document document = module.getDocument(customer, QuoteExtension.DOCUMENT_NAME);
		String collectionBinding = QuoteExtension.opportunityPropertyName + "." + OpportunityExtension.accountPropertyName + "." +  Account.interactionDescriptionPropertyName;
						
		int size = bean.getOpportunity().getAccount().getInteractions().size();
		if (size < 50) {
			Binder.sortCollectionByMetaData(bean, customer, module, document, collectionBinding);
		}
		else {
			for (int i = 0; i < size-50; i++) {
				bean.getOpportunity().getAccount().getInteractions().remove(i);
			}
			Binder.sortCollectionByMetaData(bean, customer, module, document, collectionBinding);
		}
		return new ServerSideActionResult<>(bean);
	}
}
