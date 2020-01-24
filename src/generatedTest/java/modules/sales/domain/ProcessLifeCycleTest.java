package modules.sales.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class ProcessLifeCycleTest extends AbstractDomainTest<ProcessLifeCycle> {

	@Override
	protected ProcessLifeCycle getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(ProcessLifeCycle.MODULE_NAME, ProcessLifeCycle.DOCUMENT_NAME);
	}
}