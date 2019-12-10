package modules.products.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class ProductSalesTest extends AbstractDomainTest<ProductSales> {

	@Override
	protected ProductSales getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(ProductSales.MODULE_NAME, ProductSales.DOCUMENT_NAME);
	}
}