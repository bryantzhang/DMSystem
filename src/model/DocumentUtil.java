package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.*;
import restlet.Constants;

/**
 * Utility object for domain model class Document.
 * 
 * @see dao.Document
 * @author Justin Yang
 */
public class DocumentUtil {

	public Document create(User user, Map<String, String> values) throws Exception {
		if (user == null) {
			throw new Exception();
		}
		
		Document document = new Document();
		
		// Link user with this document
		document.setUser(user);

        // Set document type
        this._updateDocumentType(document, values);

        // Set time
        document.setCreateTime(new Date());

        // Save document
		HibernateUtil.persist(document);

		return this.update(document.getId(), values);
	}

	public Document update(Integer docId, Map<String, String> values)
			throws Exception {
		Document document = this.findById(docId);

		return this._update(document, values);
	}

	public void add(Document transientInstance) throws Exception {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(Document persistentInstance) throws Exception {
		HibernateUtil.remove(persistentInstance);
	}

	public void update(Document detachedInstance) throws Exception {
		if (detachedInstance != null) {
			HibernateUtil.update(detachedInstance);
		}
	}

	public Document findById(int id) throws Exception {
		return (Document) HibernateUtil.findById(Document.class, id);
	}

    public static Document findByIdWithFullInfo(int id) throws Exception {
        Document document = (Document)HibernateUtil.findById(Document.class, id);
        return (Document)HibernateUtil.initialize(document);
    }

	@SuppressWarnings("unchecked")
	public List<Document> getAll() throws Exception {
		return HibernateUtil.getAll(Document.class);
	}

    private Document _update(Document document, Map<String, String> values) throws Exception {
        if (document != null) {
            this._updateBasisValues(document, values);

            HibernateUtil.update(document);
        }

        return document;
    }

	private void _updateBasisValues(Document document,
			Map<String, String> values) throws Exception {
        Set<String> keys = values.keySet();

        for (String key : keys) {
            if (key.equals(Constants.kTitleField)) {
                String title = (String) values.get(key);
                document.setTitle(title);
            } else if (key.equals(Constants.kAuthorField)) {
                String author = (String) values.get(key);
                document.setAuthor(author);
            } else if (key.equals(Constants.kAbstractsField)) {
                String abstracts = (String) values.get(key);
                document.setAbstracts(abstracts);
            } else if (key.equals(Constants.kKeywordsField)) {
                String keywords = (String) values.get(key);
                document.setKeywords(keywords);
            } else if (key.equals(Constants.kUrlField)) {
                String url = (String) values.get(key);
                document.setUrl(url);
            } else if (key.equals(Constants.kPublisherField)) {
                String publisher = (String) values.get(key);
                document.setPublisher(publisher);
            } else if (key.equals(Constants.kPagesField)) {
                String pagesStr = (String) values
                        .get(key);
                Integer pages = 0;
                if (!pagesStr.isEmpty()) {
                    pages =  Integer.parseInt(pagesStr);
                }
                document.setPages(pages);
            } else if (key.equals(Constants.kYearField)) {
                String year = (String) values.get(key);
                document.setYear(year);
            } else {
                String[] components = key.split("-");
                if (components.length == 2) {
                    String extraPropertyName = components[0];
                    Integer extraPropertyId = Integer.parseInt(components[1]);

                    DocumentExtraProperty extraProperty = DocumentExtraPropertyUtil.findById(extraPropertyId);
                    DocumentWithExtraProperty documentWithExtraProperty = DocumentWithExtraPropertyUtil.find(document, extraProperty);
                    if (documentWithExtraProperty == null) {
                        documentWithExtraProperty = new DocumentWithExtraProperty();

                        documentWithExtraProperty.setDocument(document);
                        documentWithExtraProperty.setDocumentExtraProperty(extraProperty);
                        documentWithExtraProperty.setPropertyValue(values.get(key));
                        DocumentWithExtraPropertyUtil.add(documentWithExtraProperty);
                    } else {
                        documentWithExtraProperty.setPropertyValue(values.get(key));
                        DocumentWithExtraPropertyUtil.update(documentWithExtraProperty);
                    }
                    document.getExtraProperties();
                }
            }
        }
	}
	
	private void _updateDocumentType(Document document, Map<String, String> values) throws Exception {
        if (values.containsKey(Constants.kDocumentTypeField)) {
            Integer docTypeId = Integer.parseInt((String) values.get(Constants.kDocumentTypeField));
            DocumentType docType = DocumentTypeUtil.findById(docTypeId);
            document.setDocumentType(docType);
        }
	}
}
