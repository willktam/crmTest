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
 * Account
 * 
 * @depend - - - RelationshipType
 * @navhas n primaryContact 0..1 ContactDetails
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Accounts extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "customers";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Accounts";

	/** @hidden */
	public static final String accountNamePropertyName = "accountName";
	/** @hidden */
	public static final String emailPropertyName = "email";
	/** @hidden */
	public static final String websitePropertyName = "website";
	/** @hidden */
	public static final String phonePropertyName = "phone";
	/** @hidden */
	public static final String faxPropertyName = "fax";
	/** @hidden */
	public static final String relationshipTypePropertyName = "relationshipType";
	/** @hidden */
	public static final String startDatePropertyName = "startDate";
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
	public static final String primaryContactPropertyName = "primaryContact";

	/**
	 * Relationship Type
	 * <br/>
	 * The type of relationship for the account
	 **/
	@XmlEnum
	public static enum RelationshipType implements Enumeration {
		customer("Customer", "Customer"),
		supplier("Supplier", "Supplier"),
		other("Other", "Other");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private RelationshipType(String code, String description) {
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

		public static RelationshipType fromCode(String code) {
			RelationshipType result = null;

			for (RelationshipType value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static RelationshipType fromDescription(String description) {
			RelationshipType result = null;

			for (RelationshipType value : values()) {
				if (value.description.equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				RelationshipType[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (RelationshipType value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Account Name
	 * <br/>
	 * The account name
	 **/
	private String accountName;
	/**
	 * Email
	 * <br/>
	 * The account email address
	 **/
	private String email;
	/**
	 * Website
	 * <br/>
	 * The account website
	 **/
	private String website;
	/**
	 * Phone
	 * <br/>
	 * The account phone number
	 **/
	private String phone;
	/**
	 * Fax
	 * <br/>
	 * The account fax number
	 **/
	private String fax;
	/**
	 * Relationship Type
	 * <br/>
	 * The type of relationship for the account
	 **/
	private RelationshipType relationshipType;
	/**
	 * Start Date
	 * <br/>
	 * The date the account began
	 **/
	private DateOnly startDate;
	/**
	 * Street
	 * <br/>
	 * The accounts street and number
	 **/
	private String street;
	/**
	 * City
	 * <br/>
	 * The accounts city
	 **/
	private String city;
	/**
	 * State or Province
	 * <br/>
	 * The accounts state or province
	 **/
	private String state;
	/**
	 * ZIP or Postal Code
	 * <br/>
	 * The accounts ZIP or postal code
	 **/
	private String postCode;
	/**
	 * Country/Region
	 * <br/>
	 * The accounts country or region
	 **/
	private String country;
	/**
	 * Primary Contact
	 * <br/>
	 * The accounts primary contact
	 **/
	private ContactDetails primaryContact = null;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Accounts.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Accounts.DOCUMENT_NAME;
	}

	public static Accounts newInstance() {
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
														"Accounts - {accountName}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Accounts) && 
					this.getBizId().equals(((Accounts) o).getBizId()));
	}

	/**
	 * {@link #accountName} accessor.
	 * @return	The value.
	 **/
	public String getAccountName() {
		return accountName;
	}

	/**
	 * {@link #accountName} mutator.
	 * @param accountName	The new value.
	 **/
	@XmlElement
	public void setAccountName(String accountName) {
		preset(accountNamePropertyName, accountName);
		this.accountName = accountName;
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
	 * {@link #website} accessor.
	 * @return	The value.
	 **/
	public String getWebsite() {
		return website;
	}

	/**
	 * {@link #website} mutator.
	 * @param website	The new value.
	 **/
	@XmlElement
	public void setWebsite(String website) {
		preset(websitePropertyName, website);
		this.website = website;
	}

	/**
	 * {@link #phone} accessor.
	 * @return	The value.
	 **/
	public String getPhone() {
		return phone;
	}

	/**
	 * {@link #phone} mutator.
	 * @param phone	The new value.
	 **/
	@XmlElement
	public void setPhone(String phone) {
		preset(phonePropertyName, phone);
		this.phone = phone;
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
	 * {@link #relationshipType} accessor.
	 * @return	The value.
	 **/
	public RelationshipType getRelationshipType() {
		return relationshipType;
	}

	/**
	 * {@link #relationshipType} mutator.
	 * @param relationshipType	The new value.
	 **/
	@XmlElement
	public void setRelationshipType(RelationshipType relationshipType) {
		preset(relationshipTypePropertyName, relationshipType);
		this.relationshipType = relationshipType;
	}

	/**
	 * {@link #startDate} accessor.
	 * @return	The value.
	 **/
	public DateOnly getStartDate() {
		return startDate;
	}

	/**
	 * {@link #startDate} mutator.
	 * @param startDate	The new value.
	 **/
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	@XmlElement
	public void setStartDate(DateOnly startDate) {
		preset(startDatePropertyName, startDate);
		this.startDate = startDate;
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
	 * {@link #primaryContact} accessor.
	 * @return	The value.
	 **/
	public ContactDetails getPrimaryContact() {
		return primaryContact;
	}

	/**
	 * {@link #primaryContact} mutator.
	 * @param primaryContact	The new value.
	 **/
	@XmlElement
	public void setPrimaryContact(ContactDetails primaryContact) {
		preset(primaryContactPropertyName, primaryContact);
		this.primaryContact = primaryContact;
	}

	/**
	 * hasCurrent
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isHasCurrent() {
		return (primaryContact != null);
	}

	/**
	 * {@link #isHasCurrent} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotHasCurrent() {
		return (! isHasCurrent());
	}
}
