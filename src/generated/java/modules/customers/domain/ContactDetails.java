package modules.customers.domain;

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
	public static final String MODULE_NAME = "customers";
	/** @hidden */
	public static final String DOCUMENT_NAME = "ContactDetails";

	/** @hidden */
	public static final String firstNamePropertyName = "firstName";
	/** @hidden */
	public static final String lastNamePropertyName = "lastName";
	/** @hidden */
	public static final String jobTitlePropertyName = "jobTitle";
	/** @hidden */
	public static final String emailPropertyName = "email";
	/** @hidden */
	public static final String mobileNumberPropertyName = "mobileNumber";
	/** @hidden */
	public static final String businessNumberPropertyName = "businessNumber";
	/** @hidden */
	public static final String faxPropertyName = "fax";
	/** @hidden */
	public static final String streetPropertyName = "street";
	/** @hidden */
	public static final String cityPropertyName = "city";
	/** @hidden */
	public static final String statePropertyName = "state";
	/** @hidden */
	public static final String postCodePropertyName = "postCode";
	/** @hidden */
	public static final String countryPropertyName = "country";

	/**
	 * First Name
	 * <br/>
	 * A customers first name
	 **/
	private String firstName;
	/**
	 * Last Name
	 * <br/>
	 * A customers last name
	 **/
	private String lastName;
	/**
	 * Job Title
	 * <br/>
	 * A customers job title
	 **/
	private String jobTitle;
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
	 * Business Number
	 * <br/>
	 * A customers business number
	 **/
	private String businessNumber;
	/**
	 * Fax
	 * <br/>
	 * The account fax number
	 **/
	private String fax;
	/**
	 * Street
	 * <br/>
	 * The customers street and number
	 **/
	private String street;
	/**
	 * City
	 * <br/>
	 * The customers city
	 **/
	private String city;
	/**
	 * State or Province
	 * <br/>
	 * The customers state or province
	 **/
	private String state;
	/**
	 * ZIP or Postal Code
	 * <br/>
	 * The customers ZIP or postal code
	 **/
	private String postCode;
	/**
	 * Country/Region
	 * <br/>
	 * The customers country or region
	 **/
	private String country;

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
														"Contact Details - {lastName}",
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
	 * {@link #firstName} accessor.
	 * @return	The value.
	 **/
	public String getFirstName() {
		return firstName;
	}

	/**
	 * {@link #firstName} mutator.
	 * @param firstName	The new value.
	 **/
	@XmlElement
	public void setFirstName(String firstName) {
		preset(firstNamePropertyName, firstName);
		this.firstName = firstName;
	}

	/**
	 * {@link #lastName} accessor.
	 * @return	The value.
	 **/
	public String getLastName() {
		return lastName;
	}

	/**
	 * {@link #lastName} mutator.
	 * @param lastName	The new value.
	 **/
	@XmlElement
	public void setLastName(String lastName) {
		preset(lastNamePropertyName, lastName);
		this.lastName = lastName;
	}

	/**
	 * {@link #jobTitle} accessor.
	 * @return	The value.
	 **/
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * {@link #jobTitle} mutator.
	 * @param jobTitle	The new value.
	 **/
	@XmlElement
	public void setJobTitle(String jobTitle) {
		preset(jobTitlePropertyName, jobTitle);
		this.jobTitle = jobTitle;
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
	 * {@link #businessNumber} accessor.
	 * @return	The value.
	 **/
	public String getBusinessNumber() {
		return businessNumber;
	}

	/**
	 * {@link #businessNumber} mutator.
	 * @param businessNumber	The new value.
	 **/
	@XmlElement
	public void setBusinessNumber(String businessNumber) {
		preset(businessNumberPropertyName, businessNumber);
		this.businessNumber = businessNumber;
	}

	/**
	 * {@link #fax} accessor.
	 * @return	The value.
	 **/
	public String getFax() {
		return fax;
	}

	/**
	 * {@link #fax} mutator.
	 * @param fax	The new value.
	 **/
	@XmlElement
	public void setFax(String fax) {
		preset(faxPropertyName, fax);
		this.fax = fax;
	}

	/**
	 * {@link #street} accessor.
	 * @return	The value.
	 **/
	public String getStreet() {
		return street;
	}

	/**
	 * {@link #street} mutator.
	 * @param street	The new value.
	 **/
	@XmlElement
	public void setStreet(String street) {
		preset(streetPropertyName, street);
		this.street = street;
	}

	/**
	 * {@link #city} accessor.
	 * @return	The value.
	 **/
	public String getCity() {
		return city;
	}

	/**
	 * {@link #city} mutator.
	 * @param city	The new value.
	 **/
	@XmlElement
	public void setCity(String city) {
		preset(cityPropertyName, city);
		this.city = city;
	}

	/**
	 * {@link #state} accessor.
	 * @return	The value.
	 **/
	public String getState() {
		return state;
	}

	/**
	 * {@link #state} mutator.
	 * @param state	The new value.
	 **/
	@XmlElement
	public void setState(String state) {
		preset(statePropertyName, state);
		this.state = state;
	}

	/**
	 * {@link #postCode} accessor.
	 * @return	The value.
	 **/
	public String getPostCode() {
		return postCode;
	}

	/**
	 * {@link #postCode} mutator.
	 * @param postCode	The new value.
	 **/
	@XmlElement
	public void setPostCode(String postCode) {
		preset(postCodePropertyName, postCode);
		this.postCode = postCode;
	}

	/**
	 * {@link #country} accessor.
	 * @return	The value.
	 **/
	public String getCountry() {
		return country;
	}

	/**
	 * {@link #country} mutator.
	 * @param country	The new value.
	 **/
	@XmlElement
	public void setCountry(String country) {
		preset(countryPropertyName, country);
		this.country = country;
	}
}
