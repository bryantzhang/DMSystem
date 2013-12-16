package model;

import java.util.Map;

/**
 * 
 * @author bryant zhang
 * 
 */
public class Evaluation {
	private String content;
	private int point;
	private boolean published;
	private int type;
	private String username;
	private Map<String, String> otherEvaluationProperties;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Map<String, String> getOtherEvaluationProperties() {
		return otherEvaluationProperties;
	}

	public void setOtherEvaluationProperties(
			Map<String, String> otherEvaluationProperties) {
		this.otherEvaluationProperties = otherEvaluationProperties;
	}

}
