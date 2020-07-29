package com.houli.system.controller;

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

import com.houli.common.annotation.Log;
import com.houli.common.controller.BaseController;
import com.houli.common.utils.PageUtils;
import com.houli.common.utils.Query;
import com.houli.common.utils.Result;
import com.houli.system.domain.RoleDO;
import com.houli.system.service.RoleService;

@RequestMapping("/system/role")
@Controller
public class RoleController extends BaseController {
	private String prefix = "system/role";
	
	@Autowired
	RoleService roleService;

	@RequiresPermissions("system:role:role")
	@GetMapping()
	String role() {
		return prefix + "/role";
	}
	
	/**
	 * 分页查询列表数据
	 */
	@ResponseBody
	@PostMapping("/list")
	@RequiresPermissions("system:role:role")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RoleDO> tableList = roleService.list(query);
		int total = roleService.count(query);
		PageUtils pageUtils = new PageUtils(tableList, total);
		return pageUtils;
	}
	
	@GetMapping("/list_all")
	@ResponseBody
	List<RoleDO> add(Model model) {
		List<RoleDO> roles = roleService.list(new HashMap<>(16));
		return roles;
	}
	
	/**
	 * 获取单条数据
	 */
	@ResponseBody
	@GetMapping("/get/{id}")
	@RequiresPermissions("system:role:get")
	public RoleDO get(@PathVariable("id") Long id){
		//查询列表数据
		RoleDO roleDO = roleService.get(id);
		return roleDO;
	}
	

	@Log("编辑角色")
	@RequiresPermissions("system:role:edit")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		RoleDO roleDO = roleService.get(id);
		model.addAttribute("role", roleDO);
		return prefix + "/edit";
	}

	@Log("保存角色")
	@RequiresPermissions("system:role:add")
	@PostMapping("/save")
	@ResponseBody()
	Result save(RoleDO role) {
		role.setUserIdCreate(getUserId());
		if (roleService.save(role) > 0) {
			return Result.ok();
		} else {
			return Result.error(1, "保存失败");
		}
	}

	@Log("更新角色")
	@RequiresPermissions("system:role:edit")
	@PostMapping("/update")
	@ResponseBody()
	Result update(RoleDO role) {
		if (roleService.update(role) > 0) {
			return Result.ok();
		} else {
			return Result.error(1, "保存失败");
		}
	}

	@Log("删除角色")
	@RequiresPermissions("system:role:remove")
	@PostMapping("/remove")
	@ResponseBody()
	Result save(Long id) {
		if (roleService.remove(id) > 0) {
			return Result.ok();
		} else {
			return Result.error(1, "删除失败");
		}
	}
	
	@RequiresPermissions("system:role:batchRemove")
	@Log("批量删除角色")
	@PostMapping("/batchRemove")
	@ResponseBody
	Result batchRemove(@RequestParam("ids[]") Long[] ids) {
		int r = roleService.batchremove(ids);
		if (r > 0) {
			return Result.ok();
		}
		return Result.error();
	}
}
