package modules.sales.Opportunity.actions;

import modules.sales.Opportunity.OpportunityExtension;
import modules.sales.domain.Opportunity;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractActionTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractActionTest} to create your own tests for this action.
 */
public class AddInteractionTest extends AbstractActionTest<OpportunityExtension, AddInteraction> {

	@Override
	protected AddInteraction getAction() {
		return new AddInteraction();
	}

	@Override
	protected OpportunityExtension getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Opportunity.MODULE_NAME, Opportunity.DOCUMENT_NAME);
	}
}