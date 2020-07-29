package com.houli.common.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houli.common.domain.DictDO;
import com.houli.common.service.DictService;
import com.houli.common.utils.PageUtils;
import com.houli.common.utils.Query;
import com.houli.common.utils.Result;

/**
 * 字典表
 * 
 * @author jxl
 * @email 630326347@qq.com
 * @date 2018-12-17 16:41:33
 */
 
@Controller
@RequestMapping("/common/dict")
public class DictController {
	private String prefix = "common/dict";
	
	@Autowired
	private DictService dictService;
	
	/**
	 * 进入列表页面
	 */
	@GetMapping()
	@RequiresPermissions("common:dict:dict")
	String Dict(){
	    return prefix + "/dict";
	}
	
	
	/**
	 * 分页查询列表数据
	 */
	@ResponseBody
	@PostMapping("/list")
	@RequiresPermissions("common:dict:dict")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DictDO> dictList = dictService.list(query);
		int total = dictService.count(query);
		PageUtils pageUtils = new PageUtils(dictList, total);
		return pageUtils;
	}
	
	/**
	 * 获取单条数据
	 */
	@ResponseBody
	@GetMapping("/get/{id}")
	@RequiresPermissions("common:dict:get")
	public DictDO get(@PathVariable("id") Long id){
		DictDO dictDO = dictService.get(id);
		return dictDO;
	}
	
	/**
	 * 添加数据
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:dict:add")
	public Result save( DictDO dict){
		if(dictService.save(dict)>0){
			return Result.ok();
		}
		return Result.error();
	}
	/**
	 * 修改数据
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:dict:edit")
	public Result update( DictDO dict){
		dictService.update(dict);
		return Result.ok();
	}
	
	/**
	 * 删除单条数据
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("common:dict:remove")
	public Result remove( Long id){
		if(dictService.remove(id)>0){
		return Result.ok();
		}
		return Result.error();
	}
	
	/**
	 * 批量删除数据
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:dict:batchRemove")
	public Result remove(@RequestParam("ids[]") Long[] ids){
		dictService.batchRemove(ids);
		return Result.ok();
	}
	
}
