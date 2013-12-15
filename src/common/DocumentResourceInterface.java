package common;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface DocumentResourceInterface {

    @Get
    public Representation retrive() throws Exception;

    @Post("?type=modify")
    public Representation modify(Form form);
    
    @Post("?type=add")
    public Representation add(Form form);

    @Delete
    public Representation remove();
}
