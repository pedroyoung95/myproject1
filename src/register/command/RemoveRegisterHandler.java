package register.command;

import java.util.HashMap;	
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import register.service.ChangePasswordService;
import register.service.InvalidPasswordException;
import register.service.MemberNotFoundException;
import register.service.RemoveRegisterService;
import mvc.command.CommandHandler;

public class RemoveRegisterHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "removeRegisterForm";
	private RemoveRegisterService removeRegisterSvc = new RemoveRegisterService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//password 파라미터를 얻기
		String password = req.getParameter("password");
		
		//errors맵을 만들어서 request attribute에 넣고,
		Map<String, Boolean> errors=  new HashMap<>();
		req.setAttribute("errors", errors);
		
		//password가 null이거나 비어 있으면 (empty) errors에 '(코드, true)'쌍을 put
		//다시 view의 이름을 리턴
		if(password == null || password.isEmpty()) {
			errors.put("password", true);
		}
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		//세션에서 User객체 얻기(authUser라는 attribute가 들어있음)
		User user = (User) req.getSession().getAttribute("authUser");
		//Service에게 일 시키기
		try {
			removeRegisterSvc.removeRegister(user, password);			
			//탈퇴가 성공하면
			//세션을 invalidate시킴
			HttpSession session = req.getSession(false); 
			if(session != null) {
				session.invalidate(); 
			}
			return "removeRegisterSuccess";
		} catch (InvalidPasswordException e) {
			errors.put("badPwd", true);
			return FORM_VIEW;
		} catch(MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
			//만약 문제가 생기면(문제1. 없는 사용자인 경우 / 문제2. 패스워드가 틀린경우 -> 두 개의 catch문으로 두 문제를 Exception으로 만들기) 
			//errors맙에 코드를 추가하고,
			//form으로 forward하도록 view이름 리턴
	}
}
