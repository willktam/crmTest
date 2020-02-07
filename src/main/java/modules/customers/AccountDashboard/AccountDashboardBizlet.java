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
		if ((ImplicitActionName.Edit.equals(actionName)) || (ImplicitActionName.New.equals(actionName)) ) {
			
		}
		return super.preExecute(actionName, bean, parentBean, webContext);
	}
	
	 @Override
	public AccountDashboardExtension newInstance(AccountDashboardExtension bean) throws Exception {
		 // set the account to the most recently edited
		 bean.setAccount(bean.getRecentAccount());

		 // add 5 interactions to the view
		 bean.getInteractions().addAll(bean.getAccount().getInteractions().subList(0, 5));
		
		 return super.newInstance(bean);
	}
	
}
