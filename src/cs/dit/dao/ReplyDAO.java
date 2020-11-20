package cs.dit.dao;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import cs.dit.dto.ReplyDTO;

public class ReplyDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ReplyDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/JSP");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void register(int bcode, String reply) {
		String sql = "insert into practicereply(rcode, bcode, reply, regdate) values(seq_practicereply_rcode.nextval,?,?,sysdate)";
		
		try {
			con = ds.getConnection();  //Connection객체 CP에서 얻어오기
			pstmt = con.prepareStatement(sql);  	//Connection객체를 통해 SQL문 준비
			pstmt.setInt(1, bcode);	//SQL문과 데이터 바인팅
			pstmt.setString(2, reply);
			int x = pstmt.executeUpdate();
			
			if(x<1) {	//1보다 적으면
				System.out.println("ReplyDAO.register(): 정상적으로 저장되지 않았습니다.");
			}else {		//1이상인 경우는 저장이 된 경우
				System.out.println("ReplyDAO.register(): 정상적으로 저장되었습니다.");
			}
		} catch (SQLException e) {
			System.out.println("ReplyDAO.register(): SQL insert 오류 : " + e.getLocalizedMessage());
		}
	}

	public JSONArray rlist(int bcode) {
		String sql = "select rcode,bcode,reply,regdate from practicereply where bcode = ? order by rcode desc";
		JSONArray list = new JSONArray();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bcode);
			rs = pstmt.executeQuery();
			
			
			// View 페이지에서 반환 받을 때 json 데이터들이 String 타입이 아닌 경우 에러가 발생하더라
			while(rs.next()) {
				JSONObject json = new JSONObject();
				json.put("rcode", rs.getInt("rcode") + "");
				json.put("bcode", rs.getInt("bcode") + "");
				json.put("reply", rs.getString("reply"));
				json.put("regdate", rs.getDate("regdate") + "");
				list.add(json);
			}rs.close();pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public void close() {
		try {
			if(con !=null) {
				con.close();
				con=null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
