package modules.customers.Account;

import org.skyve.domain.Bean;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

import modules.admin.ModulesUtil;

public class AccountBizlet extends Bizlet<AccountExtension> {
	
	private static final long serialVersionUID = 3587510445958351755L;

	@Override
	public AccountExtension preExecute(ImplicitActionName actionName, AccountExtension bean, Bean parentBean,
			WebContext webContext) throws Exception {
		if (ImplicitActionName.Save.equals(actionName) || ImplicitActionName.OK.equals(actionName)) {
			if (bean.isChanged()) {
				bean.updateInteraction();
			}	
		}
		bean.sortInteractions();
		return super.preExecute(actionName, bean, parentBean, webContext);
	}
	
	@Override
	public AccountExtension newInstance(AccountExtension bean) throws Exception {
		if (bean.isNotPersisted()) {
			bean.setSelectedTab(0);
		}
		bean.createInteraction();
		bean.setCurrentUser(ModulesUtil.currentAdminUser());
		bean.setAccountManager(bean.getCurrentUser().getContact().getName());
		return super.newInstance(bean);
	}
}
