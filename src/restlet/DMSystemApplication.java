package restlet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import org.restlet.routing.Template;
import org.restlet.security.Role;
import org.restlet.security.RoleAuthorizer;

import controller.AccountResource;
import controller.CookieAuthenticator;
import controller.DocumentResource;
import controller.DocumentsResource;
import controller.LoginResource;


public class DMSystemApplication extends Application {
	public static final String kAdminRole = "Admin";
	public static final String kNormalRole = "Normal";

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
        Router adminRouter = new Router(getContext());
        adminRouter.attach("/index", AccountResource.class);
        
        RoleAuthorizer adminAuthorizer = new RoleAuthorizer();
        adminAuthorizer.getAuthorizedRoles().add(getRole(DMSystemApplication.kAdminRole));
        adminAuthorizer.setNext(adminRouter);

        Router userRouter = new Router(getContext());
        userRouter.attach("/index", DocumentsResource.class);
        userRouter.attach("/addDoc", DocumentResource.class);
        userRouter.attach("/getDoc/{documentId}", DocumentResource.class);
        userRouter.attach("/modifyDoc/{documentId}", DocumentResource.class);
        userRouter.attach("/deleteDoc/{documentId}", DocumentResource.class);
        
        RoleAuthorizer normalAuthorizer = new RoleAuthorizer();
        normalAuthorizer.getAuthorizedRoles().add(getRole(DMSystemApplication.kNormalRole));
        normalAuthorizer.setNext(userRouter);
        
        Router roleRouter = new Router(getContext());
        roleRouter.attach("/logout/{username}", LoginResource.class);
        roleRouter.attach("/index", LoginResource.class);
        roleRouter.attach("/admin", adminAuthorizer).setMatchingMode(Template.MODE_STARTS_WITH);
        roleRouter.attach("/normal", normalAuthorizer).setMatchingMode(Template.MODE_STARTS_WITH);
        
        CookieAuthenticator authenticator = new CookieAuthenticator(
                getContext(), "My Realm");
        authenticator.setNext(roleRouter);
        
        Router defaultRouter = new Router(getContext());
        defaultRouter.attach("/", LoginResource.class);
        defaultRouter.attach("/login", LoginResource.class);
        defaultRouter.attach("/doc", authenticator).setMatchingMode(Template.MODE_STARTS_WITH);
        defaultRouter.attach("/user", authenticator).setMatchingMode(Template.MODE_STARTS_WITH);
        
        return defaultRouter;
    }

}