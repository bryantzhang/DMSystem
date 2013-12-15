package controller;

import java.util.ArrayList;
import java.util.List;

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

public class DocumentsResource extends ServerResource implements
DocumentsResourceInterface {

	@Override
	public Representation list() throws Exception {
		return new StringRepresentation("There are so many document listing here");
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
