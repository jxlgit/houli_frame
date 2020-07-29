package com.houli.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houli.common.domain.Tree;
import com.houli.common.utils.BuildTree;
import com.houli.system.dao.MenuDao;
import com.houli.system.dao.RoleMenuDao;
import com.houli.system.domain.MenuDO;
import com.houli.system.service.MenuService;

@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuDao menuMapper;
	@Autowired
	RoleMenuDao roleMenuMapper;

	/**
	 * @param
	 * @return 树形菜单
	 */
	@Cacheable
	@Override
	public Tree<MenuDO> getSysMenuTree(Long id) {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuMapper.listMenuByUserId(id);
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public List<MenuDO> list(Map<String, Object> params) {
		List<MenuDO> menusList = menuMapper.list(params);
		
		//组装为层级格式
		if (menusList == null) {
			return null;
		}
		List<MenuDO> topNodes = new ArrayList<MenuDO>();
		for (MenuDO children : menusList) {
			if (children.getChildren() == null || children.getChildren().size() == 0) {
				children.setChildren(null);
				children.setState("open");
			}
			Long pid = children.getParentId();
			if (pid == null || 0L == pid) {
				topNodes.add(children);
				continue;
			}
			for (MenuDO parent : menusList) {
				Long id = parent.getMenuId();
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
		return menuMapper.count(map);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int remove(Long id) {
		int result = menuMapper.remove(id);
		return result;
	}
	
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int batchRemove(Long[] ids){
		return menuMapper.batchRemove(ids);
	}
	
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int save(MenuDO menu) {
		if (menu.getParentId() == -1L) {
			menu.setParentId(0L);
		}
		menu.setGmtCreate(new Date());
		int r = menuMapper.save(menu);
		return r;
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int update(MenuDO menu) {
		if (menu.getParentId() == -1L) {
			menu.setParentId(0L);
		}
		menu.setGmtModified(new Date());
		int r = menuMapper.update(menu);
		return r;
	}

	@Override
	public MenuDO get(Long id) {
		MenuDO menuDO = menuMapper.get(id);
		return menuDO;
	}

	@Override
	public Tree<MenuDO> getTree() {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuMapper.list(new HashMap<>(16));
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Tree<MenuDO> getTree(Long id) {
		// 根据roleId查询权限
		List<MenuDO> menus = menuMapper.list(new HashMap<String, Object>(16));
		List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(id);
		List<Long> temp = menuIds;
		for (MenuDO menu : menus) {
			if (temp.contains(menu.getParentId())) {
				menuIds.remove(menu.getParentId());
			}
		}
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuMapper.list(new HashMap<String, Object>(16));
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> state = new HashMap<>(16);
			Long menuId = sysMenuDO.getMenuId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
				tree.setChecked(true);
			} else {
				state.put("selected", false);
				tree.setChecked(false);
			}
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

	//@Cacheable(value = "listPerms",key = "#userId")
	@Override
	public Set<String> listPerms(Long userId) {
		List<String> perms = menuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	
	@Override
	public List<Tree<MenuDO>> listMenuTree(Long id) {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuMapper.listMenuByUserId(id);
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			tree.setUrl(sysMenuDO.getUrl());
			tree.setIconCls(sysMenuDO.getIcon());
			// 添加其他属性
//			Map<String, Object> attributes = new HashMap<>(16);
//			attributes.put("url", sysMenuDO.getUrl());
//			attributes.put("icon", sysMenuDO.getIcon());
//			tree.setAttributes(attributes);
			
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		//注意在配置权限时需要包含他的父权限
		List<Tree<MenuDO>> list = BuildTree.buildList(trees, "0");
		return list;
	}

}
