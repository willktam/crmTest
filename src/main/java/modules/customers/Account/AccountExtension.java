package modules.customers.Account;

import org.locationtech.jts.geom.Point;
import org.skyve.CORE;
import org.skyve.bus.map.impl.PhotonGeocodeServiceImpl;
import org.skyve.metadata.customer.Customer;
import org.skyve.metadata.model.document.Document;
import org.skyve.metadata.module.Module;
import org.skyve.metadata.user.User;
import org.skyve.util.Binder;

import modules.admin.ModulesUtil;
import modules.customers.Interaction.InteractionExtension;
import modules.customers.domain.Account;
import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;

public class AccountExtension extends Account {
	
	private static final long serialVersionUID = 1571821523313584057L;

	public void updateInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Updated Account");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " updated the account details for " + getAccountName());
		getInteractions().add(interaction);
	}

	public void createInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Created New Account");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " created a new account.");
		getInteractions().add(interaction);
	}
	
	public void createInteraction(final Type type, final String description, final String document) {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle(String.format("New %s", type.toDescription()));
		interaction.setType(type);
		interaction.setDescription(description);
		if (document != null) {
			interaction.setDocument(document);
		}
		getInteractions().add(interaction);
	}
	
	public void sortInteractions() {
		User user = CORE.getUser();
		Customer customer = user.getCustomer();
		Module module = customer.getModule(AccountExtension.MODULE_NAME);
		Document document = module.getDocument(customer, AccountExtension.DOCUMENT_NAME);
		String collectionBinding = AccountExtension.interactionsPropertyName;
		Binder.sortCollectionByMetaData(this, customer, module, document, collectionBinding);
		
		if (getInteractions().size() > 50) {
			getInteractions().retainAll(getInteractions().subList(0, 50));
		}
	}

	public void geocode() throws Exception{
		PhotonGeocodeServiceImpl service = new PhotonGeocodeServiceImpl();
		// generate a string for the address
		String address = ModulesUtil.concatWithDelim(" ", getLine1(), getLine2(),
				getSuburb(), getState(), getPostCode(), "Australia");		
		
		// attempt to geocode the address
		Point point = service.geocode(address);		
		
		if(point != null) {
			setLocation(point);
		}
	}	
}
