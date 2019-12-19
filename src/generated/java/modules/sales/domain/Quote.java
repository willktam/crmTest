package modules.sales.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateOnly;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.DateOnlyMapper;

/**
 * Quote
 * 
 * @navhas n opportunity 1 Opportunity
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
	public static final String expireDatePropertyName = "expireDate";
	/** @hidden */
	public static final String statusPropertyName = "status";
	/** @hidden */
	public static final String statusReasonPropertyName = "statusReason";
	/** @hidden */
	public static final String descriptionPropertyName = "description";
	/** @hidden */
	public static final String paymentTermsPropertyName = "paymentTerms";

	/**
	 * Name
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
	private Opportunity opportunity = null;
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
	private String status;
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
	 * Payment Terms
	 * <br/>
	 * The payment terms of the quote
	 **/
	private String paymentTerms;

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

	public static Quote newInstance() {
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
														"Quote - {name}",
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
	public Opportunity getOpportunity() {
		return opportunity;
	}

	/**
	 * {@link #opportunity} mutator.
	 * @param opportunity	The new value.
	 **/
	@XmlElement
	public void setOpportunity(Opportunity opportunity) {
		preset(opportunityPropertyName, opportunity);
		this.opportunity = opportunity;
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
	public String getStatus() {
		return status;
	}

	/**
	 * {@link #status} mutator.
	 * @param status	The new value.
	 **/
	@XmlElement
	public void setStatus(String status) {
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
}
