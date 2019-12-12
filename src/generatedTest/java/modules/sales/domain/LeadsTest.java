package modules.sales.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class LeadsTest extends AbstractDomainTest<Leads> {

	@Override
	protected Leads getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Leads.MODULE_NAME, Leads.DOCUMENT_NAME);
	}
}