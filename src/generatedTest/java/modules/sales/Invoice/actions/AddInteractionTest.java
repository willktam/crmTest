package modules.sales.Invoice.actions;

import modules.sales.Invoice.InvoiceExtension;
import modules.sales.domain.Invoice;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractActionTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractActionTest} to create your own tests for this action.
 */
public class AddInteractionTest extends AbstractActionTest<InvoiceExtension, AddInteraction> {

	@Override
	protected AddInteraction getAction() {
		return new AddInteraction();
	}

	@Override
	protected InvoiceExtension getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Invoice.MODULE_NAME, Invoice.DOCUMENT_NAME);
	}
}