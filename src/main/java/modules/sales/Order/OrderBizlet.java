package modules.sales.Order;

import org.skyve.domain.Bean;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

import modules.admin.ModulesUtil;
import modules.sales.domain.Order;

public class OrderBizlet extends Bizlet<OrderExtension> {

	private static final long serialVersionUID = -1127372959265813196L;
	
	@Override
	public OrderExtension newInstance(OrderExtension bean) throws Exception {
		bean.setSelectedTab(0);
		// set a new order id on new instance
		bean.setOrderId(ModulesUtil.getNextDocumentNumber("ORD", Order.MODULE_NAME, Order.DOCUMENT_NAME, Order.orderIdPropertyName, 8));
		return super.newInstance(bean);
	}

	@Override
	public OrderExtension preExecute(ImplicitActionName actionName, OrderExtension bean, Bean parentBean,
			WebContext webContext) throws Exception {
		if (ImplicitActionName.Save.equals(actionName) || ImplicitActionName.OK.equals(actionName)) {
			if (bean.isChanged()) {
				bean.updateInteraction();
			}
			if (bean.isNotPersisted()) {
				bean.createInteraction();
			}
		}
		if (bean.isPersisted()) {
			bean.sortInteractions();
		}
		return super.preExecute(actionName, bean, parentBean, webContext);
	}
	
	@Override
	public void preDelete(OrderExtension bean) throws Exception {
		bean.deletedInteraction();
		super.preDelete(bean);
	}
	
	@Override
	public void preRerender(String source, OrderExtension bean, WebContext webContext) throws Exception {
		if (bean.getQuote() != null) {
			bean.setTotal(bean.getQuote().getTotal());
		}	
		super.preRerender(source, bean, webContext);
	}
}
