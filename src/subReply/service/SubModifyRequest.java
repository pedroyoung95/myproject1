package subReply.service;

import java.util.Map;

public class SubModifyRequest {

	private int subreplyNo;
	private String registerid;
	private String body;
	
	public int getSubreplyNo() {
		return subreplyNo;
	}
	public void setSubreplyNo(int subreplyNo) {
		this.subreplyNo = subreplyNo;
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
			errors.put("noModify", true);
		}
	}
	
	
}
