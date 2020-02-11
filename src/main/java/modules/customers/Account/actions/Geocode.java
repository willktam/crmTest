package modules.customers.Account.actions;

import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.web.WebContext;

import modules.customers.Account.AccountExtension;

public class Geocode implements ServerSideAction<AccountExtension> {

	private static final long serialVersionUID = -2061407327071747815L;

	@Override
	public ServerSideActionResult<AccountExtension> execute(AccountExtension bean, WebContext webContext)
			throws Exception {
		// attempt to geocode the address
		bean.geocode();
		return new ServerSideActionResult<>(bean);
	}

}
