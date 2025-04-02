package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class MovieDAO {
	@Autowired
	private MovieMapper mapper;
	
	public void movieInsert(MovieVO vo) {
		mapper.movieInsert(vo);
	}
	public void movieDelete() {
		mapper.movieDelete();
	}
	public List<MovieVO> movieListDate(){
		return mapper.movieListDate();
	}
}
