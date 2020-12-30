package content.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import content.model.Writer;
import content.model.Content;
import jdbc.JdbcUtil;

public class ContentDao {
	
	public Content insert(Connection conn, Content content) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO content "
						+ "(writer_id, writer_name, title, body, regdate, moddate, read_cnt) "
						+ "VALUES(?, ?, ?, ?, SYSDATE, SYSDATE, 0)";
		try {
			pstmt = conn.prepareStatement(sql, new String[] {"content_no", "regdate", "moddate"});
			
			pstmt.setString(1, content.getWriter().getId());
			pstmt.setString(2, content.getWriter().getName());
			pstmt.setString(3, content.getTitle());
			pstmt.setString(4, content.getBody());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				rs = pstmt.getGeneratedKeys(); //DB에서 생성되는 값을 받기 위해 getGenereatedKeys()메소드 사용
				int key = 0;
				Date regDate = null;
				Date modDate = null;
				if(rs.next()) {					
					key = rs.getInt(1);
					regDate = rs.getTimestamp(2);
					modDate = rs.getTimestamp(3);
				}
				return new Content(key, 
						content.getWriter(), 
						content.getTitle(), 
						content.getBody(),
						regDate,
						modDate,
						0);
			} else {
				return null;
			}			
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	
	private Timestamp toTimeStamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	public int selectCount(Connection conn, String option, String condition) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			if(option==null || condition.isEmpty()) {
				String sql = "SELECT COUNT(*) FROM content";
				pstmt = conn.prepareStatement(sql);
			} else if(option.equals("0")) {
				String sql = "SELECT COUNT(*) FROM content WHERE title LIKE ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "'%"+condition+"%'");
			} else if(option.equals("1")) {
				String sql = "SELECT COUNT(*) FROM content WHERE body LIKE ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "'%"+condition+"%'");
			} else if(option.equals("2")) {
				String sql = "SELECT COUNT(*) FROM content WHERE title LIKE ? OR body LIKE ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "'%"+condition+"%'");
				pstmt.setString(2, "'%"+condition+"%'");
			} else if(option.equals("3")) {
				String sql = "SELECT COUNT(*) FROM content WHERE writer_name LIKE ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "'%"+condition+"%'");		
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Content> select(Connection conn, int pageNum, int size, String option, String condition) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			if(option==null || condition.isEmpty()) {
				String sql = "SELECT * FROM( "
						+ "SELECT content_no, writer_id, writer_name, title, body, regdate, moddate, read_cnt, "
						+ "ROW_NUMBER() OVER(ORDER BY content_no DESC)rn FROM content) "
						+ "WHERE rn BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);			
				pstmt.setInt(1, (pageNum - 1) * size + 1);
				pstmt.setInt(2, pageNum * size);
			} else if(option.equals("0")) {
				String sql = "SELECT * FROM( "
						+ "SELECT content_no, writer_id, writer_name, title, body, regdate, moddate, read_cnt, "
						+ "ROW_NUMBER() OVER(ORDER BY content_no DESC)rn FROM content WHERE title LIKE ?) "
						+ "WHERE rn BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);		
				pstmt.setString(1, "%"+condition+"%");
				pstmt.setInt(2, (pageNum - 1) * size + 1);
				pstmt.setInt(3, pageNum * size);
			} else if(option.equals("1")) {
				String sql = "SELECT * FROM( "
						+ "SELECT content_no, writer_id, writer_name, title, body, regdate, moddate, read_cnt, "
						+ "ROW_NUMBER() OVER(ORDER BY content_no DESC)rn FROM content WHERE body LIKE ?) "
						+ "WHERE rn BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);		
				pstmt.setString(1, "%"+condition+"%");
				pstmt.setInt(2, (pageNum - 1) * size + 1);
				pstmt.setInt(3, pageNum * size);
			} else if(option.equals("2")) {
				String sql = "SELECT * FROM( "
						+ "SELECT content_no, writer_id, writer_name, title, body, regdate, moddate, read_cnt, "
						+ "ROW_NUMBER() OVER(ORDER BY content_no DESC)rn FROM content WHERE title LIKE ? OR body LIKE ?) "
						+ "WHERE rn BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);		
				pstmt.setString(1, "%"+condition+"%");
				pstmt.setString(2, "%"+condition+"%");
				pstmt.setInt(3, (pageNum - 1) * size + 1);
				pstmt.setInt(4, pageNum * size);
			} else if(option.equals("3")) {
				String sql = "SELECT * FROM( "
						+ "SELECT content_no, writer_id, writer_name, title, body, regdate, moddate, read_cnt, "
						+ "ROW_NUMBER() OVER(ORDER BY content_no DESC)rn FROM content WHERE writer_name LIKE ?) "
						+ "WHERE rn BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);		
				pstmt.setString(1, "%"+condition+"%");				
				pstmt.setInt(2, (pageNum - 1) * size + 1);
				pstmt.setInt(3, pageNum * size);
			}
			
			rs = pstmt.executeQuery();
			
			List<Content> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertContent(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private Content convertContent(ResultSet rs) throws SQLException {
		return new Content(
				rs.getInt("content_no"), 				
				new Writer(
						rs.getString("writer_id"), rs.getString("writer_name")), 
				rs.getString("title"),
				rs.getString("body"),
				rs.getTimestamp("regdate"),
				rs.getTimestamp("moddate"),
				rs.getInt("read_cnt"));
	}
	
	public Content selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM content WHERE content_no=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Content content = null;
			if(rs.next()) {
				content = convertContent(rs);
			}
			return content;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int wroteCnt(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM content WHERE writer_id=?";
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
	
	public void increaseReadCount(Connection conn, int no) throws SQLException {
		String sql = "UPDATE content SET read_cnt=read_cnt+1 "
						+ "WHERE content_no=?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
	
	public int update(Connection conn, int no, String title, String body) throws SQLException {
		String sql = "UPDATE content SET title=?, body=?, moddate=SYSDATE " 
						+ "WHERE content_no=?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, title);
			pstmt.setString(2, body);
			pstmt.setInt(3, no);
			return pstmt.executeUpdate();
		} 
	}
	
	public void delete(Connection conn, int no) throws SQLException {
		String sql = "DELETE content WHERE content_no=?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
}
