package com.houli.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.houli.system.domain.UserDO;

/**
 * 
 * @Description:    用户  
 * @author: jxl     
 * @date:   2018年11月9日 下午4:13:47   
 * @version V1.0
 */
@Mapper
public interface UserDao {

	UserDO get(Long userId);
	
	List<UserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();

}
