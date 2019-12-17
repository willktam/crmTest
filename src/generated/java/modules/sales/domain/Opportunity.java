package modules.sales.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import modules.customers.domain.Account;
import modules.customers.domain.ContactDetail;
import modules.products.domain.ProductInfo;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractPersistentBean;

/**
 * Opportunity
 * 
 * @navhas n product 1 ProductInfo
 * @navhas n contact 1 ContactDetail
 * @navhas n account 1 Account
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Opportunity extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "sales";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Opportunity";

	/** @hidden */
	public static final String topicPropertyName = "topic";
	/** @hidden */
	public static final String contactPropertyName = "contact";
	/** @hidden */
	public static final String accountPropertyName = "account";
	/** @hidden */
	public static final String descriptionPropertyName = "description";
	/** @hidden */
	public static final String productPropertyName = "product";

	/**
	 * Topic
	 **/
	private String topic;
	/**
	 * Contact
	 * <br/>
	 * The primary contact of the opportunity
	 **/
	private ContactDetail contact = null;
	/**
	 * Account
	 * <br/>
	 * The account of the opportunity
	 **/
	private Account account = null;
	/**
	 * Description
	 * <br/>
	 * A description of the opportunity
	 **/
	private String description;
	/**
	 * Product
	 * <br/>
	 * The product of the opportunity
	 **/
	private ProductInfo product = null;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Opportunity.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Opportunity.DOCUMENT_NAME;
	}

	public static Opportunity newInstance() {
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
														"{topic}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Opportunity) && 
					this.getBizId().equals(((Opportunity) o).getBizId()));
	}

	/**
	 * {@link #topic} accessor.
	 * @return	The value.
	 **/
	public String getTopic() {
		return topic;
	}

	/**
	 * {@link #topic} mutator.
	 * @param topic	The new value.
	 **/
	@XmlElement
	public void setTopic(String topic) {
		preset(topicPropertyName, topic);
		this.topic = topic;
	}

	/**
	 * {@link #contact} accessor.
	 * @return	The value.
	 **/
	public ContactDetail getContact() {
		return contact;
	}

	/**
	 * {@link #contact} mutator.
	 * @param contact	The new value.
	 **/
	@XmlElement
	public void setContact(ContactDetail contact) {
		preset(contactPropertyName, contact);
		this.contact = contact;
	}

	/**
	 * {@link #account} accessor.
	 * @return	The value.
	 **/
	public Account getAccount() {
		return account;
	}

	/**
	 * {@link #account} mutator.
	 * @param account	The new value.
	 **/
	@XmlElement
	public void setAccount(Account account) {
		preset(accountPropertyName, account);
		this.account = account;
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
	 * {@link #product} accessor.
	 * @return	The value.
	 **/
	public ProductInfo getProduct() {
		return product;
	}

	/**
	 * {@link #product} mutator.
	 * @param product	The new value.
	 **/
	@XmlElement
	public void setProduct(ProductInfo product) {
		preset(productPropertyName, product);
		this.product = product;
	}
}
