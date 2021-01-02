package subReply.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import content.service.ContentData;
import content.service.PermissionDeniedException;
import content.service.ReadContentService;
import mvc.command.CommandHandler;
import reply.model.Reply;
import reply.service.ReplyService;
import subReply.service.SubModifyRequest;
import subReply.service.SubReplyModifyService;
import subReply.service.SubReplyNotFoundException;

public class SubReplyModifyHandler implements CommandHandler{

	private SubReplyModifyService subModifyService = new SubReplyModifyService();
	private ReadContentService readService = new ReadContentService();
	private ReplyService replyService = new ReplyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		int contentNo = Integer.parseInt(req.getParameter("contentNo"));
		int replyId = Integer.parseInt(req.getParameter("replyId"));
		int subreplyNo = Integer.parseInt(req.getParameter("subreplyNo"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		SubModifyRequest subReqMod = new SubModifyRequest();
		subReqMod.setSubreplyNo(subreplyNo);
		subReqMod.setRegisterid(authUser.getId());
		subReqMod.setBody(req.getParameter("subReplybody"));
		req.setAttribute("subReqMod", subReqMod);
		
		if(!errors.isEmpty()) {
			errors.put("noModify", true);
			ContentData contentData = readService.getContent(contentNo, true);
			List<Reply> replyList = replyService.getReplyList(contentNo); 
			req.setAttribute("contentData", contentData);
			req.setAttribute("replyList", replyList);
			return "readContent";
		}
		try {
			subModifyService.Modify(subReqMod);
			res.sendRedirect(req.getContextPath() + "/content/read.do?no=" + req.getParameter("contentNo"));
			return null;
		} catch (SubReplyNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}	
	}
}
