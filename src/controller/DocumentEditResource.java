package controller;

import common.DocumentEditResourceInterface;
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
import restlet.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by justinyang on 13-12-18.
 */
public class DocumentEditResource extends ServerResource implements DocumentEditResourceInterface {
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
    public Representation present() {
        try {
            Document document = this.documentUtil.findByIdWithFullInfo(this.documentId);

            Map<String, Object> dataModel = new HashMap<String, Object>();
            User currentUser = UserUtil.getCurrentUser(this);
            dataModel.put("document", document);
            dataModel.put("user", currentUser);

            Representation mailVtl = new ClientResource(
                    LocalReference.createClapReference("/source/template")
                            + "/docmodify.vtl").get();
            return new TemplateRepresentation(mailVtl, dataModel,
                    MediaType.TEXT_HTML);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StringRepresentation("Something error presenting listing documents");
        }
    }

    @Override
    public Representation update(Form form) {
        try {
            Map<String, String> values = form.getValuesMap();
            this.documentUtil.update(this.documentId, values);
        } catch (Exception e) {
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            e.printStackTrace();
        }
        return new StringRepresentation("Modify document successfully");
    }
}
