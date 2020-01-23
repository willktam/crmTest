package modules.sales.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractTransientBean;

/**
 * Lead Map
 * 
 * @stereotype "transient"
 */
@XmlType
@XmlRootElement
public class LeadMap extends AbstractTransientBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "sales";
	/** @hidden */
	public static final String DOCUMENT_NAME = "LeadMap";



	@Override
	@XmlTransient
	public String getBizModule() {
		return LeadMap.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return LeadMap.DOCUMENT_NAME;
	}

	public static LeadMap newInstance() {
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
		return toString();

	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof LeadMap) && 
					this.getBizId().equals(((LeadMap) o).getBizId()));
	}
}
