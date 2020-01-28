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
import modules.customers.Account.AccountExtension;
import modules.customers.ContactDetail.ContactDetailExtension;
import modules.customers.Interaction.InteractionExtension;
import modules.customers.domain.Interaction.Type;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateOnly;
import org.skyve.domain.types.Enumeration;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.ChangeTrackingArrayList;
import org.skyve.impl.domain.types.jaxb.DateOnlyMapper;
import org.skyve.metadata.model.document.Bizlet.DomainValue;

/**
 * Account
 * 
 * @depend - - - RelationshipType
 * @depend - - - Type
 * @navhas n primaryContact 0..1 ContactDetail
 * @navcomposed n interactions 0..n Interaction
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Account extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "customers";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Account";

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
	/** @hidden */
	public static final String interactionsPropertyName = "interactions";
	/** @hidden */
	public static final String interactionDescriptionPropertyName = "interactionDescription";
	/** @hidden */
	public static final String interactionTypePropertyName = "interactionType";

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
	 * The account's street and number
	 **/
	private String street;
	/**
	 * City
	 * <br/>
	 * The account's city
	 **/
	private String city;
	/**
	 * State or Province
	 * <br/>
	 * The account's state or province
	 **/
	private String state;
	/**
	 * ZIP or Postal Code
	 * <br/>
	 * The account's ZIP or postal code
	 **/
	private String postCode;
	/**
	 * Country/Region
	 * <br/>
	 * The account's country or region
	 **/
	private String country;
	/**
	 * Primary Contact
	 * <br/>
	 * The account's primary contact
	 **/
	private ContactDetailExtension primaryContact = null;
	/**
	 * Interactions
	 **/
	private List<InteractionExtension> interactions = new ChangeTrackingArrayList<>("interactions", this);
	/**
	 * Description
	 **/
	private String interactionDescription;
	/**
	 * Type
	 **/
	private Type interactionType;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Account.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Account.DOCUMENT_NAME;
	}

	public static AccountExtension newInstance() {
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
														"{accountName}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Account) && 
					this.getBizId().equals(((Account) o).getBizId()));
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
	public ContactDetailExtension getPrimaryContact() {
		return primaryContact;
	}

	/**
	 * {@link #primaryContact} mutator.
	 * @param primaryContact	The new value.
	 **/
	@XmlElement
	public void setPrimaryContact(ContactDetailExtension primaryContact) {
		preset(primaryContactPropertyName, primaryContact);
		this.primaryContact = primaryContact;
	}

	/**
	 * {@link #interactions} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<InteractionExtension> getInteractions() {
		return interactions;
	}

	/**
	 * {@link #interactions} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public InteractionExtension getInteractionsElementById(String bizId) {
		return getElementById(interactions, bizId);
	}

	/**
	 * {@link #interactions} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setInteractionsElementById(String bizId, InteractionExtension element) {
		 setElementById(interactions, element);
	}

	/**
	 * {@link #interactionDescription} accessor.
	 * @return	The value.
	 **/
	public String getInteractionDescription() {
		return interactionDescription;
	}

	/**
	 * {@link #interactionDescription} mutator.
	 * @param interactionDescription	The new value.
	 **/
	@XmlElement
	public void setInteractionDescription(String interactionDescription) {
		preset(interactionDescriptionPropertyName, interactionDescription);
		this.interactionDescription = interactionDescription;
	}

	/**
	 * {@link #interactionType} accessor.
	 * @return	The value.
	 **/
	public Type getInteractionType() {
		return interactionType;
	}

	/**
	 * {@link #interactionType} mutator.
	 * @param interactionType	The new value.
	 **/
	@XmlElement
	public void setInteractionType(Type interactionType) {
		preset(interactionTypePropertyName, interactionType);
		this.interactionType = interactionType;
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
