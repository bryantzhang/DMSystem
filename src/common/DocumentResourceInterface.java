package common;

import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

public interface DocumentResourceInterface {

    @Get
    public Representation retrive(Variant variant) throws Exception;

    @Put
    public void modify(Representation entity);
    
    @Post
    public void add(Representation entity);

    @Delete
    public void remove(Variant variant);
}
