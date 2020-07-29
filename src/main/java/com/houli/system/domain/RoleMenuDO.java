package com.houli.system.domain;

/**
 * 
 * @Description:    角色对应菜单   
 * @author: jxl     
 * @date:   2018年11月9日 下午4:15:32   
 * @version V1.0
 */
public class RoleMenuDO {
	private Long id;
	private Long  roleId;
	private Long menuId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RoleMenuDO{" +
				"id=" + id +
				", roleId=" + roleId +
				", menuId=" + menuId +
				'}';
	}
}
