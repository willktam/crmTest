package modules.customers.AccountDashboard.actions;

import modules.customers.AccountDashboard.AccountDashboardExtension;
import modules.customers.domain.AccountDashboard;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractActionTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractActionTest} to create your own tests for this action.
 */
public class AddInteractionTest extends AbstractActionTest<AccountDashboardExtension, AddInteraction> {

	@Override
	protected AddInteraction getAction() {
		return new AddInteraction();
	}

	@Override
	protected AccountDashboardExtension getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(AccountDashboard.MODULE_NAME, AccountDashboard.DOCUMENT_NAME);
	}
}