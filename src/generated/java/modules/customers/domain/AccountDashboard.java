package modules.customers.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import modules.customers.Account.AccountExtension;
import modules.customers.AccountDashboard.AccountDashboardExtension;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractTransientBean;

/**
 * Account Dashboard
 * 
 * @navhas n account 0..1 Account
 * @stereotype "transient"
 */
@XmlType
@XmlRootElement
public class AccountDashboard extends AbstractTransientBean {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "customers";
	/** @hidden */
	public static final String DOCUMENT_NAME = "AccountDashboard";

	/** @hidden */
	public static final String accountPropertyName = "account";
	/** @hidden */
	public static final String flowbarPropertyName = "flowbar";

	/**
	 * Account
	 **/
	private AccountExtension account = null;
	/**
	 * Flowbar
	 **/
	private String flowbar;

	@Override
	@XmlTransient
	public String getBizModule() {
		return AccountDashboard.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return AccountDashboard.DOCUMENT_NAME;
	}

	public static AccountDashboardExtension newInstance() {
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
														"Account Dashboard",
														this);
		}
		catch (Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof AccountDashboard) && 
					this.getBizId().equals(((AccountDashboard) o).getBizId()));
	}

	/**
	 * {@link #account} accessor.
	 * @return	The value.
	 **/
	public AccountExtension getAccount() {
		return account;
	}

	/**
	 * {@link #account} mutator.
	 * @param account	The new value.
	 **/
	@XmlElement
	public void setAccount(AccountExtension account) {
		preset(accountPropertyName, account);
		this.account = account;
	}

	/**
	 * {@link #flowbar} accessor.
	 * @return	The value.
	 **/
	public String getFlowbar() {
		return flowbar;
	}

	/**
	 * {@link #flowbar} mutator.
	 * @param flowbar	The new value.
	 **/
	@XmlElement
	public void setFlowbar(String flowbar) {
		this.flowbar = flowbar;
	}

	/**
	 * accountSelected
	 *
	 * @return The condition
	 */
	@XmlTransient
	public boolean isAccountSelected() {
		return (account != null);
	}

	/**
	 * {@link #isAccountSelected} negation.
	 *
	 * @return The negated condition
	 */
	public boolean isNotAccountSelected() {
		return (! isAccountSelected());
	}
}
