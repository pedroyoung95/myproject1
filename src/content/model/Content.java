package content.model;

import java.util.Date;

public class Content {

	private Integer number;
	private Writer writer;
	private String title;
	private String body;
	private Date regDate;
	private Date modifiedDate;
	private int readCount;
	
	public Content(Integer number, Writer writer, String title, String body, Date regDate, Date modifiedDate, int readCount) {
		super();
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.readCount = readCount;
	}
	public Integer getNumber() {
		return number;
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
	public Date getRegDate() {
		return regDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public int getReadCount() {
		return readCount;
	}
}
