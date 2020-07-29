package com.houli.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.houli.system.domain.MenuDO;

/**
 * 
 * @Description:    菜单管理
 * @author: jxl     
 * @date:   2018年11月9日 下午4:13:04   
 * @version V1.0
 */
@Mapper
public interface MenuDao {

	MenuDO get(Long menuId);
	
	List<MenuDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MenuDO menu);
	
	int update(MenuDO menu);
	
	int remove(Long menuId);
	
	int batchRemove(Long[] menuIds);
	
	List<MenuDO> listMenuByUserId(Long id);
	
	List<String> listUserPerms(Long id);
}
