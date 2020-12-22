package auth.service;

import java.util.Date;

public class User {
	
	private String id;
	private String name;
	private Date regdate;
	private int wroteCnt;
	private int replyCnt;
	
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	public int getWroteCnt() {
		return wroteCnt;
	}
	public void setWroteCnt(int wroteCnt) {
		this.wroteCnt = wroteCnt;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
