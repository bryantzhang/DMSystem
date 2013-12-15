package controller;

import java.util.HashMap;
import java.util.Map;

import model.Document;

import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
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
	public Representation modify(Form form) {
		for (Parameter entry : form) {
			System.out.println(entry.getName() + "=" + entry.getValue());
		}
		return new StringRepresentation("Modify document successfully");
	}

	@Override
	public Representation add(Form form) {
		for (Parameter entry : form) {
			System.out.println(entry.getName() + "=" + entry.getValue());
		}
		return new StringRepresentation("Add document successfully");
	}

	@Override
	public Representation remove() {
		return null;
	}

}
