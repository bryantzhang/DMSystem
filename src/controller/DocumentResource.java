package controller;

import java.util.HashMap;
import java.util.Map;

import dao.Document;
import dao.User;
import model.DocumentUtil;
import model.UserUtil;

import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import common.DocumentResourceInterface;
import restlet.Constants;

public class DocumentResource extends ServerResource implements
		DocumentResourceInterface {
	private DocumentUtil documentUtil = new DocumentUtil();

    /** The document identifier. */
	private Integer documentId = null;

	/**
	 * Retrieve the document identifier based on the URI path variable
	 * "accountId" declared in the URI template attached to the application
	 * router.
	 */
	@Override
	protected void doInit() throws ResourceException {
		String documentIdAttribute = getAttribute("documentId");

		if (documentIdAttribute != null) {
			this.documentId = Integer.parseInt(documentIdAttribute);
		}
	}

	@Override
	public Representation retrieve() {
        try {
            Document document = this.documentUtil.findByIdWithFullInfo(this.documentId);

            Map<String, Object> dataModel = new HashMap<String, Object>();
            User currentUser = UserUtil.getCurrentUser(this);
            dataModel.put("user", currentUser);
            dataModel.put("document", document);
            Representation mailVtl = new ClientResource(
                    LocalReference.createClapReference("/source/template")
                            + "/docinfo.vtl").get();
            return new TemplateRepresentation(mailVtl, dataModel,
                    MediaType.TEXT_HTML);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StringRepresentation("Something error when retrieve document");
        }
	}

    @Override
    public Representation remove() {
        return null;
    }
}
