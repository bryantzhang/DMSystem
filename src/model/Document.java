package model;

import java.util.ArrayList;
import java.util.Map;

import javax.management.relation.RelationType;

/**
 * 
 * @author bryant zhang
 * 
 */
public class Document {
	private int id;
	private int docTypeId;
	private String title;
	private String author;
	private String year;
	private int pages;
	private String abstracts;
	private String keywords;
	private String publisher;
	private String url;
	private String username;
	private String createdTime;
	private ArrayList<Attachment> attachmentList;
	private ArrayList<Evaluation> evaluationList;
	private Map<String, String> otherProperties;
	private ArrayList<RelationType> relationList;
	private ArrayList<Tag> tagList;

	public Document(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDocTypeId() {
		return docTypeId;
	}

	public void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreateTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public ArrayList<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(ArrayList<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public ArrayList<Evaluation> getEvaluationList() {
		return evaluationList;
	}

	public void setEvaluationList(ArrayList<Evaluation> evaluationList) {
		this.evaluationList = evaluationList;
	}

	public Map<String, String> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(Map<String, String> otherProperties) {
		this.otherProperties = otherProperties;
	}

	public ArrayList<RelationType> getRelationList() {
		return relationList;
	}

	public void setRelationList(ArrayList<RelationType> relationList) {
		this.relationList = relationList;
	}

	public ArrayList<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList<Tag> tagList) {
		this.tagList = tagList;
	}

}
