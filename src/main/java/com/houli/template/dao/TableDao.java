package com.houli.template.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.houli.template.domain.TableDO;

/**
 * 
 * @author jxl
 * @email 630326347@qq.com
 * @date 2018-11-15 14:58:55
 */
@Mapper
public interface TableDao {

	TableDO get(Long id);
	
	List<TableDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TableDO table);
	
	int update(TableDO table);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
