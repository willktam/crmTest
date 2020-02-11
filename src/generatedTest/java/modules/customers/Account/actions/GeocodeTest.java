package modules.customers.Account.actions;

import modules.customers.Account.AccountExtension;
import modules.customers.domain.Account;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractActionTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractActionTest} to create your own tests for this action.
 */
public class GeocodeTest extends AbstractActionTest<AccountExtension, Geocode> {

	@Override
	protected Geocode getAction() {
		return new Geocode();
	}

	@Override
	protected AccountExtension getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Account.MODULE_NAME, Account.DOCUMENT_NAME);
	}
}