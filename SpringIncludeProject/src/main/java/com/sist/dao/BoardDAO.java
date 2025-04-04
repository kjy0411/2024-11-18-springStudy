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
			String sql="SELECT no,subject,name,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,group_tab,num "
					+ "FROM (SELECT no,subject,name,hit,regdate,group_tab,rownum as num "
					+ "FROM (SELECT no,subject,name,hit,regdate,group_tab "
					+ "FROM springReplyBoard ORDER BY group_id DESC,group_step ASC)) "
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
				vo.setGroup_tab(rs.getInt(6));
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
			String sql="SELECT COUNT(*) FROM springReplyBoard";
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
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck=false;
		try {
			getConnection();
			String sql="SELECT pwd FROM springReplyBoard WHERE no="+vo.getNo();
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			if(db_pwd.equals(vo.getPwd())) {
				bCheck=true;
				sql="UPDATE springReplyBoard SET "
					+"name=?,subject=?,content=? "
					+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				ps.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return bCheck;
	}
	// reply / delete => 트랜잭션
	/*  no : 고유번호
	 *  gi : 그룹(답변별로 모아서)
	 *  gs : 그룹안에서 출력순서
	 *  gt : 레벨(공백 깊이)
	 *  root : 상위 게시물 번호
	 *  depth : 답변 개수
	 * 						no	gi	gs	gt	root	depth
	 *  AAAAA				1	1	0	0	0		2
	 *    =>DDDDD			5	1	1	1	1		0
	 *    =>BBBBB			3	1	2	1	1		1
	 *      =>CCCCC			4	1	3	2	3		0
	 *  EEEEE				2	2	0	0	0		0
	 *  
	 */
	public void replyInsert(int pno,BoardVO vo) {
		try {
			getConnection();
			conn.setAutoCommit(false); // around start
			// SQL문장 여러개 있는 경우 => Transactional
			
			// => 상위 게시물 => group_id,group_step,group_tab
			String sql="SELECT group_id,group_step,group_tab "
					+ "FROM springReplyBoard "
					+ "WHERE no="+pno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int gi=rs.getInt(1); // => 그대로
			int gs=rs.getInt(2); // => +1
			int gt=rs.getInt(3); // => +1
			rs.close();
			// => group_step을 증가
			sql="UPDATE springReplyBoard SET "
					+ "group_step=group_step+1 "
					+ "WHERE group_id=? AND group_step>?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, gi);
			ps.setInt(2, gs);
			ps.executeUpdate();
			// => insert
			sql="INSERT INTO springReplyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root) "
				+"VALUES(srb_no_seq.nextval,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setInt(5, gi);
			ps.setInt(6, gs+1);
			ps.setInt(7, gt+1);
			ps.setInt(8, pno);
			ps.executeUpdate();
			// => depth++
			sql="UPDATE springReplyBoard SET "
				+"depth=depth+1 "
				+"WHERE no="+pno;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			
			conn.commit(); // around end
		} catch (Exception e) {
			try {
				conn.rollback(); // after-throwing
			} catch (Exception e2) {}
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true); // after
			} catch (Exception e2) {}
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
