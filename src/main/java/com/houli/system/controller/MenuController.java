package com.houli.system.controller;

import java.util.ArrayList;
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
import com.houli.common.utils.Result;
import com.houli.system.domain.MenuDO;
import com.houli.system.service.MenuService;

/**
 * @author bootdo 1992lcg@163.com
 */
@RequestMapping("/system/menu")
@Controller
public class MenuController extends BaseController {
	private String prefix = "system/menu";
	
	@Autowired
	MenuService menuService;

	@RequiresPermissions("system:menu:menu")
	@GetMapping()
	String menu(Model model) {
		return prefix+"/menu";
	}

	@RequiresPermissions("system:menu:menu")
	@PostMapping("/list")
	@ResponseBody
	List<MenuDO> list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		List<MenuDO> menusList = menuService.list(null);
		return menusList;
	}

	/**
	 * 获取单条数据
	 */
	@ResponseBody
	@GetMapping("/get/{id}")
	@RequiresPermissions("system:menu:get")
	public MenuDO get(@PathVariable("id") Long id){
		MenuDO mdo = menuService.get(id);
		return mdo;
	}
	
	@Log("添加菜单")
	@RequiresPermissions("system:menu:add")
	@GetMapping("/add/{pId}")
	String add(Model model, @PathVariable("pId") Long pId) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
		return prefix + "/add";
	}

	@Log("编辑菜单")
	@RequiresPermissions("system:menu:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		MenuDO mdo = menuService.get(id);
		Long pId = mdo.getParentId();
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
		model.addAttribute("menu", mdo);
		return prefix+"/edit";
	}

	@Log("保存菜单")
	@RequiresPermissions("system:menu:add")
	@PostMapping("/save")
	@ResponseBody
	Result save(MenuDO menu) {
		if (menuService.save(menu) > 0) {
			return Result.ok();
		} else {
			return Result.error(1, "保存失败");
		}
	}

	@Log("更新菜单")
	@RequiresPermissions("system:menu:edit")
	@PostMapping("/update")
	@ResponseBody
	Result update(MenuDO menu) {
		if (menuService.update(menu) > 0) {
			return Result.ok();
		} else {
			return Result.error(1, "更新失败");
		}
	}

	@Log("删除菜单")
	@RequiresPermissions("system:menu:remove")
	@PostMapping("/remove")
	@ResponseBody
	Result remove(Long id) {
		if (menuService.remove(id) > 0) {
			return Result.ok();
		} else {
			return Result.error(1, "删除失败");
		}
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:dept:batchRemove")
	Result remove(@RequestParam("ids[]") Long[] id) {
		menuService.batchRemove(id);
		return Result.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	List<Tree<MenuDO>> tree() {
		Tree<MenuDO>  tree = menuService.getTree();
		List<Tree<MenuDO>> listTree = new ArrayList<Tree<MenuDO>>();
		listTree.add(tree);
		return listTree;
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	List<Tree<MenuDO>> tree(@PathVariable("roleId") Long roleId) {
		Tree<MenuDO> tree = menuService.getTree(roleId);
		List<Tree<MenuDO>> listTree = new ArrayList<Tree<MenuDO>>();
		listTree.add(tree);
		return listTree;
	}
}
