package modules.sales.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class OrderTest extends AbstractDomainTest<Order> {

	@Override
	protected Order getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Order.MODULE_NAME, Order.DOCUMENT_NAME);
	}
}