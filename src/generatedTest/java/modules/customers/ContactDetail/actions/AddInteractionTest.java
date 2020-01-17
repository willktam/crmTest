package modules.customers.ContactDetail.actions;

import modules.customers.ContactDetail.ContactDetailExtension;
import modules.customers.domain.ContactDetail;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractActionTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractActionTest} to create your own tests for this action.
 */
public class AddInteractionTest extends AbstractActionTest<ContactDetailExtension, AddInteraction> {

	@Override
	protected AddInteraction getAction() {
		return new AddInteraction();
	}

	@Override
	protected ContactDetailExtension getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(ContactDetail.MODULE_NAME, ContactDetail.DOCUMENT_NAME);
	}
}