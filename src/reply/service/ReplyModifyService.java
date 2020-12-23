package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import content.service.PermissionDeniedException;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import reply.dao.ReplyDao;
import reply.model.Reply;

public class ReplyModifyService {

	private ReplyDao replyDao = new ReplyDao();
	
	public void Modify(ModifyRequest modReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Reply reply = replyDao.selectById(conn, modReq.getReplyid());
			if(reply == null) {
				throw new ReplyNotFoundException();
			}
			if(!modReq.getRegisterid().equals(reply.getRegisterid())) {
				throw new PermissionDeniedException();
			}
			
			replyDao.update(conn, modReq.getReplyid(), modReq.getBody());
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} catch(PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
