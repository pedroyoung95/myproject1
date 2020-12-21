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
	
	public ContentPage getArticlePage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			int total = contentDao.selectCount(conn);
			List<Content> content = contentDao.select(conn, pageNum, size);
			return new ContentPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
