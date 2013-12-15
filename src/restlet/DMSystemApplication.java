package restlet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import controller.AccountResource;
import controller.LoginResource;

/**
 * The reusable mail server application.
 */
public class DMSystemApplication extends Application {
	
    /**
     * Creates a root Router to dispatch call to server resources.
     */
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/", LoginResource.class);
        router.attach("/login/", LoginResource.class);
        router.attach("/logout/{accountId}", LoginResource.class);
        router.attach("/authenticate/", LoginResource.class);
        
        
        return router;
    }

}