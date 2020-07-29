package com.houli.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.houli.common.domain.Tree;
import com.houli.system.domain.DeptDO;
import com.houli.system.domain.UserDO;
import com.houli.system.vo.UserVO;

@Service
public interface UserService {
	UserDO get(Long id);

	List<UserDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserDO user);

	int update(UserDO user);

	int remove(Long userId);

	int batchremove(Long[] userIds);

	boolean exit(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	int resetPwd(UserVO userVO,UserDO userDO) throws Exception;
	int adminResetPwd(UserVO userVO) throws Exception;
	Tree<DeptDO> getTree();

	/**
	 * 更新个人信息
	 * @param userDO
	 * @return
	 */
	int updatePersonal(UserDO userDO);

}
