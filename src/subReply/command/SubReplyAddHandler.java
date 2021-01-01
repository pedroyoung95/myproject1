package subReply.command;

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
import reply.service.ReplyService;
import subReply.model.SubReply;
import subReply.service.SubReplyAddService;
import subReply.service.SubReplyService;

public class SubReplyAddHandler implements CommandHandler{

	private SubReplyAddService addService = new SubReplyAddService();
	private ReadContentService readService = new ReadContentService();
	private ReplyService replyService = new ReplyService();
	private SubReplyService subReplyService = new SubReplyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("authUser");		
		int contentNo = Integer.parseInt(req.getParameter("contentNo"));
		String userId = user.getId();
		String body = req.getParameter("subReplybody");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		if(body==null||body.trim().isEmpty()) {
			errors.put("noSubReply", true);
		}
		if(!errors.isEmpty()) {
			ContentData contentData = readService.getContent(contentNo, true);
			List<Reply> replyList = replyService.getReplyList(contentNo); 
			List<SubReply> subReplyList = subReplyService.getSubReplyList(contentNo);
			req.setAttribute("contentData", contentData);
			req.setAttribute("replyList", replyList);
			req.setAttribute("subReplyList", subReplyList);
			return "readContent";
		} else {
			addService.add(userId, contentNo, body);			
			res.sendRedirect(req.getContextPath() + "/content/read.do?no=" + req.getParameter("contentNo"));
			return null;
		}
		
	}
}
