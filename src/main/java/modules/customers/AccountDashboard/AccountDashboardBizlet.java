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
			bean.setNumbers();
			bean.setDates();
			bean.setAccountLocation(bean.getAccount().getLocation());
		}
		return super.preExecute(actionName, bean, parentBean, webContext);
	}
	
	@Override
	public void preRerender(String source, AccountDashboardExtension bean, WebContext webContext) throws Exception {
		bean.setNumbers();
		bean.setDates();
		bean.setAccountLocation(bean.getAccount().getLocation());
	
		super.preRerender(source, bean, webContext);
	}
	
	 @Override
	public AccountDashboardExtension newInstance(AccountDashboardExtension bean) throws Exception {
		// set the account to the most recently edited
		bean.setAccount(bean.getRecentAccount());

		// add up to 4 interactions to the view
		if (bean.getAccount().getInteractions().size() < 4) {
			bean.getInteractions().addAll(bean.getAccount().getInteractions());
		}
		else {
			bean.getInteractions().addAll(bean.getAccount().getInteractions().subList(0, 4)); 
		}
		
		return super.newInstance(bean);
	}
}
