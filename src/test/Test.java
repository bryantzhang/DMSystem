package test;

import java.util.Date;

import dao.Attachment;
import dao.Document;
import dao.DocumentExtraProperty;
import dao.DocumentRelation;
import dao.DocumentType;
import dao.DocumentWithExtraProperty;
import dao.Evaluation;
import dao.EvaluationExtraProperty;
import dao.EvaluationWithExtraProperty;
import dao.Operation;
import dao.RelationType;
import dao.Tag;
import dao.User;

import org.hibernate.Session;

import dao.HibernateUtil;
 
public class Test {
    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
 
        session.beginTransaction();
        
        User user = new User();
        user.setName("justin");
        user.setPassword("123");
        user.setUsername("hello");
        session.save(user);
        
        DocumentType docType = new DocumentType();
        docType.setName("issue");
        session.save(docType);
        
        Document document = new Document();
 
        document.setTitle("Hello world");
        document.setAuthor("justin");
        document.setYear(new Date());
        document.setAbstracts("This is a abstract");
        document.setKeywords("This is a keyword");
        document.setPages(12);
        document.setUrl("http://www.baidu.com/baike/whatwrong");
        document.setCreateTime(new Date());
        document.setPublisher("Renming Publisher");
        
        document.setUser(user);
        user.getDocuments().add(document);
        
        document.setDocumentType(docType);
        docType.getDocuments().add(document);
        
        session.save(document);
        
        Attachment attachment = new Attachment();
        attachment.setName("sfdas.pdf");
        attachment.setUrl("http://asdfasdf.com/sadfasdf");
        
        document.getAttachments().add(attachment);
        attachment.setDocument(document);
        
        session.save(attachment);
        
        Evaluation evaluation = new Evaluation();
        evaluation.setContent("helloworld");
        evaluation.setPoint(5);
        evaluation.setPublished(true);
        evaluation.setType(1);
        
        evaluation.setDocument(document);
        evaluation.setUser(user);
        
        session.save(evaluation);
        
        Tag tag = new Tag();
        tag.setName("java");
        
        tag.setDocument(document);
        
        session.save(tag);
        
        Operation operation = new Operation();
        operation.setExpression("Add a new document");
        operation.setTime(new Date());
        operation.setType(3);
        
        operation.setUser(user);
        
        session.save(operation);
        
        Document document2 = new Document();
        document2.setTitle("Hello world 2");
        document2.setAuthor("justin");
        document2.setYear(new Date());
        document2.setAbstracts("This is a abstract");
        document2.setKeywords("This is a keyword");
        document2.setPages(12);
        document2.setUrl("http://www.baidu.com/baike/whatwrong");
        document2.setCreateTime(new Date());
        document2.setPublisher("Renming Publisher");
        
        document2.setUser(user);
        document2.setDocumentType(docType);
        
        session.save(document2);
        
        RelationType relationType = new RelationType();
        relationType.setName("Reference");
        session.save(relationType);

        DocumentRelation documentRelation = new DocumentRelation();
        documentRelation.setReferer(document);
        documentRelation.setReferee(document2);
        documentRelation.setRelationType(relationType);
        
        document.getRefererRelations().add(documentRelation);
        
        session.save(documentRelation);
        
        DocumentExtraProperty documentExtraProperty = new DocumentExtraProperty();
        documentExtraProperty.setPropertyName("Home");
        
        documentExtraProperty.setDocumentType(docType);
        
        session.save(documentExtraProperty);
        
        DocumentWithExtraProperty documentWithExtraProperty = new DocumentWithExtraProperty();
        documentWithExtraProperty.setDocument(document);
        documentWithExtraProperty.setDocumentExtraProperty(documentExtraProperty);
        documentWithExtraProperty.setPropertyValue("Nanjing");
        
        session.save(documentWithExtraProperty);
        
        EvaluationExtraProperty evaluationExtraProperty = new EvaluationExtraProperty();
        evaluationExtraProperty.setPropertyName("Feeling");
        
        session.save(evaluationExtraProperty);
        
        EvaluationWithExtraProperty evaluationWithExtraProperty = new EvaluationWithExtraProperty();
        evaluationWithExtraProperty.setEvaluation(evaluation);
        evaluationWithExtraProperty.setEvaluationExtraProperty(evaluationExtraProperty);
        evaluationWithExtraProperty.setPropertyValue("Oh yeah");
        
        session.save(evaluationWithExtraProperty);

        session.getTransaction().commit();
    }
}