package modules.admin.Configuration;

import org.skyve.domain.Bean;
import org.skyve.impl.util.UtilImpl;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.SingletonCachedBizlet;
import org.skyve.util.Binder;
import org.skyve.web.WebContext;

import modules.admin.ModulesUtil;
import modules.admin.Startup.StartupBizlet;
import modules.admin.Startup.StartupExtension;
import modules.admin.domain.Configuration;
import modules.admin.domain.Contact;
import modules.admin.domain.Startup;
import modules.admin.domain.User;
import modules.admin.domain.UserProxy;

public class ConfigurationBizlet extends SingletonCachedBizlet<ConfigurationExtension> {

	private static final long serialVersionUID = -1282437688681930236L;

	@Override
	public ConfigurationExtension newInstance(ConfigurationExtension bean) throws Exception {
		ConfigurationExtension result = super.newInstance(bean);

		// Set the user name and email to the logged in user
		UserProxy user = ModulesUtil.currentAdminUserProxy();
		Contact contact = user.getContact();
		bean.setEmailFrom(contact.getEmail1());

		if (result.getPasswordComplexityModel() == null) {
			result.setPasswordComplexityModel(ComplexityModel.DEFAULT_COMPLEXITY_MODEL);
		}
		if (result.getFromEmail() == null) {
			result.setFromEmail(UtilImpl.SMTP_SENDER);
		}
		if (result.getPasswordResetEmailSubject() == null) {
			result.setPasswordResetEmailSubject("Password Reset");
		}
		if (result.getPasswordResetEmailBody() == null) {
			String body = String.format("<html><head/><body>Hi {%s},<p/>" +
					"Please click below to reset your password.<p/>" +
					"<a href=\"{url}/pages/resetPassword.jsp?t={%s}\">" +
					"Reset Password</a></body></html>",
					Binder.createCompoundBinding(User.contactPropertyName, Contact.namePropertyName),
					User.passwordResetTokenPropertyName);
			result.setPasswordResetEmailBody(body);
		}

		// initialise the startup bean
		StartupExtension startup = Startup.newInstance();
		startup.loadProperties();
		bean.setStartup(startup);

		return result;
	}

	@Override
	public ConfigurationExtension preExecute(ImplicitActionName actionName, ConfigurationExtension bean, Bean parentBean,
			WebContext webContext) throws Exception {

		if (ImplicitActionName.New.equals(actionName)) {
			// initialise the startup bean
			StartupExtension startup = Startup.newInstance();
			startup.loadProperties();
			bean.setStartup(startup);
		} else if (ImplicitActionName.Save.equals(actionName) || ImplicitActionName.OK.equals(actionName)) {
			if (bean.getUserSelfRegistrationGroup() == null) {
				bean.setAllowUserSelfRegistration(Boolean.FALSE);
			}
			
			bean.getStartup().saveConfiguration();
		}

		return super.preExecute(actionName, bean, parentBean, webContext);
	}

	@Override
	public void preRerender(String source, ConfigurationExtension bean, WebContext webContext) throws Exception {

		if (Binder.createCompoundBinding(Configuration.startupPropertyName, Startup.mapTypePropertyName).equals(source)) {
			// set the default layers for the selected map type
			switch (bean.getStartup().getMapType()) {
				case gmap:
					bean.getStartup().setMapLayer(StartupBizlet.MAP_LAYER_GMAP);
					break;
				case leaflet:
					bean.getStartup().setMapLayer(StartupBizlet.MAP_LAYER_OPEN_STREET_MAP);
					break;
				default:
					break;
			}
		}

		super.preRerender(source, bean, webContext);
	}

}
