package controller;

import common.DocumentImportResourceInterface;
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
import org.restlet.resource.ServerResource;
import restlet.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by justinyang on 13-12-18.
 */
public class DocumentImportResource extends ServerResource implements DocumentImportResourceInterface {
    private DocumentUtil documentUtil = new DocumentUtil();

    @Override
    public Representation present() {
        try {
            Map<String, Object> dataModel = new HashMap<String, Object>();
            User currentUser = UserUtil.getCurrentUser(this);
            dataModel.put("user", currentUser);

            Representation mailVtl = new ClientResource(
                    LocalReference.createClapReference("/source/template")
                            + "/docimport.vtl").get();
            return new TemplateRepresentation(mailVtl, dataModel,
                    MediaType.TEXT_HTML);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StringRepresentation("Something error when listing documents");
        }
    }

    @Override
    public Representation upload(Form form) {
        try {
            Map<String, String> values = this._createValueMap(form);
            String username = getClientInfo().getUser().getIdentifier();
            UserUtil userUtil = new UserUtil();
            User user = userUtil.findByUsername(username);
            this.documentUtil.create(user, values);
        } catch (Exception e) {
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            e.printStackTrace();
        }
        return new StringRepresentation("Add document successfully");
    }

    private Map<String, String> _createValueMap(Form form) throws Exception {
        Map<String, String> values = new HashMap<String, String>();
        String title = form.getFirstValue(Constants.kTitleField);
        if (title != null && !title.isEmpty()) {
            values.put(Constants.kTitleField, title);
        }
        String author = form.getFirstValue(Constants.kAuthorField);
        if (author != null && !author.isEmpty()) {
            values.put(Constants.kAuthorField, author);
        }
        String abstracts = form.getFirstValue(Constants.kAbstractsField);
        if (abstracts != null && !abstracts.isEmpty()) {
            values.put(Constants.kAbstractsField, abstracts);
        }
        String keywords = form.getFirstValue(Constants.kKeywordsField);
        if (keywords != null && !keywords.isEmpty()) {
            values.put(Constants.kKeywordsField, keywords);
        }
        String url = form.getFirstValue(Constants.kUrlField);
        if (url != null && !url.isEmpty()) {
            values.put(Constants.kUrlField, url);
        }
        String publisher = form.getFirstValue(Constants.kPublisherField);
        if (publisher != null && !publisher.isEmpty()) {
            values.put(Constants.kPublisherField, publisher);
        }
        String year = form.getFirstValue(Constants.kYearField);
        if (year != null && !year.isEmpty()) {
            values.put(Constants.kYearField, year);
        }
        String pages = form.getFirstValue(Constants.kPagesField);
        if (pages != null && !pages.isEmpty()) {
            values.put(Constants.kPagesField, pages);
        }
        String documentType = form
                .getFirstValue(Constants.kDocumentTypeField);
        if (documentType != null && !documentType.isEmpty()) {
            values.put(Constants.kDocumentTypeField, documentType);
        }
        return values;
    }
}
