package com.edu.springboot.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
 
@Mapper
public interface MyService {
	
	public List<CommonDTO> select();
	public int insert(CommonDTO commonDTO);
	public List<CommonDTO> selectExcel();
	public CommonDTO selectOne(CommonDTO commonDTO);
	
	
//	public int update(CommonDTO commonDTO);
//	public int delete(CommonDTO commonDTO);
}
