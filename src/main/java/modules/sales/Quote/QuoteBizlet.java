package modules.sales.Quote;

import org.skyve.domain.Bean;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

import modules.admin.ModulesUtil;
import modules.customers.domain.Account;
import modules.sales.domain.Quote;

public class QuoteBizlet extends Bizlet<QuoteExtension> {

	private static final long serialVersionUID = 6426735460765073764L;
	
//	@Override
//	public Quote newInstance(Quote bean) throws Exception {
//		
//		// set a new quote id when a new one is created
//		bean.setQuoteId(ModulesUtil.getNextDocumentNumber("QUO", Quote.MODULE_NAME, Quote.DOCUMENT_NAME, Quote.quoteIdPropertyName, 8));
//		return super.newInstance(bean);
//	}
//	
//	@Override
//	public Quote preExecute(ImplicitActionName actionName, Quote bean, Bean parentBean, WebContext webContext)
//			throws Exception {
//		
//		if(ImplicitActionName.OK.equals(actionName) || ImplicitActionName.Save.equals(actionName)) {
//			if(bean.isNotPersisted()) {
//				// create an account history
//				//Account account = Account.newInstance();
//			}
//		}
//		
//		return super.preExecute(actionName, bean, parentBean, webContext);
//	}
	@Override
	public QuoteExtension newInstance(QuoteExtension bean) throws Exception {
		// set a new quote id when a new one is created
		bean.setQuoteId(ModulesUtil.getNextDocumentNumber("QUO", Quote.MODULE_NAME, Quote.DOCUMENT_NAME, Quote.quoteIdPropertyName, 8));
		
		
		return super.newInstance(bean);
	}

	@Override
	public QuoteExtension preExecute(ImplicitActionName actionName, QuoteExtension bean, Bean parentBean,
			WebContext webContext) throws Exception {
		if (ImplicitActionName.Save.equals(actionName) || ImplicitActionName.OK.equals(actionName)) {
			if (bean.isChanged()) {
				bean.updateInteraction();
			}	
		}
		return super.preExecute(actionName, bean, parentBean, webContext);
	}
	
	@Override
	public void preDelete(QuoteExtension bean) throws Exception {
		bean.deletedInteraction();
		super.preDelete(bean);
	}
}
