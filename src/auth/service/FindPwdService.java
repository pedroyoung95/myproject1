package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
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
	
	public void makeNewPwd(String id, String newPwd) {		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Register register = registerDao.selectById(conn, id);
			if(register == null) {
				throw new LoginFailException();
			}
			register.changePassword(newPwd);
			registerDao.update(conn, register);
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);			
		}
	}
}
