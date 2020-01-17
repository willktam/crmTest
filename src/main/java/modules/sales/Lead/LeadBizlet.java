package modules.sales.Lead;

import org.skyve.domain.Bean;
import org.skyve.domain.messages.ValidationException;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

public class LeadBizlet extends Bizlet<LeadExtension> {

	private static final long serialVersionUID = 3811137001565191612L;
	
	@Override
	public LeadExtension preExecute(ImplicitActionName actionName, LeadExtension bean, Bean parentBean,
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
	public void preDelete(LeadExtension bean) throws Exception {
		bean.deletedInteraction();
		super.preDelete(bean);
	}
	
//	@Override
//	public void validate(LeadExtension bean, ValidationException e) throws Exception {
//		// TODO Auto-generated method stub
//		super.validate(bean, e);
//	}
	
}
