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

public class DocumentResource extends ServerResource implements
		DocumentResourceInterface {
    private UserUtil userUtil = new UserUtil();
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
	public Representation retrieve() throws Exception {
		Document document = this.documentUtil.findByIdWithFullInfo(this.documentId);

		Representation result;
		if (document != null) {
			Map<String, Object> dataModel = new HashMap<String, Object>();
            User currentUser = UserUtil.getCurrentUser(this);
            dataModel.put("user", currentUser);
            dataModel.put("document", document);
			Representation mailVtl = new ClientResource(
					LocalReference.createClapReference("/source/template")
							+ "/docmodify.vtl").get();
			result = new TemplateRepresentation(mailVtl, dataModel,
					MediaType.TEXT_HTML);
		} else {
			result = new StringRepresentation("No such document");
		}
		return result;
	}

	@Override
	public Representation modify(Form form) {
		try {
			Map<String, String> values = this._createValueMap(form);
			this.documentUtil.update(this.documentId, values);
		} catch (Exception e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			e.printStackTrace();
		}
		return new StringRepresentation("Modify document successfully");
	}

	@Override
	public Representation add(Form form) {
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

	@Override
	public Representation remove() {
		return null;
	}

	private Map<String, String> _createValueMap(Form form) throws Exception {
		Map<String, String> values = new HashMap<String, String>();
		String title = form.getFirstValue(Document.kTitleProperty);
		if (title != null && !title.isEmpty()) {
			values.put(Document.kTitleProperty, title);
		}
		String author = form.getFirstValue(Document.kAuthorProperty);
		if (author != null && !author.isEmpty()) {
			values.put(Document.kAuthorProperty, author);
		}
		String abstracts = form.getFirstValue(Document.kAbstractsProperty);
		if (abstracts != null && !abstracts.isEmpty()) {
			values.put(Document.kAbstractsProperty, abstracts);
		}
		String keywords = form.getFirstValue(Document.kKeywordsProperty);
		if (keywords != null && !keywords.isEmpty()) {
			values.put(Document.kKeywordsProperty, keywords);
		}
		String url = form.getFirstValue(Document.kUrlProperty);
		if (url != null && !url.isEmpty()) {
			values.put(Document.kUrlProperty, url);
		}
		String publisher = form.getFirstValue(Document.kPublisherProperty);
		if (publisher != null && !publisher.isEmpty()) {
			values.put(Document.kPublisherProperty, publisher);
		}
		String year = form.getFirstValue(Document.kYearProperty);
		if (year != null && !year.isEmpty()) {
			values.put(Document.kYearProperty, year);
		}
		String pages = form.getFirstValue(Document.kPagesProperty);
		if (pages != null && !pages.isEmpty()) {
			values.put(Document.kPagesProperty, pages);
		}
		String documentType = form
				.getFirstValue(Document.kDocumentTypeProperty);
		if (documentType != null && !documentType.isEmpty()) {
			values.put(Document.kDocumentTypeProperty, documentType);
		}
		return values;
	}
}
