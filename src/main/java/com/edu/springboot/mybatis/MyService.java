package com.edu.springboot.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
 
@Mapper
public interface MyService {
	
	public List<CommonDTO> select();
	
	
	
	
	
	
	
//	public int insert(CommonDTO memberDTO);
//	public CommonDTO selectOne(CommonDTO memberDTO);
//	public int update(CommonDTO memberDTO);
//	public int delete(CommonDTO memberDTO);
}
