package modules.sales.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class QuoteTest extends AbstractDomainTest<Quote> {

	@Override
	protected Quote getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Quote.MODULE_NAME, Quote.DOCUMENT_NAME);
	}
}