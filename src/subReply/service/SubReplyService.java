package subReply.service;

import java.sql.Connection;	
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import subReply.dao.SubReplyDao;
import subReply.model.SubReply;

public class SubReplyService {
	private SubReplyDao subReplyDao = new SubReplyDao();
	
	public List<SubReply> getSubReplyList(int contentNum) {
		Connection conn = ConnectionProvider.getConnection();
		
		List<SubReply> list;
		try {
			list = subReplyDao.listReply(conn, contentNum);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
