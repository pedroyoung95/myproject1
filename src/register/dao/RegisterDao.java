package register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import register.model.Register;

public class RegisterDao {
	
	public void selectById(Connection conn, String id) throws SQLException {
		
		Register register = null;
		String sql = "SELECT registerid, name, password, regdate "
						+ "FROM register WHERE registerid=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				register = new Register();
				register.setId(rs.getString(1));
				register.setName(rs.getString(2));
				register.setPassword(rs.getString(3));
				register.setRegdate(rs.getTimestamp(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
