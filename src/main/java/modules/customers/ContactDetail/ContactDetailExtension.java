package modules.customers.ContactDetail;

import org.skyve.CORE;
import org.skyve.metadata.customer.Customer;
import org.skyve.metadata.model.document.Document;
import org.skyve.metadata.module.Module;
import org.skyve.metadata.user.User;
import org.skyve.util.Binder;

import modules.customers.Interaction.InteractionExtension;
import modules.customers.domain.ContactDetail;
import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;

public class ContactDetailExtension extends ContactDetail {
	
	private static final long serialVersionUID = -1879786610392669581L;
	
	public void updateInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Updated Contact");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " updated the contact details for " + getFirstName() + " " + getLastName());
		getInteractions().add(interaction);
	}

	public void createInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Created New Contact");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " created a new contact.");
		getInteractions().add(interaction);
	}
	
	public void createInteraction(final Type type, final String description) {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle(String.format("New %s", type.toDescription()));
		interaction.setType(type);
		interaction.setDescription(description);
		getInteractions().add(interaction);
	}

	public void getNewInteractions() {
		User user = CORE.getUser();
		Customer customer = user.getCustomer();
		Module module = customer.getModule(ContactDetail.MODULE_NAME);
		Document document = module.getDocument(customer, ContactDetail.DOCUMENT_NAME);
		String collectionBinding = ContactDetail.interactionsPropertyName;
		Binder.sortCollectionByMetaData(this, customer, module, document, collectionBinding);
		
		if (getInteractions().size() > 30) {
			getInteractions().retainAll(getInteractions().subList(0, 30));
		}		
	}
	
}
