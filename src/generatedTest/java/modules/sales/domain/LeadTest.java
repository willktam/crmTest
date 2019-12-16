package modules.sales.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class LeadTest extends AbstractDomainTest<Lead> {

	@Override
	protected Lead getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Lead.MODULE_NAME, Lead.DOCUMENT_NAME);
	}
}