package modules.sales.Opportunity;

import org.skyve.domain.Bean;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

public class OpportunityBizlet extends Bizlet<OpportunityExtension> {

	private static final long serialVersionUID = -1901236116078837869L;

	@Override
	public OpportunityExtension preExecute(ImplicitActionName actionName, OpportunityExtension bean, Bean parentBean,
			WebContext webContext) throws Exception {
		if (ImplicitActionName.Save.equals(actionName) || ImplicitActionName.OK.equals(actionName)) {
			// make an update interaction if the record is changed
			if (bean.isChanged()) {
				bean.updateInteraction();
			}	
			// make a created interaction if the record has not been saved
			if (bean.isNotPersisted()) {
				bean.createInteraction();
			}	
		}
		return super.preExecute(actionName, bean, parentBean, webContext);
	}
	
	@Override
	public void preDelete(OpportunityExtension bean) throws Exception {
		bean.deletedInteraction();
		super.preDelete(bean);
	}
}
