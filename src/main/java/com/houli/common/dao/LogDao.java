package com.houli.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.houli.common.domain.LogDO;

/**
 * 系统日志
 * @author jxl
 * @email 630326347@qq.com
 * @date 2018-12-06 16:57:24
 */
@Mapper
public interface LogDao {

	LogDO get(Long id);
	
	List<LogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LogDO log);
	
	int update(LogDO log);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
