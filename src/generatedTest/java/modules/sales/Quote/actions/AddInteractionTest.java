package modules.sales.Quote.actions;

import modules.sales.Quote.QuoteExtension;
import modules.sales.domain.Quote;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractActionTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractActionTest} to create your own tests for this action.
 */
public class AddInteractionTest extends AbstractActionTest<QuoteExtension, AddInteraction> {

	@Override
	protected AddInteraction getAction() {
		return new AddInteraction();
	}

	@Override
	protected QuoteExtension getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Quote.MODULE_NAME, Quote.DOCUMENT_NAME);
	}
}