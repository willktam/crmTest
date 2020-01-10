package modules.customers.ContactDetail;

import modules.customers.domain.ContactDetail;
import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;

public class ContactDetailExtension extends ContactDetail {
	
	private static final long serialVersionUID = -1879786610392669581L;
	
	
	public void createInteraction() {
		//
		Interaction interaction = Interaction.newInstance();
		interaction.setTitle("Updated A Contact");
		interaction.setType(Type.other);
		getInteractions().add(interaction);
	}
	

}
