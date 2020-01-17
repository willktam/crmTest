package modules.sales.Lead;

import modules.customers.Interaction.InteractionExtension;
import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;
import modules.sales.domain.Lead;

public class LeadExtension extends Lead {

	private static final long serialVersionUID = -2679423500867553655L;

	public void createInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Created New Lead");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " created a new lead for contact: " + getContactDetails().getFirstName() + " " + getContactDetails().getLastName());
		getContactDetails().getInteractions().add(interaction);
	}
	
	public void updateInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Updated Lead");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " updated the lead details for " + getName() + " with contact: " + getContactDetails().getFirstName() + " " + getContactDetails().getLastName());
		getContactDetails().getInteractions().add(interaction);
	}
	
	public void deletedInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setBizLock(getContactDetails().getBizLock());
		interaction.setBizKey(getContactDetails().getBizKey());
		interaction.setTitle("Deleted Lead");
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
