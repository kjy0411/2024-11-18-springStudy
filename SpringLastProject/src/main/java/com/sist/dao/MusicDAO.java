package com.sist.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class MusicDAO {
	@Autowired
	private MusicMapper mapper;
	
	public void musicInsert(MusicVO vo) {
		mapper.musicInsert(vo);
	}
	public void musicDelete() {
		mapper.musicDelete();
	}
	public MusicVO musicTop() {
		return mapper.musicTop();
	}
}
