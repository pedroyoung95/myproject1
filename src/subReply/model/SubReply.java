package subReply.model;

import java.util.Date;

public class SubReply {
	
	private int num;
	private int contentNum;		
	private int replyId;
	private String registerid;
	private String body;
	private Date regDate;
	private Date modfiedDate;	
	
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getContentNum() {
		return contentNum;
	}
	public void setContentNum(int contentNum) {
		this.contentNum = contentNum;
	}
	public Date getModfiedDate() {
		return modfiedDate;
	}
	public void setModfiedDate(Date modfiedDate) {
		this.modfiedDate = modfiedDate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
}
