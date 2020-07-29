package com.houli.common.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.houli.common.domain.GeneratorDO;
import com.houli.common.service.GeneratorService;
import com.houli.common.utils.GenUtils;
import com.houli.common.utils.PageUtils;
import com.houli.common.utils.Query;
import com.houli.common.utils.Result;

/**
 * 
 * @Description:  代码生成控制层
 * @author: jxl     
 * @date:   2018年11月9日 下午4:09:59   
 * @version V1.0
 */
@RequestMapping("/common/generator")
@Controller
public class GeneratorController extends BaseController{
	String prefix = "common/generator";
	
	@Autowired
	GeneratorService generatorService;

	@GetMapping()
	String generator() {
		return prefix + "/list";
	}

	@ResponseBody
	@PostMapping("/list")
	@RequiresPermissions("common:generator:generator")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<GeneratorDO> list = generatorService.list(query);
		int total = generatorService.count(query);
		PageUtils pageUtils = new PageUtils(list, total);
		return pageUtils;
	};

//	@ResponseBody
//	@GetMapping("/list")
//	List<Map<String, Object>> list() {
//		List<Map<String, Object>> list = generatorService.list();
//		return list;
//	};
	
	@RequestMapping("/code/{tableName}")
	@RequiresPermissions("common:generator:code")
	public void code(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("tableName") String tableName) throws IOException {
		String[] tableNames = new String[] { tableName };
		byte[] data = generatorService.generatorCode(tableNames);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + tableName + ".zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
	}

	@RequestMapping("/batchCode")
	@RequiresPermissions("common:generator:batchcode")
	public void batchCode(HttpServletRequest request, HttpServletResponse response, String tables) throws IOException {
		String[] tableNames = new String[] {};
		tableNames = JSON.parseArray(tables).toArray(tableNames);
		byte[] data = generatorService.generatorCode(tableNames);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"springboot_template.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
	}
	
	@ResponseBody
	@GetMapping("/get")
	@RequiresPermissions("common:generator:edit")
	public Map<String, Object> get(){
		Configuration conf = GenUtils.getConfig();
		Map<String, Object> property = new HashMap<>(16);
		property.put("author", conf.getProperty("author"));
		property.put("email", conf.getProperty("email"));
		property.put("package", conf.getProperty("package"));
		property.put("autoRemovePre", conf.getProperty("autoRemovePre"));
		property.put("tablePrefix", conf.getProperty("tablePrefix"));
		property.put("templateIndex", conf.getProperty("templateIndex"));
		return property;
	}

	@ResponseBody
	@PostMapping("/update")
	@RequiresPermissions("common:generator:edit")
	Result update(@RequestParam Map<String, Object> map) {
		try {
			PropertiesConfiguration conf = new PropertiesConfiguration("generator.properties");
			conf.setProperty("author", map.get("author"));
			conf.setProperty("email", map.get("email"));
			conf.setProperty("package", map.get("package"));
			conf.setProperty("autoRemovePre", map.get("autoRemovePre"));
			conf.setProperty("tablePrefix", map.get("tablePrefix"));
			conf.setProperty("templateIndex", map.get("templateIndex"));
			conf.save();
		} catch (ConfigurationException e) {
			return Result.error("保存配置文件出错");
		}
		return Result.ok();
	}
}
