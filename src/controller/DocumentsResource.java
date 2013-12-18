package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.DocumentUtil;

import model.UserUtil;
import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ServerResource;

import common.DocumentsResourceInterface;
import dao.Document;
import dao.User;

public class DocumentsResource extends ServerResource implements
DocumentsResourceInterface {
	
	private DocumentUtil documentUtil = new DocumentUtil();

	@Override
	public Representation list() throws Exception {
        User currentUser = UserUtil.getCurrentUser(this);
		List<Document> documents = this.documentUtil.getAll();
		Map<String, Object> dataModel = new HashMap<String, Object>();
		dataModel.put("documents", documents);
        dataModel.put("user", currentUser);

		Representation mailVtl = new ClientResource(
				LocalReference.createClapReference("/source/template")
						+ "/doclist.vtl").get();
		return new TemplateRepresentation(mailVtl, dataModel,
				MediaType.TEXT_HTML);
	}

	@Override
	public Representation search(Form form) {
		List documents = new ArrayList();
		
		Parameter keywordParameter = form.getFirst("keyword");
		
		if (keywordParameter != null) {
			String keyword = keywordParameter.getValue();
			documents.add(keyword);
		}

		if (documents.size() != 0) {
			return new StringRepresentation("Found " + documents.size() + "Documents");
		} else {
			return new StringRepresentation("Empty");
		}
	}

}
