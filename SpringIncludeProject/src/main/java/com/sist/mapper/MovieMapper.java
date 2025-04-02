package com.sist.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.*;
import com.sist.vo.MovieVO;

public interface MovieMapper {
	@Insert("INSERT INTO aopMovie VALUES("
			+ "#{mno},#{title},#{poster},#{director},#{genre})")
	public void movieInsert(MovieVO vo);
	@Delete("DELETE FROM aopMovie")
	public void movieDelete();
	@Select("SELECT * FROM aopMovie ORDER BY mno ASC")
	public List<MovieVO> movieListDate();
}
