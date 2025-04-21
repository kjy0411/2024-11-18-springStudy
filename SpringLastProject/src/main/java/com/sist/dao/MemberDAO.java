package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class MemberDAO {
	@Autowired
	public MemberMapper mapper;
	
	public MemberVO memberInfoData(String userid) {
		return mapper.memberInfoData(userid);
	}
}
