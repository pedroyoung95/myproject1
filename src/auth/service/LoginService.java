package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import register.dao.RegisterDao;
import register.model.Register;

public class LoginService {
	
	private RegisterDao registerDao = new RegisterDao();
	
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
			return user;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}