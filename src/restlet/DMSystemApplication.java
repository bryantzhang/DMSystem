package restlet;

import controller.*;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.restlet.routing.Template;
import org.restlet.security.Role;
import org.restlet.security.RoleAuthorizer;

import dao.User;

public class DMSystemApplication extends Application {
	public static final String kAdminRole = "Admin";
	public static final String kNormalRole = "Normal";
	public static final String kUnauthorizedRole = "Unauthorized";

	public DMSystemApplication() {
		// Declare the supported roles
		getRoles().add(new Role(kUnauthorizedRole));
		getRoles().add(new Role(kNormalRole));
		getRoles().add(new Role(kAdminRole));
	}

	public Role getRole(User.Authority authority) {
		Role role = null;
		switch (authority) {
		case Normal:
			role = getApplication().getRole(kNormalRole);
			break;
		case Admin:
			role = getApplication().getRole(kAdminRole);
			break;
		default:
			role = getApplication().getRole(kUnauthorizedRole);
			break;
		}
		return role;
	}

	/**
	 * Creates a root Router to dispatch call to server resources.
	 */
	@Override
	public Restlet createInboundRoot() {
		Router adminRouter = new Router(getContext());
		adminRouter.attach("/index", AccountResource.class);

		RoleAuthorizer adminAuthorizer = new RoleAuthorizer();
		adminAuthorizer.getAuthorizedRoles().add(
				getRole(DMSystemApplication.kAdminRole));
		adminAuthorizer.setNext(adminRouter);

		Router userRouter = new Router(getContext());
		userRouter.attach("/index", DocumentsResource.class);
		userRouter.attach("/addDoc", DocumentResource.class);
		userRouter.attach("/getDoc/{documentId}", DocumentResource.class);
		userRouter.attach("/modifyDoc/{documentId}", DocumentResource.class);
		userRouter.attach("/deleteDoc/{documentId}", DocumentResource.class);
		userRouter.attach("/addAttachment/{documentId}",
				AttachmentResource.class);
		userRouter.attach("/deleteAttachment/{attachmentId}",
				AttachmentResource.class);
        userRouter.attach("/getInfo/{userId}", AccountInfoResource.class);
        userRouter.attach("/updateInfo/{userId}", AccountInfoResource.class);


		RoleAuthorizer normalAuthorizer = new RoleAuthorizer();
		normalAuthorizer.getAuthorizedRoles().add(
				getRole(DMSystemApplication.kNormalRole));
		normalAuthorizer.setNext(userRouter);

		Router roleRouter = new Router(getContext());
		roleRouter.attach("/logout/{username}", LoginResource.class);
		roleRouter.attach("/index", LoginResource.class);
		roleRouter.attach("/admin", adminAuthorizer).setMatchingMode(
				Template.MODE_STARTS_WITH);
		roleRouter.attach("/normal", normalAuthorizer).setMatchingMode(
				Template.MODE_STARTS_WITH);

		CookieAuthenticator authenticator = new CookieAuthenticator(
				getContext(), "My Realm");
		authenticator.setNext(roleRouter);

		Router defaultRouter = new Router(getContext());

		String rootUri = "file:///" + System.getProperty("user.home")
				+ "/Developer/DMSystemResource";
		Directory directory = new Directory(getContext(), rootUri);
		directory.setListingAllowed(true);
		defaultRouter.attach("/source", directory);
		defaultRouter.attach("/", LoginResource.class);
		defaultRouter.attach("/login", LoginResource.class);
		defaultRouter.attach("/doc", authenticator).setMatchingMode(
				Template.MODE_STARTS_WITH);
		defaultRouter.attach("/user", authenticator).setMatchingMode(
				Template.MODE_STARTS_WITH);

		return defaultRouter;
	}

}