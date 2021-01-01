package subReply.service;

import java.sql.Connection;		
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import subReply.dao.SubReplyDao;
import subReply.model.SubReply;

public class SubReplyReadService {

	private SubReplyDao subReplyDao = new SubReplyDao();
	
	public SubReply getSubReply(int no) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			SubReply subReply = subReplyDao.selectById(conn, no);
			
			if(subReply == null) {
				throw new SubReplyNotFoundException();
			}			
			return subReply;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
}
