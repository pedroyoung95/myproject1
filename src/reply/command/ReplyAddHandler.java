package reply.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import content.service.ContentData;
import content.service.ReadContentService;
import mvc.command.CommandHandler;
import reply.model.Reply;
import reply.service.ReplyAddService;
import reply.service.ReplyService;

public class ReplyAddHandler implements CommandHandler {
	
	private ReplyAddService addService = new ReplyAddService();
	private ReadContentService readService = new ReadContentService();
	private ReplyService replyService = new ReplyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("authUser");
		int contentNo = Integer.parseInt(req.getParameter("no"));
		String userId = user.getId();
		String body = req.getParameter("body");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		if(body==null||body.trim().isEmpty()) {
			errors.put("body", true);
		}
		if(!errors.isEmpty()) {
//			RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/content/read.do?no=" + req.getParameter("no"));
//			dispatcher.forward(req, res);
			ContentData contentData = readService.getContent(contentNo, true);
			List<Reply> replyList = replyService.getReplyList(contentNo); 
			req.setAttribute("contentData", contentData);
			req.setAttribute("replyList", replyList);
			return "readContent";
		} else {		
			addService.add(userId, contentNo, body);			
			res.sendRedirect(req.getContextPath() + "/content/read.do?no=" + req.getParameter("no"));
			//return "replyAddSuccess";
			return null;
		}
	}

}
