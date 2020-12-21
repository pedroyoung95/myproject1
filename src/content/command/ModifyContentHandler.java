package content.command;

import java.io.IOException;	
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import content.service.ContentData;
import content.service.ContentNotFoundException;
import content.service.ModifyContentService;
import content.service.ModifyRequest;
import content.service.PermissionDeniedException;
import content.service.ReadContentService;
import auth.service.User;
import mvc.command.CommandHandler;

public class ModifyContentHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "modifyForm";
	
	private ReadContentService readService = new ReadContentService();
	private ModifyContentService modifyService = new ModifyContentService();
	
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
			String noVal = req.getParameter("no");
			int no = Integer.valueOf(noVal);
			ContentData contentData = readService.getContent(no, false);
			User authUser = (User) req.getSession().getAttribute("authUser");
			if(!canModify(authUser, contentData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, contentData.getContent().getTitle(), contentData.getContent().getBody());
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		} catch (Exception e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private boolean canModify(User authUser, ContentData contentData) {
		String writerId = contentData.getContent().getWriter().getId();
		return authUser.getId().equals(writerId);
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, req.getParameter("title"), req.getParameter("body"));
		req.setAttribute("modReq", modReq);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "modifySuccess";
		} catch (ContentNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
