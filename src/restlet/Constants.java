package restlet;

/**
 * Created by justinyang on 13-12-18.
 */
public class Constants {

    // Roles
    public static final String kAdminRole = "Admin";
    public static final String kNormalRole = "Normal";
    public static final String kUnauthorizedRole = "Unauthorized";

    // Authority of system Roles
    public static enum Authority {
        Unauthorized, Normal, Admin
    };

    // Using for user info form
    public static final String kNewPasswordField = "newPassword";
    public static final String kOldPasswordField = "oldPassword";


    // Using for document form
    public static final String kTitleField = "title";
    public static final String kAuthorField = "author";
    public static final String kYearField = "year";
    public static final String kPagesField = "pages";
    public static final String kAbstractsField = "abstracts";
    public static final String kKeywordsField = "keywords";
    public static final String kUrlField = "url";
    public static final String kPublisherField = "publisher";
    public static final String kDocumentTypeField = "documentType";

}
