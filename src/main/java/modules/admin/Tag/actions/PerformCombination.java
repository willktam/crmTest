package modules.admin.Tag.actions;

import org.skyve.domain.messages.Message;
import org.skyve.domain.messages.ValidationException;
import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.web.WebContext;

import modules.admin.Tag.TagBizlet;
import modules.admin.domain.Tag;

public class PerformCombination implements ServerSideAction<Tag> {
	/**
	 * For Serialization
	 */
	private static final long serialVersionUID = 2886341074753936987L;

	/**
	 * Update the payment batch details.
	 */
	@Override
	public ServerSideActionResult<Tag> execute(Tag bean, WebContext webContext) throws Exception {

		if (bean.getCombinationsOperator() == null) {
			throw new ValidationException(new Message(Tag.combinationsOperatorPropertyName, "You have not set an operator."));
		}

		if (bean.getOperandTag() == null) {
			throw new ValidationException(new Message(Tag.operandTagPropertyName, "You have not set the other Tag."));
		}

		switch (bean.getCombinationsOperator()) {
		case union:
			TagBizlet.union(bean, bean.getOperandTag());
			break;
		case except:
			TagBizlet.except(bean, bean.getOperandTag());
			break;
		case intersect:
			TagBizlet.intersect(bean, bean.getOperandTag());
			break;
		default:
			break;
		}

		return new ServerSideActionResult<>(bean);
	}
}
