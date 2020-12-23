package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import reply.dao.ReplyDao;
import reply.model.Reply;

public class ReplyReadService {

	private ReplyDao replyDao = new ReplyDao();
	
	public Reply getReply(int no) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			Reply reply = replyDao.selectById(conn, no);
			
			if(reply == null) {
				throw new ReplyNotFoundException();
			}			
			return reply;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
