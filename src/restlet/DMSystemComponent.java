package restlet;

import org.restlet.Client;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Parameter;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;
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

		// Configure the default virtual host
		VirtualHost host = getDefaultHost();

		// Attach the application to the default virtual host
		host.attachDefault(new DMSystemApplication());

		// Configure the log service
		getLogService().setLoggerName("DMSystem.AccessLog");
		getLogService().setLogPropertiesRef("clap:///log.properties");
	}
}
