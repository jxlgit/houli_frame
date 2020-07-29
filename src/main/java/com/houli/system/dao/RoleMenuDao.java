package com.houli.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.houli.system.domain.RoleMenuDO;

/**
 * 
 * @Description:    角色与菜单对应关系  
 * @author: jxl     
 * @date:   2018年11月9日 下午4:13:34   
 * @version V1.0
 */
@Mapper
public interface RoleMenuDao {

	RoleMenuDO get(Long id);
	
	List<RoleMenuDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RoleMenuDO roleMenu);
	
	int update(RoleMenuDO roleMenu);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	List<Long> listMenuIdByRoleId(Long roleId);
	
	int removeByRoleId(Long roleId);

	int removeByMenuId(Long menuId);
	
	int batchSave(List<RoleMenuDO> list);
}
