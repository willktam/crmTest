package modules.customers.domain;

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
import modules.customers.ContactDetail.ContactDetailExtension;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateTime;
import org.skyve.domain.types.Enumeration;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.impl.domain.types.jaxb.DateTimeMapper;
import org.skyve.metadata.model.document.Bizlet.DomainValue;

/**
 * Interaction
 * 
 * @depend - - - Type
 * @navhas n contact 0..1 ContactDetail
 * @navhas n user 1 User
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public class Interaction extends AbstractPersistentBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "customers";
	/** @hidden */
	public static final String DOCUMENT_NAME = "Interaction";

	/** @hidden */
	public static final String titlePropertyName = "title";
	/** @hidden */
	public static final String userPropertyName = "user";
	/** @hidden */
	public static final String descriptionPropertyName = "description";
	/** @hidden */
	public static final String typePropertyName = "type";
	/** @hidden */
	public static final String interactionTimePropertyName = "interactionTime";
	/** @hidden */
	public static final String contactPropertyName = "contact";

	/**
	 * Type
	 * <br/>
	 * The type of interaction
	 **/
	@XmlEnum
	public static enum Type implements Enumeration {
		email("Email", "Email"),
		phone("Phone", "Phone"),
		meeting("Meeting", "Meeting"),
		other("Other", "Other");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private Type(String code, String description) {
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

		public static Type fromCode(String code) {
			Type result = null;

			for (Type value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static Type fromDescription(String description) {
			Type result = null;

			for (Type value : values()) {
				if (value.description.equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				Type[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (Type value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Title
	 * <br/>
	 * The title of the interaction
	 **/
	private String title;
	/**
	 * User
	 * <br/>
	 * The user who performed the interaction
	 **/
	private UserExtension user = null;
	/**
	 * Description
	 * <br/>
	 * The description of the interaction
	 **/
	private String description;
	/**
	 * Type
	 * <br/>
	 * The type of interaction
	 **/
	private Type type;
	/**
	 * Time of Interaction
	 * <br/>
	 * The time the interaction took place
	 **/
	private DateTime interactionTime;
	/**
	 * Contact
	 * <br/>
	 * The contact associated with this interaction
	 **/
	private ContactDetailExtension contact = null;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Interaction.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Interaction.DOCUMENT_NAME;
	}

	public static Interaction newInstance() {
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
														"{title}",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Interaction) && 
					this.getBizId().equals(((Interaction) o).getBizId()));
	}

	/**
	 * {@link #title} accessor.
	 * @return	The value.
	 **/
	public String getTitle() {
		return title;
	}

	/**
	 * {@link #title} mutator.
	 * @param title	The new value.
	 **/
	@XmlElement
	public void setTitle(String title) {
		preset(titlePropertyName, title);
		this.title = title;
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
	 * {@link #type} accessor.
	 * @return	The value.
	 **/
	public Type getType() {
		return type;
	}

	/**
	 * {@link #type} mutator.
	 * @param type	The new value.
	 **/
	@XmlElement
	public void setType(Type type) {
		preset(typePropertyName, type);
		this.type = type;
	}

	/**
	 * {@link #interactionTime} accessor.
	 * @return	The value.
	 **/
	public DateTime getInteractionTime() {
		return interactionTime;
	}

	/**
	 * {@link #interactionTime} mutator.
	 * @param interactionTime	The new value.
	 **/
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(DateTimeMapper.class)
	@XmlElement
	public void setInteractionTime(DateTime interactionTime) {
		preset(interactionTimePropertyName, interactionTime);
		this.interactionTime = interactionTime;
	}

	/**
	 * {@link #contact} accessor.
	 * @return	The value.
	 **/
	public ContactDetailExtension getContact() {
		return contact;
	}

	/**
	 * {@link #contact} mutator.
	 * @param contact	The new value.
	 **/
	@XmlElement
	public void setContact(ContactDetailExtension contact) {
		preset(contactPropertyName, contact);
		this.contact = contact;
	}
}
