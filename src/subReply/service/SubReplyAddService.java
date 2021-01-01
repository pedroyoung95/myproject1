package subReply.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import subReply.dao.SubReplyDao;

public class SubReplyAddService {
	private SubReplyDao subReplyDao = new SubReplyDao();

	public void add(String userId, int contentNo, int replyId, String body) {
		Connection conn = ConnectionProvider.getConnection();
		
		try {
			subReplyDao.insert(conn, userId, contentNo, replyId, body);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	
}
