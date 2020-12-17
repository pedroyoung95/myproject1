package register.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import register.service.JoinService;

public class JoinHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "joinForm";
	private JoinService joinService = new JoinService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		return null;
	}
}
