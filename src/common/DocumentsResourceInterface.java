package common;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface DocumentsResourceInterface {

    @Get
    public Representation list() throws Exception;

    @Put
    public Representation search(Form form);
}
