package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface MemberMapper {
	@Select("SELECT COUNT(*) FROM project_member "
			+"WHERE id=#{id}")
	public int memberIdCount(String id);
	  
	@Select("SELECT pwd,id,name,sex FROM project_member "
			+"WHERE id=#{id}")
	public MemberVO memberInfoData(String id);
}
