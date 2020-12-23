package reply.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import reply.service.ReplyNotFoundException;
import content.service.PermissionDeniedException;
import content.service.ReplyModifyService;
import mvc.command.CommandHandler;
import reply.service.ModifyRequest;
import reply.service.ReplyReadService;

public class ReplyModifyHandler implements CommandHandler{

	private static final String FORM_VIEW = "replyModifyForm";
	private ReplyModifyService modifyService = new ReplyModifyService();
	
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
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.valueOf(noVal);
		String contentNo = req.getParameter("contentNo");
		
		ModifyRequest modReq = new ModifyRequest();
		modReq.setReplyid(no);
		modReq.setRegisterid(authUser.getId());
		modReq.setBody(req.getParameter("body"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			modifyService.Modify(modReq);
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
