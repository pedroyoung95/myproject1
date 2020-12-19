package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import register.dao.RegisterDao;
import register.model.Register;

public class FindPwdService {

	private RegisterDao registerDao = new RegisterDao();
	
	public boolean checkId(String id) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			Register register = registerDao.selectById(conn, id);
			if(register == null) {
				throw new LoginFailException();
			}
			return id.equals(register.getId());			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void makeNewPwd() {
		
	}
}
