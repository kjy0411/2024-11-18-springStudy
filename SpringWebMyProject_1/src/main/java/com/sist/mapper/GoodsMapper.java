package com.sist.mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;
import com.sist.vo.*;
public interface GoodsMapper {
	@Select("SELECT no,goods_name,goods_sub,goods_poster,goods_price,hit,num "
			+ "FROM (SELECT no,goods_name,goods_sub,goods_poster,goods_price,hit,rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(goods_all ga_no_pk)*/no,goods_name,goods_sub,goods_poster,goods_price,hit "
			+ "FROM goods_all)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(Map map);
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodsTotalPage();
	@Update("UPDATE goods_all SET hit=hit+1 WHERE no=#{no}")
	public void hitIncrement(int no);
	@Select("SELECT * FROM goods_all WHERE no=#{no}")
	public GoodsVO goodsDetailData(int no);
}
