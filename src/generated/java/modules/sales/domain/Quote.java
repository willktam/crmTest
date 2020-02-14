package modules.sales.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.customers.Account.AccountExtension;
import modules.sales.Opportunity.OpportunityExtension;
import modules.sales.Quote.QuoteExtension;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateOnly;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.DateOnlyMapper;

/**
 * Quote
 * 
 * @navhas n opportunity 1 Opportunity
 * @navhas n account 1 Account
 * @navhas n status 1 Configuration
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Quote extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "sales";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Quote";

	/** @hidden */
	public static final String namePropertyName = "name";
	/** @hidden */
	public static final String quoteIdPropertyName = "quoteId";
	/** @hidden */
	public static final String opportunityPropertyName = "opportunity";
	/** @hidden */
	public static final String accountPropertyName = "account";
	/** @hidden */
	public static final String expireDatePropertyName = "expireDate";
	/** @hidden */
	public static final String statusPropertyName = "status";
	/** @hidden */
	public static final String statusReasonPropertyName = "statusReason";
	/** @hidden */
	public static final String descriptionPropertyName = "description";
	/** @hidden */
	public static final String totalPropertyName = "total";
	/** @hidden */
	public static final String paymentTermsPropertyName = "paymentTerms";
	/** @hidden */
	public static final String freightTermsPropertyName = "freightTerms";
	/** @hidden */
	public static final String streetBillPropertyName = "streetBill";
	/** @hidden */
	public static final String cityBillPropertyName = "cityBill";
	/** @hidden */
	public static final String stateBillPropertyName = "stateBill";
	/** @hidden */
	public static final String postCodeBillPropertyName = "postCodeBill";
	/** @hidden */
	public static final String countryBillPropertyName = "countryBill";
	/** @hidden */
	public static final String shippingMethodPropertyName = "shippingMethod";
	/** @hidden */
	public static final String streetShipPropertyName = "streetShip";
	/** @hidden */
	public static final String cityShipPropertyName = "cityShip";
	/** @hidden */
	public static final String stateShipPropertyName = "stateShip";
	/** @hidden */
	public static final String postCodeShipPropertyName = "postCodeShip";
	/** @hidden */
	public static final String countryShipPropertyName = "countryShip";

	/**
	 * Quote Name
	 * <br/>
	 * The name of the quote
	 **/
	private String name;
	/**
	 * Quote ID
	 * <br/>
	 * The id number of the quote
	 **/
	private String quoteId;
	/**
	 * Opportunity
	 * <br/>
	 * The opportunity of this quote
	 **/
	private OpportunityExtension opportunity = null;
	/**
	 * Account
	 * <br/>
	 * The account for this quote
	 **/
	private AccountExtension account = null;
	/**
	 * Quote Expire Date
	 * <br/>
	 * The date the quote expires
	 **/
	private DateOnly expireDate;
	/**
	 * Quote Status
	 * <br/>
	 * The status of the quote
	 **/
	private Configuration status = null;
	/**
	 * Status Reason
	 * <br/>
	 * The reason for the current status of the quote
	 **/
	private String statusReason;
	/**
	 * Description
	 * <br/>
	 * A description of the quote
	 **/
	private String description;
	/**
	 * Provisional Total
	 * <br/>
	 * The provisional total for this opportunity
	 **/
	private Long total;
	/**
	 * Payment Terms
	 * <br/>
	 * The payment terms of the quote
	 **/
	private String paymentTerms;
	/**
	 * Freight Terms
	 * <br/>
	 * The freight terms of the quote
	 **/
	private String freightTerms;
	/**
	 * Bill To Street
	 * <br/>
	 * The quote's billing street and number
	 **/
	private String streetBill;
	/**
	 * Bill To City
	 * <br/>
	 * The quote's billing city
	 **/
	private String cityBill;
	/**
	 * Bill To State or Province
	 * <br/>
	 * The quote's billing state or province
	 **/
	private String stateBill;
	/**
	 * Bill To ZIP or Postal Code
	 * <br/>
	 * The quote's billing ZIP or postal code
	 **/
	private String postCodeBill;
	/**
	 * Bill To Country or Region
	 * <br/>
	 * The quote's billing country or region
	 **/
	private String countryBill;
	/**
	 * Shipping Method
	 * <br/>
	 * The shipping method for this quote
	 **/
	private String shippingMethod;
	/**
	 * Ship To Street
	 * <br/>
	 * The quote's shipping street and number
	 **/
	private String streetShip;
	/**
	 * Ship To City
	 * <br/>
	 * The quote's shipping city
	 **/
	private String cityShip;
	/**
	 * Ship To State or Province
	 * <br/>
	 * The quote's shipping state or province
	 **/
	private String stateShip;
	/**
	 * Ship To ZIP or Postal Code
	 * <br/>
	 * The quote's shipping ZIP or postal code
	 **/
	private String postCodeShip;
	/**
	 * Ship To Country or Region
	 * <br/>
	 * The quote's shipping country or region
	 **/
	private String countryShip;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Quote.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Quote.DOCUMENT_NAME;
	}

	public static QuoteExtension newInstance() {
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
														"{quoteId}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Quote) && 
					this.getBizId().equals(((Quote) o).getBizId()));
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
	 * {@link #quoteId} accessor.
	 * @return	The value.
	 **/
	public String getQuoteId() {
		return quoteId;
	}

	/**
	 * {@link #quoteId} mutator.
	 * @param quoteId	The new value.
	 **/
	@XmlElement
	public void setQuoteId(String quoteId) {
		preset(quoteIdPropertyName, quoteId);
		this.quoteId = quoteId;
	}

	/**
	 * {@link #opportunity} accessor.
	 * @return	The value.
	 **/
	public OpportunityExtension getOpportunity() {
		return opportunity;
	}

	/**
	 * {@link #opportunity} mutator.
	 * @param opportunity	The new value.
	 **/
	@XmlElement
	public void setOpportunity(OpportunityExtension opportunity) {
		preset(opportunityPropertyName, opportunity);
		this.opportunity = opportunity;
	}

	/**
	 * {@link #account} accessor.
	 * @return	The value.
	 **/
	public AccountExtension getAccount() {
		return account;
	}

	/**
	 * {@link #account} mutator.
	 * @param account	The new value.
	 **/
	@XmlElement
	public void setAccount(AccountExtension account) {
		preset(accountPropertyName, account);
		this.account = account;
	}

	/**
	 * {@link #expireDate} accessor.
	 * @return	The value.
	 **/
	public DateOnly getExpireDate() {
		return expireDate;
	}

	/**
	 * {@link #expireDate} mutator.
	 * @param expireDate	The new value.
	 **/
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	@XmlElement
	public void setExpireDate(DateOnly expireDate) {
		preset(expireDatePropertyName, expireDate);
		this.expireDate = expireDate;
	}

	/**
	 * {@link #status} accessor.
	 * @return	The value.
	 **/
	public Configuration getStatus() {
		return status;
	}

	/**
	 * {@link #status} mutator.
	 * @param status	The new value.
	 **/
	@XmlElement
	public void setStatus(Configuration status) {
		preset(statusPropertyName, status);
		this.status = status;
	}

	/**
	 * {@link #statusReason} accessor.
	 * @return	The value.
	 **/
	public String getStatusReason() {
		return statusReason;
	}

	/**
	 * {@link #statusReason} mutator.
	 * @param statusReason	The new value.
	 **/
	@XmlElement
	public void setStatusReason(String statusReason) {
		preset(statusReasonPropertyName, statusReason);
		this.statusReason = statusReason;
	}

	/**
	 * {@link #description} accessor.
	 * @return	The value.
	 **/
	public String getDescription() {
		return description;
	}

	/**
	 * {@link #description} mutator.
	 * @param description	The new value.
	 **/
	@XmlElement
	public void setDescription(String description) {
		preset(descriptionPropertyName, description);
		this.description = description;
	}

	/**
	 * {@link #total} accessor.
	 * @return	The value.
	 **/
	public Long getTotal() {
		return total;
	}

	/**
	 * {@link #total} mutator.
	 * @param total	The new value.
	 **/
	@XmlElement
	public void setTotal(Long total) {
		preset(totalPropertyName, total);
		this.total = total;
	}

	/**
	 * {@link #paymentTerms} accessor.
	 * @return	The value.
	 **/
	public String getPaymentTerms() {
		return paymentTerms;
	}

	/**
	 * {@link #paymentTerms} mutator.
	 * @param paymentTerms	The new value.
	 **/
	@XmlElement
	public void setPaymentTerms(String paymentTerms) {
		preset(paymentTermsPropertyName, paymentTerms);
		this.paymentTerms = paymentTerms;
	}

	/**
	 * {@link #freightTerms} accessor.
	 * @return	The value.
	 **/
	public String getFreightTerms() {
		return freightTerms;
	}

	/**
	 * {@link #freightTerms} mutator.
	 * @param freightTerms	The new value.
	 **/
	@XmlElement
	public void setFreightTerms(String freightTerms) {
		preset(freightTermsPropertyName, freightTerms);
		this.freightTerms = freightTerms;
	}

	/**
	 * {@link #streetBill} accessor.
	 * @return	The value.
	 **/
	public String getStreetBill() {
		return streetBill;
	}

	/**
	 * {@link #streetBill} mutator.
	 * @param streetBill	The new value.
	 **/
	@XmlElement
	public void setStreetBill(String streetBill) {
		preset(streetBillPropertyName, streetBill);
		this.streetBill = streetBill;
	}

	/**
	 * {@link #cityBill} accessor.
	 * @return	The value.
	 **/
	public String getCityBill() {
		return cityBill;
	}

	/**
	 * {@link #cityBill} mutator.
	 * @param cityBill	The new value.
	 **/
	@XmlElement
	public void setCityBill(String cityBill) {
		preset(cityBillPropertyName, cityBill);
		this.cityBill = cityBill;
	}

	/**
	 * {@link #stateBill} accessor.
	 * @return	The value.
	 **/
	public String getStateBill() {
		return stateBill;
	}

	/**
	 * {@link #stateBill} mutator.
	 * @param stateBill	The new value.
	 **/
	@XmlElement
	public void setStateBill(String stateBill) {
		preset(stateBillPropertyName, stateBill);
		this.stateBill = stateBill;
	}

	/**
	 * {@link #postCodeBill} accessor.
	 * @return	The value.
	 **/
	public String getPostCodeBill() {
		return postCodeBill;
	}

	/**
	 * {@link #postCodeBill} mutator.
	 * @param postCodeBill	The new value.
	 **/
	@XmlElement
	public void setPostCodeBill(String postCodeBill) {
		preset(postCodeBillPropertyName, postCodeBill);
		this.postCodeBill = postCodeBill;
	}

	/**
	 * {@link #countryBill} accessor.
	 * @return	The value.
	 **/
	public String getCountryBill() {
		return countryBill;
	}

	/**
	 * {@link #countryBill} mutator.
	 * @param countryBill	The new value.
	 **/
	@XmlElement
	public void setCountryBill(String countryBill) {
		preset(countryBillPropertyName, countryBill);
		this.countryBill = countryBill;
	}

	/**
	 * {@link #shippingMethod} accessor.
	 * @return	The value.
	 **/
	public String getShippingMethod() {
		return shippingMethod;
	}

	/**
	 * {@link #shippingMethod} mutator.
	 * @param shippingMethod	The new value.
	 **/
	@XmlElement
	public void setShippingMethod(String shippingMethod) {
		preset(shippingMethodPropertyName, shippingMethod);
		this.shippingMethod = shippingMethod;
	}

	/**
	 * {@link #streetShip} accessor.
	 * @return	The value.
	 **/
	public String getStreetShip() {
		return streetShip;
	}

	/**
	 * {@link #streetShip} mutator.
	 * @param streetShip	The new value.
	 **/
	@XmlElement
	public void setStreetShip(String streetShip) {
		preset(streetShipPropertyName, streetShip);
		this.streetShip = streetShip;
	}

	/**
	 * {@link #cityShip} accessor.
	 * @return	The value.
	 **/
	public String getCityShip() {
		return cityShip;
	}

	/**
	 * {@link #cityShip} mutator.
	 * @param cityShip	The new value.
	 **/
	@XmlElement
	public void setCityShip(String cityShip) {
		preset(cityShipPropertyName, cityShip);
		this.cityShip = cityShip;
	}

	/**
	 * {@link #stateShip} accessor.
	 * @return	The value.
	 **/
	public String getStateShip() {
		return stateShip;
	}

	/**
	 * {@link #stateShip} mutator.
	 * @param stateShip	The new value.
	 **/
	@XmlElement
	public void setStateShip(String stateShip) {
		preset(stateShipPropertyName, stateShip);
		this.stateShip = stateShip;
	}

	/**
	 * {@link #postCodeShip} accessor.
	 * @return	The value.
	 **/
	public String getPostCodeShip() {
		return postCodeShip;
	}

	/**
	 * {@link #postCodeShip} mutator.
	 * @param postCodeShip	The new value.
	 **/
	@XmlElement
	public void setPostCodeShip(String postCodeShip) {
		preset(postCodeShipPropertyName, postCodeShip);
		this.postCodeShip = postCodeShip;
	}

	/**
	 * {@link #countryShip} accessor.
	 * @return	The value.
	 **/
	public String getCountryShip() {
		return countryShip;
	}

	/**
	 * {@link #countryShip} mutator.
	 * @param countryShip	The new value.
	 **/
	@XmlElement
	public void setCountryShip(String countryShip) {
		preset(countryShipPropertyName, countryShip);
		this.countryShip = countryShip;
	}

	/**
	 * opportunitySelected
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isOpportunitySelected() {
		return (opportunity != null);
	}

	/**
	 * {@link #isOpportunitySelected} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotOpportunitySelected() {
		return (! isOpportunitySelected());
	}

	/**
	 * uploadSelected
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isUploadSelected() {
		return (getAccount() != null && getAccount().getInteractionType() != null && getAccount().getInteractionType().name() == "upload");
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
