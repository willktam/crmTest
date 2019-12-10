package modules.owner.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractPersistentBean;

/**
 * Contact Details
 * 
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class ContactDetails extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "owner";
	/** @hidden */
	public static final String DOCUMENT_NAME = "ContactDetails";

	/** @hidden */
	public static final String namePropertyName = "name";
	/** @hidden */
	public static final String emailPropertyName = "email";
	/** @hidden */
	public static final String bioPropertyName = "bio";

	/**
	 * Name
	 * <br/>
	 * The owners name
	 **/
	private String name;
	/**
	 * Email
	 * <br/>
	 * Owners email address
	 **/
	private String email;
	/**
	 * Bio
	 **/
	private String bio;

	@Override
	@XmlTransient
	public String getBizModule() {
		return ContactDetails.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return ContactDetails.DOCUMENT_NAME;
	}

	public static ContactDetails newInstance() {
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
														"Contact Details - {name}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof ContactDetails) && 
					this.getBizId().equals(((ContactDetails) o).getBizId()));
	}

	/**
	 * {@link #name} accessor.
	 * @return	The value.
	 **/
	public String getName() {
		return name;
	}

	/**
	 * {@link #name} mutator.
	 * @param name	The new value.
	 **/
	@XmlElement
	public void setName(String name) {
		preset(namePropertyName, name);
		this.name = name;
	}

	/**
	 * {@link #email} accessor.
	 * @return	The value.
	 **/
	public String getEmail() {
		return email;
	}

	/**
	 * {@link #email} mutator.
	 * @param email	The new value.
	 **/
	@XmlElement
	public void setEmail(String email) {
		preset(emailPropertyName, email);
		this.email = email;
	}

	/**
	 * {@link #bio} accessor.
	 * @return	The value.
	 **/
	public String getBio() {
		return bio;
	}

	/**
	 * {@link #bio} mutator.
	 * @param bio	The new value.
	 **/
	@XmlElement
	public void setBio(String bio) {
		preset(bioPropertyName, bio);
		this.bio = bio;
	}
}
