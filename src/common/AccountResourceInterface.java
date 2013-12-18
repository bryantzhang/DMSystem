package common;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.data.Form;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

/**
 * User account resource.
 */
public interface AccountResourceInterface {

    @Get
    public Representation retrieveAccounts();
    
    @Post
    public Representation add(Form form);
    
    @Delete
    public Representation remove();

}
