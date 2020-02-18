package modules.sales.Order;

import org.skyve.CORE;
import org.skyve.metadata.customer.Customer;
import org.skyve.metadata.model.document.Document;
import org.skyve.metadata.module.Module;
import org.skyve.metadata.user.User;
import org.skyve.util.Binder;

import modules.customers.Interaction.InteractionExtension;
import modules.customers.domain.Account;
import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;
import modules.sales.domain.Order;

public class OrderExtension extends Order {

	private static final long serialVersionUID = 7354159931630623922L;

	public void createInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Created New Order");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " created a new order for account: " + 
				getAccount().getPrimaryContact().getFirstName()
				+ " " + getAccount().getPrimaryContact().getLastName());
		getAccount().getInteractions().add(interaction);
	}
	public void createInteraction(final Type type, final String description, final String document) {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle(String.format("New %s", type.toDescription()));
		interaction.setType(type);
		interaction.setDescription(description);
		if (document != null) {
			interaction.setDocument(document);
		}
		getAccount().getInteractions().add(interaction);
	}
	
	public void updateInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Updated Order");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " updated the order details for " + getOrderId() + " with account: " + getAccount().getAccountName() + ".");
		getAccount().getInteractions().add(interaction);
	}

	public void deletedInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setBizLock(getAccount().getBizLock());
		interaction.setBizKey(getAccount().getBizKey());
		interaction.setTitle("Deleted Order");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " deleted the order " + getOrderId() + ".");
		getAccount().getInteractions().add(interaction);
	}
	
	public void sortInteractions() {
		User user = CORE.getUser();
		Customer customer = user.getCustomer();
		Module module = customer.getModule(Account.MODULE_NAME);
		Document document = module.getDocument(customer, Account.DOCUMENT_NAME);
		String collectionBinding = Account.interactionsPropertyName;
		Binder.sortCollectionByMetaData(getAccount(), customer, module, document, collectionBinding);
		
		if (getAccount().getInteractions().size() > 50) {
			getAccount().getInteractions().retainAll(getAccount().getInteractions().subList(0, 50));
		}
	}
}