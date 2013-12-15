package common;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface LoginResourceInterface {
    @Get
    public Representation login() throws Exception;

    @Put
    public Representation authenticate(Form form);
    
    @Delete
    public Representation logout(String username);
}
