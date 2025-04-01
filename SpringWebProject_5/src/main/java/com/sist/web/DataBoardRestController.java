package com.sist.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
// 데이터값 전송 => 자바스크립트와 통신 => 일반문자열 / JSON
@RestController
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dDao;

	@Autowired
	private FileInfoDAO fDao;
	
	/*  
	 *  참조기 테이블 삭제시 => declare begin ~ end
	 *  <delete id="" parameterType="string">
	 *  	declare
	 *  	begin
	 *  		DELETE FROM jjim WHERE id=#{id}
	 *  		DELETE FROM like WHERE id=#{id}
	 *  		DELETE FROM reply WHERE id=#{id}
	 *  		DELETE FROM reserve WHERE id=#{id}
	 *  		DELETE FROM cart WHERE id=#{id}
	 *  		DELETE FROM buy WHERE id=#{id}
	 *  	end
	 *  </delete>
	 */
	
	@GetMapping(value = "databoard/delete_ok.do",produces = "text/plain;charset=UTF-8")
	public String databoard_delete_ok(int no,String pwd) {
		String result="no";
		String db_pwd=dDao.boardGetPassword(no);
		
		List<FileInfoVO> list=fDao.fileListData(no);
		int count=dDao.databoardFileCount(no);
		
		if(db_pwd.equals(pwd)) {
			result="yes";
			
			if(count>0) {
				fDao.fineInfoDelete(no);
				try {
					for(FileInfoVO vo:list) {
						File file=new File("c:\\download\\"+vo.getFilename());
						file.delete();
					}
				} catch (Exception e) {}
			}
			dDao.boardDelete(no);
		}
		return result;
	}
}
