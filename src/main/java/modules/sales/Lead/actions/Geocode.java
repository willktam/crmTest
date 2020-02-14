package modules.sales.Lead.actions;

import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.web.WebContext;

import modules.sales.Lead.LeadExtension;

public class Geocode implements ServerSideAction<LeadExtension> {
	
	private static final long serialVersionUID = -2211685225705386038L;

	@Override
	public ServerSideActionResult<LeadExtension> execute(LeadExtension bean, WebContext webContext) throws Exception {
		bean.geocode();
		return new ServerSideActionResult<>(bean);
	}

}
