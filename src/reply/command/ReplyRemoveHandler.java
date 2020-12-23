package reply.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import content.service.PermissionDeniedException;
import mvc.command.CommandHandler;
import reply.dao.ReplyDao;
import reply.model.Reply;
import reply.service.ReplyNotFoundException;
import reply.service.ReplyReadService;
import reply.service.ReplyRemoveService;

public class ReplyRemoveHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "replyRemoveForm";
	private ReplyRemoveService replyRemoveService = new ReplyRemoveService();
	private ReplyReadService readService = new ReplyReadService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		HttpSession session = req.getSession();
		User authUser = (User) session.getAttribute("authUser");
		
		String contentNo = req.getParameter("contentNo");
		int no = Integer.parseInt(req.getParameter("no"));
		String password = req.getParameter("password");
		Reply reply = readService.getReply(no);
		
		if(!authUser.getId().equals(reply.getRegisterid())) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		try {
			replyRemoveService.remove(no, authUser, password);
		} catch (PermissionDeniedException e) {
			errors.put("invalidePassword", true);
			return FORM_VIEW;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		res.sendRedirect(req.getContextPath() + "/content/read.do?no=" + contentNo);
		return null;
		//return "replyRemoveSuccess";
	}
}

