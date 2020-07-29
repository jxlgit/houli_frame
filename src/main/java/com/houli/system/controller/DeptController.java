package com.houli.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houli.common.config.Constant;
import com.houli.common.controller.BaseController;
import com.houli.common.domain.Tree;
import com.houli.common.utils.Result;
import com.houli.system.domain.DeptDO;
import com.houli.system.service.DeptService;

/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:40:36
 */

@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {
	private String prefix = "system/dept";
	
	@Autowired
	private DeptService sysDeptService;

	@GetMapping()
	@RequiresPermissions("system:dept:dept")
	String dept() {
		return prefix + "/dept";
	}

	@ResponseBody
	@PostMapping("/list")
	@RequiresPermissions("system:dept:dept")
	public List<DeptDO> list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		List<DeptDO> deptList = sysDeptService.list(null);
		return deptList;
	}
	
	/**
	 * 获取单条数据
	 */
	@ResponseBody
	@GetMapping("/get/{id}")
	@RequiresPermissions("system:dept:get")
	public DeptDO get(@PathVariable("id") Long id){
		DeptDO sysDept = sysDeptService.get(id);
		return sysDept;
	}
	
	@GetMapping("/add/{pId}")
	@RequiresPermissions("system:dept:add")
	String add(@PathVariable("pId") Long pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "总部门");
		} else {
			model.addAttribute("pName", sysDeptService.get(pId).getName());
		}
		return  prefix + "/add";
	}

	@GetMapping("/edit/{deptId}")
	@RequiresPermissions("system:dept:edit")
	String edit(@PathVariable("deptId") Long deptId, Model model) {
		DeptDO sysDept = sysDeptService.get(deptId);
		model.addAttribute("sysDept", sysDept);
		if(Constant.DEPT_ROOT_ID.equals(sysDept.getParentId())) {
			model.addAttribute("parentDeptName", "无");
		}else {
			DeptDO parDept = sysDeptService.get(sysDept.getParentId());
			model.addAttribute("parentDeptName", parDept.getName());
		}
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:dept:add")
	public Result save(DeptDO sysDept) {
		if (sysDeptService.save(sysDept) > 0) {
			return Result.ok();
		}
		return Result.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@PostMapping("/update")
	@RequiresPermissions("system:dept:edit")
	public Result update(DeptDO sysDept) {
		if (sysDeptService.update(sysDept) > 0) {
			return Result.ok();
		}
		return Result.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("system:dept:remove")
	public Result remove(Long deptId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", deptId);
		if(sysDeptService.count(map)>0) {
			return Result.error(1, "包含下级部门,不允许删除");
		}
		if(sysDeptService.checkDeptHasUser(deptId)) {
			if (sysDeptService.remove(deptId) > 0) {
				return Result.ok();
			}
		}else {
			return Result.error(1, "部门包含用户,不允许删除");
		}
		return Result.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:dept:batchRemove")
	public Result remove(@RequestParam("ids[]") Long[] deptIds) {
		sysDeptService.batchRemove(deptIds);
		return Result.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public List<Tree<DeptDO>> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = sysDeptService.getTree();
		List<Tree<DeptDO>> listTree = new ArrayList<Tree<DeptDO>>();
		listTree.add(tree);
		return listTree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/deptTree";
	}

}
