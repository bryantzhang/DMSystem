package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.AttachmentUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.restlet.data.Status;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

import common.AttachmentResourceInterface;
import dao.Attachment;

public class AttachmentResource extends ServerResource implements
		AttachmentResourceInterface {
	private AttachmentUtil attachmentUtil = new AttachmentUtil();

	@Override
	public Representation retrieve() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation add(Representation input) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1000240);

		RestletFileUpload upload = new RestletFileUpload(factory);
		List<FileItem> items = null;
		try {
			items = upload.parseRepresentation(input);

			String documentIdAttribute = getAttribute("documentId");
			Integer documentId = 0;
			if (documentIdAttribute != null) {
				documentId = Integer.parseInt(documentIdAttribute);
			}

			try {
				List<File> attachments = new ArrayList<File>();
				for (final Iterator<FileItem> it = items.iterator(); it
						.hasNext();) {
					FileItem fi = it.next();

					if (fi.isFormField()) {

					} else {
						String fileName = fi.getName();
						String tempDir = System.getProperty("java.io.tmpdir");
						String filePath = tempDir + File.separator + fileName;
						File file = new File(filePath);
						fi.getInputStream();
						// fi.write(file);

						attachments.add(file);
					}
				}

				this.attachmentUtil.addAttachments(documentId, attachments);
				getResponse().setStatus(Status.SUCCESS_CREATED);
				return new StringRepresentation("Success add attachments");
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			e.printStackTrace();
			return new StringRepresentation("Failt to add attachments");
		}
	}

	@Override
	public Representation remove() {
		Attachment attachment;
		try {
			String attachmentIdAttribute = getAttribute("attachmentId");
			Integer attachmentId = 0;
			if (attachmentIdAttribute != null) {
				attachmentId = Integer.parseInt(attachmentIdAttribute);
			}
			
			attachment = this.attachmentUtil.findById(attachmentId);
			if (attachment != null) {
				this.attachmentUtil.remove(attachment);
			}
			getResponse().setStatus(Status.SUCCESS_CREATED);
			return new StringRepresentation("Remove an attachments");
		} catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			e.printStackTrace();
			return new StringRepresentation("Failt to remove attachments");
		}
	}

}
