package model;

/**
 * 
 * @author bryant zhang
 * 
 */
public class Attachment {
	private int id;
	private String name;
	private String url;
	private int docId;

	public Attachment(int id) {
		this.id = id;
	}

	public Attachment(String name, String url, int docId) {
		this.name = name;
		this.url = url;
		this.docId = docId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
