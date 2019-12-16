package modules.products.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class ProductSaleTest extends AbstractDomainTest<ProductSale> {

	@Override
	protected ProductSale getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(ProductSale.MODULE_NAME, ProductSale.DOCUMENT_NAME);
	}
}