package model;

// Generated Dec 16, 2013 7:25:34 PM by Hibernate Tools 4.0.0

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import dao.Attachment;
import dao.Document;

/**
 * Utility object for domain model class Attachment.
 * @see dao.Attachment
 * @author Justin Yang
 */
public class AttachmentUtil {
	
	public void add(Attachment transientInstance) throws Exception {
		HibernateUtil.persist(transientInstance);
	}

	public void remove(Attachment persistentInstance) throws Exception {
		HibernateUtil.remove(persistentInstance);
	}

	public void update(Attachment detachedInstance) throws Exception {
		if (detachedInstance != null) {
			HibernateUtil.update(detachedInstance);
		}
	}

	public Attachment findById(int id) throws Exception {
		return (Attachment) HibernateUtil.findById(Attachment.class, id);
	}

	public void addAttachments(Integer documentId, List<File> files) throws Exception {
		DocumentUtil documentUtil = new DocumentUtil();
		Document document = documentUtil.findById(documentId);
		if (document == null) {
			throw new Exception("Cannot find document with id: " + documentId);
		}
		
		List<Attachment> attachments = new ArrayList<Attachment>(0);
		for (File file : files) {
			Attachment attachment = new Attachment();
			attachment.setName(file.getName());
			attachment.setUrl(file.getPath());
			attachment.setDocument(document);
			
			attachments.add(attachment);
		}
		
		HibernateUtil.persist(attachments);
	}
	
	public void removeAttachments(List<Integer> attachmentIds) throws Exception {
		for (Integer attachmentId : attachmentIds) {
			Attachment attachment = this.findById(attachmentId);
			this.remove(attachment);
		}
	}
}
