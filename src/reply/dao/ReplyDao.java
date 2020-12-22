package reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import reply.model.Reply;

public class ReplyDao {

	public void insert(Connection conn, String userId, int contentNo, String body) throws SQLException {		
		String sql = "INSERT INTO reply "
				  		+ "(registerid, content_no, body, regdate, moddate) "
				  		+ "VALUES(?, ?, ?, SYSDATE,SYSDATE)";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, userId);
			pstmt.setInt(2, contentNo);
			pstmt.setString(3, body);
			
			pstmt.executeUpdate();
		}
		
	}

	public List<Reply> listReply(Connection conn, int contentNum) throws SQLException {
		String sql = "SELECT replyid, registerid, content_no, body, regdate, moddate " 
						+ "FROM reply "
						+ "WHERE content_no=? " 
						+ "ORDER BY replyid DESC";
		List<Reply> list = new ArrayList<Reply>();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, contentNum);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Reply r = new Reply();
				r.setId(rs.getInt(1));
				r.setRegisterid(rs.getString(2));
				r.setContentNum(rs.getInt(3));
				r.setBody(rs.getString(4));
				r.setRegDate(rs.getTimestamp(5));
				r.setModfiedDate(rs.getTimestamp(6));
				
				list.add(r);
			}
		}
		return list;
	}
	
	public int replyCnt(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM reply WHERE registerid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
}
