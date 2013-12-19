package controller;

import common.DocumentImportResourceInterface;
import dao.Document;
import dao.User;
import model.DocumentUtil;
import model.UserUtil;
import org.restlet.data.Form;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ServerResource;
import restlet.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by justinyang on 13-12-18.
 */
public class DocumentImportResource extends ServerResource implements DocumentImportResourceInterface {
    private DocumentUtil documentUtil = new DocumentUtil();

    @Override
    public Representation present() {
        try {
            Map<String, Object> dataModel = new HashMap<String, Object>();
            User currentUser = UserUtil.getCurrentUser(this);
            dataModel.put("user", currentUser);

            Representation mailVtl = new ClientResource(
                    LocalReference.createClapReference("/source/template")
                            + "/docimport.vtl").get();
            return new TemplateRepresentation(mailVtl, dataModel,
                    MediaType.TEXT_HTML);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new StringRepresentation("Something error when listing documents");
        }
    }

    @Override
    public void upload(Form form) {
        try {
            Map<String, String> values = form.getValuesMap();
            String username = getClientInfo().getUser().getIdentifier();
            UserUtil userUtil = new UserUtil();
            User user = userUtil.findByUsername(username);
            this.documentUtil.create(user, values);
            String redirectUrl = "/user/normal/index";
            getResponse().redirectSeeOther(redirectUrl);
        } catch (Exception e) {
            e.printStackTrace();
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            Representation modifyVtl = this.present();
            getResponse().setEntity(modifyVtl);
        }
    }
}
