package modules.sales.Opportunity;

import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;
import modules.sales.domain.Opportunity;

public class OpportunityExtension extends Opportunity {

	private static final long serialVersionUID = -4924207318968337320L;

	public void updateInteraction() {
		Interaction interaction = Interaction.newInstance();
		interaction.setTitle("Updated An Opportunity");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " updated the opportunity details for " + getTopic() + " with account: " + getAccount().getAccountName() + ".");
		getAccount().getInteractions().add(interaction);
	}

	public void deletedInteraction() {
		Interaction interaction = Interaction.newInstance();
		interaction.setBizLock(getAccount().getBizLock());
		interaction.setBizKey(getAccount().getBizKey());
		interaction.setTitle("Deleted An Opportunity");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " deleted the opportunity for " + getTopic() + ".");
		getAccount().getInteractions().add(interaction);
	}

}
