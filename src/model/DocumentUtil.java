package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.List;
import java.util.Map;

import dao.Document;
import dao.DocumentType;
import dao.User;
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

		this._updateValues(document, values);
		
		// Link user with this document
		document.setUser(user);
		
		HibernateUtil.persist(document);

		return document;
	}

	public Document update(Integer docId, Map<String, String> values)
			throws Exception {
		Document document = this.findById(docId);

		if (document != null) {
			this._updateValues(document, values);
		}

		HibernateUtil.update(document);

		return document;
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

	private void _updateValues(Document document, Map<String, String> values) throws Exception {
		this._updateBasisValues(document, values);
		
		if (values.containsKey(Constants.kDocumentTypeField)) {
			Integer docTypeId = Integer.parseInt((String)values.get(Constants.kDocumentTypeField));
			this._updateDocumentType(document, docTypeId);
		}
	}

	private void _updateBasisValues(Document document,
			Map<String, String> values) {
		if (values.containsKey(Constants.kTitleField)) {
			String title = (String) values.get(Constants.kTitleField);
			document.setTitle(title);
		}

		if (values.containsKey(Constants.kAuthorField)) {
			String author = (String) values.get(Constants.kAuthorField);
			document.setAuthor(author);
		}

		if (values.containsKey(Constants.kAbstractsField)) {
			String abstracts = (String) values.get(Constants.kAbstractsField);
			document.setAbstracts(abstracts);
		}

		if (values.containsKey(Constants.kKeywordsField)) {
			String keywords = (String) values.get(Constants.kKeywordsField);
			document.setKeywords(keywords);
		}

		if (values.containsKey(Constants.kUrlField)) {
			String url = (String) values.get(Constants.kUrlField);
			document.setUrl(url);
		}

		if (values.containsKey(Constants.kPublisherField)) {
			String publisher = (String) values.get(Constants.kPublisherField);
			document.setPublisher(publisher);
		}

		if (values.containsKey(Constants.kPagesField)) {
			Integer pages = Integer.parseInt((String) values
					.get(Constants.kPagesField));
			document.setPages(pages);
		}

		if (values.containsKey(Constants.kYearField)) {
			String year = (String) values.get(Constants.kYearField);
			document.setYear(year);
		}

		document.setCreateTime(new Date());
	}
	
	private void _updateDocumentType(Document document, Integer docTypeId) throws Exception {
		DocumentTypeUtil  documentTypeUtil = new DocumentTypeUtil();
		DocumentType docType = documentTypeUtil.findById(docTypeId);
		document.setDocumentType(docType);
	}
}
