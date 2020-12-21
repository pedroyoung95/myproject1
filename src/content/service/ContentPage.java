package content.service;

import java.util.List;	

import content.model.Content;

public class ContentPage {
	private int total; //총 게시물 수
	private int currentPage; //현재 페이지
	private List<Content> content; //select한 데이터들
	private int totalPages; //총 페이지 수
	private int startPage; //시작 페이지
	private int endPage; //마지막 페이지
	
	public int getTotal() {
		return total;
	}
	public boolean hasNoArticles() {
		return total == 0;
	}
	public boolean hasArticles() {
		return total > 0;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public List<Content> getContent() {
		return content;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setContent(List<Content> content) {
		this.content = content;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public ContentPage(int total, int currentPage, int size, List<Content> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if(total != 0) {
			totalPages = total / size;
			if(total % size > 0) {
				totalPages++;
			}
			this.startPage = (currentPage - 1) / 5 * 5 + 1;			
			this.endPage = startPage + 4;
			if(endPage > totalPages) endPage = totalPages;
		}
	}
}
