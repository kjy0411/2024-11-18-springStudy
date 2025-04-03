package com.sist.dao;

import org.checkerframework.checker.units.qual.t;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.sql.*;
import com.sist.vo.*;
//@Scope("prototype") : 여러개 생성
@Repository // 싱글톤
public class BoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public BoardDAO() {
		try {
			Class.forName("oracle.jdbc.dirve.OracleDriver");
		} catch (Exception e) {}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {}
	}
	
	public void disConnection() {
		try {
			if(ps!=null) ps.close();;
			if(conn!=null) conn.close();;
		} catch (Exception e) {}
	}
	/////////////////////// SqlSessionFactory
	public List<BoardVO> boardListData(int page){
		List<BoardVO> list=new ArrayList<BoardVO>();
		try {
			getConnection();
			String sql="SELECT no,subject,name,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,num "
					+ "FROM (SELECT no,subject,name,hit,regdate,rownum as num "
					+ "FROM (SELECT no,subject,name,hit,regdate "
					+ "FROM springReplyBoard ORDER BY no DESC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=(rowSize*page);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setHit(rs.getInt(4));
				vo.setDbday(rs.getString(5));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	public int boardRowCount() {
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/10) FROM springReplyBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return total;
	}
	public void boardInsert(BoardVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO springReplyBoard(no,name,subject,content,pwd,group_id) "
					+ "VALUES(srb_no_seq.nextval,?,?,?,?,(SELECT NVL(MAX(group_id)+1,1) FROM springReplyBoard))";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	/*  
	 *  @Aspect
	 *  public class Transactional{
	 *  	@Around()
	 *  	public void around(JoinPoint jp){
	 *  		------------- around start : setAutoCommit(false)
	 *  		소스코딩
	 *  		------------- around end : commit()
	 *  	}
	 *  	@AfterThrowable()
	 *  	public void afterThorwing(Exception e){
	 *  		// catch : rollback()	
	 *  	}
	 *  	@After()
	 *  	public void after(JoinPoint jp){
	 *  		// finally : setAutoCommit(true)	
	 *  	}
	 *  }
	 */
	public BoardVO boardDetailData(int no){
		BoardVO vo=new BoardVO();
		try {
			getConnection();
			
			conn.setAutoCommit(false);
			//around start
			String sql="UPDATE springReplyBoard SET "
					+ "hit=hit+1 "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			
			sql="SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday "
					+ "FROM springReplyBoard "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setHit(rs.getInt(5));
			vo.setDbday(rs.getString(6));
			rs.close();
			//around end
			conn.commit();
		} catch (Exception ex) {
			//after-throwing
			try {
				conn.rollback();
			} catch (Exception e) {}
			ex.printStackTrace();
		} finally {
			//after
			try {
				conn.setAutoCommit(true);
			} catch (Exception e) {}
			disConnection();
		}
		return vo;
	}
	public BoardVO boardUpdateData(int no){
		BoardVO vo=new BoardVO();
		try {
			getConnection();
			
			String sql="SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday "
					+ "FROM springReplyBoard "
					+ "WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setHit(rs.getInt(5));
			vo.setDbday(rs.getString(6));
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return vo;
	}
	public void boardUpdate(BoardVO vo) {
		try {
			getConnection();
			String sql="SELECT pwd FROM springReplyBoard";
			sql="UPDATE springReplyBoard SET "
					+ "name=?,subject=?,content=? "
					+ "WHERE no=?";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	public void boardDelete(BoardVO vo) {
		try {
			getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
}
