package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.MemberDTO;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	//커넥션풀에서 커넥션을 구해오는 메소드
	private Connection getConnection() throws Exception {
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}
	
	
	public int insert(MemberDTO member) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into member0609 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getJumin1());
			pstmt.setString(5, member.getJumin2());
			pstmt.setString(6, member.getMailid());
			pstmt.setString(7, member.getDomain());
			pstmt.setString(8, member.getTel1());
			pstmt.setString(9, member.getTel2());
			pstmt.setString(10, member.getTel3());
			pstmt.setString(11, member.getPhone1());
			pstmt.setString(12, member.getPhone2());
			pstmt.setString(13, member.getPhone3());
			pstmt.setString(14, member.getPost());
			pstmt.setString(15, member.getAddress());
			pstmt.setString(16, member.getGender());
			pstmt.setString(17, member.getHobby());
			pstmt.setString(18, member.getIntro());
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			
		} finally {
			if(pstmt!=null) {try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
			if(conn!=null) {try{conn.close();}catch(Exception e){e.printStackTrace();}}
		}
		
		return result;
	}
	
	public boolean idcheck(String id) {
		ResultSet res = null;
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "select count(id) from member0609 where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			res = pstmt.executeQuery();
			
			if(res!=null)
				result = false;
			else
				result = true;
			
		} catch(Exception e) {
			
		} finally {
			if(pstmt!=null) {try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
			if(conn!=null) {try{conn.close();}catch(Exception e){e.printStackTrace();}}
		}
		
		return result;
	}
}
