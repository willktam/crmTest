package modules.admin.Communication.actions;

import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.web.WebContext;

import modules.admin.domain.Communication;

public class BatchSelected implements ServerSideAction<Communication> {

	private static final long serialVersionUID = 8136709192590507528L;

	@Override
	public ServerSideActionResult<Communication> execute(Communication bean, WebContext webContext) throws Exception {
		bean.setRefreshBatches(Boolean.FALSE);
		return new ServerSideActionResult<>(bean);
	}
}
