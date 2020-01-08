package modules.customers.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class InteractionTest extends AbstractDomainTest<Interaction> {

	@Override
	protected Interaction getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Interaction.MODULE_NAME, Interaction.DOCUMENT_NAME);
	}
}