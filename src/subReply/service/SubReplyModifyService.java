package subReply.service;

import java.sql.Connection;
import java.sql.SQLException;

import content.service.PermissionDeniedException;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import subReply.dao.SubReplyDao;
import subReply.model.SubReply;

public class SubReplyModifyService {

	private SubReplyDao subReplyDao = new SubReplyDao();
	
	public void Modify(SubModifyRequest subReqMod) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			SubReply subReply = subReplyDao.selectById(conn, subReqMod.getSubreplyNo());
			if(subReply == null) {
				throw new SubReplyNotFoundException();
			}
			if(!subReqMod.getRegisterid().equals(subReply.getRegisterid())) {
				throw new PermissionDeniedException();
			}
			subReplyDao.update(conn, subReqMod.getSubreplyNo(), subReqMod.getBody());
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
