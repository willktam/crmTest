package modules.owner.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class ContactDetailsTest extends AbstractDomainTest<ContactDetails> {

	@Override
	protected ContactDetails getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(ContactDetails.MODULE_NAME, ContactDetails.DOCUMENT_NAME);
	}
}