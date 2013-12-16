package restlet;

import org.restlet.Client;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Parameter;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;
import org.restlet.security.MemoryRealm;
import org.restlet.security.User;
import org.restlet.util.Series;

public class DMSystemComponent extends Component {
	/**
	 * Launches the mail server component.
	 * 
	 * @param args
	 *            The arguments.
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new DMSystemComponent().start();
	}

	/**
	 * Constructor.
	 * 
	 * @throws Exception
	 */
	public DMSystemComponent() throws Exception {
		// Set basic properties
		setName("DMSystem Server component");
		setDescription("Document Management System");
		setOwner("Software college");
		setAuthor("The Code Team");

		// Add connectors
		getClients().add(new Client(Protocol.CLAP));
		
		Server server = new Server(new Context(), Protocol.HTTPS, 8183);
		Series<Parameter> parameters = server.getContext().getParameters();
		parameters.add("keystorePath", "src/serverKey.jks");
		parameters.add("keystorePassword", "password");
		parameters.add("keystoreType", "JKS");
		parameters.add("keyPassword", "password");
		// Tracing
		parameters.set("tracing", "true");
		getServers().add(server);

		DMSystemApplication app = new DMSystemApplication();
		
		// Configure the default virtual host
		VirtualHost host = getDefaultHost();

		// Attach the application to the default virtual host
		host.attachDefault(app);

		// Configure the security realm
        MemoryRealm realm = new MemoryRealm();
        User admin = new User("admin", "admin");
        realm.getUsers().add(admin);
        realm.map(admin, app.getRole(DMSystemApplication.kAdminRole));

        User normal = new User("user", "user");
        realm.getUsers().add(normal);
        realm.map(normal, app.getRole(DMSystemApplication.kNormalRole));

        // Set the realm's default enroler and verifier
        app.getContext().setDefaultEnroler(realm.getEnroler());
        app.getContext().setDefaultVerifier(realm.getVerifier());
        
		// Configure the log service
		getLogService().setLoggerName("DMSystem.AccessLog");
		getLogService().setLogPropertiesRef("clap:///log.properties");
	}
}
