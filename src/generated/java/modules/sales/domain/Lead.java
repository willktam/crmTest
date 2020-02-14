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
 * @navhas n leadType 1 Configuration
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
	public static final String line1PropertyName = "line1";
	/** @hidden */
	public static final String line2PropertyName = "line2";
	/** @hidden */
	public static final String suburbPropertyName = "suburb";
	/** @hidden */
	public static final String statePropertyName = "state";
	/** @hidden */
	public static final String postCodePropertyName = "postCode";
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
	 * Line 1
	 * <br/>
	 * The company's street
	 **/
	private String line1;
	/**
	 * Line 2
	 * <br/>
	 * The company's street
	 **/
	private String line2;
	/**
	 * Suburb
	 * <br/>
	 * The company's suburb
	 **/
	private String suburb;
	/**
	 * State
	 * <br/>
	 * The company's state
	 **/
	private String state;
	/**
	 * Postal Code
	 * <br/>
	 * The company's postal code
	 **/
	private String postCode;
	/**
	 * Lead Type
	 * <br/>
	 * The type of this lead
	 **/
	private Configuration leadType = null;
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
	 * {@link #line1} accessor.
	 * @return	The value.
	 **/
	public String getLine1() {
		return line1;
	}

	/**
	 * {@link #line1} mutator.
	 * @param line1	The new value.
	 **/
	@XmlElement
	public void setLine1(String line1) {
		preset(line1PropertyName, line1);
		this.line1 = line1;
	}

	/**
	 * {@link #line2} accessor.
	 * @return	The value.
	 **/
	public String getLine2() {
		return line2;
	}

	/**
	 * {@link #line2} mutator.
	 * @param line2	The new value.
	 **/
	@XmlElement
	public void setLine2(String line2) {
		preset(line2PropertyName, line2);
		this.line2 = line2;
	}

	/**
	 * {@link #suburb} accessor.
	 * @return	The value.
	 **/
	public String getSuburb() {
		return suburb;
	}

	/**
	 * {@link #suburb} mutator.
	 * @param suburb	The new value.
	 **/
	@XmlElement
	public void setSuburb(String suburb) {
		preset(suburbPropertyName, suburb);
		this.suburb = suburb;
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
	 * {@link #leadType} accessor.
	 * @return	The value.
	 **/
	public Configuration getLeadType() {
		return leadType;
	}

	/**
	 * {@link #leadType} mutator.
	 * @param leadType	The new value.
	 **/
	@XmlElement
	public void setLeadType(Configuration leadType) {
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

	/**
	 * uploadSelected
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isUploadSelected() {
		return (getContactDetails() != null && getContactDetails().getInteractionType() != null && getContactDetails().getInteractionType().name() == "upload");
	}

	/**
	 * {@link #isUploadSelected} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotUploadSelected() {
		return (! isUploadSelected());
	}
}
