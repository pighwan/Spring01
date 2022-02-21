package service;

import java.util.List;

import util.BoardDAO;
import vo.BoardVO;


public class BoardService {
	
	BoardDAO dao = new BoardDAO();
	
	public List<BoardVO> selectList() {
		return dao.selectList();
	} //selectList
	public BoardVO selectOne(BoardVO vo) {
		return dao.selectOne(vo);
	} //selectOne

	public int insert(BoardVO vo) {
		return dao.insert(vo) ;
	} //insert
	public int update(BoardVO vo) {
		return dao.update(vo) ;
	} //update
	public int delete(BoardVO vo) {
		return dao.delete(vo) ;
	} //delete
	
	// ** 조회수증가
	public int countUp(BoardVO vo) {
		return dao.countUp(vo) ;
	} //countUp

} //class
