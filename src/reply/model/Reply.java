package reply.model;

import java.util.Date;

public class Reply {
	
	private int id;
	private String registerid;
	private int contentNum;
	private String body;
	private Date regDate;
	private Date modfiedDate;
	
	public Date getModfiedDate() {
		return modfiedDate;
	}
	public void setModfiedDate(Date modfiedDate) {
		this.modfiedDate = modfiedDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRegisterid() {
		return registerid;
	}
	public void setRegisterid(String registerid) {
		this.registerid = registerid;
	}
	public int getContentNum() {
		return contentNum;
	}
	public void setContentNum(int contentNum) {
		this.contentNum = contentNum;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
}
