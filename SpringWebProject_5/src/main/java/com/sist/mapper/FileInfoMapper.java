package com.sist.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.*;
import com.sist.vo.*;

public interface FileInfoMapper {
	@Insert("INSERT INTO springFileInfo VALUES("
			+ "#{no},#{filename},#{filesize})")
	public void boardFileInsert(FileInfoVO vo);
	@Select("SELECT * FROM springFileInfo WHERE no=#{no}")
	public List<FileInfoVO> fileListData(int no);
	@Delete("DELETE FROM springFileInfo WHERE no=#{no}")
	public void fineInfoDelete(int no);
}
