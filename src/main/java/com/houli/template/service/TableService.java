package com.houli.template.service;

import java.util.List;
import java.util.Map;

import com.houli.template.domain.TableDO;

/**
 * 
 * 
 * @author jxl
 * @email 630326347@qq.com
 * @date 2018-11-15 14:58:55
 */
public interface TableService {
	
	TableDO get(Long id);
	
	List<TableDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TableDO table);
	
	int update(TableDO table);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
