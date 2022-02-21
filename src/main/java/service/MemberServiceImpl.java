package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.MemberDAO;
import vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO dao = new MemberDAO();
	
	@Override
	public List<MemberVO> selectList() {
		return dao.selectList();
	} //selectList
	@Override
	public MemberVO selectOne(MemberVO vo) {
		return dao.selectOne(vo);
	} //selectOne

	@Override
	public int insert(MemberVO vo) {
		return dao.insert(vo) ;
	} //insert
	@Override
	public int update(MemberVO vo) {
		return dao.update(vo) ;
	} //update
	@Override
	public int delete(MemberVO vo) {
		return dao.delete(vo) ;
	} //delete
	
} //class
