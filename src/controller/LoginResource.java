package controller;

import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import restlet.DMSystemApplication;
import common.LoginResourceInterface;

public class LoginResource extends ServerResource implements
		LoginResourceInterface {

	@Override
	public void login() throws Exception {
		if (getRequest().getClientInfo().isAuthenticated()) {
			if (isInRole(DMSystemApplication.kAdminRole)) {
				String redirectUrl = "/user/admin/index";
				getResponse().redirectSeeOther(redirectUrl);
			} else if (isInRole(DMSystemApplication.kNormalRole)) {
				String redirectUrl = "/user/normal/index";
				getResponse().redirectSeeOther(redirectUrl);
			}
		} else {
			Representation loginvtl = new ClientResource(
					LocalReference.createClapReference("/source/template")
							+ "/login.vtl").get();
			getResponse().setEntity(
					new TemplateRepresentation(loginvtl, getRequest()
							.getResourceRef(), MediaType.TEXT_HTML));
		}
	}

    
    @Post
    public void redirect(Representation input) throws Exception {
		if (isInRole(DMSystemApplication.kAdminRole)) {
			String redirectUrl = "/user/admin/index";
			getResponse().redirectSeeOther(redirectUrl);
		} else if (isInRole(DMSystemApplication.kNormalRole)) {
			String redirectUrl = "/user/normal/index";
			getResponse().redirectSeeOther(redirectUrl);
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
