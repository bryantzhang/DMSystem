package common;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * Created by justinyang on 13-12-18.
 */
public interface AccountInfoResourceInterface {

    @Get
    public Representation retrieveInfo();

    @Post
    public void updateInfo(Form form);
}
