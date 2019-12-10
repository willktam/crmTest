package modules.customers.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateOnly;
import org.skyve.domain.types.Enumeration;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.DateOnlyMapper;
import org.skyve.metadata.model.document.Bizlet.DomainValue;

/**
 * Contact Details
 * 
 * @depend - - - ContactType
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
	public static final String MODULE_NAME = "customers";
	/** @hidden */
	public static final String DOCUMENT_NAME = "ContactDetails";

	/** @hidden */
	public static final String namePropertyName = "name";
	/** @hidden */
	public static final String contactTypePropertyName = "contactType";
	/** @hidden */
	public static final String dateOfBirthPropertyName = "dateOfBirth";
	/** @hidden */
	public static final String addressPropertyName = "address";
	/** @hidden */
	public static final String emailPropertyName = "email";
	/** @hidden */
	public static final String mobileNumberPropertyName = "mobileNumber";
	/** @hidden */
	public static final String workNumberPropertyName = "workNumber";

	/**
	 * Contact Type
	 * <br/>
	 * A customers contact type
	 **/
	@XmlEnum
	public static enum ContactType implements Enumeration {
		person("Person", "Person"),
		organisation("Organisation", "Organisation");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private ContactType(String code, String description) {
			this.code = code;
			this.description = description;
			this.domainValue = new DomainValue(code, description);
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toDescription() {
			return description;
		}

		@Override
		public DomainValue toDomainValue() {
			return domainValue;
		}

		public static ContactType fromCode(String code) {
			ContactType result = null;

			for (ContactType value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static ContactType fromDescription(String description) {
			ContactType result = null;

			for (ContactType value : values()) {
				if (value.description.equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				ContactType[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (ContactType value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Name
	 * <br/>
	 * A customers name
	 **/
	private String name;
	/**
	 * Contact Type
	 * <br/>
	 * A customers contact type
	 **/
	private ContactType contactType;
	/**
	 * Date of Birth
	 * <br/>
	 * A customers date of birth
	 **/
	private DateOnly dateOfBirth;
	/**
	 * Address
	 * <br/>
	 * A customers physical address
	 **/
	private String address;
	/**
	 * Email
	 * <br/>
	 * A customers email address
	 **/
	private String email;
	/**
	 * Mobile Number
	 * <br/>
	 * A customers mobile number
	 **/
	private String mobileNumber;
	/**
	 * Work Number
	 * <br/>
	 * A customers work number
	 **/
	private String workNumber;

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
	 * {@link #contactType} accessor.
	 * @return	The value.
	 **/
	public ContactType getContactType() {
		return contactType;
	}

	/**
	 * {@link #contactType} mutator.
	 * @param contactType	The new value.
	 **/
	@XmlElement
	public void setContactType(ContactType contactType) {
		preset(contactTypePropertyName, contactType);
		this.contactType = contactType;
	}

	/**
	 * {@link #dateOfBirth} accessor.
	 * @return	The value.
	 **/
	public DateOnly getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * {@link #dateOfBirth} mutator.
	 * @param dateOfBirth	The new value.
	 **/
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	@XmlElement
	public void setDateOfBirth(DateOnly dateOfBirth) {
		preset(dateOfBirthPropertyName, dateOfBirth);
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * {@link #address} accessor.
	 * @return	The value.
	 **/
	public String getAddress() {
		return address;
	}

	/**
	 * {@link #address} mutator.
	 * @param address	The new value.
	 **/
	@XmlElement
	public void setAddress(String address) {
		preset(addressPropertyName, address);
		this.address = address;
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
	 * {@link #mobileNumber} accessor.
	 * @return	The value.
	 **/
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * {@link #mobileNumber} mutator.
	 * @param mobileNumber	The new value.
	 **/
	@XmlElement
	public void setMobileNumber(String mobileNumber) {
		preset(mobileNumberPropertyName, mobileNumber);
		this.mobileNumber = mobileNumber;
	}

	/**
	 * {@link #workNumber} accessor.
	 * @return	The value.
	 **/
	public String getWorkNumber() {
		return workNumber;
	}

	/**
	 * {@link #workNumber} mutator.
	 * @param workNumber	The new value.
	 **/
	@XmlElement
	public void setWorkNumber(String workNumber) {
		preset(workNumberPropertyName, workNumber);
		this.workNumber = workNumber;
	}
}
