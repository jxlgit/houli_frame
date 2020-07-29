package com.houli.common.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houli.common.dao.GeneratorDao;
import com.houli.common.domain.GeneratorDO;
import com.houli.common.service.GeneratorService;
import com.houli.common.utils.GenUtils;


@Service
public class GeneratorServiceImpl implements GeneratorService {
	
	@Autowired
	private GeneratorDao generatorDao;

	@Override
	public List<GeneratorDO> list(Map<String, Object> params) {
		List<GeneratorDO> list = generatorDao.list(params);
		return list;
	}
	
	@Override
	public int count(Map<String, Object> params) {
		return generatorDao.count(params);
	}

	@Override
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		for(String tableName : tableNames){
			//查询表信息
			GeneratorDO table = generatorDao.get(tableName);
			//查询列信息
			List<Map<String, String>> columns = generatorDao.listColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}
