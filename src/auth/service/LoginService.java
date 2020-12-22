package auth.service;

import java.sql.Connection;	
import java.sql.SQLException;

import content.dao.ContentDao;
import jdbc.ConnectionProvider;
import register.dao.RegisterDao;
import register.model.Register;
import reply.dao.ReplyDao;

public class LoginService {
	
	private RegisterDao registerDao = new RegisterDao();
	private ContentDao contentDao = new ContentDao();
	private ReplyDao replyDao = new ReplyDao();
	
	public User Login(String id, String password) {
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			Register register = registerDao.selectById(conn, id);
			
			if(register == null) {
				throw new LoginFailException();
			}
			if(!register.matchPassword(password)) {
				throw new LoginFailException();
			}
			
			User user = new User();
			user.setId(register.getId());
			user.setName(register.getName());
			user.setRegdate(register.getRegdate());
			user.setWroteCnt(contentDao.wroteCnt(conn, id));
			user.setReplyCnt(replyDao.replyCnt(conn, id));
			return user;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
