package reply.command;

import java.io.IOException;	
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import reply.service.ReplyNotFoundException;
import reply.service.ReplyReadService;
import content.service.PermissionDeniedException;
import mvc.command.CommandHandler;
import reply.model.Reply;
import reply.service.ModifyRequest;
import reply.service.ReplyModifyService;

public class ReplyModifyHandler implements CommandHandler{

	private static final String FORM_VIEW = "replyModifyForm";
	private ReplyModifyService modifyService = new ReplyModifyService();
	private ReplyReadService readService = new ReplyReadService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			User authUser = (User) req.getSession().getAttribute("authUser");
			String noVal = req.getParameter("no");
			int no = Integer.valueOf(noVal);
			Reply reply = readService.getReply(no);
			
			if(!authUser.getId().equals(reply.getRegisterid())) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			ModifyRequest repModReq = new ModifyRequest();
			repModReq.setReplyid(no);
			repModReq.setRegisterid(authUser.getId());
			repModReq.setBody(req.getParameter("body"));
			
			req.setAttribute("repModReq", repModReq);
			return FORM_VIEW;
		} catch (Exception e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.valueOf(noVal);
		String contentNo = req.getParameter("contentNo");		
		
		ModifyRequest repModReq = new ModifyRequest();
		repModReq.setReplyid(no);
		repModReq.setRegisterid(authUser.getId());
		repModReq.setBody(req.getParameter("body"));
		
		req.setAttribute("repModReq", repModReq);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		repModReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			modifyService.Modify(repModReq);
			//return "replyModifySuccess";
			res.sendRedirect(req.getContextPath() + "/content/read.do?no=" + contentNo);
			return null;
		} catch (ReplyNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}