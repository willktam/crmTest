package modules.products.domain;

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
 * Product Price List
 * 
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class ProductPriceList extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "products";
	/** @hidden */
	public static final String DOCUMENT_NAME = "ProductPriceList";

	/** @hidden */
	public static final String namePropertyName = "name";
	/** @hidden */
	public static final String descriptionPropertyName = "description";
	/** @hidden */
	public static final String retailPricePropertyName = "retailPrice";
	/** @hidden */
	public static final String bulkPricePropertyName = "bulkPrice";
	/** @hidden */
	public static final String startDatePropertyName = "startDate";
	/** @hidden */
	public static final String endDatePropertyName = "endDate";
	/** @hidden */
	public static final String currencyPropertyName = "currency";

	/**
	 * Price List Name
	 * <br/>
	 * The price list name
	 **/
	private String name;
	/**
	 * Description
	 * <br/>
	 * A description of the price list
	 **/
	private String description;
	/**
	 * Retail Price Per Unit
	 * <br/>
	 * The retail price of the product
	 **/
	private Long retailPrice;
	/**
	 * Bulk Price Per Unit
	 * <br/>
	 * The retail price of the product
	 **/
	private Long bulkPrice;
	/**
	 * Start Date
	 * <br/>
	 * The start date of the price list
	 **/
	private DateOnly startDate;
	/**
	 * End Date
	 * <br/>
	 * The end date of the price list
	 **/
	private DateOnly endDate;
	/**
	 * Currency
	 * <br/>
	 * The currency used for this price list
	 **/
	private String currency;

	@Override
	@XmlTransient
	public String getBizModule() {
		return ProductPriceList.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return ProductPriceList.DOCUMENT_NAME;
	}

	public static ProductPriceList newInstance() {
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
		return ((o instanceof ProductPriceList) && 
					this.getBizId().equals(((ProductPriceList) o).getBizId()));
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
	 * {@link #retailPrice} accessor.
	 * @return	The value.
	 **/
	public Long getRetailPrice() {
		return retailPrice;
	}

	/**
	 * {@link #retailPrice} mutator.
	 * @param retailPrice	The new value.
	 **/
	@XmlElement
	public void setRetailPrice(Long retailPrice) {
		preset(retailPricePropertyName, retailPrice);
		this.retailPrice = retailPrice;
	}

	/**
	 * {@link #bulkPrice} accessor.
	 * @return	The value.
	 **/
	public Long getBulkPrice() {
		return bulkPrice;
	}

	/**
	 * {@link #bulkPrice} mutator.
	 * @param bulkPrice	The new value.
	 **/
	@XmlElement
	public void setBulkPrice(Long bulkPrice) {
		preset(bulkPricePropertyName, bulkPrice);
		this.bulkPrice = bulkPrice;
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
	 * {@link #currency} accessor.
	 * @return	The value.
	 **/
	public String getCurrency() {
		return currency;
	}

	/**
	 * {@link #currency} mutator.
	 * @param currency	The new value.
	 **/
	@XmlElement
	public void setCurrency(String currency) {
		preset(currencyPropertyName, currency);
		this.currency = currency;
	}
}
