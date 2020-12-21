package content.command;

import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;

import content.service.ContentPage;
import content.service.ListContentService;
import mvc.command.CommandHandler;

public class ListContentHandler implements CommandHandler{
	
	private ListContentService listService = new ListContentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		ContentPage contentPage = listService.getContentPage(pageNo);
		req.setAttribute("contentPage", contentPage);
		return "listContent";
	}
}
