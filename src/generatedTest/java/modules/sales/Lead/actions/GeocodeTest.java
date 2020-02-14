package modules.sales.Lead.actions;

import modules.sales.Lead.LeadExtension;
import modules.sales.domain.Lead;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractActionTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractActionTest} to create your own tests for this action.
 */
public class GeocodeTest extends AbstractActionTest<LeadExtension, Geocode> {

	@Override
	protected Geocode getAction() {
		return new Geocode();
	}

	@Override
	protected LeadExtension getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Lead.MODULE_NAME, Lead.DOCUMENT_NAME);
	}
}