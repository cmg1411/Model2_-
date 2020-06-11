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
	
	public int idcheck(String id) {
		ResultSet res = null;
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "select id from member0609 where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			res = pstmt.executeQuery();
			
			if(res.next())
				result = 1; // 중복아이디
			else
				result = -1; //사용가능아이디
			
		} catch(Exception e) {
			
		} finally {
			if(res!=null) {try{res.close();}catch(Exception e){e.printStackTrace();}}
			if(pstmt!=null) {try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
			if(conn!=null) {try{conn.close();}catch(Exception e){e.printStackTrace();}}
		}
		
		return result;
	}
	
	public int memberAuth(String id, String passwd) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			conn = getConnection();
			
			String sql = "select * from member0609 where id=? and passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			
			res = pstmt.executeQuery();
			
			if(res.next()) { // 회원인증
				result = 1;
			}else { // 회원없음
				result = -1;
			}
			
		} catch(Exception e) {
			
		} finally {
			if(res!=null) {try{res.close();}catch(Exception e){e.printStackTrace();}}
			if(pstmt!=null) {try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
			if(conn!=null) {try{conn.close();}catch(Exception e){e.printStackTrace();}}
		}
		
		return result;
	}
	
	public MemberDTO getmember(String id) {
		MemberDTO dto = new MemberDTO();
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			conn = getConnection();
			
			String sql = "select * from member0609 where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			res = pstmt.executeQuery();
			
			if(res.next()) { // 회원인증
				dto.setId(res.getString("id"));
				dto.setPasswd(res.getString("passwd"));
				dto.setName(res.getString("name"));
				dto.setJumin1(res.getString("jumin1"));
				dto.setJumin2(res.getString("jumin2"));
				dto.setMailid(res.getString("mailid"));
				dto.setDomain(res.getString("domain"));
				dto.setTel1(res.getString("tel1"));
				dto.setTel2(res.getString("tel2"));
				dto.setTel3(res.getString("tel3"));
				dto.setPhone1(res.getString("phone1"));
				dto.setPhone2(res.getString("phone2"));
				dto.setPhone3(res.getString("phone3"));
				dto.setPost(res.getString("post"));
				dto.setAddress(res.getString("address"));
				dto.setGender(res.getString("gender"));
				dto.setHobby(res.getString("hobby"));
				dto.setIntro(res.getString("intro"));
				dto.setTimestamp(res.getTimestamp("timestamp"));
				
			}else { // 회원없음
				return null;
			}
			
		} catch(Exception e) {
			
		} finally {
			if(res!=null) {try{res.close();}catch(Exception e){e.printStackTrace();}}
			if(pstmt!=null) {try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
			if(conn!=null) {try{conn.close();}catch(Exception e){e.printStackTrace();}}
		}
		
		return dto;
	}
	
	//수정
	public int update(MemberDTO member) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "update member0609 set name=?, jumin1=?, jumin2=?, mailid=?, domain=?";
			sql+="tel1=?, tel2=?, tel3=?, phone1=?, phone2=?, phone3=?, post=?, address=?,";
			sql+="gender=?, hobby=?, intro=? where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getJumin1());
			pstmt.setString(3, member.getJumin2());
			pstmt.setString(4, member.getMailid());
			pstmt.setString(5, member.getDomain());
			pstmt.setString(6, member.getTel1());
			pstmt.setString(7, member.getTel2());
			pstmt.setString(8, member.getTel3());
			pstmt.setString(9, member.getPhone1());
			pstmt.setString(10, member.getPhone2());
			pstmt.setString(11, member.getPhone3());
			pstmt.setString(12, member.getPost());
			pstmt.setString(13, member.getAddress());
			pstmt.setString(14, member.getGender());
			pstmt.setString(15, member.getHobby());
			pstmt.setString(16, member.getIntro());
			pstmt.setString(17, member.getId());
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			
		} finally {
			if(pstmt!=null) {try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
			if(conn!=null) {try{conn.close();}catch(Exception e){e.printStackTrace();}}
		}
		
		return result;
	}
	 
}
