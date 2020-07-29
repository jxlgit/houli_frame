package com.houli.system.service;

import com.houli.common.domain.Tree;
import com.houli.system.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Description:    TODO(部门管理)   
 * @author: jxl     
 * @date:   2018年12月17日 下午4:25:48   
 * @version V1.0
 */
public interface DeptService {
	
	DeptDO get(Long deptId);
	
	List<DeptDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptDO sysDept);
	
	int update(DeptDO sysDept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

	Tree<DeptDO> getTree();
	
	boolean checkDeptHasUser(Long deptId);
}
