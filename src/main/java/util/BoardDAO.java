package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.BoardVO;

//** DAO (Data Access Object)
//=> CRUD 구현 
// C: create -> insert
// R: read   -> selectList, selectOne
// U: update -> update
// D: delete -> delete

//=> 전역변수 정의 , 메서드 작성
public class BoardDAO {
	
	Connection cn = DBConnection.getConnection();
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	String sql;
	
	// ** selectList
	public List<BoardVO> selectList() {
		sql="select seq, title, id, regdate, cnt from board order by seq desc" ;
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			// 요청객체로 결과 전달
			// => 출력자료가 있는지 확인
			if (rs.next()) {
				// => 출력자료 1row -> vo 에 set  -> list 에 add 
				do {
					BoardVO vo = new BoardVO();
					vo.setSeq(rs.getInt(1));
					vo.setTitle(rs.getString(2));
					vo.setId(rs.getString(3));
					vo.setRegdate(rs.getString(4));
					vo.setCnt(rs.getInt(5));
					list.add(vo);
				}while(rs.next());
			}else {
				System.out.println("** selectList: 출력 자료가 없습니다 ~~");
				list=null;
			}
		} catch (Exception e) {
			System.out.println("** selectList Exception => "+e.toString());
			list=null;
		}
		return list;
	} //selectList()

	// ** selectOne
	public BoardVO selectOne(BoardVO vo) {
		sql="select * from board where seq=?" ;
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1,vo.getSeq());
			rs = pst.executeQuery();
			// 결과 확인
			if (rs.next()) {
				vo.setSeq(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setId(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setRegdate(rs.getString(5));
				vo.setCnt(rs.getInt(6));
			}else {
				System.out.println("** 글번호에 해당하는 자료가 없습니다 ~~ **");
				vo=null;
			} //else
		} catch (Exception e) {
			System.out.println("** selectOne Exception => "+e.toString());
			vo=null;
		}
		return vo;
	} //selectOne	
	
	// ** 새글등록 -> insert
	// ** MySQL 
	//sql="insert into board(title,id,content) values(?,?,?)";
	     // insert into board (title,id,content) values ('Spring 이란?','green','처음엔 ~~~ 편리하다'); 
	public int insert(BoardVO vo) {
		// ** Oracle : seq 에 nvl함수 적용 
		sql="insert into board values("
				+ "(select nvl(max(seq),0)+1 from board)"
				+ ",?,?,?,sysdate,0)";
		// insert into board values (100,'Spring 이란?','green','처음엔 ~~~ 편리하다',sysdate,0); 
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,vo.getTitle());
			pst.setString(2,vo.getId());
			pst.setString(3,vo.getContent());
			return pst.executeUpdate();
			// executeUpdate() => 처리된 row 의 갯수 return
		} catch (Exception e) {
			System.out.println("** insert Exception => "+e.toString());
			return 0;
		}
	} //insert
	
	// ** Update
	public int update(BoardVO vo) {
		sql="update board set title=?,content=? where seq=?";
		// update board set title='newTitle', content='newContent' where seq=100 ; 
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1,vo.getTitle());
			pst.setString(2,vo.getContent());
			pst.setInt(3,vo.getSeq());
			return pst.executeUpdate();
			// executeUpdate() => 처리된 row 의 갯수 return
		} catch (Exception e) {
			System.out.println("** update Exception => "+e.toString());
			return 0;
		}
	} //update
	
	// ** Delete
	public int delete(BoardVO vo) {
		sql="delete from board where seq=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, vo.getSeq());
			return pst.executeUpdate() ;
		} catch (Exception e) {
			System.out.println("** delete Exception => "+e.toString());
			return 0;
		}
	} //delete
	
	// ** 조회수증가
	public int countUp(BoardVO vo) {
		sql="update board set cnt=cnt+1 where seq=?";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1,vo.getSeq());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** countUp Exception => "+e.toString());
			return 0;
		} 
	} //countUp

} // class
