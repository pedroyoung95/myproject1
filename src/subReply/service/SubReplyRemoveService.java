package subReply.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import content.service.PermissionDeniedException;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import subReply.dao.SubReplyDao;
import subReply.model.SubReply;

public class SubReplyRemoveService {
	
	private SubReplyDao subReplyDao = new SubReplyDao();
	
	public void remove(int subReplyNo, User authUser) {
		
		Connection conn = ConnectionProvider.getConnection();
		
		try {
			conn.setAutoCommit(false);
				
			SubReply subReply = subReplyDao.selectById(conn, subReplyNo);
			
			if(!authUser.getId().equals(subReply.getRegisterid())) {
				throw new PermissionDeniedException();
			}
			
			subReplyDao.delete(conn, subReplyNo);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
