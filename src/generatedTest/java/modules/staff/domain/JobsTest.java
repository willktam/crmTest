package modules.staff.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class JobsTest extends AbstractDomainTest<Jobs> {

	@Override
	protected Jobs getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Jobs.MODULE_NAME, Jobs.DOCUMENT_NAME);
	}
}