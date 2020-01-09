package modules.customers.Interaction;

import org.skyve.domain.types.DateTime;
import org.skyve.metadata.model.document.Bizlet;

import modules.admin.ModulesUtil;
import modules.customers.domain.Interaction;

public class InteractionBizlet extends Bizlet<Interaction> {

	private static final long serialVersionUID = -201294803305106431L;

	@Override
	public Interaction newInstance(Interaction bean) throws Exception {
		// set the current user
		bean.setUser(ModulesUtil.currentAdminUser());
		
		// set the current time of the new instance
		DateTime date = new DateTime();
		bean.setInteractionTime(date);
		
		return super.newInstance(bean);
	}
	
}
