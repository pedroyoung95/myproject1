package content.service;

import java.sql.Connection;	
import java.sql.SQLException;

import content.dao.ContentDao;
import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import register.dao.RegisterDao;
import register.model.Register;

public class DeleteContentService {
	private RegisterDao registerDao = new RegisterDao();
	private ContentDao contentDao = new ContentDao();

	public void delete(int no, User authUser, String password) {

		Connection conn = ConnectionProvider.getConnection();
		try {
			conn.setAutoCommit(false);
			Register register = registerDao.selectById(conn, authUser.getId());

			if (!register.getPassword().equals(password)) {
				throw new PermissionDeniedException();
			}

			contentDao.delete(conn, no);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}
		
	}
	
}
