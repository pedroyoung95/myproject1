package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import reply.dao.ReplyDao;

public class ReplyAddService {
	private ReplyDao replyDao = new ReplyDao();

	public void add(String userId, int contentNo, String body) {
		Connection conn = ConnectionProvider.getConnection();
		
		try {
			replyDao.insert(conn, userId, contentNo, body);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	
}
