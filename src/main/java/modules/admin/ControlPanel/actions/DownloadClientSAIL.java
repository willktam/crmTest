package modules.admin.ControlPanel.actions;

import org.skyve.metadata.controller.DownloadAction;
import org.skyve.web.WebContext;

import modules.admin.ControlPanel.ControlPanelExtension;
import modules.admin.domain.ControlPanel.SailExecutor;
import modules.admin.domain.ControlPanel.SailUserAgentType;
import router.DefaultUxUiSelector;

public class DownloadClientSAIL extends DownloadAction<ControlPanelExtension> {
	private static final long serialVersionUID = 6509370665603777126L;

	@Override
	public void prepare(ControlPanelExtension bean, WebContext webContext)
	throws Exception {
		bean.setSailExecutor(SailExecutor.primeFacesInlineWebDriver);
		bean.setSailUxUi(DefaultUxUiSelector.EXTERNAL.getName());
		bean.setSailUserAgentType(SailUserAgentType.desktop);
		
		new GenerateMenuSAIL().execute(bean, webContext);
		bean.setSail(bean.getUnescapedResults());
		
		new DownloadSAIL().prepare(bean, webContext);
	}
	
	@Override
	public Download download(ControlPanelExtension bean, WebContext webContext)
	throws Exception {
		return new DownloadSAIL().download(bean, webContext);
	}
}
