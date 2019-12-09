package modules.admin.JobSchedule.actions;

import modules.admin.domain.JobSchedule;

import org.skyve.CORE;
import org.skyve.EXT;
import org.skyve.domain.messages.ValidationException;
import org.skyve.domain.messages.Message;
import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.metadata.customer.Customer;
import org.skyve.metadata.module.JobMetaData;
import org.skyve.metadata.module.Module;
import org.skyve.metadata.user.User;
import org.skyve.persistence.Persistence;
import org.skyve.util.Util;
import org.skyve.web.WebContext;

public class RunJobNow implements ServerSideAction<JobSchedule> {
	private static final long serialVersionUID = -1037253249182913062L;

	@Override
	public ServerSideActionResult<JobSchedule> execute(JobSchedule bean, WebContext webContext) throws Exception {

		Persistence persistence = CORE.getPersistence();
		User user = persistence.getUser();
		Customer customer = user.getCustomer();

		// don't know which module this is in
		Util.LOGGER.info("Job requested for immediate execution: " + bean.getJobName());

		String[] parts = bean.getJobName().split("\\.");
		if (parts.length < 2) {
			StringBuilder sb = new StringBuilder();
			sb.append("The Job you've selected ");
			sb.append(bean.getJobName());
			sb.append(" can't be identified.");

			throw new ValidationException(new Message(JobSchedule.jobNamePropertyName, sb.toString()));
		}

		Module module = customer.getModule(parts[0]);
		JobMetaData job = module.getJob(parts[1]);

		// run as the current user
		EXT.runOneShotJob(job, bean, user);

		// Used to update the UI.
		bean.setJobScheduledImmediately(Boolean.TRUE);

		return new ServerSideActionResult<>(bean);
	}
}
