package com.houli.common.service;

import java.util.List;
import java.util.Map;

import com.houli.common.domain.DictDO;

/**
 * 字典表
 * 
 * @author jxl
 * @email 630326347@qq.com
 * @date 2018-12-17 16:41:33
 */
public interface DictService {
	
	DictDO get(Long id);
	
	List<DictDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DictDO dict);
	
	int update(DictDO dict);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	/**
	 * 获取所有字典类型
	 * @return
	 */
	List<DictDO> listType();
	
	/**
	 * 根据type和value获取name
 	 * @return
	 */
	String getName(String type,String value);

	/**
	 * 获取性别列表
 	 * @return
	 */
	List<DictDO> getSexList();

	/**
	 * 根据type获取数据
	 * @param map
	 * @return
	 */
	List<DictDO> listByType(String type);
}
