package modules.customers.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class AccountsTest extends AbstractDomainTest<Accounts> {

	@Override
	protected Accounts getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Accounts.MODULE_NAME, Accounts.DOCUMENT_NAME);
	}
}