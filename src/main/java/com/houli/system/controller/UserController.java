package com.houli.system.controller;

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
import com.houli.common.domain.Tree;
import com.houli.common.service.DictService;
import com.houli.common.utils.MD5Utils;
import com.houli.common.utils.PageUtils;
import com.houli.common.utils.Query;
import com.houli.common.utils.Result;
import com.houli.system.domain.DeptDO;
import com.houli.system.domain.UserDO;
import com.houli.system.service.RoleService;
import com.houli.system.service.UserService;
import com.houli.system.vo.UserVO;

@RequestMapping("/system/user")
@Controller
public class UserController extends BaseController {
	
	private String prefix="system/user";
	
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	DictService dictService;
	@RequiresPermissions("system:user:user")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@PostMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	/**
	 * 进入添加页面
	 */
	@GetMapping("/add")
	@RequiresPermissions("system:user:add")
	@Log("添加用户")
	String add(){
	    return prefix + "/add";
	}

	@RequiresPermissions("system:user:get")
	@GetMapping("/get/{id}")
	String preview(Model model, @PathVariable("id") Long id) {
		UserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		List<Long> roleIds = roleService.list(id);
		model.addAttribute("roleIds", roleIds);
		return prefix+"/preview";
	}
	
	@RequiresPermissions("system:user:edit")
	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		UserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		List<Long> roleIds = roleService.list(id);
		model.addAttribute("roleIds", roleIds);
		return prefix+"/edit";
	}

	@RequiresPermissions("system:user:add")
	@Log("保存用户")
	@PostMapping("/save")
	@ResponseBody
	Result save(UserDO user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user) > 0) {
			return Result.ok();
		}
		return Result.error();
	}

	@RequiresPermissions("system:user:edit")
	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	Result update(UserDO user) {
		if (userService.update(user) > 0) {
			return Result.ok();
		}
		return Result.error();
	}


	@RequiresPermissions("system:user:edit")
	@Log("更新用户")
	@PostMapping("/updatePeronal")
	@ResponseBody
	Result updatePeronal(UserDO user) {
		if (userService.updatePersonal(user) > 0) {
			return Result.ok();
		}
		return Result.error();
	}


	@RequiresPermissions("system:user:remove")
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	Result remove(Long id) {
		if (userService.remove(id) > 0) {
			return Result.ok();
		}
		return Result.error();
	}

	@RequiresPermissions("system:user:batchRemove")
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	Result batchRemove(@RequestParam("ids[]") Long[] userIds) {
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return Result.ok();
		}
		return Result.error();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exit(params);
	}

	@RequiresPermissions("system:user:resetPwd")
	@Log("请求更改用户密码")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") Long userId, Model model) {

		UserDO userDO = new UserDO();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return prefix + "/reset_pwd";
	}

	@Log("提交更改用户密码")
	@PostMapping("/resetPwd")
	@ResponseBody
	Result resetPwd(UserVO userVO) {
		try{
			userService.resetPwd(userVO, getUser());
			return Result.ok();
		}catch (Exception e){
			return Result.error(1,e.getMessage());
		}

	}
	@RequiresPermissions("system:user:resetPwd")
	@Log("admin提交更改用户密码")
	@PostMapping("/adminResetPwd")
	@ResponseBody
	Result adminResetPwd(UserVO userVO) {
		try{
			userService.adminResetPwd(userVO);
			return Result.ok();
		}catch (Exception e){
			return Result.error(1,e.getMessage());
		}

	}
	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = userService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}

	@GetMapping("/personal")
	String personal(Model model) {
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		model.addAttribute("sexList",dictService.getSexList());
		return prefix + "/personal";
	}
}
