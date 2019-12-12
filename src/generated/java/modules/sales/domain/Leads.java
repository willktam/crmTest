package modules.sales.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import modules.customers.domain.ContactDetails;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractPersistentBean;

/**
 * Lead
 * 
 * @navhas n contactDetails 1 ContactDetails
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Leads extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "sales";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Leads";

	/** @hidden */
	public static final String contactDetailsPropertyName = "contactDetails";

	/**
	 * Contact Details
	 * <br/>
	 * The contact details of the lead
	 **/
	private ContactDetails contactDetails = null;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Leads.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Leads.DOCUMENT_NAME;
	}

	public static Leads newInstance() {
		try {
			return CORE.getUser().getCustomer().getModule(MODULE_NAME).getDocument(CORE.getUser().getCustomer(), DOCUMENT_NAME).newInstance(CORE.getUser());
		}
		catch (RuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			throw new DomainException(e);
		}
	}

	@Override
	@XmlTransient
	public String getBizKey() {
		try {
			return org.skyve.util.Binder.formatMessage(org.skyve.CORE.getUser().getCustomer(),
														"{contactDetails.lastName}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Leads) && 
					this.getBizId().equals(((Leads) o).getBizId()));
	}

	/**
	 * {@link #contactDetails} accessor.
	 * @return	The value.
	 **/
	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	/**
	 * {@link #contactDetails} mutator.
	 * @param contactDetails	The new value.
	 **/
	@XmlElement
	public void setContactDetails(ContactDetails contactDetails) {
		preset(contactDetailsPropertyName, contactDetails);
		this.contactDetails = contactDetails;
	}

	/**
	 * Staff
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isStaff() {
		return (isUserInRole("sales","Staff"));
	}

	/**
	 * {@link #isStaff} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotStaff() {
		return (! isStaff());
	}
}
