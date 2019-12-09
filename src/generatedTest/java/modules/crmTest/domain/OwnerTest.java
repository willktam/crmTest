package modules.crmTest.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class OwnerTest extends AbstractDomainTest<Owner> {

	@Override
	protected Owner getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Owner.MODULE_NAME, Owner.DOCUMENT_NAME);
	}
}