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
	
	public Reply selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM reply WHERE replyid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Reply reply = null;
			if(rs.next()) {
				reply = new Reply();
				reply.setId(rs.getInt("replyid"));
				reply.setRegisterid(rs.getString("registerid"));
				reply.setContentNum(rs.getInt("content_no"));
				reply.setBody(rs.getString("body"));
				reply.setRegDate(rs.getTimestamp("regdate"));
				reply.setModfiedDate(rs.getTimestamp("moddate"));
			}
			return reply;
		} finally {
			JdbcUtil.close(pstmt);
		}
		
	} 
	
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
	
	public void delete(Connection conn, int replyid) throws SQLException {
		String sql = "DELETE reply WHERE replyid=?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, replyid);
			pstmt.executeUpdate();
		}
	}
	
	public int update(Connection conn, int no, String body) throws SQLException {
		String sql = "UPDATE reply SET body=?, moddate=SYSDATE "
						+ "WHERE replyid=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, body);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
}
