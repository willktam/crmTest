package modules.customers.ContactDetail;

import org.skyve.util.Util;

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
	
	@Override
	public String getFlowbar() {
		StringBuilder markup = new StringBuilder();
		markup.append("<div class=\"flowbar-wrapper\">");
		markup.append("<ul class=\"flowbar\">");
		markup.append("<li class=\"current\" onclick=\"location.href='"+ Util.getDocumentUrl(MODULE_NAME, DOCUMENT_NAME, getBizId()) + "';\"> Contact Details </li>");
		markup.append("<li> Lead </li><li> Account </li><li> Opportunity </li><li > Quote </li></ul></div>");
		
		return markup.toString();
	}
	
}
