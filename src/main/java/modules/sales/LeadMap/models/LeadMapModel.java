package modules.sales.LeadMap.models;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Geometry;
import org.skyve.CORE;
import org.skyve.metadata.view.model.map.MapFeature;
import org.skyve.metadata.view.model.map.MapItem;
import org.skyve.metadata.view.model.map.MapModel;
import org.skyve.metadata.view.model.map.MapResult;
import org.skyve.persistence.DocumentQuery;
import org.skyve.persistence.Persistence;

import modules.customers.domain.ContactDetail;
import modules.sales.domain.Lead;
import modules.sales.domain.LeadMap;

public class LeadMapModel extends MapModel<LeadMap> {

	private static final long serialVersionUID = -6620665458880660348L;

	@Override
	public MapResult getResult(Geometry mapBounds) throws Exception {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery qleads = persistence.newDocumentQuery(Lead.MODULE_NAME, Lead.DOCUMENT_NAME);
		List<Lead> leads = qleads.beanResults();
		List<MapItem> items = new ArrayList<>();		

		for (Lead lead : leads) {

			ContactDetail contact = lead.getContactDetails();
			
			MapItem item = new MapItem();
			item.setBizId(lead.getBizId());
			item.setModuleName(lead.getBizModule());
			item.setDocumentName(lead.getBizDocument());

			MapFeature myLocation = new MapFeature();
			myLocation.setGeometry(lead.getCompanyLocation());

			StringBuilder markup = new StringBuilder();
			markup.append("<div><h2>").append(lead.getName()).append("</h2>");
			markup.append("<span>Website: <i><a href=//").append(lead.getWebsite()).append(">").append(lead.getWebsite()).append("</a></i>");
			markup.append("</br></span><span>Contact: ").append(contact.getFirstName()).append(" ")
					.append(contact.getLastName());
			markup.append("</br></span><span>Email: ").append(contact.getEmail());
			markup.append("</br></span><span>Mobile: ").append(contact.getMobileNumber());
			markup.append("</br></span><span>Business: ").append(contact.getBusinessNumber());
			markup.append("</br></span><span>Preferred Method of Contact: ").append(contact.getMethod());
			markup.append("</span></div>");
			item.setInfoMarkup(markup.toString());
			item.getFeatures().add(myLocation);
			items.add(item);

		}
		
		return new MapResult(items, null);
	}
}