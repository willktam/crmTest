package modules.customers.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class AccountTest extends AbstractDomainTest<Account> {

	@Override
	protected Account getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Account.MODULE_NAME, Account.DOCUMENT_NAME);
	}
}