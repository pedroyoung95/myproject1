package subReply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import subReply.model.SubReply;

public class SubReplyDao {
	
	public SubReply selectById(Connection conn, int subReplyNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM subreply WHERE subreply_no=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, subReplyNo);
			rs = pstmt.executeQuery();
			SubReply subReply = null;
			if(rs.next()) {
				subReply = new SubReply();
				subReply.setNum(rs.getInt("subreply_no"));
				subReply.setContentNum(rs.getInt("content_no"));
				subReply.setReplyId(rs.getInt("replyid"));
				subReply.setRegisterid(rs.getString("registerid"));				
				subReply.setBody(rs.getString("body"));
				subReply.setRegDate(rs.getTimestamp("regdate"));
				subReply.setModfiedDate(rs.getTimestamp("moddate"));
			}
			return subReply;
		} finally {
			JdbcUtil.close(pstmt);
		}
		
	} 
	
	public void insert(Connection conn, String userId, int contentNo, int replyId, String body) throws SQLException {		
		String sql = "INSERT INTO subreply "
				  		+ "(content_no, replyid, registerid, body, regdate, moddate) "
				  		+ "VALUES(?, ?, ?, ?, SYSDATE,SYSDATE)";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, contentNo);
			pstmt.setInt(2, replyId);
			pstmt.setString(3, userId);
			pstmt.setString(4, body);
			
			pstmt.executeUpdate();
		}
		
	}

	public List<SubReply> listReply(Connection conn, int contentNo) throws SQLException {
		String sql = "SELECT subreply_no, content_no, replyid, registerid, body, regdate, moddate " 
						+ "FROM subreply "
						+ "WHERE content_no=? " 
						+ "ORDER BY subreply_no DESC";
		List<SubReply> sublist = new ArrayList<SubReply>();
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, contentNo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				SubReply r = new SubReply();
				r.setNum(rs.getInt(1));
				r.setContentNum(rs.getInt(2));
				r.setReplyId(rs.getInt(3));
				r.setRegisterid(rs.getString(4));				
				r.setBody(rs.getString(5));
				r.setRegDate(rs.getTimestamp(6));
				r.setModfiedDate(rs.getTimestamp(7));
				
				sublist.add(r);
			}
		}
		return sublist;
	}
	
	public int subReplyCnt(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM subreply WHERE registerid=?";
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
	
	public void delete(Connection conn, int subReplyNo) throws SQLException {
		String sql = "DELETE subreply WHERE subreply_no=?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, subReplyNo);
			pstmt.executeUpdate();
		}
	}
	
	public int update(Connection conn, int no, String body) throws SQLException {
		String sql = "UPDATE subreply SET body=?, moddate=SYSDATE "
						+ "WHERE subreply_no=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, body);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
}
