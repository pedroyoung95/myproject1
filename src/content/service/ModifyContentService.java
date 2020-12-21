package content.service;

import java.sql.Connection;	
import java.sql.SQLException;

import content.dao.ContentDao;
import content.model.Content;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ModifyContentService {
	
	private ContentDao contentDao = new ContentDao();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Content content = contentDao.selectById(conn, modReq.getContentNumber());
			if(content == null) {
				throw new ContentNotFoundException();
			}
			if(!canModify(modReq.getUserId(), content)) {
				throw new PermissionDeniedException();
			}
			contentDao.update(conn, modReq.getContentNumber(), modReq.getTitle(), modReq.getBody());
			
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
	
	private boolean canModify(String modfyingUserId, Content content) {
		return content.getWriter().getId().equals(modfyingUserId);
	}
}
