package modules.admin.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import modules.admin.User.UserExtension;
import modules.admin.UserDashboard.UserDashboardExtension;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractTransientBean;
import org.skyve.impl.domain.ChangeTrackingArrayList;

/**
 * User Dashboard
 * 
 * @navhas n currentUser 0..1 User
 * @navcomposed n favourites 0..n Generic
 * @stereotype "transient"
 */
@XmlType
@XmlRootElement
public class UserDashboard extends AbstractTransientBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "admin";
	/** @hidden */
	public static final String DOCUMENT_NAME = "UserDashboard";

	/** @hidden */
	public static final String currentUserPropertyName = "currentUser";
	/** @hidden */
	public static final String favouritesPropertyName = "favourites";

	/**
	 * Current User
	 **/
	private UserExtension currentUser = null;
	/**
	 * Favourites
	 **/
	private List<Generic> favourites = new ChangeTrackingArrayList<>("favourites", this);

	@Override
	@XmlTransient
	public String getBizModule() {
		return UserDashboard.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return UserDashboard.DOCUMENT_NAME;
	}

	public static UserDashboardExtension newInstance() {
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
														"User Dashboard",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof UserDashboard) && 
					this.getBizId().equals(((UserDashboard) o).getBizId()));
	}

	/**
	 * {@link #currentUser} accessor.
	 * @return	The value.
	 **/
	public UserExtension getCurrentUser() {
		return currentUser;
	}

	/**
	 * {@link #currentUser} mutator.
	 * @param currentUser	The new value.
	 **/
	@XmlElement
	public void setCurrentUser(UserExtension currentUser) {
		preset(currentUserPropertyName, currentUser);
		this.currentUser = currentUser;
	}

	/**
	 * {@link #favourites} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<Generic> getFavourites() {
		return favourites;
	}

	/**
	 * {@link #favourites} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public Generic getFavouritesElementById(String bizId) {
		return getElementById(favourites, bizId);
	}

	/**
	 * {@link #favourites} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setFavouritesElementById(String bizId, Generic element) {
		 setElementById(favourites, element);
	}
}
