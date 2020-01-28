package modules.sales.ProcessLifeCycle;

import org.skyve.domain.types.DateOnly;
import org.skyve.metadata.model.document.Bizlet;

import modules.admin.ModulesUtil;
import modules.sales.domain.ProcessLifeCycle;

public class ProcessLifeCycleBizlet extends Bizlet<ProcessLifeCycleExtension> {

	private static final long serialVersionUID = 1590624689773537205L;
	
	@Override
	public ProcessLifeCycleExtension newInstance(ProcessLifeCycleExtension bean) throws Exception {
		// set a new order id on new instance
		bean.setProcessId(ModulesUtil.getNextDocumentNumber("PRO", ProcessLifeCycle.MODULE_NAME, ProcessLifeCycle.DOCUMENT_NAME, ProcessLifeCycle.processIdPropertyName, 8));
		// set the date of the new instance
		DateOnly date = new DateOnly();
		bean.setStartDate(date);
		return super.newInstance(bean);
	}
}
