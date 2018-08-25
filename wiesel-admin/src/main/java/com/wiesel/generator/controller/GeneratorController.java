package com.wiesel.generator.controller;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wiesel.common.utils.GenUtils;
import com.wiesel.generator.service.IGeneratorService;

import wiesel.common.api.ApiResult;
import wiesel.common.base.entity.PageReq;
import wiesel.common.base.entity.PageResp;

/**
 *
 * @ClassName 类名：GeneratorController
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月11日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月11日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Controller
@RequestMapping("/generator")
public class GeneratorController {

	private String prefix = "generator";

	@Autowired
	private IGeneratorService generatorService;

	@GetMapping("")
	public String user(Model model) {
		return prefix + "/generator";
	}

	/**
	 * 列表
	 * 
	 * @param <T>
	 */
	@ResponseBody
	@RequiresPermissions("common:generator")
	@GetMapping("/list")
	public <T> ApiResult<PageResp<Map<String, Object>>> list(PageReq<T> pageReq, @RequestParam Map<String, Object> params) {
		// 查询列表数据
		List<Map<String, Object>> list = generatorService.queryList(params);
		int total = generatorService.queryTotal(params);

		PageResp<Map<String, Object>> pageResp = new PageResp<Map<String, Object>>();
		pageResp.setRows(list);
		pageResp.setTotal(total);

		return ApiResult.ok(pageResp);
	}

	@RequestMapping("/code/{tableName}")
	public void code(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("tableName") String tableName) throws IOException {
		String[] tableNames = new String[] { tableName };
		byte[] data = generatorService.generatorCode(tableNames);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"wiesel.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
	}

	@RequestMapping("/batchCode")
	public void batchCode(HttpServletRequest request, HttpServletResponse response, String tables) throws IOException {
		String[] tableNames = new String[] {};
		tableNames = JSON.parseArray(tables).toArray(tableNames);
		byte[] data = generatorService.generatorCode(tableNames);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"wiesel.zip\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());
	}
	
	
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：返回生成策略编辑界面
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param model
	 * @return
	 *
	 * @date   创建时间：2018年8月22日
	 * @author 作者：wuj
	 */
	@GetMapping("/edit")
	public String edit(Model model) {
		Configuration conf = GenUtils.getConfig();
		Map<String, Object> property = new HashMap<>(16);
		property.put("author", conf.getProperty("author"));
		property.put("email", conf.getProperty("email"));
		property.put("package", conf.getProperty("package"));
		property.put("autoRemovePre", conf.getProperty("autoRemovePre"));
		property.put("tablePrefix", conf.getProperty("tablePrefix"));
		model.addAttribute("property", property);
		return prefix + "/edit";
	}

	@ResponseBody
	@PostMapping("/update")
	public	ApiResult<String> update(@RequestParam Map<String, Object> map) {
		try {
			PropertiesConfiguration conf = new PropertiesConfiguration("generator.properties");
			conf.setProperty("author", map.get("author"));
			conf.setProperty("email", map.get("email"));
			conf.setProperty("package", map.get("package"));
			conf.setProperty("autoRemovePre", map.get("autoRemovePre"));
			conf.setProperty("tablePrefix", map.get("tablePrefix"));
			conf.save();
		} catch (ConfigurationException e) {
			return ApiResult.error("保存配置文件出错");
		}
		return ApiResult.ok();
	}
	
	//
	// /**
	// * 更新全部后端代码
	// */
	// @RequestMapping("/allcode")
	// @ResponseBody
	// public R allcode(HttpServletRequest request, HttpServletResponse
	// response) throws IOException {
	// String[] tableNames = new String[] {};
	// String tables = request.getParameter("tables");
	// tableNames = JSON.parseArray(tables).toArray(tableNames);
	//
	// generatorService.generatorAllCode(tableNames);
	//
	// return R.ok("后端代码全部更新成功，请刷新IDE");
	// }
	//
	// /**
	// * 更新全部api接口代码
	// */
	// @RequestMapping("/apicode")
	// @ResponseBody
	// public R apicode(HttpServletRequest request, HttpServletResponse
	// response) throws IOException {
	// String[] tableNames = new String[] {};
	// String tables = request.getParameter("tables");
	// tableNames = JSON.parseArray(tables).toArray(tableNames);
	//
	// generatorService.generatorApiCode(tableNames);
	//
	// return R.ok("移动端接口全部更新成功，请刷新IDE");
	// }

	// /**
	// * 更新代码
	// */
	// @ResponseBody
	// @RequestMapping("/update")
	// public R update(HttpServletRequest request, HttpServletResponse response)
	// throws IOException {
	// String[] tableNames = new String[] {};
	// String tables = request.getParameter("tables");
	// tableNames = JSON.parseArray(tables).toArray(tableNames);
	//
	// generatorService.updateCode(tableNames);
	// return R.ok("代码更新成功，请刷新IDE");
	//
	// }

}
