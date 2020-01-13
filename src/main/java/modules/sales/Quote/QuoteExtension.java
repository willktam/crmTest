package modules.sales.Quote;

import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;
import modules.sales.domain.Quote;

public class QuoteExtension extends Quote {

	private static final long serialVersionUID = -6750421527636249717L;

	public void updateInteraction() {
		Interaction interaction = Interaction.newInstance();
		interaction.setTitle("Updated An Opportunity");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " updated the quote details for " + getQuoteId() + " with account: " + getOpportunity().getAccount().getAccountName() + ".");
		getOpportunity().getAccount().getInteractions().add(interaction);
	}

	public void deletedInteraction() {
		Interaction interaction = Interaction.newInstance();
		interaction.setBizLock(getOpportunity().getAccount().getBizLock());
		interaction.setBizKey(getOpportunity().getAccount().getBizKey());
		interaction.setTitle("Deleted A Quote");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " deleted the quote " + getQuoteId() + ".");
		getOpportunity().getAccount().getInteractions().add(interaction);
	}

}
