package modules.sales.Order.actions;

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
import modules.sales.Order.OrderExtension;

public class AddInteraction implements ServerSideAction<OrderExtension> {
	
	private static final long serialVersionUID = 2507199927157448886L;

	@Override
	public ServerSideActionResult<OrderExtension> execute(OrderExtension bean, WebContext webContext) throws Exception {
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
		Module module = customer.getModule(OrderExtension.MODULE_NAME);
		Document document = module.getDocument(customer, OrderExtension.DOCUMENT_NAME);
		String collectionBinding = OrderExtension.accountPropertyName + "." +  Account.interactionDescriptionPropertyName;
				
		int size = bean.getAccount().getInteractions().size();
		if (size < 50) {
			Binder.sortCollectionByMetaData(bean, customer, module, document, collectionBinding);
		}
		else {
			for (int i = 0; i < size-50; i++) {
				bean.getAccount().getInteractions().remove(i);
			}
			Binder.sortCollectionByMetaData(bean, customer, module, document, collectionBinding);
		}
		return new ServerSideActionResult<>(bean);
	}
}