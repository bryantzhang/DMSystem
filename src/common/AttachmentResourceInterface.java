package common;

import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface AttachmentResourceInterface {

    @Get
    public Representation retrieve();
    
    @Post
    public Representation add(Representation input);
    
    @Delete
    public Representation remove();
}
