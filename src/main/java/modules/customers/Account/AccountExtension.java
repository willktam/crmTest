package modules.customers.Account;

import modules.customers.Interaction.InteractionExtension;
import modules.customers.domain.Account;
import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;

public class AccountExtension extends Account {
	
	private static final long serialVersionUID = 1571821523313584057L;

	public void updateInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Updated An Account");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " updated the account details for " + getAccountName());
		getInteractions().add(interaction);
	}

	public void createInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Created A New Account");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " created a new account.");
		getInteractions().add(interaction);
	}
}
