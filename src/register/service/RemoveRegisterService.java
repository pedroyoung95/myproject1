package register.service;

import java.sql.Connection;	

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import register.dao.RegisterDao;
import register.model.Register;

public class RemoveRegisterService {
	
	private RegisterDao registerDao = new RegisterDao();
	
	public void removeRegister(User user, String password) {
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			//1. selectById로 DB에서 멤버 얻기
			Register register = registerDao.selectById(con, user.getId());
//			1.1. member가 없으면 MemberNotFoundException발생
			if(register == null) {
				throw new MemberNotFoundException();
			}
			//	1.2. password가 member의 password와 다르면 InvalidPasswordException발생
			if(!register.matchPassword(password)) {
				throw new InvalidPasswordException();
			}
			//멤버가 있음이 확인되면 회원 탈퇴가 이뤄짐
			registerDao.delete(con, register);
			con.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con);
		}
	}
}
