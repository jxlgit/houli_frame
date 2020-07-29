/**
 * 
 */
package com.houli.common.service;

import java.util.List;
import java.util.Map;

import com.houli.common.domain.GeneratorDO;

/**
 * 
 * @Description:    代码生成业务层   
 * @author: jxl     
 * @date:   2018年11月9日 下午4:12:16   
 * @version V1.0
 */
public interface GeneratorService {
	List<GeneratorDO> list(Map<String, Object> params);
	int count(Map<String, Object> params);
	
	byte[] generatorCode(String[] tableNames);
}
