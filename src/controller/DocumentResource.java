package controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Document;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.data.Status;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.representation.Variant;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import common.DocumentResourceInterface;

public class DocumentResource extends ServerResource implements
		DocumentResourceInterface {
	/** The document identifier. */
	private Integer documentId;

	/**
	 * Retrieve the document identifier based on the URI path variable
	 * "accountId" declared in the URI template attached to the application
	 * router.
	 */
	@Override
	protected void doInit() throws ResourceException {
		String accountIdAttribute = getAttribute("documentId");

		if (accountIdAttribute != null) {
			this.documentId = Integer.parseInt(getAttribute("documentId"));
		}
	}

	@Override
	public Representation retrive() throws Exception {
		Document document = new Document();
		document.setDocumentId(this.documentId);

		Map<String, Object> dataModel = new HashMap<String, Object>();
		dataModel.put("document", document);
		Representation mailVtl = new ClientResource(
				LocalReference.createClapReference(getClass().getPackage())
						+ "/Document.vtl").get();
		return new TemplateRepresentation(mailVtl, dataModel,
				MediaType.TEXT_HTML);
	}

	@Override
	public Representation modify(Representation form) {
//		for (Parameter entry : form) {
//			System.out.println(entry.getName() + "=" + entry.getValue());
//		}
		return new StringRepresentation("Modify document successfully");
	}

	@Override
	public Representation add(Representation input) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1000240);

		RestletFileUpload upload = new RestletFileUpload(factory);
		List<FileItem> items;
		try {
			items = upload.parseRepresentation(input);

			File file = null;

			for (final Iterator<FileItem> it = items.iterator(); it.hasNext();) {
				FileItem fi = it.next();

				if (fi.isFormField()) {
					System.out
							.println(fi.getFieldName() + "=" + fi.getString());
				} else {
					String fileName = fi.getName();
					String tempDir = System.getProperty("java.io.tmpdir");
					String filePath = tempDir + File.separator + fileName;
					file = new File(filePath);
					fi.getInputStream();
					fi.write(file);
				}
			}
		} catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			e.printStackTrace();
			return new StringRepresentation(e.getMessage(),
					MediaType.TEXT_PLAIN);
		}

//		 String redirectUrl = ...; // address of newly created resource
//		 getResponse().redirectSeeOther(redirectUrl);
		return new StringRepresentation("Add document successfully");
	}

	@Override
	public Representation remove() {
		return null;
	}

}
