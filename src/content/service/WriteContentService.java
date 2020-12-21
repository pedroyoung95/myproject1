package content.service;

import java.sql.Connection;		
import java.sql.SQLException;
import java.util.Date;

import content.dao.ContentDao;
import content.model.Content;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class WriteContentService {
	
	private ContentDao contentDao = new ContentDao();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Content content = toContent(req); //WriteRequest객체를 Article로 변환하기 위한 작업
			Content savedContent = contentDao.insert(conn, content); //insert메소드는 두 번째 파라미터로 Article타입을 받기 때문
			
			if(savedContent == null) {
				throw new RuntimeException("fail to insert content");
			}
			
			conn.commit();
			
			return savedContent.getNumber();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	private Content toContent(WriteRequest req) {
		Date now = new Date();
		return new Content(null, req.getWriter(), req.getTitle(), req.getBody(), null, null, 0);
	}
}
