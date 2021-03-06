package content.service;

import java.sql.Connection;	
import java.sql.SQLException;
import java.util.List;

import content.dao.ContentDao;
import content.model.Content;
import jdbc.ConnectionProvider;

public class ListContentService {
	
	private ContentDao contentDao = new ContentDao();
	private int size = 10;
	
	public ContentPage getContentPage(int pageNum, String option, String condition) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			int total = contentDao.selectCount(conn, option, condition);
			List<Content> content = contentDao.select(conn, pageNum, size, option, condition);
			return new ContentPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
