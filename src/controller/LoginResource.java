package controller;

import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import restlet.Constants;
import common.LoginResourceInterface;

public class LoginResource extends ServerResource implements
		LoginResourceInterface {

	@Override
	public void login() {
		if (getRequest().getClientInfo().isAuthenticated()) {
			if (isInRole(Constants.kAdminRole)) {
				String redirectUrl = "/user/admin/adminuser";
				getResponse().redirectSeeOther(redirectUrl);
			} else if (isInRole(Constants.kNormalRole)) {
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
    public void redirect(Representation input) {
        if (isInRole(Constants.kAdminRole)) {
			String redirectUrl = "/user/admin/adminuser";
			getResponse().redirectSeeOther(redirectUrl);
		} else if (isInRole(Constants.kNormalRole)) {
			String redirectUrl = "/user/normal/index";
			getResponse().redirectSeeOther(redirectUrl);
		}
    }

	@Override
	public Representation logout(String username) {
        getResponse().getCookieSettings().removeFirst(Constants.kCredentialsKey);
        Representation loginvtl = new ClientResource(
                LocalReference.createClapReference("/source/template")
                        + "/login.vtl").get();
		return new TemplateRepresentation(loginvtl, MediaType.TEXT_HTML);
	}

}
