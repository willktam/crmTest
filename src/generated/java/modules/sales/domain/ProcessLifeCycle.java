package modules.sales.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.customers.Account.AccountExtension;
import modules.customers.ContactDetail.ContactDetailExtension;
import modules.sales.Invoice.InvoiceExtension;
import modules.sales.Lead.LeadExtension;
import modules.sales.Opportunity.OpportunityExtension;
import modules.sales.Order.OrderExtension;
import modules.sales.ProcessLifeCycle.ProcessLifeCycleExtension;
import modules.sales.Quote.QuoteExtension;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateOnly;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.DateOnlyMapper;

/**
 * Process Life Cycle
 * 
 * @navhas n quote 0..1 Quote
 * @navhas n contact 0..1 ContactDetail
 * @navhas n opportunity 0..1 Opportunity
 * @navhas n invoice 0..1 Invoice
 * @navhas n lead 0..1 Lead
 * @navhas n account 0..1 Account
 * @navhas n status 1 Configuration
 * @navhas n order 0..1 Order
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class ProcessLifeCycle extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "sales";
	/** @hidden */
	public static final String DOCUMENT_NAME = "ProcessLifeCycle";

	/** @hidden */
	public static final String processIdPropertyName = "processId";
	/** @hidden */
	public static final String statusPropertyName = "status";
	/** @hidden */
	public static final String startDatePropertyName = "startDate";
	/** @hidden */
	public static final String endDatePropertyName = "endDate";
	/** @hidden */
	public static final String contactPropertyName = "contact";
	/** @hidden */
	public static final String leadPropertyName = "lead";
	/** @hidden */
	public static final String accountPropertyName = "account";
	/** @hidden */
	public static final String opportunityPropertyName = "opportunity";
	/** @hidden */
	public static final String quotePropertyName = "quote";
	/** @hidden */
	public static final String orderPropertyName = "order";
	/** @hidden */
	public static final String invoicePropertyName = "invoice";
	/** @hidden */
	public static final String flowbarPropertyName = "flowbar";

	/**
	 * Process Life Cycle ID
	 * <br/>
	 * The ID for this process life cycle
	 **/
	private String processId;
	/**
	 * Process Status
	 * <br/>
	 * The current status of the process life cycle
	 **/
	private Configuration status = null;
	/**
	 * Start Date
	 * <br/>
	 * The date this life cycle began
	 **/
	private DateOnly startDate;
	/**
	 * End Date
	 * <br/>
	 * The end date for this life cycle
	 **/
	private DateOnly endDate;
	/**
	 * Contact
	 **/
	private ContactDetailExtension contact = null;
	/**
	 * Lead
	 **/
	private LeadExtension lead = null;
	/**
	 * Account
	 **/
	private AccountExtension account = null;
	/**
	 * Opportunity
	 **/
	private OpportunityExtension opportunity = null;
	/**
	 * Quote
	 **/
	private QuoteExtension quote = null;
	/**
	 * Order
	 **/
	private OrderExtension order = null;
	/**
	 * Invoice
	 **/
	private InvoiceExtension invoice = null;
	/**
	 * Flowbar
	 **/
	private String flowbar;

	@Override
	@XmlTransient
	public String getBizModule() {
		return ProcessLifeCycle.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return ProcessLifeCycle.DOCUMENT_NAME;
	}

	public static ProcessLifeCycleExtension newInstance() {
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
														"{processId}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof ProcessLifeCycle) && 
					this.getBizId().equals(((ProcessLifeCycle) o).getBizId()));
	}

	/**
	 * {@link #processId} accessor.
	 * @return	The value.
	 **/
	public String getProcessId() {
		return processId;
	}

	/**
	 * {@link #processId} mutator.
	 * @param processId	The new value.
	 **/
	@XmlElement
	public void setProcessId(String processId) {
		preset(processIdPropertyName, processId);
		this.processId = processId;
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
	 * {@link #endDate} accessor.
	 * @return	The value.
	 **/
	public DateOnly getEndDate() {
		return endDate;
	}

	/**
	 * {@link #endDate} mutator.
	 * @param endDate	The new value.
	 **/
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	@XmlElement
	public void setEndDate(DateOnly endDate) {
		preset(endDatePropertyName, endDate);
		this.endDate = endDate;
	}

	/**
	 * {@link #contact} accessor.
	 * @return	The value.
	 **/
	public ContactDetailExtension getContact() {
		return contact;
	}

	/**
	 * {@link #contact} mutator.
	 * @param contact	The new value.
	 **/
	@XmlElement
	public void setContact(ContactDetailExtension contact) {
		preset(contactPropertyName, contact);
		this.contact = contact;
	}

	/**
	 * {@link #lead} accessor.
	 * @return	The value.
	 **/
	public LeadExtension getLead() {
		return lead;
	}

	/**
	 * {@link #lead} mutator.
	 * @param lead	The new value.
	 **/
	@XmlElement
	public void setLead(LeadExtension lead) {
		preset(leadPropertyName, lead);
		this.lead = lead;
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
	 * {@link #quote} accessor.
	 * @return	The value.
	 **/
	public QuoteExtension getQuote() {
		return quote;
	}

	/**
	 * {@link #quote} mutator.
	 * @param quote	The new value.
	 **/
	@XmlElement
	public void setQuote(QuoteExtension quote) {
		preset(quotePropertyName, quote);
		this.quote = quote;
	}

	/**
	 * {@link #order} accessor.
	 * @return	The value.
	 **/
	public OrderExtension getOrder() {
		return order;
	}

	/**
	 * {@link #order} mutator.
	 * @param order	The new value.
	 **/
	@XmlElement
	public void setOrder(OrderExtension order) {
		preset(orderPropertyName, order);
		this.order = order;
	}

	/**
	 * {@link #invoice} accessor.
	 * @return	The value.
	 **/
	public InvoiceExtension getInvoice() {
		return invoice;
	}

	/**
	 * {@link #invoice} mutator.
	 * @param invoice	The new value.
	 **/
	@XmlElement
	public void setInvoice(InvoiceExtension invoice) {
		preset(invoicePropertyName, invoice);
		this.invoice = invoice;
	}

	/**
	 * {@link #flowbar} accessor.
	 * @return	The value.
	 **/
	public String getFlowbar() {
		return flowbar;
	}

	/**
	 * {@link #flowbar} mutator.
	 * @param flowbar	The new value.
	 **/
	@XmlElement
	public void setFlowbar(String flowbar) {
		this.flowbar = flowbar;
	}

	/**
	 * accountSelected
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isAccountSelected() {
		return (account != null);
	}

	/**
	 * {@link #isAccountSelected} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotAccountSelected() {
		return (! isAccountSelected());
	}

	/**
	 * contactSelected
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isContactSelected() {
		return (contact != null);
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
	 * invoiceSelected
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isInvoiceSelected() {
		return (invoice != null);
	}

	/**
	 * {@link #isInvoiceSelected} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotInvoiceSelected() {
		return (! isInvoiceSelected());
	}

	/**
	 * leadSelected
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isLeadSelected() {
		return (lead != null);
	}

	/**
	 * {@link #isLeadSelected} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotLeadSelected() {
		return (! isLeadSelected());
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
	 * orderSelected
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isOrderSelected() {
		return (order != null);
	}

	/**
	 * {@link #isOrderSelected} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotOrderSelected() {
		return (! isOrderSelected());
	}

	/**
	 * quoteSelected
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isQuoteSelected() {
		return (quote != null);
	}

	/**
	 * {@link #isQuoteSelected} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotQuoteSelected() {
		return (! isQuoteSelected());
	}
}
