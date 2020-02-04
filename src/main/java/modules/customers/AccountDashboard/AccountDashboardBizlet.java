package modules.customers.AccountDashboard;

import org.skyve.domain.Bean;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

public class AccountDashboardBizlet extends Bizlet<AccountDashboardExtension> {

	private static final long serialVersionUID = 4393377812970761283L;

	@Override
	public AccountDashboardExtension preExecute(ImplicitActionName actionName, AccountDashboardExtension bean,
			Bean parentBean, WebContext webContext) throws Exception {
		
		return super.preExecute(actionName, bean, parentBean, webContext);
	}
	
	 @Override
	public AccountDashboardExtension newInstance(AccountDashboardExtension bean) throws Exception {
		 // set the account to the most recently edited
		 bean.setAccount(bean.getRecentAccount());
		 return super.newInstance(bean);
	}
	
}
