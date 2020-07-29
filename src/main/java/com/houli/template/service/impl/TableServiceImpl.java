package com.houli.template.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houli.template.dao.TableDao;
import com.houli.template.domain.TableDO;
import com.houli.template.service.TableService;


@Service
public class TableServiceImpl implements TableService {
	@Autowired
	private TableDao tableDao;
	
	@Override
	public TableDO get(Long id){
		return tableDao.get(id);
	}
	
	@Override
	public List<TableDO> list(Map<String, Object> map){
		return tableDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tableDao.count(map);
	}
	
	@Override
	public int save(TableDO table){
		return tableDao.save(table);
	}
	
	@Override
	public int update(TableDO table){
		return tableDao.update(table);
	}
	
	@Override
	public int remove(Long id){
		return tableDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return tableDao.batchRemove(ids);
	}
	
}
