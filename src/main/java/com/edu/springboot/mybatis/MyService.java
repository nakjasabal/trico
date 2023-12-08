package com.edu.springboot.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
 
@Mapper
public interface MyService {
	
	public List<CommonDTO> select();
	public int insert(CommonDTO commonDTO);
	public List<CommonDTO> selectExcel(CommonDTO commonDTO);
	public CommonDTO selectOne(CommonDTO commonDTO);
	public List<CommonDTO> groupByFlag();
	public int dataUpdate(Map<String, String> maps);
	
	
//	public int update(CommonDTO commonDTO);
//	public int delete(CommonDTO commonDTO);
}
