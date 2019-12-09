package modules.crmTest.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class CustomerTest extends AbstractDomainTest<Customer> {

	@Override
	protected Customer getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(Customer.MODULE_NAME, Customer.DOCUMENT_NAME);
	}
}