package com.myspring.test.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
	
	@Autowired
	BoardDAO dao;
	
	//contextPath
	@ModelAttribute("cp")
	public String getContextPath(Model model, HttpServletRequest request) {
		model.addAttribute("cp", request.getContextPath());
		return request.getContextPath();
	}
	
	//Board 전체 List
	@RequestMapping(value = "/board/boardList", method=RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		System.out.println("==== list() ==== ");
		ArrayList<Board> boardList = dao.getBoardList();
		
		// 한 페이지에 보여줄 게시글 개수
		int pageSize = 3;
		
		// 현재 페이지 번호
		int currentPage = 1;
		if(request.getParameter("pageNum") != null) {
			currentPage = Integer.parseInt(request.getParameter("pageNum"));	
		}
		
		// 전체 게시글 수
		int count = boardList.size();
			
		// MySQL에서 꺼내올 게시글의 시작번호(= 0부터 시작하는 인덱스 값)
		int startRow = (currentPage - 1) * pageSize;
		
		// boardList.jsp 페이지에서 현재 페이지의 게시글 시작 number
				int number = count - (currentPage - 1) * pageSize;	
				
		// 페이지 하단에 클릭 가능한 번호의 개수		예) [1][2][3][4][5][다음]
		int clickablePageCount = 5;
		// 전체 페이지 수
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		
		// 페이지 하단 번호의 시작 값
		int startPage = 0;
		if(currentPage % clickablePageCount != 0) {
			startPage = (currentPage / clickablePageCount) * clickablePageCount + 1;
		}else {
			startPage = (currentPage / clickablePageCount - 1) * clickablePageCount + 1;
		}
		
		int endPage = startPage + clickablePageCount - 1;
		if(endPage > pageCount) endPage = pageCount;
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("number", number);
		model.addAttribute("count", count);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageSize", pageSize);
		
		model.addAttribute("clickablePageCount", clickablePageCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board/boardList";
	}
	
	//글쓰기
	@RequestMapping(value = "/board/boardWritePro", method = RequestMethod.POST)
	public String boardWritePro(HttpServletRequest request, Model model, Board bean) {
		System.out.println("==== boardWritePro() ====");
		
		dao.insertBoard(bean);
		
		model.addAttribute("bean", bean);
		
		return "redirect:/board/boardList";
	}
	
	
	
	@RequestMapping(value = "/board/boardWriteForm", method = RequestMethod.GET)
	public String boardWriteForm() {
		System.out.println("==== boardWriteFrom() ====");
		
		return "board/boardWriteForm";
	}
	
	@RequestMapping(value = "/board/boardInfo", method = RequestMethod.GET)
	public String boardInfo(Integer num, Model model) {
		System.out.println("==== boardInfo() ====");
		
		Board bean = dao.getOneBoard(num);
		
		model.addAttribute("bean", bean);
		
		return "board/boardInfo";
	}
	
	@RequestMapping(value="/board/deleteBoard", method=RequestMethod.GET)
	public String deleteBoard(Integer num) {
		System.out.println("==== delete() ====");
		
		dao.deleteBoard(num);
		
		return "redirect:/board/boardList";
	}

}
