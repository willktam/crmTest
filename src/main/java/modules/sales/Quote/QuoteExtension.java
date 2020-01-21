package modules.sales.Quote;

import modules.customers.Interaction.InteractionExtension;
import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;
import modules.sales.domain.Quote;

public class QuoteExtension extends Quote {

	private static final long serialVersionUID = -6750421527636249717L;

	public void createInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Created New Quote");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " created a new quote for account: " + 
				getOpportunity().getAccount().getPrimaryContact().getFirstName()
				+ " " + getOpportunity().getAccount().getPrimaryContact().getLastName());
		getOpportunity().getAccount().getInteractions().add(interaction);
	}
	public void createInteraction(final Type type, final String description) {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle(String.format("New %s", type.toDescription()));
		interaction.setType(type);
		interaction.setDescription(description);
		getOpportunity().getAccount().getInteractions().add(interaction);
	}
	
	public void updateInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Updated Quote");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " updated the quote details for " + getQuoteId() + " with account: " + getOpportunity().getAccount().getAccountName() + ".");
		getOpportunity().getAccount().getInteractions().add(interaction);
	}

	public void deletedInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setBizLock(getOpportunity().getAccount().getBizLock());
		interaction.setBizKey(getOpportunity().getAccount().getBizKey());
		interaction.setTitle("Deleted Quote");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " deleted the quote " + getQuoteId() + ".");
		getOpportunity().getAccount().getInteractions().add(interaction);
	}
}