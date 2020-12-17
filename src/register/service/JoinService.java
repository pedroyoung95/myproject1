package register.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import register.dao.RegisterDao;
import register.model.Register;

public class JoinService {

	private RegisterDao registerDao = new RegisterDao();
	
	public void join(JoinRequest joinReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Register reg = registerDao.selectById(conn, joinReq.getId());
			if(reg != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicatedIdException();
			}
			
			Register register = new Register();
			register.setId(joinReq.getId());
			register.setName(joinReq.getName());
			register.setPassword(joinReq.getPassword());
			
			registerDao.insert(conn, register);
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
