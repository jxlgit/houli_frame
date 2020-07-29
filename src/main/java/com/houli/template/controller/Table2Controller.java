package com.houli.template.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
 * 模板二：表格呈现方式-增加，修改在新开的页面（适用于呈现数据较多的页面）
 * 
 * @author jxl
 * @email 630326347@qq.com
 * @date 2018-11-15 14:58:55
 */
 
@Controller
@RequestMapping("/template_code/table2")
public class Table2Controller extends BaseController{
	private String prefix = "template_code/table2";
	
	@Autowired
	private TableService tableService;
	
	/**
	 * 进入列表页面
	 */
	@Log("访问Table2资源")
	@GetMapping()
	@RequiresPermissions("template_code:table2:table")
	String Table(){
	    return prefix + "/list";
	}
	
	/**
	 * 进入添加页面
	 */
	@GetMapping("/add")
	@RequiresPermissions("template_code:table2:add")
	String add(){
	    return prefix + "/add";
	}
	
	/**
	 * 获取数据进入修改页面
	 */
	@GetMapping("/edit/{id}")
	@RequiresPermissions("template_code:table2:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TableDO table = tableService.get(id);
		model.addAttribute("table", table);
	    return prefix + "/edit";
	}
	
	/**
	 * 获取数据进入预览页面
	 */
	@GetMapping("/get/{id}")
	@RequiresPermissions("template_code:table2:get")
	String get(@PathVariable("id") Long id,Model model){
		TableDO table = tableService.get(id);
		model.addAttribute("table", table);
	    return prefix + "/preview";
	}
	
	/**
	 * 分页查询列表数据
	 */
	@ResponseBody
	@PostMapping("/list")
	@RequiresPermissions("template_code:table2:table")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TableDO> tableList = tableService.list(query);
		int total = tableService.count(query);
		PageUtils pageUtils = new PageUtils(tableList, total);
		return pageUtils;
	}
	
	/**
	 * 添加数据
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("template_code:table2:add")
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
	@RequiresPermissions("template_code:table2:edit")
	public Result update( TableDO table){
		tableService.update(table);
		return Result.ok();
	}
	
	/**
	 * 删除数据
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("template_code:table2:remove")
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
	@RequiresPermissions("template_code:table2:batchRemove")
	public Result remove(@RequestParam("ids[]") Long[] ids){
		tableService.batchRemove(ids);
		return Result.ok();
	}
	
}
