package cn.cd.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.cxd.utils.MySqlDruid;

public class BookDAO {
	public BookBean searchBook(String bookid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		BookBean book = new BookBean();
		try {
			conn  = MySqlDruid.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM books WHERE bookid=?");
			pstmt.setString(1, bookid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				book.setBookid(rs.getString("bookid"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPrice(rs.getFloat("price"));
				return book;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				MySqlDruid.close(rs, pstmt, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean insertBook(BookBean book) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn  = MySqlDruid.getConnection();
			pstmt = conn.prepareStatement("INSERT INTO books VALUES(?,?,?,?,?)");
			pstmt.setString(1, book.getBookid());
			pstmt.setString(2, book.getTitle());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getPublisher());
			pstmt.setFloat(5, book.getPrice());
			
			int result = pstmt.executeUpdate();
			if(result != 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				MySqlDruid.close(pstmt, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	
	
}
