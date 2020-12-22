package content.command;

import java.io.IOException;	
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import content.service.ContentData;
import content.service.DeleteContentService;
import content.service.PermissionDeniedException;
import content.service.ReadContentService;
import auth.service.User;
import mvc.command.CommandHandler;

public class DeleteContentHandler implements CommandHandler {
	private static final String FORM_VIEW = "deleteContentForm";
	
	private ReadContentService readService = new ReadContentService();
	private DeleteContentService deleteContentService = new DeleteContentService();

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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		HttpSession session = req.getSession();
		User authUser = (User) session.getAttribute("authUser");
		
		int no = Integer.parseInt(req.getParameter("no"));
		String password = req.getParameter("password");
		
		ContentData contentData = readService.getContent(no, false);
	
		if (!authUser.getId().equals(contentData.getContent().getWriter().getId())) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		try {
			deleteContentService.delete(no, authUser, password);
			
		} catch (PermissionDeniedException e) {
			errors.put("invalidePassword", true);
			return FORM_VIEW;
		}	catch (Exception e) {
			throw new RuntimeException(e);
		}		
		return "deleteContentSuccess";
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {		
		return FORM_VIEW;
	}
}





