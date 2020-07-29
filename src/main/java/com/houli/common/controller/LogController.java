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

import com.houli.common.domain.LogDO;
import com.houli.common.service.LogService;
import com.houli.common.utils.PageUtils;
import com.houli.common.utils.Query;
import com.houli.common.utils.Result;

/**
 * 系统日志
 * 
 * @author jxl
 * @email 630326347@qq.com
 * @date 2018-12-06 16:57:24
 */
 
@Controller
@RequestMapping("/common/log")
public class LogController {
	private String prefix = "common/log";
	
	@Autowired
	private LogService logService;
	
	/**
	 * 进入列表页面
	 */
	@GetMapping()
	@RequiresPermissions("common:log:log")
	String Log(){
	    return prefix + "/log";
	}
	
	/**
	 * 分页查询列表数据
	 */
	@ResponseBody
	@PostMapping("/list")
	@RequiresPermissions("common:log:log")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LogDO> logList = logService.list(query);
		int total = logService.count(query);
		PageUtils pageUtils = new PageUtils(logList, total);
		return pageUtils;
	}
	
	/**
	 * 获取单条数据
	 */
	@ResponseBody
	@GetMapping("/get/{id}")
	@RequiresPermissions("common:log:get")
	public LogDO get(@PathVariable("id") Long id){
		LogDO logDO = logService.get(id);
		return logDO;
	}
	
	/**
	 * 添加数据
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:log:add")
	public Result save( LogDO log){
		if(logService.save(log)>0){
			return Result.ok();
		}
		return Result.error();
	}
	/**
	 * 修改数据
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:log:edit")
	public Result update( LogDO log){
		logService.update(log);
		return Result.ok();
	}
	
	/**
	 * 删除单条数据
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("common:log:remove")
	public Result remove( Long id){
		if(logService.remove(id)>0){
		return Result.ok();
		}
		return Result.error();
	}
	
	/**
	 * 批量删除数据
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:log:batchRemove")
	public Result remove(@RequestParam("ids[]") Long[] ids){
		logService.batchRemove(ids);
		return Result.ok();
	}
	
}
