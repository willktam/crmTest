package modules.sales.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.Enumeration;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.metadata.model.document.Bizlet.DomainValue;

/**
 * Configuration
 * 
 * @depend - - - StatusOption
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Configuration extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "sales";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Configuration";

	/** @hidden */
	public static final String statusOptionPropertyName = "statusOption";
	/** @hidden */
	public static final String fieldPropertyName = "field";

	/**
	 * Choose Status
	 * <br/>
	 * The status to add a new field for
	 **/
	@XmlEnum
	public static enum StatusOption implements Enumeration {
		leadType("Lead Type", "Lead Type"),
		quoteStatus("Quote Status", "Quote Status"),
		orderStatus("Order Status", "Order Status"),
		invoiceStatus("Invoice Status", "Invoice Status");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private StatusOption(String code, String description) {
			this.code = code;
			this.description = description;
			this.domainValue = new DomainValue(code, description);
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toDescription() {
			return description;
		}

		@Override
		public DomainValue toDomainValue() {
			return domainValue;
		}

		public static StatusOption fromCode(String code) {
			StatusOption result = null;

			for (StatusOption value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static StatusOption fromDescription(String description) {
			StatusOption result = null;

			for (StatusOption value : values()) {
				if (value.description.equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				StatusOption[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (StatusOption value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Choose Status
	 * <br/>
	 * The status to add a new field for
	 **/
	private StatusOption statusOption;
	/**
	 * Field
	 * <br/>
	 * The new field for the chosen option
	 **/
	private String field;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Configuration.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Configuration.DOCUMENT_NAME;
	}

	public static Configuration newInstance() {
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
														"{statusOption}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Configuration) && 
					this.getBizId().equals(((Configuration) o).getBizId()));
	}

	/**
	 * {@link #statusOption} accessor.
	 * @return	The value.
	 **/
	public StatusOption getStatusOption() {
		return statusOption;
	}

	/**
	 * {@link #statusOption} mutator.
	 * @param statusOption	The new value.
	 **/
	@XmlElement
	public void setStatusOption(StatusOption statusOption) {
		preset(statusOptionPropertyName, statusOption);
		this.statusOption = statusOption;
	}

	/**
	 * {@link #field} accessor.
	 * @return	The value.
	 **/
	public String getField() {
		return field;
	}

	/**
	 * {@link #field} mutator.
	 * @param field	The new value.
	 **/
	@XmlElement
	public void setField(String field) {
		preset(fieldPropertyName, field);
		this.field = field;
	}
}
