package modules.sales.Lead;

import modules.sales.domain.Lead;

public class LeadExtension extends Lead {

	private static final long serialVersionUID = -2679423500867553655L;

	@Override
	public String getProgress() {
		if (getContactDetails() != null && getContactDetails().getFirstName() != null) {
			String template = String.format("<span class='pill'>%s</span>", getContactDetails().getFirstName());
			return template;
		}
		return null;
	}

}
