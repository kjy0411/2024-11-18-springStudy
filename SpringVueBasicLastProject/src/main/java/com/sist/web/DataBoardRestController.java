package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;

// => Vue / React => Spring-Boot
// yml
@RestController
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dao;
	
	@GetMapping(value = "databoard/list_vue.do")
	public Map databoardListData(int page){
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<DataBoardVO> list=dao.dataBoardListData(map);
		int totalpage=dao.dataBoardTotalPage();
		
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		
		map=new HashMap();
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		
		//ObjectMapper mapper=new ObjectMapper();
		//String json=mapper.writeValueAsString(map);
		return map;
	}
	@PostMapping(value = "databoard/insert_ok.do",produces = "text/plain;charset=UTF-8")
	public String databoardInsert(DataBoardVO vo,HttpServletRequest request) {
		String path="C:\\upload";
		//String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
		String result="";
		try {
			List<MultipartFile> list=vo.getFiles();
			if(list==null) {
				vo.setFilename("");
				vo.setFilesize("");
				vo.setFilecount(0);
			}else {
				String filename="";
				String filesize="";
				for(MultipartFile mf:list) {
					String name=mf.getOriginalFilename();
					File file=new File(path+"\\"+name);
					mf.transferTo(file);
					filename+=name+",";
					filesize+=file.length()+",";
				}
				filename=filename.substring(0,filename.lastIndexOf(","));
				filesize=filesize.substring(0,filesize.lastIndexOf(","));
				vo.setFilename(filename);
				vo.setFilesize(filesize);
				vo.setFilecount(list.size());
			}
			result="yes";
			dao.dataBoardInsert(vo);
		} catch (Exception e) {
			e.printStackTrace();
			result=e.getMessage();
		}
		return result;
	}
	@GetMapping(value = "databoard/detail_vue.do")
	public DataBoardVO databoard_detail(int no) throws Exception{
		DataBoardVO vo=dao.dataBoardDetailData(no);
		
		return vo;
	}
	@GetMapping(value = "databoard/download.do")
	public void databoard_download(String fn,HttpServletResponse response) {
		try {
			String path="c:\\upload";
			File file=new File(path+"\\"+fn);
			
			response.setContentLength((int)file.length());
			response.setHeader("Content-Disposition", "attachment;filename"+URLEncoder.encode(fn,"UTF-8"));
			
			//복사
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			int i=0; // 읽은 바이트 수
			byte[] buffer=new byte[1024];
			while((i=bis.read(buffer,0,1024))!=-1) {
				bos.write(buffer,0,i);
			}
			bis.close();
			bos.close();
		} catch (Exception e) {}
	}
}
