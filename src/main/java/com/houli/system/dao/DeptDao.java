package com.houli.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.houli.system.domain.DeptDO;

/**
 * 
 * @Description:    部门管理  
 * @author: jxl     
 * @date:   2018年11月9日 下午4:12:52   
 * @version V1.0
 */
@Mapper
public interface DeptDao {

	DeptDO get(Long deptId);
	
	List<DeptDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DeptDO dept);
	
	int update(DeptDO dept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);
	
	Long[] listParentDept();
	
	int getDeptUserNumber(Long deptId);
}
