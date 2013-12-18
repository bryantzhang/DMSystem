package controller;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.*;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.Verifier;
import restlet.Constants;

public class CookieAuthenticator extends ChallengeAuthenticator {

	public CookieAuthenticator(Context context, boolean optional, String realm) {
		super(context, optional, ChallengeScheme.HTTP_COOKIE, realm);
	}

	public CookieAuthenticator(Context context, boolean optional, String realm,
			Verifier verifier) {
		super(context, optional, ChallengeScheme.HTTP_COOKIE, realm, verifier);
	}

	public CookieAuthenticator(Context context, String realm) {
		super(context, ChallengeScheme.HTTP_COOKIE, realm);
	}

	@Override
	protected void afterHandle(Request request, Response response) {
		super.afterHandle(request, response);
		Cookie cookie = request.getCookies().getFirst(Constants.kCredentialsKey);

		if (request.getClientInfo().isAuthenticated() && (cookie == null)) {
			String identifier = request.getChallengeResponse().getIdentifier();
			String secret = new String(request.getChallengeResponse()
					.getSecret());
			CookieSetting cookieSetting = new CookieSetting(Constants.kCredentialsKey,
					identifier + "=" + secret);
			cookieSetting.setAccessRestricted(true);
			cookieSetting.setPath("/");
			cookieSetting.setComment("Unsecured cookie based authentication");
			cookieSetting.setMaxAge(300);
			response.getCookieSettings().add(cookieSetting);
		}
	}

	@Override
	protected int beforeHandle(Request request, Response response) {
		Cookie cookie = request.getCookies().getFirst(Constants.kCredentialsKey);

		if (cookie != null) {
			// Extract the challenge response from the cookie
			String[] credentials = cookie.getValue().split("=");

			if (credentials.length == 2) {
				String identifier = credentials[0];
				String secret = credentials[1];
				request.setChallengeResponse(new ChallengeResponse(
						ChallengeScheme.HTTP_COOKIE, identifier, secret));
			}
		} else if (Method.POST.equals(request.getMethod())
				&& request.getResourceRef().getQueryAsForm().getFirst("login") == null) {
			// Intercepting a login form
			Form credentials = new Form(request.getEntity());
			String identifier = credentials.getFirstValue("username");
			String secret = credentials.getFirstValue("password");
			request.setChallengeResponse(new ChallengeResponse(
					ChallengeScheme.HTTP_COOKIE, identifier, secret));

			// Continue call processing to return the target representation if
			// authentication is successful or a new login page
			request.setMethod(Method.GET);
		}
		return super.beforeHandle(request, response);
	}

	@Override
	public void challenge(Response response, boolean stale) {
        Representation vtl = new ClientResource(
        LocalReference.createClapReference("/source/template")
                + "/login.vtl").get();
        response.setEntity(new TemplateRepresentation(vtl, response
                .getRequest().getResourceRef(), MediaType.TEXT_HTML));
        response.setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
	}

}
