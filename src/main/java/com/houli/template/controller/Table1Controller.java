package com.houli.template.controller;

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

import com.houli.common.annotation.Log;
import com.houli.common.controller.BaseController;
import com.houli.common.utils.PageUtils;
import com.houli.common.utils.Query;
import com.houli.common.utils.Result;
import com.houli.template.domain.TableDO;
import com.houli.template.service.TableService;

/**
 * 模板一：表格呈现方式-增删改查都在同一页面，全部使用弹窗方式实现 （适用于呈现数据较少的页面）
 * 
 * @author jxl
 * @email 630326347@qq.com
 * @date 2018-11-15 14:58:55
 */
 
@Controller
@RequestMapping("/template_code/table1")
public class Table1Controller extends BaseController{
	private String prefix = "template_code/table1";
	
	@Autowired
	private TableService tableService;
	
	/**
	 * 进入列表页面
	 */
	@Log("访问Table1资源")
	@GetMapping()
	@RequiresPermissions("template_code:table1:table")
	String Table(){
	    return prefix + "/list";
	}
	
	/**
	 * 分页查询列表数据
	 */
	@ResponseBody
	@PostMapping("/list")
	@RequiresPermissions("template_code:table1:table")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TableDO> tableList = tableService.list(query);
		int total = tableService.count(query);
		PageUtils pageUtils = new PageUtils(tableList, total);
		return pageUtils;
	}
	
	/**
	 * 获取单条数据
	 */
	@ResponseBody
	@GetMapping("/get/{id}")
	@RequiresPermissions("template_code:table1:get")
	public TableDO get(@PathVariable("id") Long id){
		//查询列表数据
		TableDO tableDO = tableService.get(id);
		return tableDO;
	}
	
	/**
	 * 添加数据
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("template_code:table1:add")
	public Result save( TableDO table){
		if(tableService.save(table)>0){
			return Result.ok();
		}
		return Result.error();
	}
	/**
	 * 修改数据
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("template_code:table1:edit")
	public Result update( TableDO table){
		tableService.update(table);
		return Result.ok();
	}
	
	/**
	 * 删除单条数据
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("template_code:table1:remove")
	public Result remove(Long id){
		if(tableService.remove(id)>0){
		return Result.ok();
		}
		return Result.error();
	}
	
	/**
	 * 批量删除数据
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("template_code:table1:batchRemove")
	public Result remove(@RequestParam("ids[]") Long[] ids){
		tableService.batchRemove(ids);
		return Result.ok();
	}
	
}
