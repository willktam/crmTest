package modules.customers.Interaction;

import modules.customers.domain.Interaction;

public class InteractionExtension extends Interaction {

	private static final long serialVersionUID = 7036377442570071671L;

	@Override
	public String getInteractionTemplate() {
		System.out.println(getInteractionTime().toString());
		
		
		System.out.println(getType());
		StringBuilder markup = new StringBuilder();
//		markup.append("<div class='container'><div class='infoTemp'><h2 class='titleTemp'>").append(getTitle());
//		markup.append("</h2><p class='descriptionTemp'>").append(getDescription());
//		markup.append("</p></div><div class='dateAuth'><p class='dateTemp'> 4:00PM 15/01/20");
//		markup.append("</p><p class='authorTemp'>").append(getUser().getContact().getName());
//		markup.append("</p></div><div class='iconTemp'><span class='fa fa-users'></span></div></div>");
		
		
		markup.append("<div class='container'><div class='infoTemp'><h2 class='titleTemp'>").append(getTitle());
		markup.append("</h2><p class='descriptionTemp'>").append(getDescription());
		markup.append("</p></div><div class='dateAuth'><p class='dateTemp'> 4:00PM 15/01/20");
		markup.append("</p><p class='authorTemp'>").append(getUser().getContact().getName());
		markup.append("</p></div><div class='iconTemp'><span class='fa fa-users'></span></div></div>");
		
		
		
		
		
		return markup.toString();		
	}
}
