package modules.crmTest.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractPersistentBean;

/**
 * Customer
 * 
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Customer extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "crmTest";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Customer";

	/** @hidden */
	public static final String namePropertyName = "name";
	/** @hidden */
	public static final String agePropertyName = "age";
	/** @hidden */
	public static final String addressPropertyName = "address";

	/**
	 * Name
	 * <br/>
	 * Your name
	 **/
	private String name;
	/**
	 * Age
	 * <br/>
	 * Your Age
	 **/
	private Integer age;
	/**
	 * Address
	 * <br/>
	 * Your physical address
	 **/
	private String address;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Customer.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Customer.DOCUMENT_NAME;
	}

	public static Customer newInstance() {
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
														"Customer - {name}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Customer) && 
					this.getBizId().equals(((Customer) o).getBizId()));
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
	 * {@link #age} accessor.
	 * @return	The value.
	 **/
	public Integer getAge() {
		return age;
	}

	/**
	 * {@link #age} mutator.
	 * @param age	The new value.
	 **/
	@XmlElement
	public void setAge(Integer age) {
		preset(agePropertyName, age);
		this.age = age;
	}

	/**
	 * {@link #address} accessor.
	 * @return	The value.
	 **/
	public String getAddress() {
		return address;
	}

	/**
	 * {@link #address} mutator.
	 * @param address	The new value.
	 **/
	@XmlElement
	public void setAddress(String address) {
		preset(addressPropertyName, address);
		this.address = address;
	}
}
