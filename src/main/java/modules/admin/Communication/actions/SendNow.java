package modules.admin.Communication.actions;

import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.web.WebContext;

import modules.admin.Communication.CommunicationUtil;
import modules.admin.domain.Communication;
import modules.admin.domain.Communication.ActionType;

public class SendNow implements ServerSideAction<Communication> {

	private static final long serialVersionUID = 3665299003466155770L;

	@Override
	public ServerSideActionResult<Communication> execute(Communication communication, WebContext webContext) throws Exception {

		communication.setActionType(ActionType.sendImmediately);
		
		Communication result = CommunicationUtil.kickOffJob(communication);
		
		return new ServerSideActionResult<>(result);
	}
}
