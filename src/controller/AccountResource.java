package controller;

import dao.User;
import model.UserUtil;
import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ServerResource;

import common.AccountResourceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountResource extends ServerResource implements
		AccountResourceInterface {

	@Override
	public Representation retrieveAccounts() {
        Representation result;

        try {
            List<User> users = UserUtil.getAllUser();

            Map<String, Object> dataModel = new HashMap<String, Object>();
            dataModel.put("users", users);
            Representation mailVtl = new ClientResource(
                    LocalReference.createClapReference("/source/template")
                            + "/adminuser.vtl").get();
            result = new TemplateRepresentation(mailVtl, dataModel,
                    MediaType.TEXT_HTML);
        } catch (Exception exception) {
            result = new StringRepresentation("Something error");
        }
        return result;
	}

	@Override
	public Representation add(Form form) {
		if (isInRole("Admin")) {
			
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation remove() {
		return null;
	}
}
