package modules.customers.Interaction;

import org.skyve.util.Util;

import modules.customers.domain.Interaction;

public class InteractionExtension extends Interaction {

	private static final long serialVersionUID = 7036377442570071671L;

	@Override
	public String getInteractionTemplate() {
		String type = getType().toString();
		switch (type) {
		case "email":
			type = "fa fa-envelope";
			break;
		case "phone":
			type = "fa fa-phone";
			break;
		case "meeting":
			type = "fa fa-users";
			break;
		case "comment":
			type = "fa fa-comment";
			break;
		case "other":
			type = "fa fa-info-circle";
			break;
		default:
			break;
		}
		
		

		StringBuilder link = new StringBuilder();
		link.append(Util.getHomeUrl());
		link.append("?a=e&m=").append("customers").append("&d=").append("Interaction");
		link.append("&i=").append(getBizId());
		

		StringBuilder markup = new StringBuilder();
		markup.append("<div class='container' onclick=\"location.href='"+ link +"';\">"
				+ "<div class='iconTemp'><span class='"+ type +"'></span></div>");
		markup.append("<div class='infoTemp'><span class='titleTemp'> ").append(getTitle());
		markup.append("</span></br><p class='descriptionTemp'> ").append(getDescription());
		markup.append("</p></div><div class='dateAuth'><span class='dateTemp'>").append("12:00 15/01/20");
		markup.append("</span></br><span class='authorTemp'> ").append(getUser().getContact().getName());
		markup.append("</span></div></div>");
			
		
		return markup.toString();		
	}
}
