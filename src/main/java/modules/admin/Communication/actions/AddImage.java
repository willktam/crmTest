package modules.admin.Communication.actions;

import org.apache.commons.codec.binary.Base64;
import org.skyve.domain.messages.UploadException;
import org.skyve.metadata.controller.UploadAction;
import org.skyve.util.FileUtil;
import org.skyve.web.WebContext;

import modules.admin.domain.Communication;

public class AddImage extends UploadAction<Communication> {

	private static final long serialVersionUID = -8154709480999519405L;


	@Override
	public Communication upload(Communication communication, UploadedFile file, UploadException exception, WebContext webContext) throws Exception {

		Communication result = communication;

		String base64EncodedImage = Base64.encodeBase64String(FileUtil.getFileBytes(file.getInputStream()));
		StringBuilder imageTag = new StringBuilder();
		imageTag.append("<div><img src=\"data:image/png;base64,");
		imageTag.append(base64EncodedImage);
		imageTag.append("\" /></div>");

		if (result.getBody() == null) {
			result.setBody(imageTag.toString());
		} else {
			result.setBody(result.getBody() + "<p/>" + imageTag.toString());
		}
		
		return result;
	}
}
