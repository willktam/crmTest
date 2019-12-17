package modules.products.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractPersistentBean;

/**
 * Product Unit Group
 * 
 * @navhas n productPriceList 0..1 ProductPriceList
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class ProductUnitGroup extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "products";
	/** @hidden */
	public static final String DOCUMENT_NAME = "ProductUnitGroup";

	/** @hidden */
	public static final String namePropertyName = "name";
	/** @hidden */
	public static final String descriptionPropertyName = "description";
	/** @hidden */
	public static final String primaryUnitPropertyName = "primaryUnit";
	/** @hidden */
	public static final String productPriceListPropertyName = "productPriceList";

	/**
	 * Unit Group Name
	 * <br/>
	 * The unit group name
	 **/
	private String name;
	/**
	 * Description
	 * <br/>
	 * A description of the unit group
	 **/
	private String description;
	/**
	 * Primary Unit
	 * <br/>
	 * The lowest unit of measure of the product, e.g. Piece
	 **/
	private String primaryUnit;
	/**
	 * Product Price List
	 * <br/>
	 * The price list of the unit group
	 **/
	private ProductPriceList productPriceList = null;

	@Override
	@XmlTransient
	public String getBizModule() {
		return ProductUnitGroup.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return ProductUnitGroup.DOCUMENT_NAME;
	}

	public static ProductUnitGroup newInstance() {
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
														"{name}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof ProductUnitGroup) && 
					this.getBizId().equals(((ProductUnitGroup) o).getBizId()));
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
	 * {@link #primaryUnit} accessor.
	 * @return	The value.
	 **/
	public String getPrimaryUnit() {
		return primaryUnit;
	}

	/**
	 * {@link #primaryUnit} mutator.
	 * @param primaryUnit	The new value.
	 **/
	@XmlElement
	public void setPrimaryUnit(String primaryUnit) {
		preset(primaryUnitPropertyName, primaryUnit);
		this.primaryUnit = primaryUnit;
	}

	/**
	 * {@link #productPriceList} accessor.
	 * @return	The value.
	 **/
	public ProductPriceList getProductPriceList() {
		return productPriceList;
	}

	/**
	 * {@link #productPriceList} mutator.
	 * @param productPriceList	The new value.
	 **/
	@XmlElement
	public void setProductPriceList(ProductPriceList productPriceList) {
		preset(productPriceListPropertyName, productPriceList);
		this.productPriceList = productPriceList;
	}
}
