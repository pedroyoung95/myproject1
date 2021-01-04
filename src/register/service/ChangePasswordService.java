package register.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import register.dao.RegisterDao;
import register.model.Register;

public class ChangePasswordService {
	
	private RegisterDao registerDao = new RegisterDao();
	
	public void changePassword(String userId, String curPwd, String newPwd) {
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			//해당 멤버가 있는지 확인하는 if문
			Register register = registerDao.selectById(con, userId);
			if(register == null) {
				throw new MemberNotFoundException();
			}
			//해당 암호가 맞는지 확인하는 if문
			if(!register.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			//멤버가 있음이 확인되면 암호변경 과정이 이뤄짐
			register.changePassword(newPwd);
			registerDao.update(con, register);
			con.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con);			
		}
	}
}
