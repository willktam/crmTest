package modules.staff.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.admin.User.UserExtension;
import org.locationtech.jts.geom.Geometry;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateOnly;
import org.skyve.domain.types.Enumeration;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.DateOnlyMapper;
import org.skyve.impl.domain.types.jaxb.GeometryMapper;
import org.skyve.metadata.model.document.Bizlet.DomainValue;

/**
 * Staff List
 * 
 * @depend - - - SocialTitle
 * @navcomposed n user 1 User
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class StaffList extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "staff";
	/** @hidden */
	public static final String DOCUMENT_NAME = "StaffList";

	/** @hidden */
	public static final String userPropertyName = "user";
	/** @hidden */
	public static final String socialTitlePropertyName = "socialTitle";
	/** @hidden */
	public static final String dateOfBirthPropertyName = "dateOfBirth";
	/** @hidden */
	public static final String idCardNoPropertyName = "idCardNo";
	/** @hidden */
	public static final String issuedDatePropertyName = "issuedDate";
	/** @hidden */
	public static final String expiredDatePropertyName = "expiredDate";
	/** @hidden */
	public static final String deptPropertyName = "dept";
	/** @hidden */
	public static final String buPropertyName = "bu";
	/** @hidden */
	public static final String jobTitlePropertyName = "jobTitle";
	/** @hidden */
	public static final String startDatePropertyName = "startDate";
	/** @hidden */
	public static final String bioPropertyName = "bio";
	/** @hidden */
	public static final String staffLocationPropertyName = "staffLocation";

	/**
	 * Social Title
	 **/
	@XmlEnum
	public static enum SocialTitle implements Enumeration {
		mr("mr", "Mr"),
		ms("ms", "Ms"),
		mrs("mrs", "Mrs"),
		dr("dr", "Dr");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private SocialTitle(String code, String description) {
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

		public static SocialTitle fromCode(String code) {
			SocialTitle result = null;

			for (SocialTitle value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static SocialTitle fromDescription(String description) {
			SocialTitle result = null;

			for (SocialTitle value : values()) {
				if (value.description.equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				SocialTitle[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (SocialTitle value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * User
	 **/
	private UserExtension user = null;
	/**
	 * Social Title
	 **/
	private SocialTitle socialTitle = SocialTitle.mr;
	/**
	 * Date Of Birth
	 **/
	private DateOnly dateOfBirth;
	/**
	 * ID Card Number
	 **/
	private String idCardNo;
	/**
	 * Issued Date
	 **/
	private DateOnly issuedDate;
	/**
	 * Expired Date
	 **/
	private DateOnly expiredDate;
	/**
	 * Department
	 **/
	private String dept;
	/**
	 * Business Unit
	 **/
	private String bu;
	/**
	 * Job Title
	 **/
	private String jobTitle;
	/**
	 * Start Date
	 **/
	private DateOnly startDate;
	/**
	 * BIO
	 * <br/>
	 * Brief bio
	 **/
	private String bio;
	/**
	 * Staff Location
	 **/
	private Geometry staffLocation;

	@Override
	@XmlTransient
	public String getBizModule() {
		return StaffList.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return StaffList.DOCUMENT_NAME;
	}

	public static StaffList newInstance() {
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
														"{user.contact.name}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof StaffList) && 
					this.getBizId().equals(((StaffList) o).getBizId()));
	}

	/**
	 * {@link #user} accessor.
	 * @return	The value.
	 **/
	public UserExtension getUser() {
		return user;
	}

	/**
	 * {@link #user} mutator.
	 * @param user	The new value.
	 **/
	@XmlElement
	public void setUser(UserExtension user) {
		preset(userPropertyName, user);
		this.user = user;
	}

	/**
	 * {@link #socialTitle} accessor.
	 * @return	The value.
	 **/
	public SocialTitle getSocialTitle() {
		return socialTitle;
	}

	/**
	 * {@link #socialTitle} mutator.
	 * @param socialTitle	The new value.
	 **/
	@XmlElement
	public void setSocialTitle(SocialTitle socialTitle) {
		preset(socialTitlePropertyName, socialTitle);
		this.socialTitle = socialTitle;
	}

	/**
	 * {@link #dateOfBirth} accessor.
	 * @return	The value.
	 **/
	public DateOnly getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * {@link #dateOfBirth} mutator.
	 * @param dateOfBirth	The new value.
	 **/
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	@XmlElement
	public void setDateOfBirth(DateOnly dateOfBirth) {
		preset(dateOfBirthPropertyName, dateOfBirth);
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * {@link #idCardNo} accessor.
	 * @return	The value.
	 **/
	public String getIdCardNo() {
		return idCardNo;
	}

	/**
	 * {@link #idCardNo} mutator.
	 * @param idCardNo	The new value.
	 **/
	@XmlElement
	public void setIdCardNo(String idCardNo) {
		preset(idCardNoPropertyName, idCardNo);
		this.idCardNo = idCardNo;
	}

	/**
	 * {@link #issuedDate} accessor.
	 * @return	The value.
	 **/
	public DateOnly getIssuedDate() {
		return issuedDate;
	}

	/**
	 * {@link #issuedDate} mutator.
	 * @param issuedDate	The new value.
	 **/
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	@XmlElement
	public void setIssuedDate(DateOnly issuedDate) {
		preset(issuedDatePropertyName, issuedDate);
		this.issuedDate = issuedDate;
	}

	/**
	 * {@link #expiredDate} accessor.
	 * @return	The value.
	 **/
	public DateOnly getExpiredDate() {
		return expiredDate;
	}

	/**
	 * {@link #expiredDate} mutator.
	 * @param expiredDate	The new value.
	 **/
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	@XmlElement
	public void setExpiredDate(DateOnly expiredDate) {
		preset(expiredDatePropertyName, expiredDate);
		this.expiredDate = expiredDate;
	}

	/**
	 * {@link #dept} accessor.
	 * @return	The value.
	 **/
	public String getDept() {
		return dept;
	}

	/**
	 * {@link #dept} mutator.
	 * @param dept	The new value.
	 **/
	@XmlElement
	public void setDept(String dept) {
		preset(deptPropertyName, dept);
		this.dept = dept;
	}

	/**
	 * {@link #bu} accessor.
	 * @return	The value.
	 **/
	public String getBu() {
		return bu;
	}

	/**
	 * {@link #bu} mutator.
	 * @param bu	The new value.
	 **/
	@XmlElement
	public void setBu(String bu) {
		preset(buPropertyName, bu);
		this.bu = bu;
	}

	/**
	 * {@link #jobTitle} accessor.
	 * @return	The value.
	 **/
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * {@link #jobTitle} mutator.
	 * @param jobTitle	The new value.
	 **/
	@XmlElement
	public void setJobTitle(String jobTitle) {
		preset(jobTitlePropertyName, jobTitle);
		this.jobTitle = jobTitle;
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
	 * {@link #bio} accessor.
	 * @return	The value.
	 **/
	public String getBio() {
		return bio;
	}

	/**
	 * {@link #bio} mutator.
	 * @param bio	The new value.
	 **/
	@XmlElement
	public void setBio(String bio) {
		preset(bioPropertyName, bio);
		this.bio = bio;
	}

	/**
	 * {@link #staffLocation} accessor.
	 * @return	The value.
	 **/
	public Geometry getStaffLocation() {
		return staffLocation;
	}

	/**
	 * {@link #staffLocation} mutator.
	 * @param staffLocation	The new value.
	 **/
	@XmlJavaTypeAdapter(GeometryMapper.class)
	@XmlElement
	public void setStaffLocation(Geometry staffLocation) {
		preset(staffLocationPropertyName, staffLocation);
		this.staffLocation = staffLocation;
	}

	/**
	 * StaffManager
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isStaffManager() {
		return (isUserInRole("staff","StaffManager"));
	}

	/**
	 * {@link #isStaffManager} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotStaffManager() {
		return (! isStaffManager());
	}
}
