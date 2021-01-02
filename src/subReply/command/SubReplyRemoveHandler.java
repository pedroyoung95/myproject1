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
import subReply.service.SubReplyRemoveService;

public class SubReplyRemoveHandler implements CommandHandler{

	private SubReplyRemoveService subRemoveService = new SubReplyRemoveService();
	private ReadContentService readService = new ReadContentService();
	private ReplyService replyService = new ReplyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		HttpSession session = req.getSession();
		User authUser = (User) session.getAttribute("authUser");
		
		int contentNo = Integer.parseInt(req.getParameter("contentNo"));
		int subReplyNo = Integer.parseInt(req.getParameter("subReplyNo"));		
		
		try {
			subRemoveService.remove(subReplyNo, authUser);
		} catch (Exception e) {
			errors.put("noPermission_sub", true);
			ContentData contentData = readService.getContent(contentNo, true);
			List<Reply> replyList = replyService.getReplyList(contentNo); 
			req.setAttribute("contentData", contentData);
			req.setAttribute("replyList", replyList);
			return "readContent";
		}
		res.sendRedirect(req.getContextPath() + "/content/read.do?no=" + req.getParameter("contentNo"));
		return null;
	}
}
