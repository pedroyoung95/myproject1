package reply.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import content.service.ContentData;
import content.service.PermissionDeniedException;
import content.service.ReadContentService;
import mvc.command.CommandHandler;
import reply.dao.ReplyDao;
import reply.model.Reply;
import reply.service.ReplyNotFoundException;
import reply.service.ReplyReadService;
import reply.service.ReplyRemoveService;
import reply.service.ReplyService;

public class ReplyRemoveHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "readContent";
	private ReplyRemoveService replyRemoveService = new ReplyRemoveService();
	private ReplyReadService readService = new ReplyReadService();
	private ReadContentService readContentService = new ReadContentService();
	private ReplyService replyService = new ReplyService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		HttpSession session = req.getSession();
		User authUser = (User) session.getAttribute("authUser");
		
		int contentNo = Integer.parseInt(req.getParameter("contentNo"));
		int replyid = Integer.parseInt(req.getParameter("no"));
		Reply reply = readService.getReply(replyid);
		
		try {
			replyRemoveService.remove(replyid, authUser);
		} catch (Exception e) {
			errors.put("noPermission", true);
			ContentData contentData = readContentService.getContent(contentNo, true);
			List<Reply> replyList = replyService.getReplyList(contentNo); 
			req.setAttribute("contentData", contentData);
			req.setAttribute("replyList", replyList);
			return FORM_VIEW;
		}
		res.sendRedirect(req.getContextPath() + "/content/read.do?no=" + req.getParameter("contentNo"));
		return null;		
	}
}

