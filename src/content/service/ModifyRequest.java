package content.service;

import java.util.Map;

public class ModifyRequest {
	
	private String userId;
	private int contentNumber;
	private String title;
	private String body;
	
	public ModifyRequest(String userId, int contentNumber, String title, String body) {
		this.userId = userId;
		this.contentNumber = contentNumber;
		this.title = title;
		this.body = body;
	}

	public String getUserId() {
		return userId;
	}

	public int getContentNumber() {
		return contentNumber;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(title == null || title.trim().isEmpty()) {
			errors.put("title", true);
		}
	}
}
