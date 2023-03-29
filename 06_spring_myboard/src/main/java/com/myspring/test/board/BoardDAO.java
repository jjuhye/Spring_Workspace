package com.myspring.test.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;


@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	

	
	public ArrayList<Board> getBoardList(){
		ArrayList<Board> boardList = new ArrayList<Board>();
		List<Board> list = sqlSession.selectList("mapper.board.selectAllBoardList");
		for(Board b : list) {
			boardList.add(b);
		}
		return boardList;
	}
	
	public void insertBoard(Board board) {
		sqlSession.insert("mapper.board.insertBoard", board);
	}
	
	public Board getOneBoard(int num) {
		
		Board board = sqlSession.selectOne("mapper.board.getBoardInfo", num);
		int count=board.getReadcount();
		sqlSession.update("mapper.board.addCount",count);
		return board;
	}
	
	public void deleteBoard(int num) {
		sqlSession.delete("mapper.board.deleteBoard", num);
	}
	
}
