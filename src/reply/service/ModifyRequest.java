package reply.service;

import java.util.Map;

public class ModifyRequest {

	private int replyid;
	private String registerid;
	private String body;
	
	public int getReplyid() {
		return replyid;
	}
	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}
	public String getRegisterid() {
		return registerid;
	}
	public void setRegisterid(String registerid) {
		this.registerid = registerid;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if(body == null || body.trim().isEmpty()) {
			errors.put("noReply", true);
		}
	}
	
	
}
