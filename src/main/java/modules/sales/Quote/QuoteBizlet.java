package modules.sales.Quote;

import org.skyve.domain.Bean;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

import modules.admin.ModulesUtil;
import modules.sales.domain.Quote;

public class QuoteBizlet extends Bizlet<QuoteExtension> {

	private static final long serialVersionUID = 6426735460765073764L;
	
	@Override
	public QuoteExtension newInstance(QuoteExtension bean) throws Exception {
		bean.setSelectedTab(0);
		// set a new quote id when a new one is created
		bean.setQuoteId(ModulesUtil.getNextDocumentNumber("QUO", Quote.MODULE_NAME, Quote.DOCUMENT_NAME, Quote.quoteIdPropertyName, 8));
		return super.newInstance(bean);
	}

	@Override
	public QuoteExtension preExecute(ImplicitActionName actionName, QuoteExtension bean, Bean parentBean,
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
		if (bean.isPersisted()) {
			bean.sortInteractions();
		}
		return super.preExecute(actionName, bean, parentBean, webContext);
	}
	
	@Override
	public void preDelete(QuoteExtension bean) throws Exception {
		bean.deletedInteraction();
		super.preDelete(bean);
	}
	
	@Override
	public void preRerender(String source, QuoteExtension bean, WebContext webContext) throws Exception {
		bean.setTotal(bean.getOpportunity().getTotal());
		super.preRerender(source, bean, webContext);
	}
}
