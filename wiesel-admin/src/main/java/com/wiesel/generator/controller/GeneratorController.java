package com.wiesel.generator.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wiesel.common.base.entity.PageReq;
import com.wiesel.common.base.entity.PageResp;
import com.wiesel.common.utils.R;
import com.wiesel.generator.service.IGeneratorService;
import com.wiesel.system.entity.User;
import com.wiesel.system.service.IDeptService;
import com.wiesel.system.service.IRoleService;
import com.wiesel.system.service.IUserRoleService;
import com.wiesel.system.service.IUserService;

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
	String user(Model model) {
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
	public <T> R list(PageReq<T> pageReq, @RequestParam Map<String, Object> params) {
		// 查询列表数据
		Integer pageNo= pageReq.getPageNo();
		Integer pageSize = pageReq.getPageSize();
		Map<String, Object>  pMap= new HashMap<>();
		List<Map<String, Object>> list = generatorService.queryList(params);
		int total = generatorService.queryTotal(params);

		PageResp<Map<String, Object>> pageResp = new PageResp<Map<String, Object>>();
		pageResp.setRows(list);
		pageResp.setTotal(total);

		return R.ok(pageResp);
	}

	// /**
	// * 生成代码
	// */
	// @RequestMapping("/code")
	// public void code(HttpServletRequest request, HttpServletResponse
	// response) throws IOException {
	// String[] tableNames = new String[] {};
	// String tables = request.getParameter("tables");
	// tableNames = JSON.parseArray(tables).toArray(tableNames);
	//
	// byte[] data = generatorService.generatorCode(tableNames);
	//
	// response.reset();
	// response.setHeader("Content-Disposition", "attachment;
	// filename=\"freeter.zip\"");
	// response.addHeader("Content-Length", "" + data.length);
	// response.setContentType("application/octet-stream; charset=UTF-8");
	//
	// IOUtils.write(data, response.getOutputStream());
	// }
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
