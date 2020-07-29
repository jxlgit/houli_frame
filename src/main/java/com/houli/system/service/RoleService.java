package com.houli.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.houli.system.domain.RoleDO;

@Service
public interface RoleService {

	RoleDO get(Long id);

	List<RoleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Long id);

	List<Long> list(Long userId);

	int batchremove(Long[] ids);
}
