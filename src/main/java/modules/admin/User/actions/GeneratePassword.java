package modules.admin.User.actions;

import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.web.WebContext;

import modules.admin.PasswordGenerator;
import modules.admin.domain.User;

public class GeneratePassword implements ServerSideAction<User> {
	/**
	 * For Serialization.
	 */
	private static final long serialVersionUID = 3904239033808385824L;

	@Override
	public ServerSideActionResult<User> execute(User user, WebContext webContext) throws Exception {

		generatePassword(user);

		return new ServerSideActionResult<>(user);
		// form
	}

	@SuppressWarnings({ "unused", "static-access" })
	public static void generatePassword(User user) throws Exception {
		user.setGeneratedPassword(new PasswordGenerator().generate());

		user.setNewPassword(user.getGeneratedPassword());
		user.setConfirmPassword(user.getGeneratedPassword());
		user.setPasswordExpired(Boolean.TRUE);

	}
}
