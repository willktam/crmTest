package modules.admin.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractTransientBean;
import org.skyve.impl.domain.ChangeTrackingArrayList;

/**
 * System Dashboard
 * 
 * @navhas n status 0..n Generic
 * @stereotype "transient"
 */
@XmlType
@XmlRootElement
public class SystemDashboard extends AbstractTransientBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "admin";
	/** @hidden */
	public static final String DOCUMENT_NAME = "SystemDashboard";

	/** @hidden */
	public static final String statusPropertyName = "status";

	/**
	 * Status
	 **/
	private List<Generic> status = new ChangeTrackingArrayList<>("status", this);

	@Override
	@XmlTransient
	public String getBizModule() {
		return SystemDashboard.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return SystemDashboard.DOCUMENT_NAME;
	}

	public static SystemDashboard newInstance() {
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
		return ((o instanceof SystemDashboard) && 
					this.getBizId().equals(((SystemDashboard) o).getBizId()));
	}

	/**
	 * {@link #status} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<Generic> getStatus() {
		return status;
	}

	/**
	 * {@link #status} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public Generic getStatusElementById(String bizId) {
		return getElementById(status, bizId);
	}

	/**
	 * {@link #status} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setStatusElementById(String bizId, Generic element) {
		 setElementById(status, element);
	}
}
