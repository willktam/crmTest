package modules.admin.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.Timestamp;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.TimestampMapper;

/**
 * Job
 * 
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Job extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "admin";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Job";

	/** @hidden */
	public static final String startTimePropertyName = "startTime";
	/** @hidden */
	public static final String endTimePropertyName = "endTime";
	/** @hidden */
	public static final String displayNamePropertyName = "displayName";
	/** @hidden */
	public static final String percentCompletePropertyName = "percentComplete";
	/** @hidden */
	public static final String statusPropertyName = "status";
	/** @hidden */
	public static final String logPropertyName = "log";

	/**
	 * Start Time
	 **/
	private Timestamp startTime;
	/**
	 * End Time
	 **/
	private Timestamp endTime;
	/**
	 * Name
	 **/
	private String displayName;
	/**
	 * Percent Complete
	 **/
	private Integer percentComplete;
	/**
	 * Status
	 **/
	private String status;
	/**
	 * Log
	 **/
	private String log;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Job.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Job.DOCUMENT_NAME;
	}

	public static Job newInstance() {
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
														"Job {displayName}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Job) && 
					this.getBizId().equals(((Job) o).getBizId()));
	}

	/**
	 * {@link #startTime} accessor.
	 * @return	The value.
	 **/
	public Timestamp getStartTime() {
		return startTime;
	}

	/**
	 * {@link #startTime} mutator.
	 * @param startTime	The new value.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(TimestampMapper.class)
	@XmlElement
	public void setStartTime(Timestamp startTime) {
		preset(startTimePropertyName, startTime);
		this.startTime = startTime;
	}

	/**
	 * {@link #endTime} accessor.
	 * @return	The value.
	 **/
	public Timestamp getEndTime() {
		return endTime;
	}

	/**
	 * {@link #endTime} mutator.
	 * @param endTime	The new value.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(TimestampMapper.class)
	@XmlElement
	public void setEndTime(Timestamp endTime) {
		preset(endTimePropertyName, endTime);
		this.endTime = endTime;
	}

	/**
	 * {@link #displayName} accessor.
	 * @return	The value.
	 **/
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * {@link #displayName} mutator.
	 * @param displayName	The new value.
	 **/
	@XmlElement
	public void setDisplayName(String displayName) {
		preset(displayNamePropertyName, displayName);
		this.displayName = displayName;
	}

	/**
	 * {@link #percentComplete} accessor.
	 * @return	The value.
	 **/
	public Integer getPercentComplete() {
		return percentComplete;
	}

	/**
	 * {@link #percentComplete} mutator.
	 * @param percentComplete	The new value.
	 **/
	@XmlElement
	public void setPercentComplete(Integer percentComplete) {
		preset(percentCompletePropertyName, percentComplete);
		this.percentComplete = percentComplete;
	}

	/**
	 * {@link #status} accessor.
	 * @return	The value.
	 **/
	public String getStatus() {
		return status;
	}

	/**
	 * {@link #status} mutator.
	 * @param status	The new value.
	 **/
	@XmlElement
	public void setStatus(String status) {
		preset(statusPropertyName, status);
		this.status = status;
	}

	/**
	 * {@link #log} accessor.
	 * @return	The value.
	 **/
	public String getLog() {
		return log;
	}

	/**
	 * {@link #log} mutator.
	 * @param log	The new value.
	 **/
	@XmlElement
	public void setLog(String log) {
		preset(logPropertyName, log);
		this.log = log;
	}
}
