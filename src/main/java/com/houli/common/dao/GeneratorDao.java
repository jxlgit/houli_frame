package com.houli.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.houli.common.domain.GeneratorDO;

/**
 * 
 * @Description:    代码生成Dao  
 * @author: jxl     
 * @date:   2018年11月9日 下午4:12:52   
 * @version V1.0
 */
@Mapper
public interface GeneratorDao {
	
	GeneratorDO get(String tableName);
	
	List<GeneratorDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	@Select("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns\r\n"
			+ " where table_name = #{tableName} and table_schema = (select database()) order by ordinal_position")
	List<Map<String, String>> listColumns(String tableName);
	
}
