package modules.customers.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import modules.customers.ContactDetail.ContactDetailExtension;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.Enumeration;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.ChangeTrackingArrayList;
import org.skyve.metadata.model.document.Bizlet.DomainValue;

/**
 * Contact Detail
 * 
 * @depend - - - Method
 * @navcomposed n interactions 0..n Interaction
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class ContactDetail extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "customers";
	/** @hidden */
	public static final String DOCUMENT_NAME = "ContactDetail";

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
	public static final String methodPropertyName = "method";
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
	/** @hidden */
	public static final String interactionsPropertyName = "interactions";

	/**
	 * Preferred Method of Contact
	 * <br/>
	 * The customer's preferred method of contact
	 **/
	@XmlEnum
	public static enum Method implements Enumeration {
		email("Email", "Email"),
		mobileNumber("Mobile Number", "Mobile Number"),
		businessNumber("Business Number", "Business Number"),
		fax("Fax", "Fax"),
		any("Any", "Any");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private Method(String code, String description) {
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

		public static Method fromCode(String code) {
			Method result = null;

			for (Method value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static Method fromDescription(String description) {
			Method result = null;

			for (Method value : values()) {
				if (value.description.equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				Method[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (Method value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * First Name
	 * <br/>
	 * The customer's first name
	 **/
	private String firstName;
	/**
	 * Last Name
	 * <br/>
	 * The customer's last name
	 **/
	private String lastName;
	/**
	 * Job Title
	 * <br/>
	 * The customer's job title
	 **/
	private String jobTitle;
	/**
	 * Email
	 * <br/>
	 * The customer's email address
	 **/
	private String email;
	/**
	 * Mobile Number
	 * <br/>
	 * The customer's mobile number
	 **/
	private String mobileNumber;
	/**
	 * Business Number
	 * <br/>
	 * The customer's business number
	 **/
	private String businessNumber;
	/**
	 * Fax
	 * <br/>
	 * The customer's fax number
	 **/
	private String fax;
	/**
	 * Preferred Method of Contact
	 * <br/>
	 * The customer's preferred method of contact
	 **/
	private Method method;
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
	/**
	 * Interactions
	 **/
	private List<Interaction> interactions = new ChangeTrackingArrayList<>("interactions", this);

	@Override
	@XmlTransient
	public String getBizModule() {
		return ContactDetail.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return ContactDetail.DOCUMENT_NAME;
	}

	public static ContactDetailExtension newInstance() {
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
														"{firstName} {lastName}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof ContactDetail) && 
					this.getBizId().equals(((ContactDetail) o).getBizId()));
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
	 * {@link #method} accessor.
	 * @return	The value.
	 **/
	public Method getMethod() {
		return method;
	}

	/**
	 * {@link #method} mutator.
	 * @param method	The new value.
	 **/
	@XmlElement
	public void setMethod(Method method) {
		preset(methodPropertyName, method);
		this.method = method;
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

	/**
	 * {@link #interactions} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<Interaction> getInteractions() {
		return interactions;
	}

	/**
	 * {@link #interactions} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public Interaction getInteractionsElementById(String bizId) {
		return getElementById(interactions, bizId);
	}

	/**
	 * {@link #interactions} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setInteractionsElementById(String bizId, Interaction element) {
		 setElementById(interactions, element);
	}
}
