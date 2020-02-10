package modules.customers.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.customers.Account.AccountExtension;
import modules.customers.AccountDashboard.AccountDashboardExtension;
import modules.customers.Interaction.InteractionExtension;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateTime;
import org.skyve.impl.domain.AbstractTransientBean;
import org.skyve.impl.domain.ChangeTrackingArrayList;
import org.skyve.impl.domain.types.jaxb.DateTimeMapper;

/**
 * Account Dashboard
 * 
 * @navhas n account 0..1 Account
 * @navhas n interactions 0..n Interaction
 * @stereotype "transient"
 */
@XmlType
@XmlRootElement
public class AccountDashboard extends AbstractTransientBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "customers";
	/** @hidden */
	public static final String DOCUMENT_NAME = "AccountDashboard";

	/** @hidden */
	public static final String accountPropertyName = "account";
	/** @hidden */
	public static final String interactionsPropertyName = "interactions";
	/** @hidden */
	public static final String noOpportunityPropertyName = "noOpportunity";
	/** @hidden */
	public static final String noQuotePropertyName = "noQuote";
	/** @hidden */
	public static final String noOrderPropertyName = "noOrder";
	/** @hidden */
	public static final String noInvoicePropertyName = "noInvoice";
	/** @hidden */
	public static final String dateOpportunityPropertyName = "dateOpportunity";
	/** @hidden */
	public static final String dateQuotePropertyName = "dateQuote";
	/** @hidden */
	public static final String dateOrderPropertyName = "dateOrder";
	/** @hidden */
	public static final String dateInvoicePropertyName = "dateInvoice";
	/** @hidden */
	public static final String flowbarPropertyName = "flowbar";
	/** @hidden */
	public static final String actionTemplatePropertyName = "actionTemplate";

	/**
	 * Account
	 **/
	private AccountExtension account = null;
	/**
	 * Interactions
	 **/
	private List<InteractionExtension> interactions = new ChangeTrackingArrayList<>("interactions", this);
	/**
	 * Number of Opportunities
	 **/
	private transient Integer noOpportunity = new Integer(0);
	/**
	 * Number of Quotes
	 **/
	private transient Integer noQuote = new Integer(0);
	/**
	 * Number of Orders
	 **/
	private transient Integer noOrder = new Integer(0);
	/**
	 * Number of Invoices
	 **/
	private transient Integer noInvoice = new Integer(0);
	/**
	 * Date Opportunity Updated
	 **/
	private transient DateTime dateOpportunity;
	/**
	 * Date Quote Updated
	 **/
	private transient DateTime dateQuote;
	/**
	 * Date Order Updated
	 **/
	private transient DateTime dateOrder;
	/**
	 * Date Invoice Updated
	 **/
	private transient DateTime dateInvoice;
	/**
	 * Flowbar
	 **/
	private String flowbar;
	/**
	 * Suggested Actions
	 **/
	private String actionTemplate;

	@Override
	@XmlTransient
	public String getBizModule() {
		return AccountDashboard.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return AccountDashboard.DOCUMENT_NAME;
	}

	public static AccountDashboardExtension newInstance() {
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
														"Account Dashboard - {account}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof AccountDashboard) && 
					this.getBizId().equals(((AccountDashboard) o).getBizId()));
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
	 * {@link #noOpportunity} accessor.
	 * @return	The value.
	 **/
	public Integer getNoOpportunity() {
		return noOpportunity;
	}

	/**
	 * {@link #noOpportunity} mutator.
	 * @param noOpportunity	The new value.
	 **/
	@XmlElement
	public void setNoOpportunity(Integer noOpportunity) {
		preset(noOpportunityPropertyName, noOpportunity);
		this.noOpportunity = noOpportunity;
	}

	/**
	 * {@link #noQuote} accessor.
	 * @return	The value.
	 **/
	public Integer getNoQuote() {
		return noQuote;
	}

	/**
	 * {@link #noQuote} mutator.
	 * @param noQuote	The new value.
	 **/
	@XmlElement
	public void setNoQuote(Integer noQuote) {
		preset(noQuotePropertyName, noQuote);
		this.noQuote = noQuote;
	}

	/**
	 * {@link #noOrder} accessor.
	 * @return	The value.
	 **/
	public Integer getNoOrder() {
		return noOrder;
	}

	/**
	 * {@link #noOrder} mutator.
	 * @param noOrder	The new value.
	 **/
	@XmlElement
	public void setNoOrder(Integer noOrder) {
		preset(noOrderPropertyName, noOrder);
		this.noOrder = noOrder;
	}

	/**
	 * {@link #noInvoice} accessor.
	 * @return	The value.
	 **/
	public Integer getNoInvoice() {
		return noInvoice;
	}

	/**
	 * {@link #noInvoice} mutator.
	 * @param noInvoice	The new value.
	 **/
	@XmlElement
	public void setNoInvoice(Integer noInvoice) {
		preset(noInvoicePropertyName, noInvoice);
		this.noInvoice = noInvoice;
	}

	/**
	 * {@link #dateOpportunity} accessor.
	 * @return	The value.
	 **/
	public DateTime getDateOpportunity() {
		return dateOpportunity;
	}

	/**
	 * {@link #dateOpportunity} mutator.
	 * @param dateOpportunity	The new value.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(DateTimeMapper.class)
	@XmlElement
	public void setDateOpportunity(DateTime dateOpportunity) {
		preset(dateOpportunityPropertyName, dateOpportunity);
		this.dateOpportunity = dateOpportunity;
	}

	/**
	 * {@link #dateQuote} accessor.
	 * @return	The value.
	 **/
	public DateTime getDateQuote() {
		return dateQuote;
	}

	/**
	 * {@link #dateQuote} mutator.
	 * @param dateQuote	The new value.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(DateTimeMapper.class)
	@XmlElement
	public void setDateQuote(DateTime dateQuote) {
		preset(dateQuotePropertyName, dateQuote);
		this.dateQuote = dateQuote;
	}

	/**
	 * {@link #dateOrder} accessor.
	 * @return	The value.
	 **/
	public DateTime getDateOrder() {
		return dateOrder;
	}

	/**
	 * {@link #dateOrder} mutator.
	 * @param dateOrder	The new value.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(DateTimeMapper.class)
	@XmlElement
	public void setDateOrder(DateTime dateOrder) {
		preset(dateOrderPropertyName, dateOrder);
		this.dateOrder = dateOrder;
	}

	/**
	 * {@link #dateInvoice} accessor.
	 * @return	The value.
	 **/
	public DateTime getDateInvoice() {
		return dateInvoice;
	}

	/**
	 * {@link #dateInvoice} mutator.
	 * @param dateInvoice	The new value.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(DateTimeMapper.class)
	@XmlElement
	public void setDateInvoice(DateTime dateInvoice) {
		preset(dateInvoicePropertyName, dateInvoice);
		this.dateInvoice = dateInvoice;
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
	 * {@link #actionTemplate} accessor.
	 * @return	The value.
	 **/
	public String getActionTemplate() {
		return actionTemplate;
	}

	/**
	 * {@link #actionTemplate} mutator.
	 * @param actionTemplate	The new value.
	 **/
	@XmlElement
	public void setActionTemplate(String actionTemplate) {
		this.actionTemplate = actionTemplate;
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
}
