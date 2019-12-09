package modules.admin.UserList.actions;

import modules.admin.domain.UserList;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractActionTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractActionTest} to create your own tests for this action.
 */
public class CreateUsersTest extends AbstractActionTest<UserList, CreateUsers> {

	@Override
	protected CreateUsers getAction() {
		return new CreateUsers();
	}

	@Override
	protected UserList getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(UserList.MODULE_NAME, UserList.DOCUMENT_NAME);
	}
}