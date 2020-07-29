package com.houli.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houli.common.domain.Tree;
import com.houli.common.utils.BuildTree;
import com.houli.system.dao.DeptDao;
import com.houli.system.domain.DeptDO;
import com.houli.system.service.DeptService;



@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao sysDeptMapper;

	@Override
	public DeptDO get(Long deptId){
		return sysDeptMapper.get(deptId);
	}

	@Override
	public List<DeptDO> list(Map<String, Object> map){
		List<DeptDO> deptList = sysDeptMapper.list(null);
		
		//组装为层级格式
		if (deptList == null) {
			return null;
		}
		List<DeptDO> topNodes = new ArrayList<DeptDO>();
		for (DeptDO children : deptList) {
			if (children.getChildren() == null || children.getChildren().size() == 0) {
				children.setChildren(null);
				children.setState("open");
			}
			Long pid = children.getParentId();
			if (pid == null || 0L == pid) {
				topNodes.add(children);
				continue;
			}
			for (DeptDO parent : deptList) {
				Long id = parent.getDeptId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					continue;
				}
			}
		}
				
		return topNodes;
	}

	@Override
	public int count(Map<String, Object> map){
		return sysDeptMapper.count(map);
	}

	@Override
	public int save(DeptDO sysDept){
		if (sysDept.getParentId() == -1L) {
			sysDept.setParentId(0L);
		}
		return sysDeptMapper.save(sysDept);
	}

	@Override
	public int update(DeptDO sysDept){
		if (sysDept.getParentId() == -1L) {
			sysDept.setParentId(0L);
		}
		return sysDeptMapper.update(sysDept);
	}

	@Override
	public int remove(Long deptId){
		return sysDeptMapper.remove(deptId);
	}

	@Override
	public int batchRemove(Long[] deptIds){
		return sysDeptMapper.batchRemove(deptIds);
	}

	@Override
	public Tree<DeptDO> getTree() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String,Object>(16));
		for (DeptDO sysDept : sysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public boolean checkDeptHasUser(Long deptId) {
		// TODO Auto-generated method stub
		//查询部门以及此部门的下级部门
		int result = sysDeptMapper.getDeptUserNumber(deptId);
		return result==0?true:false;
	}

}
