package modules.customers.ContactDetail;

import modules.admin.ModulesUtil;
import modules.customers.domain.ContactDetail;
import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;

public class ContactDetailExtension extends ContactDetail {
	
	private static final long serialVersionUID = -1879786610392669581L;
	
	
	public void updateInteraction() {
		//
		Interaction interaction = Interaction.newInstance();
		interaction.setTitle("Updated A Contact");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " updated the contact details for " + getFirstName() + " " + getLastName());
		getInteractions().add(interaction);
	}


	public void createInteraction() {
		//
		Interaction interaction = Interaction.newInstance();
		interaction.setTitle("Created A New Contact");
		interaction.setType(Type.other);
		getInteractions().add(interaction);
	}

}
