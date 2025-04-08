package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface FoodMapper {
  @Select("SELECT fno,poster,name,rownum "
		 +"FROM project_food "
		 +"WHERE rownum<=12")
  public List<FoodVO> foodListData();
}
