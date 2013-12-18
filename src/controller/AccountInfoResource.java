package controller;

import common.AccountInfoResourceInterface;
import dao.User;
import model.UserUtil;
import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import restlet.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by justinyang on 13-12-18.
 */
public class AccountInfoResource extends ServerResource implements AccountInfoResourceInterface {
    private Integer userId;

    @Override
    protected void doInit() throws ResourceException {
        String userIdAttribute = getAttribute("userId");

        if (userIdAttribute != null) {
            this.userId = Integer.parseInt(userIdAttribute);
        }
    }

    @Override
    public Representation retrieveInfo() {
        Representation result;

        try {
            User user = UserUtil.findById(this.userId);

            Map<String, Object> dataModel = new HashMap<String, Object>();
            dataModel.put("user", user);
            Representation mailVtl = new ClientResource(
                    LocalReference.createClapReference("/source/template")
                            + "/modifypwd.vtl").get();
            result = new TemplateRepresentation(mailVtl, dataModel,
                    MediaType.TEXT_HTML);
        } catch (Exception exception) {
            result = new StringRepresentation("No such user");
        }
        return result;
    }

    @Override
    public void updateInfo(Form form) {
        try {
            User user = UserUtil.findById(this.userId);

            String oldPassword = form.getFirstValue(Constants.kOldPasswordField);
            if (oldPassword == null || oldPassword.isEmpty() || !user.getPassword().equals(oldPassword)) {
                throw new Exception();
            }

            String newPassword = form.getFirstValue(Constants.kNewPasswordField);
            String comfirmPassword = form.getFirstValue(Constants.kNewPasswordConfirmField);
            if (newPassword == null || newPassword.isEmpty() || comfirmPassword == null || !comfirmPassword.equals(newPassword)) {
                throw new Exception();
            }
            user.setPassword(newPassword);
            UserUtil.update(user);
            String redirectUrl = "/login";
            getResponse().redirectSeeOther(redirectUrl);
        } catch (Exception exception) {
            getResponse().setStatus(Status.CLIENT_ERROR_PRECONDITION_FAILED);
            //TODO:: send response to client
        }
    }
}
