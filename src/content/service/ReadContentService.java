package content.service;

import java.sql.Connection;	
import java.sql.SQLException;

import content.dao.ContentDao;
import content.model.Content;
import jdbc.ConnectionProvider;

public class ReadContentService {
	
	private ContentDao contentDao = new ContentDao();
	
	public ContentData getContent(int contentNum, boolean increaseReadCount) {
		try(Connection conn = ConnectionProvider.getConnection();) {
			Content content = contentDao.selectById(conn, contentNum);
			if(content == null) {
				throw new ContentNotFoundException();
			}
			if(increaseReadCount) {
				contentDao.increaseReadCount(conn, contentNum); //increaseReadCount파라미터의 값이 true일 때만 조회수 증가
			}
			return new ContentData(content);
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}
}
