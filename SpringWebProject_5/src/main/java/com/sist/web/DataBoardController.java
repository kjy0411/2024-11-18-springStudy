package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/*  
 *  1. DataBasc 연동
 *  2. Web 연동
 *  		Model	   DAO
 *  브라우저 <====> 자바 <====> 오라클
 *    | NodeJS	   | 파이썬,C#
 *    
 * 		DispatcherServlet => HandleMapping => ViewResolver => JSP
 * 				| 동작하기 위한 작업 설정 (메뉴얼 설정)
 * 									 --------- XML, properties
 * 											   ---------------- 메타 데이터
 *  1. 사용자가 요청한 데이터 읽기 => detail.do?no=10
 *  	<a> <form> ajax, axios
 *  	*** detail.do
 *  			   -- DispatcherServlet web.xml
 *  					| => interceptor (자동로그인)
 *  2. DispatchServlet => HandlerMapping
 *  					  --------------
 *  						| @RequestMapping
 *  						| @GetMapping
 *  						| @PostMapping
 *  							=> 밑에 있는 메소드 호출
 *  							| 출력에 필요한 데이터를 JSP
 *  							-----------------------
 *  									| => interceptor (보안처리)
 *  							JSP로 데이터를 전송 
 *  								=> 어떤 JSP로 전송 할지
 *  									=> return "main/main.jsp"
 *  								=> 어떤 값을 전송 할지
 *  									=> request.setAttribute()
 *  									=> model.addAttribute()
 *  								=> 전송하는 클래스
 *  									=> ViewResolver
 *  										| 경로명 / 확장자
 *  
 *  											| => interceptor
 */
/*
 *  @GetMapping : 목록 출력, 상세보기 => SELECT
 *  	default => 화면 출력
 *  				<a> sendRedirect, location.href
 *  @PostMapping : 입력값 => INSERT
 *  				<form> ajax => type:'post', axios.post()
 *  --------------------------------------- web에서는 두개만 사용
 *  @putMapping : 수정 => UPDATE
 *  @DeleteMapping : 삭제 => DELETE
 *  -------------- RestFul @RequestMapping
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class DataBoardController {
	@Autowired
	private DataBoardDAO dDao;
	
	@Autowired
	private FileInfoDAO fDao;
	
	@GetMapping("databoard/list.do")
	public String databoard_list(String page,Model model) {
		// request대신 JSP 전송하는 객체 : Model
		// request => Cookie 읽기
		// response => Cookie 저장, 다운로드시에 사용
		/*  
		 *  매개변수로 데이터를 받는 경우
		 *  => 값이 없다 : String (null값이 들어간다)
		 *  => 모든 값이 있는 경우 : 해당 데이터형
		 *  
		 *  1. 순서가 없다
		 *  2. 모든 요청값은 String(null값이 들어간다)
		 *  3.
		 *  4.
		 *  5. List, String[]
		 *  			| 동적 쿼리
		 *  	| 파일 업로드
		 *  6. JPS내장 객체를 받을 수 있다
		 *  	HttpServletRequest
		 *  	HttpServletResponse
		 *  	HttpSession
		 *  	RedirectAttributes
		 *  	ServletContext => application => realPath
		 */
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<DataBoardVO> list=dDao.boardListData((curpage*10)-9, (curpage*10));
		int count=dDao.boardRowCount();
		int tatolpage=(int)(Math.ceil(count/10.0));
		count=count-((curpage*10)-10);
		
		// 전송
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",tatolpage);
		model.addAttribute("count",count);
		return "databoard/list";
	}
	@GetMapping("databoard/insert.do")
	public String databoard_insert() {
		return "databoard/insert";
	}
	@PostMapping("databoard/insert_ok.do")
	public String databoard_insert_ok(DataBoardVO vo) {
		List<MultipartFile> list=vo.getFiles();
		System.out.println("전송된 파일 수 :"+list.size());
		for (int i = list.size() - 1; i >= 0; i--) {
		    if (list.get(i).getSize() == 0) {
		        list.remove(i);
		    }
		}
		System.out.println("실제 파일 수 :"+list.size());
		String path="c:\\download\\";
		try {
			vo.setFilecount(list.size());
			
			int no=dDao.boardInsert(vo);
			// 입력된 데이터
			
			// 파일 저장 => 데이터베이스에 저장
			FileInfoVO fvo=new FileInfoVO();
			fvo.setNo(no);
			if(list.size()>0) {
				for(MultipartFile mf:list) {
					String filename=mf.getOriginalFilename();
					File file=new File(path+filename);
					mf.transferTo(file);// 업로드
					fvo.setFilename(filename);
					fvo.setFilesize(file.length());
					
					fDao.boardFileInsert(fvo);// db에 저장
				}
			}
		} catch (Exception e) {}
		return "redirect:list.do";
	}
	@GetMapping("databoard/detail.do")
	public String databoard_detail(int no,Model model) {
		DataBoardVO vo=dDao.boardDetailData(no);
		List<FileInfoVO> list=fDao.fileListData(no);
		model.addAttribute("vo",vo);
		model.addAttribute("list",list);
		return "databoard/detail";
	}
}
