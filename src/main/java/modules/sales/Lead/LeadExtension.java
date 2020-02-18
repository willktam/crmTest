package modules.sales.Lead;

import org.locationtech.jts.geom.Point;
import org.skyve.CORE;
import org.skyve.bus.map.impl.PhotonGeocodeServiceImpl;
import org.skyve.metadata.customer.Customer;
import org.skyve.metadata.model.document.Document;
import org.skyve.metadata.module.Module;
import org.skyve.metadata.user.User;
import org.skyve.util.Binder;

import modules.admin.ModulesUtil;
import modules.customers.Interaction.InteractionExtension;
import modules.customers.domain.ContactDetail;
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
	public void createInteraction(final Type type, final String description) {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle(String.format("New %s", type.toDescription()));
		interaction.setType(type);
		interaction.setDescription(description);
		getContactDetails().getInteractions().add(interaction);
	}
	
	public void createInteraction(final Type type, final String description, final String document) {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle(String.format("New %s", type.toDescription()));
		interaction.setType(type);
		interaction.setDescription(description);
		if (document != null) {
			interaction.setDocument(document);
		}
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
	
	public void sortInteractions() {
		User user = CORE.getUser();
		Customer customer = user.getCustomer();
		Module module = customer.getModule(ContactDetail.MODULE_NAME);
		Document document = module.getDocument(customer, ContactDetail.DOCUMENT_NAME);
		String collectionBinding = ContactDetail.interactionsPropertyName;
		Binder.sortCollectionByMetaData(getContactDetails(), customer, module, document, collectionBinding);
			
		// keep only the newest interactions
		if (getContactDetails().getInteractions().size() > 50) {
			getContactDetails().getInteractions().retainAll(getContactDetails().getInteractions().subList(0, 50));
		}
	}
	
	public void geocode() throws Exception {
		PhotonGeocodeServiceImpl service = new PhotonGeocodeServiceImpl();
		// generate a string for the address
		String address = ModulesUtil.concatWithDelim(" ", getLine1(), getLine2(),
				getSuburb(), getState(), getPostCode(), "Australia");		
		
		// attempt to geocode the address
		Point point = service.geocode(address);		
		
		if(point != null) {
			setCompanyLocation(point);
		}
	}
}
