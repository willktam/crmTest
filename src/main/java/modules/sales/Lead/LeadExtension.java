package modules.sales.Lead;

import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;
import modules.sales.domain.Lead;

public class LeadExtension extends Lead {

	private static final long serialVersionUID = -2679423500867553655L;

	public Interaction createInteraction() {
		Interaction interaction = Interaction.newInstance();
		interaction.setTitle("Created A New Lead");
		interaction.setType(Type.other);
		//getContactDetails().getInteractions().add(interaction);
		return interaction;
	}
	
	public void updateInteraction() {
		Interaction interaction = Interaction.newInstance();
		interaction.setTitle("Updated A Lead");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " updated the lead details for " + getName() + " with contact: " + getContactDetails().getFirstName() + " " + getContactDetails().getLastName());
		getContactDetails().getInteractions().add(interaction);
	}
	
	public void deletedInteraction() {
		Interaction interaction = Interaction.newInstance();
		interaction.setBizLock(getContactDetails().getBizLock());
		interaction.setBizKey(getContactDetails().getBizKey());
		interaction.setTitle("Deleted A Lead");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " deleted the lead for " + getName());
		getContactDetails().getInteractions().add(interaction);
	}
	
	@Override
	public String getProgress() {
		if (getContactDetails() != null && getContactDetails().getFirstName() != null) {
			String template = String.format("<span class='pill'>%s</span>", getContactDetails().getFirstName());
			return template;
		}
		return null;
	}
}
