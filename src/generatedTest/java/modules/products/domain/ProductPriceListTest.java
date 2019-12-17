package modules.products.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class ProductPriceListTest extends AbstractDomainTest<ProductPriceList> {

	@Override
	protected ProductPriceList getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(ProductPriceList.MODULE_NAME, ProductPriceList.DOCUMENT_NAME);
	}
}