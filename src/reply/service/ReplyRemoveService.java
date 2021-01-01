package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import content.service.PermissionDeniedException;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import register.dao.RegisterDao;
import register.model.Register;
import reply.dao.ReplyDao;
import reply.model.Reply;

public class ReplyRemoveService {

	private RegisterDao registerDao = new RegisterDao();
	private ReplyDao replyDao = new ReplyDao();
	
	public void remove(int no, User authUser) {
		
		Connection conn = ConnectionProvider.getConnection();
		try {
			conn.setAutoCommit(false);
			Register register = registerDao.selectById(conn, authUser.getId());			
			Reply reply = replyDao.selectById(conn, no);
			
			if(!register.getId().equals(reply.getRegisterid())) {
				throw new PermissionDeniedException();
			}
			
			replyDao.delete(conn, no);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
