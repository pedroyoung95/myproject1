package reply.service;

import java.sql.Connection;	
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import reply.dao.ReplyDao;
import reply.model.Reply;

public class ReplyService {
	private ReplyDao replyDao = new ReplyDao();
	
	public List<Reply> getReplyList(int contentNum) {
		Connection conn = ConnectionProvider.getConnection();
		
		List<Reply> list;
		try {
			list = replyDao.listReply(conn, contentNum);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
	}

}
