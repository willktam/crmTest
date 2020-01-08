package modules.sales.Order;

import org.skyve.metadata.model.document.Bizlet;

import modules.admin.ModulesUtil;
import modules.sales.domain.Order;

public class OrderBizlet extends Bizlet<Order> {

	private static final long serialVersionUID = -1127372959265813196L;
	
	@Override
	public Order newInstance(Order bean) throws Exception {
		// 
		bean.setOrderId(ModulesUtil.getNextDocumentNumber("ORD", Order.MODULE_NAME, Order.DOCUMENT_NAME, Order.orderIdPropertyName, 8));
		return super.newInstance(bean);
	}

}
