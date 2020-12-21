package content.service;

import java.util.Map;	

import content.model.Writer;

public class WriteRequest {
	
	private Writer writer;
	private String title;
	private String body;
	
	public WriteRequest(Writer writer, String title, String body) {
		super();
		this.writer = writer;
		this.title = title;
		this.body = body;
	}
	public Writer getWriter() {
		return writer;
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
