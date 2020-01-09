package modules.customers.ContactDetail;

import org.skyve.domain.Bean;
import org.skyve.domain.types.DateTime;

import modules.admin.ModulesUtil;
import modules.customers.domain.ContactDetail;
import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;

public class ContactDetailExtension extends ContactDetail {
	
	private static final long serialVersionUID = -1879786610392669581L;
	
	
	public Interaction createInteraction() {
		// 
		Interaction interaction = new Interaction();
		interaction.setUser(ModulesUtil.currentAdminUser());
		
		interaction.setTitle("Updated A Contact");
		interaction.setType(Type.other);
		DateTime date = new DateTime();
		interaction.setInteractionTime(date);
		
		return interaction;
	}
	

}
