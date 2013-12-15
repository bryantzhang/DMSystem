package restlet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;
import org.restlet.security.Role;
import org.restlet.security.RoleAuthorizer;

import controller.AccountResource;
import controller.CookieAuthenticator;
import controller.DMSystemSecreteVerifier;
import controller.DocumentResource;
import controller.DocumentsResource;
import controller.LoginResource;


/**
 * The reusable mail server application.
 */
public class DMSystemApplication extends Application {
	public static final String kAdminRole = "Admin";
	public static final String kUserRole = "User";

	public DMSystemApplication() {
		// Declare the supported roles
        getRoles().add(new Role("Admin"));
        getRoles().add(new Role("User"));
	}
	
    /**
     * Creates a root Router to dispatch call to server resources.
     */
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/login", LoginResource.class);
        router.attach("/user", DocumentsResource.class);
        router.attach("/logout/{username}", LoginResource.class);
        router.attach("/document/add", DocumentResource.class);
        router.attach("/document/retrieve/{documentId}", DocumentResource.class);
        router.attach("/document/modify/{documentId}", DocumentResource.class);
        router.attach("/document/delete/{documentId}", DocumentResource.class);
        
//        RoleAuthorizer authorizer = new RoleAuthorizer();
//        authorizer.getAuthorizedRoles().add(getRole(DMSystemApplication.kUserRole));
//        authorizer.setNext(router);
        
//        RoleAuthorizer adminAuthorizer = new RoleAuthorizer();
//        authorizer.getAuthorizedRoles().add(getRole(DMSystemApplication.kAdminRole));

        CookieAuthenticator authenticator = new CookieAuthenticator(
                getContext(), "My Realm");
//        authenticator.setNext(authorizer);
//        authenticator.setVerifier(new DMSystemSecreteVerifier());
        authenticator.setNext(router);
        
        return authenticator;
    }

}