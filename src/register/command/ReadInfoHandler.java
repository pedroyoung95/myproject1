package register.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;

public class ReadInfoHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {		
		return "readInfo";
	}
}
