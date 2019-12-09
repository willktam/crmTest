package modules.admin.Tag;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.skyve.CORE;
import org.skyve.EXT;
import org.skyve.domain.Bean;
import org.skyve.domain.PersistentBean;
import org.skyve.domain.messages.MessageSeverity;
import org.skyve.impl.metadata.customer.CustomerImpl;
import org.skyve.job.Job;
import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.metadata.customer.Customer;
import org.skyve.metadata.model.document.Document;
import org.skyve.metadata.module.Module;
import org.skyve.metadata.repository.Repository;
import org.skyve.metadata.user.User;
import org.skyve.persistence.Persistence;
import org.skyve.util.BeanValidator;
import org.skyve.util.PushMessage;

import modules.admin.Communication.CommunicationUtil;
import modules.admin.Communication.CommunicationUtil.ResponseMode;
import modules.admin.domain.DataMaintenance.EvictOption;
import modules.admin.domain.Tag;

public class PerformDocumentActionForTagJob extends Job {
	private static final long serialVersionUID = 6282346785863992703L;

	@Override
	public String cancel() {
		return null;
	}

	@Override
	public void execute() throws Exception {

		List<String> log = getLog();

		Tag tag = (Tag) getBean();
		log.add("Started Document Action for Tagged Items Job at " + new Date());

		if (tag.getDocumentAction() != null) {

			// get relevant document to action
			Persistence pers = CORE.getPersistence();
			User user = pers.getUser();
			Customer customer = user.getCustomer();
			Module module = customer.getModule(tag.getActionModuleName());
			Document document = module.getDocument(customer, tag.getActionDocumentName());

			// get action from actionname
			Repository rep = CORE.getRepository();

			ServerSideAction<Bean> act = null;
			EvictOption evict = tag.getEvictOption();
			

			// retrieve action for non-default actions only
			if (!TagDefaultAction.isDefaultTagAction(tag.getDocumentAction())) {
				act = rep.getServerSideAction(customer, document, tag.getDocumentAction(), true);
			}

			List<Bean> beans = TagBizlet.getTaggedItemsForDocument(tag, tag.getActionModuleName(), tag.getActionDocumentName());

			int size = beans.size();
			int processed = 0;
			Iterator<Bean> it = beans.iterator();
			while (it.hasNext()) {
				PersistentBean pb = (PersistentBean) it.next();

				StringBuilder sb = new StringBuilder();
				sb.append("Action request for [").append(tag.getDocumentAction());
				sb.append("] for document [").append(tag.getActionDocumentName()).append("] - ");
				if (tag.getDocumentCondition() != null) {
					sb.append(" with condition [").append(tag.getDocumentCondition()).append("] - ");
				} else {
					sb.append(" unconditionally ");
				}
				sb.append("'").append(pb.getBizKey()).append("'");
				try {
					boolean conditionSatisfied = true;
					if (tag.getDocumentCondition() != null) {
						conditionSatisfied = conditionSatisfied && pb.evaluateCondition(tag.getDocumentCondition());
					}
					if (conditionSatisfied) {
						if (act != null) {
							CustomerImpl internalCustomer = (CustomerImpl) customer;
							boolean vetoed = internalCustomer.interceptBeforeServerSideAction(document, tag.getDocumentAction(), pb, null);
							if (!vetoed) {
								ServerSideActionResult<Bean> result = act.execute(pb, null);
								internalCustomer.interceptAfterServerSideAction(document, tag.getDocumentAction(), result, null);
								pb = (PersistentBean) result.getBean();
							}
						}
						// remove successfully validated beans
						if (Boolean.TRUE.equals(tag.getUnTagSuccessful())) {
							EXT.untag(tag.getBizId(), pb);
						}

						if (TagDefaultAction.tagDelete.equals(TagDefaultAction.fromCode(tag.getDocumentAction()))) {
							// remove from tag and delete
							EXT.untag(tag.getBizId(), pb);
							pers.delete(pb);
						} else if (TagDefaultAction.tagValidate.equals(TagDefaultAction.fromCode(tag.getDocumentAction()))) {
							BeanValidator.validateBeanAgainstDocument(document, pb);
						} else if (TagDefaultAction.tagUpsert.equals(TagDefaultAction.fromCode(tag.getDocumentAction()))) {
							pers.upsertBeanTuple(pb);
						} else {
							pers.save(pb);
						}
						
						pers.commit(false);
						if (EvictOption.bean.equals(evict)) {
							pers.evictCached(pb);
						}
						else if (EvictOption.all.equals(evict)) {
							pers.evictAllCached();
						}
						pers.begin();

						sb.append(" - Successful");

					} else {
						sb.append(" - Condition not satisfied");
					}

				} catch (Exception e) {
					sb.append(" - Unsuccessful");
					sb.append("\n").append(e.getMessage());
				}
				processed++;
				setPercentComplete((int) (((float) processed) / ((float) size) * 100F));

				log.add(sb.toString());
			}

			if (Boolean.TRUE.equals(tag.getNotification())) {
				
				// send email notification for completion of Job
				CommunicationUtil.sendFailSafeSystemCommunication(TagBizlet.SYSTEM_TAG_ACTION_NOTIFICATION, TagBizlet.SYSTEM_TAG_ACTION_DEFAULT_SUBJECT, TagBizlet.SYSTEM_TAG_ACTION_DEFAULT_BODY, ResponseMode.SILENT, null, tag);
			}
		}

		setPercentComplete(100);
		log.add("Finished Document Action for Tagged Items Job at " + new Date());
		EXT.push(new PushMessage().user(CORE.getUser()).growl(MessageSeverity.info, "Tag action Job completed."));
	}
}
