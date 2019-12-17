package modules.products.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class ProductUnitGroupTest extends AbstractDomainTest<ProductUnitGroup> {

	@Override
	protected ProductUnitGroup getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(ProductUnitGroup.MODULE_NAME, ProductUnitGroup.DOCUMENT_NAME);
	}
}