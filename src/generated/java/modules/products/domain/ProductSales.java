package modules.products.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractPersistentBean;

/**
 * Product Sales
 * 
 * @navhas n product 1 ProductInfo
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class ProductSales extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "products";
	/** @hidden */
	public static final String DOCUMENT_NAME = "ProductSales";

	/** @hidden */
	public static final String productPropertyName = "product";
	/** @hidden */
	public static final String activePropertyName = "active";
	/** @hidden */
	public static final String numerOfSalesPropertyName = "numerOfSales";

	/**
	 * Product
	 **/
	private ProductInfo product = null;
	/**
	 * Active
	 * <br/>
	 * Is the sale of the product active
	 **/
	private Boolean active;
	/**
	 * Numer of Sales
	 * <br/>
	 * Number of sales for the product
	 **/
	private Long numerOfSales;

	@Override
	@XmlTransient
	public String getBizModule() {
		return ProductSales.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return ProductSales.DOCUMENT_NAME;
	}

	public static ProductSales newInstance() {
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
														"Product Sales - {product}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof ProductSales) && 
					this.getBizId().equals(((ProductSales) o).getBizId()));
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

	/**
	 * {@link #active} accessor.
	 * @return	The value.
	 **/
	public Boolean getActive() {
		return active;
	}

	/**
	 * {@link #active} mutator.
	 * @param active	The new value.
	 **/
	@XmlElement
	public void setActive(Boolean active) {
		preset(activePropertyName, active);
		this.active = active;
	}

	/**
	 * {@link #numerOfSales} accessor.
	 * @return	The value.
	 **/
	public Long getNumerOfSales() {
		return numerOfSales;
	}

	/**
	 * {@link #numerOfSales} mutator.
	 * @param numerOfSales	The new value.
	 **/
	@XmlElement
	public void setNumerOfSales(Long numerOfSales) {
		preset(numerOfSalesPropertyName, numerOfSales);
		this.numerOfSales = numerOfSales;
	}
}
