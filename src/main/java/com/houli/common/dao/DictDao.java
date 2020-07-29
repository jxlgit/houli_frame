package com.houli.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.houli.common.domain.DictDO;

/**
 * 字典表
 * @author jxl
 * @email 630326347@qq.com
 * @date 2018-12-17 16:41:33
 */
@Mapper
public interface DictDao {

	DictDO get(Long id);
	
	List<DictDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DictDO dict);
	
	int update(DictDO dict);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	List<DictDO> listType();
}
