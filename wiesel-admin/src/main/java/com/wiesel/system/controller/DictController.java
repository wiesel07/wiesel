package com.wiesel.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wiesel.common.enums.ErrorCode;

import com.wiesel.system.controller.req.DictReq;

import com.wiesel.system.entity.Dict;
import com.wiesel.system.service.IDictService;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
import wiesel.common.api.ApiResult;
import wiesel.common.base.entity.PageReq;
import wiesel.common.base.entity.PageResp;

/**
 *
 * @ClassName 类名：DictServiceImpl
 * @Description 功能说明： 字典表
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2018-09-01
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************修订记录************************************
 * 
 *          2018-09-01 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Controller
@RequestMapping("/system/dict")
public class DictController {

	private String prefix = "system/dict";

	@Autowired
	private IDictService IdictService;

	@ApiIgnore
	@GetMapping()
	@RequiresPermissions("system:dict:dict")
	String dict() {
		return prefix + "/dict";
	}

	@ApiOperation(value = "获取列表", notes = "")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:dict:dict")
	ApiResult<PageResp<Dict>> list(PageReq<Dict> pageReq) {

		Page<Dict> page = new Page<Dict>(pageReq.getPageNo(), pageReq.getPageSize());
		EntityWrapper<Dict> wrapper = new EntityWrapper<>();

		page = IdictService.selectPage(page, wrapper);

		PageResp<Dict> pageResp = new PageResp<Dict>();
		pageResp.setRows(page.getRecords());
		pageResp.setTotal(page.getTotal());
		return ApiResult.ok(pageResp);
	}

	@ApiOperation(value = "新增")
	@RequiresPermissions("system:dict:add")
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}

	@ApiOperation(value = "编辑")
	@GetMapping("/edit/{dictId}")
	@RequiresPermissions("system:dict:edit")
	String edit(@PathVariable("dictId") String dictId, Model model) {
		Dict dict = IdictService.selectById(Long.valueOf(dictId));
		model.addAttribute("dict", dict);

		return prefix + "/edit";
	}


	@ApiOperation(value = "保存")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:dict:add")
	public ApiResult<String> save(DictReq dictReq) {
		Dict dict= new Dict();
		BeanUtil.copyProperties(dictReq, dict);
		IdictService.insert(dict);
		return ApiResult.ok();
	}


	@ApiOperation(value = "修改")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:dict:edit")
	public ApiResult<String> update(DictReq dictReq) {
		Dict dict= new Dict();
		BeanUtil.copyProperties(dictReq, dict);
		IdictService.updateById(dict);

		return ApiResult.ok();
	}

	@ApiOperation(value = "删除")
	@PostMapping("/delete")
	@ResponseBody
	@RequiresPermissions("system:dict:delete")
	public  ApiResult<String> delete(String dictId) {
		if (StringUtils.isEmpty(dictId)) {
			return ApiResult.error(ErrorCode.PARAM_IS_NULL);
		}
		IdictService.deleteById(Long.valueOf(dictId));
		return ApiResult.ok();
	}


	@ApiOperation(value = "批量删除")
	@PostMapping("/batchDelete")
	@ResponseBody
	@RequiresPermissions("system:dict:batchDelete")
	public  ApiResult<String> batchDelete(String[] dictIds) {
		if (dictIds==null||dictIds.length <= 0) {
			return ApiResult.error(ErrorCode.PARAM_IS_NULL);
		}
		List<Long> delIds = new ArrayList<>();
		for (String dictId : dictIds) {
			delIds.add(Long.valueOf(dictId));
		}

		IdictService.deleteBatchIds(delIds);

		return ApiResult.ok();
	}
}
