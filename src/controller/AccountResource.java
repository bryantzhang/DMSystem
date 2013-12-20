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
	private UserUtil userutil=new UserUtil();

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
        } catch (Exception ex) {
            ex.printStackTrace();
            result = new StringRepresentation("Something error");
        }
        return result;
	}

	@Override
	public Representation add(Form form) {
		Representation result = null;
		if (isInRole("Admin")) {
			Map<String, String> values = form.getValuesMap();
			try {
				this.userutil.add(values);
				result=this.retrieveAccounts();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result=new StringRepresentation("add unsucccess!");
			}
		}
		return result;
	}

	@Override
	public Representation remove() {
		int userid = Integer.parseInt((String) getRequest().getAttributes().get("id"));
		Representation result=null;
		if(isInRole("Admin")){
			try {
				this.userutil.remove(userid);
				result=this.retrieveAccounts();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result=new StringRepresentation("remove unsucccess!");
			}
		}
		return result;
	}
}
