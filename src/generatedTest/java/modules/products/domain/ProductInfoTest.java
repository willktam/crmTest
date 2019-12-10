package modules.products.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class ProductInfoTest extends AbstractDomainTest<ProductInfo> {

	@Override
	protected ProductInfo getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(ProductInfo.MODULE_NAME, ProductInfo.DOCUMENT_NAME);
	}
}