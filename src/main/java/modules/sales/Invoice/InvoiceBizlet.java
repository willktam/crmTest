package modules.sales.Invoice;

import org.skyve.metadata.model.document.Bizlet;

import modules.admin.ModulesUtil;
import modules.sales.domain.Invoice;

public class InvoiceBizlet extends Bizlet<Invoice> {

	private static final long serialVersionUID = 7457511695397365804L;
	
	@Override
	public Invoice newInstance(Invoice bean) throws Exception {
		// Create a new id on new instance
		bean.setInvoiceId(ModulesUtil.getNextDocumentNumber("INV", Invoice.MODULE_NAME, Invoice.DOCUMENT_NAME, Invoice.invoiceIdPropertyName, 8));
		return super.newInstance(bean);
	}
}
