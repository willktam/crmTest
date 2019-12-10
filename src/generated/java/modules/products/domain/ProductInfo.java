package modules.products.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractPersistentBean;

/**
 * Product Info
 * 
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class ProductInfo extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "products";
	/** @hidden */
	public static final String DOCUMENT_NAME = "ProductInfo";

	/** @hidden */
	public static final String namePropertyName = "name";
	/** @hidden */
	public static final String productIdPropertyName = "productId";
	/** @hidden */
	public static final String descriptionPropertyName = "description";

	/**
	 * Name
	 * <br/>
	 * Product name
	 **/
	private String name;
	/**
	 * Product Id
	 * <br/>
	 * The products id number
	 **/
	private String productId;
	/**
	 * Description
	 * <br/>
	 * A description of the product
	 **/
	private String description;

	@Override
	@XmlTransient
	public String getBizModule() {
		return ProductInfo.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return ProductInfo.DOCUMENT_NAME;
	}

	public static ProductInfo newInstance() {
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
														"Product Info - {name}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof ProductInfo) && 
					this.getBizId().equals(((ProductInfo) o).getBizId()));
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
	 * {@link #productId} accessor.
	 * @return	The value.
	 **/
	public String getProductId() {
		return productId;
	}

	/**
	 * {@link #productId} mutator.
	 * @param productId	The new value.
	 **/
	@XmlElement
	public void setProductId(String productId) {
		preset(productIdPropertyName, productId);
		this.productId = productId;
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
}
