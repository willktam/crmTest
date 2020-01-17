package modules.customers.Interaction;

import org.skyve.util.Util;

import com.ibm.icu.text.SimpleDateFormat;

import modules.customers.domain.Interaction;

public class InteractionExtension extends Interaction {

	private static final long serialVersionUID = 7036377442570071671L;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a, dd/MM/yy");

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

		StringBuilder markup = new StringBuilder();
		markup.append("<div class='container' onclick=\"location.href='"+ Util.getDocumentUrl(this) +"';\">"
				+ "<div class='iconTemp'><span class='"+ type +"'></span></div>");
		markup.append("<div class='infoTemp'><span class='titleTemp'> ").append(getTitle());
		markup.append("</span></br><p class='descriptionTemp'> ").append(getDescription());
		markup.append("</p></div><div class='dateAuth'><span class='dateTemp'>").append(sdf.format(getInteractionTime()));
		markup.append("</span></br><span class='authorTemp'> ").append(getUser().getContact().getName());
		markup.append("</span></div></div>");
			
		
		return markup.toString();		
	}
}
