package subReply.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import content.service.PermissionDeniedException;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import register.dao.RegisterDao;
import register.model.Register;
import subReply.dao.SubReplyDao;
import subReply.model.SubReply;

public class SubReplyRemoveService {

	private RegisterDao registerDao = new RegisterDao();
	private SubReplyDao subReplyDao = new SubReplyDao();
	
	public void remove(int no, User authUser) {
		
		Connection conn = ConnectionProvider.getConnection();
		
		try {
			conn.setAutoCommit(false);
			
			Register register = registerDao.selectById(conn, authUser.getId());		
			SubReply subReply = subReplyDao.selectById(conn, no);
			
			if(!register.getId().equals(subReply.getRegisterid())) {
				throw new PermissionDeniedException();
			}
			
			subReplyDao.delete(conn, no);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
