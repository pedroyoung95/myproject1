package register.command;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import register.service.ChangePasswordService;
import register.service.InvalidPasswordException;
import register.service.MemberNotFoundException;

public class ChangePasswordHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "changePwdForm";
	private ChangePasswordService changePwdSvc = new ChangePasswordService();
	
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
		User user = (User) req.getSession().getAttribute("authUser");
		
		Map<String, Boolean> errors=  new HashMap<>();
		req.setAttribute("errors", errors);
		
		String curPwd = req.getParameter("curPwd");
		String newPwd = req.getParameter("newPwd");
		
		//암호 변경 입력 시 문제가 생기면(errors맵에 값이 추가되면) 아래의 코드 실행(문제가 생기면 form 뷰로 이동하게 됨)
		if(curPwd == null || curPwd.isEmpty()) {
			errors.put("curPwd", true);
		}
		if(newPwd == null || newPwd.isEmpty()) {
			errors.put("newPwd", true);
		}
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		//암호변경 입력 중에 문제가 없으면 암호변경 과정이 이뤄지는 코드 실행
		try {
			changePwdSvc.changePassword(user.getId(), curPwd, newPwd);
			return "changePwdSuccess";
		} catch (InvalidPasswordException e) {
			errors.put("badCurPwd", true);
			return FORM_VIEW;
		} catch(MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}
}
