package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.FindPwdService;
import mvc.command.CommandHandler;

public class MakeNewPwdHandler implements CommandHandler{

	private static final String FORM_VIEW = "findPwdForm2";
	private FindPwdService findPwdService = new FindPwdService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("post")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		String id = req.getParameter("id");
		String password = trim(req.getParameter("password"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if(password == null || password.isEmpty()) {
			errors.put("password", true);
		}
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			findPwdService.makeNewPwd(id, password);
			return "newPwdSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			return FORM_VIEW;
		}
	}
	
	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}
