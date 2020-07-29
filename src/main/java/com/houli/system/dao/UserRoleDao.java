package com.houli.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.houli.system.domain.UserRoleDO;

/**
 * 
 * @Description:    用户与角色对应关系  
 * @author: jxl     
 * @date:   2018年11月9日 下午4:14:12   
 * @version V1.0
 */
@Mapper
public interface UserRoleDao {

	UserRoleDO get(Long id);

	List<UserRoleDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserRoleDO userRole);

	int update(UserRoleDO userRole);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<Long> listRoleId(Long userId);

	int removeByUserId(Long userId);

	int removeByRoleId(Long roleId);

	int batchSave(List<UserRoleDO> list);

	int batchRemoveByUserId(Long[] ids);
}
