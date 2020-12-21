package content.command;

import java.util.List;	

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import content.service.ContentNotFoundException;
import content.service.ContentData;
import content.service.ReadContentService;
import mvc.command.CommandHandler;
import reply.model.Reply;
import reply.service.ReplyService;

public class ReadContentHandler implements CommandHandler{
	
	private ReadArticleService readService = new ReadArticleService();
	private ReplyService replyService = new ReplyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int articleNum = Integer.parseInt(noVal);
		try {
			ContentData articleData = readService.getArticle(articleNum, true);
			List<Reply> replyList = replyService.getReplyList(articleNum); 
			req.setAttribute("articleData", articleData);
			req.setAttribute("replyList", replyList);
			return "readArticle";
		} catch (ContentNotFoundException e) {
			//req.getServletContext().log("no article", e);
			System.out.println("게시물이 없습니다");
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}
}
