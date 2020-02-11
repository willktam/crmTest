package modules.customers.Account;

import org.locationtech.jts.geom.Point;
import org.skyve.bus.map.impl.PhotonGeocodeServiceImpl;

import modules.admin.ModulesUtil;
import modules.customers.Interaction.InteractionExtension;
import modules.customers.domain.Account;
import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;

public class AccountExtension extends Account {
	
	private static final long serialVersionUID = 1571821523313584057L;

	public void updateInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Updated Account");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " updated the account details for " + getAccountName());
		getInteractions().add(interaction);
	}

	public void createInteraction() {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle("Created New Account");
		interaction.setType(Type.other);
		interaction.setDescription(interaction.getUser().getContact().getName() + " created a new account.");
		getInteractions().add(interaction);
	}
	
	public void createInteraction(final Type type, final String description) {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle(String.format("New %s", type.toDescription()));
		interaction.setType(type);
		interaction.setDescription(description);
		getInteractions().add(interaction);
	}

	

	public void geocode() throws Exception{
		PhotonGeocodeServiceImpl service = new PhotonGeocodeServiceImpl();
		// generate a string for the address
		String address = ModulesUtil.concatWithDelim(" ", getLine1(), getLine2(),
				getSuburb(), getState(), getPostCode(), "Australia");		
		
		// attempt to geocode the address
		Point point = service.geocode(address);		
		
		if(point != null) {
			setLocation(point);
		}
		
	}
	
	
}
