package controller;

import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ServerResource;

import common.LoginResourceInterface;

public class LoginResource extends ServerResource implements
		LoginResourceInterface {

	@Override
	public Representation login() throws Exception {
		Representation mailVtl = new ClientResource(
				LocalReference.createClapReference(getClass().getPackage())
						+ "/Login.vtl").get();
		return new TemplateRepresentation(mailVtl, MediaType.TEXT_HTML);
	}

	@Override
	public Representation authenticate(Form form) {
		boolean success = false;
		
		Parameter usernameParameter = form.getFirst("username");
		Parameter passwordParameter = form.getFirst("password");
		
		if (usernameParameter != null && passwordParameter != null) {
			String username = usernameParameter.getValue();
			String password = passwordParameter.getValue();
			
			if (username.equals("admin") && password.equals("admin")) {
				success = true;
			}
		}

		if (success) {
			return new StringRepresentation("Login Success, welcome");
		} else {
			Representation mailVtl = new ClientResource(
					LocalReference.createClapReference(getClass().getPackage())
							+ "/Login.vtl").get();
			return new TemplateRepresentation(mailVtl,
					MediaType.TEXT_HTML);
		}
	}

	@Override
	public Representation logout(String username) {
		Representation mailVtl = new ClientResource(
				LocalReference.createClapReference(getClass().getPackage())
						+ "/Login.vtl").get();
		return new TemplateRepresentation(mailVtl, MediaType.TEXT_HTML);
	}

}
