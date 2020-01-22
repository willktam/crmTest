package modules.sales.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.customers.ContactDetail.ContactDetailExtension;
import modules.sales.Lead.LeadExtension;
import org.locationtech.jts.geom.Geometry;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.GeometryMapper;

/**
 * Lead
 * 
 * @navhas n contactDetails 1 ContactDetail
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
	public static final String namePropertyName = "name";
	/** @hidden */
	public static final String websitePropertyName = "website";
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
	public static final String leadTypePropertyName = "leadType";
	/** @hidden */
	public static final String companyLocationPropertyName = "companyLocation";
	/** @hidden */
	public static final String progressPropertyName = "progress";
	/** @hidden */
	public static final String selectedTabPropertyName = "selectedTab";

	/**
	 * Contact Details
	 * <br/>
	 * The contact details of the lead
	 **/
	private ContactDetailExtension contactDetails = null;
	/**
	 * Company Name
	 * <br/>
	 * The company name
	 **/
	private String name;
	/**
	 * Website
	 * <br/>
	 * The company website
	 **/
	private String website;
	/**
	 * Street
	 * <br/>
	 * The company's street and number
	 **/
	private String street;
	/**
	 * City
	 * <br/>
	 * The company's city
	 **/
	private String city;
	/**
	 * State or Province
	 * <br/>
	 * The company's state or province
	 **/
	private String state;
	/**
	 * ZIP or Postal Code
	 * <br/>
	 * The company's ZIP or postal code
	 **/
	private String postCode;
	/**
	 * Country/Region
	 * <br/>
	 * The company's country or region
	 **/
	private String country;
	/**
	 * Lead Type
	 * <br/>
	 * The type of this lead
	 **/
	private String leadType;
	/**
	 * Company Location
	 **/
	private Geometry companyLocation;
	/**
	 * Progress
	 **/
	private String progress;
	/**
	 * selectedTab
	 **/
	private transient Integer selectedTab = new Integer(0);

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
	public ContactDetailExtension getContactDetails() {
		return contactDetails;
	}

	/**
	 * {@link #contactDetails} mutator.
	 * @param contactDetails	The new value.
	 **/
	@XmlElement
	public void setContactDetails(ContactDetailExtension contactDetails) {
		preset(contactDetailsPropertyName, contactDetails);
		this.contactDetails = contactDetails;
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
	 * {@link #leadType} accessor.
	 * @return	The value.
	 **/
	public String getLeadType() {
		return leadType;
	}

	/**
	 * {@link #leadType} mutator.
	 * @param leadType	The new value.
	 **/
	@XmlElement
	public void setLeadType(String leadType) {
		preset(leadTypePropertyName, leadType);
		this.leadType = leadType;
	}

	/**
	 * {@link #companyLocation} accessor.
	 * @return	The value.
	 **/
	public Geometry getCompanyLocation() {
		return companyLocation;
	}

	/**
	 * {@link #companyLocation} mutator.
	 * @param companyLocation	The new value.
	 **/
	@XmlJavaTypeAdapter(GeometryMapper.class)
	@XmlElement
	public void setCompanyLocation(Geometry companyLocation) {
		preset(companyLocationPropertyName, companyLocation);
		this.companyLocation = companyLocation;
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
	 * {@link #selectedTab} accessor.
	 * @return	The value.
	 **/
	public Integer getSelectedTab() {
		return selectedTab;
	}

	/**
	 * {@link #selectedTab} mutator.
	 * @param selectedTab	The new value.
	 **/
	@XmlElement
	public void setSelectedTab(Integer selectedTab) {
		preset(selectedTabPropertyName, selectedTab);
		this.selectedTab = selectedTab;
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
	 * contactSelected
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isContactSelected() {
		return (contactDetails != null);
	}

	/**
	 * {@link #isContactSelected} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotContactSelected() {
		return (! isContactSelected());
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
