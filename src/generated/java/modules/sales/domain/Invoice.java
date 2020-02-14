package modules.sales.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.customers.Account.AccountExtension;
import modules.sales.Invoice.InvoiceExtension;
import modules.sales.Order.OrderExtension;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateOnly;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.DateOnlyMapper;

/**
 * Invoice
 * 
 * @navhas n invoiceStatus 1 Configuration
 * @navhas n account 1 Account
 * @navhas n order 1 Order
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Invoice extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "sales";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Invoice";

	/** @hidden */
	public static final String invoiceIdPropertyName = "invoiceId";
	/** @hidden */
	public static final String namePropertyName = "name";
	/** @hidden */
	public static final String orderPropertyName = "order";
	/** @hidden */
	public static final String invoiceStatusPropertyName = "invoiceStatus";
	/** @hidden */
	public static final String descriptionPropertyName = "description";
	/** @hidden */
	public static final String deliveryDatePropertyName = "deliveryDate";
	/** @hidden */
	public static final String dueDatePropertyName = "dueDate";
	/** @hidden */
	public static final String accountPropertyName = "account";
	/** @hidden */
	public static final String totalPropertyName = "total";

	/**
	 * Invoice ID
	 * <br/>
	 * The ID for this invoice
	 **/
	private String invoiceId;
	/**
	 * Invoice Name
	 * <br/>
	 * The name of the invoice
	 **/
	private String name;
	/**
	 * Order ID
	 * <br/>
	 * The order associated with this invoice
	 **/
	private OrderExtension order = null;
	/**
	 * Invoice Status
	 * <br/>
	 * THe current status of this invoice
	 **/
	private Configuration invoiceStatus = null;
	/**
	 * Description
	 * <br/>
	 * A description of the invoice
	 **/
	private String description;
	/**
	 * Delivery Date
	 * <br/>
	 * The date the order is delivered
	 **/
	private DateOnly deliveryDate;
	/**
	 * Due Date
	 * <br/>
	 * The date the order is due
	 **/
	private DateOnly dueDate;
	/**
	 * Account
	 * <br/>
	 * The account for the invoice
	 **/
	private AccountExtension account = null;
	/**
	 * Total
	 * <br/>
	 * The total for this invoice
	 **/
	private Long total;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Invoice.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Invoice.DOCUMENT_NAME;
	}

	public static InvoiceExtension newInstance() {
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
														"{invoiceId}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Invoice) && 
					this.getBizId().equals(((Invoice) o).getBizId()));
	}

	/**
	 * {@link #invoiceId} accessor.
	 * @return	The value.
	 **/
	public String getInvoiceId() {
		return invoiceId;
	}

	/**
	 * {@link #invoiceId} mutator.
	 * @param invoiceId	The new value.
	 **/
	@XmlElement
	public void setInvoiceId(String invoiceId) {
		preset(invoiceIdPropertyName, invoiceId);
		this.invoiceId = invoiceId;
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
	 * {@link #invoiceStatus} accessor.
	 * @return	The value.
	 **/
	public Configuration getInvoiceStatus() {
		return invoiceStatus;
	}

	/**
	 * {@link #invoiceStatus} mutator.
	 * @param invoiceStatus	The new value.
	 **/
	@XmlElement
	public void setInvoiceStatus(Configuration invoiceStatus) {
		preset(invoiceStatusPropertyName, invoiceStatus);
		this.invoiceStatus = invoiceStatus;
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
	 * {@link #deliveryDate} accessor.
	 * @return	The value.
	 **/
	public DateOnly getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * {@link #deliveryDate} mutator.
	 * @param deliveryDate	The new value.
	 **/
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	@XmlElement
	public void setDeliveryDate(DateOnly deliveryDate) {
		preset(deliveryDatePropertyName, deliveryDate);
		this.deliveryDate = deliveryDate;
	}

	/**
	 * {@link #dueDate} accessor.
	 * @return	The value.
	 **/
	public DateOnly getDueDate() {
		return dueDate;
	}

	/**
	 * {@link #dueDate} mutator.
	 * @param dueDate	The new value.
	 **/
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	@XmlElement
	public void setDueDate(DateOnly dueDate) {
		preset(dueDatePropertyName, dueDate);
		this.dueDate = dueDate;
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
	 * uploadSelected
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isUploadSelected() {
		return (getAccount().getInteractionType() != null && getAccount().getInteractionType().name() == "upload");
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
