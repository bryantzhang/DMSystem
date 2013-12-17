package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.DocumentUtil;

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

public class DocumentsResource extends ServerResource implements
DocumentsResourceInterface {
	
	private DocumentUtil documentUtil = new DocumentUtil();
		
	@Override
	public Representation list() throws Exception {
		List<Document> documents = this.documentUtil.getAll();
		Map<String, Object> dataModel = new HashMap<String, Object>();
		dataModel.put("documents", documents);
		Representation mailVtl = new ClientResource(
				LocalReference.createClapReference("/source/html")
						+ "/document_list.vtl").get();
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
