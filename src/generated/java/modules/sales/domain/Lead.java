package modules.sales.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import modules.customers.domain.ContactDetails;
import modules.sales.Lead.LeadExtension;
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
public class Lead extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "sales";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Lead";

	/** @hidden */
	public static final String contactDetailsPropertyName = "contactDetails";
	/** @hidden */
	public static final String progressPropertyName = "progress";

	/**
	 * Contact Details
	 * <br/>
	 * The contact details of the lead
	 **/
	private ContactDetails contactDetails = null;
	/**
	 * Progress
	 **/
	private String progress;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Lead.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Lead.DOCUMENT_NAME;
	}

	public static LeadExtension newInstance() {
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
		return ((o instanceof Lead) && 
					this.getBizId().equals(((Lead) o).getBizId()));
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
	 * {@link #progress} accessor.
	 * @return	The value.
	 **/
	public String getProgress() {
		return progress;
	}

	/**
	 * {@link #progress} mutator.
	 * @param progress	The new value.
	 **/
	@XmlElement
	public void setProgress(String progress) {
		this.progress = progress;
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

	/**
	 * True when the progress markup is not blank
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isHasProgress() {
		return (getProgress() != null);
	}

	/**
	 * {@link #isHasProgress} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotHasProgress() {
		return (! isHasProgress());
	}
}
