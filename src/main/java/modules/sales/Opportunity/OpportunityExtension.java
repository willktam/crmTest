package modules.sales.Opportunity;

import modules.customers.Interaction.InteractionExtension;
import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;
import modules.sales.domain.Opportunity;

public class OpportunityExtension extends Opportunity {

	private static final long serialVersionUID = -4924207318968337320L;

	public void createInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Created New Opportunity");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " created a new opportunity for account: " + getAccount().getPrimaryContact().getFirstName() + " " + getAccount().getPrimaryContact().getLastName());
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
		interaction.setTitle("Updated Opportunity");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " updated the opportunity details for " + getTopic() + " with account: " + getAccount().getAccountName() + ".");
		getAccount().getInteractions().add(interaction);
	}

	public void deletedInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setBizLock(getAccount().getBizLock());
		interaction.setBizKey(getAccount().getBizKey());
		interaction.setTitle("Deleted Opportunity");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " deleted the opportunity for " + getTopic() + ".");
		getAccount().getInteractions().add(interaction);
	}

}
