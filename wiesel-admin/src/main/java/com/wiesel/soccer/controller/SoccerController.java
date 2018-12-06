package com.wiesel.soccer.controller;

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

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sun.org.apache.bcel.internal.generic.I2F;
import com.wiesel.common.enums.ErrorCode;
import com.wiesel.soccer.controller.req.SoccerReq;
import com.wiesel.soccer.entity.Soccer;
import com.wiesel.soccer.service.ISoccerService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
import wiesel.common.api.ApiResult;
import wiesel.common.base.entity.PageReq;
import wiesel.common.base.entity.PageResp;

/**
 *
 * @ClassName 类名：SoccerServiceImpl
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2018-12-05
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************          修订记录************************************
 * 
 *          2018-12-05 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Controller
@RequestMapping("/soccer/soccer")
public class SoccerController {

	private String prefix = "soccer/soccer";

	@Autowired
	private ISoccerService IsoccerService;

	@ApiIgnore
	@GetMapping()
	@RequiresPermissions("soccer:soccer:soccer")
	String soccer() {
		return prefix + "/soccer";
	}

	@ApiOperation(value = "获取列表", notes = "")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("soccer:soccer:soccer")
	ApiResult<PageResp<Soccer>> list(PageReq<Soccer> pageReq,SoccerReq soccerReq) {

		Page<Soccer> page = new Page<Soccer>(pageReq.getPageNo(), pageReq.getPageSize());

		Soccer soccer = new Soccer();
		BeanUtil.copyProperties(soccerReq, soccer);
		EntityWrapper<Soccer> wrapper = new EntityWrapper<>(soccer);
		wrapper.orderBy(Soccer.USER_NAME);
		wrapper.orderBy(Soccer.GMT_CREATE, false);
		
		if (StrUtil.isNotBlank(pageReq.getStartDate())) {
			//wrapper.eq("DATE_FORMAT("+Soccer.GMT_CREATE+", '%Y%m%d')", pageReq.getStartDate());
			wrapper.like(Soccer.GMT_CREATE, pageReq.getStartDate(), SqlLike.RIGHT);
		}

		page = IsoccerService.selectPage(page, wrapper);

		PageResp<Soccer> pageResp = new PageResp<Soccer>();
		pageResp.setRows(page.getRecords());
		pageResp.setTotal(page.getTotal());
		return ApiResult.ok(pageResp);
	}

	@ApiOperation(value = "新增")
	@RequiresPermissions("soccer:soccer:add")
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}

	@ApiOperation(value = "编辑")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("soccer:soccer:edit")
	String edit(@PathVariable("id") String id, Model model) {
		Soccer soccer = IsoccerService.selectById(Long.valueOf(id));
		model.addAttribute("soccer", soccer);

		return prefix + "/edit";
	}

	@ApiOperation(value = "保存")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("soccer:soccer:add")
	public ApiResult<String> save(SoccerReq soccerReq) {
		Soccer soccer = new Soccer();
		BeanUtil.copyProperties(soccerReq, soccer);
		IsoccerService.insert(soccer);
		return ApiResult.ok();
	}

	@ApiOperation(value = "修改")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("soccer:soccer:edit")
	public ApiResult<String> update(SoccerReq soccerReq) {
		Soccer soccer = new Soccer();
		BeanUtil.copyProperties(soccerReq, soccer);
		IsoccerService.updateById(soccer);

		return ApiResult.ok();
	}

	@ApiOperation(value = "删除")
	@PostMapping("/delete")
	@ResponseBody
	@RequiresPermissions("soccer:soccer:delete")
	public ApiResult<String> delete(String id) {
		if (StringUtils.isEmpty(id)) {
			return ApiResult.error(ErrorCode.PARAM_IS_NULL);
		}
		IsoccerService.deleteById(Long.valueOf(id));
		return ApiResult.ok();
	}

	@ApiOperation(value = "批量删除")
	@PostMapping("/batchDelete")
	@ResponseBody
	@RequiresPermissions("soccer:soccer:batchDelete")
	public ApiResult<String> batchDelete(String[] ids) {
		if (ids == null || ids.length <= 0) {
			return ApiResult.error(ErrorCode.PARAM_IS_NULL);
		}
		List<Long> delIds = new ArrayList<>();
		for (String id : ids) {
			delIds.add(Long.valueOf(id));
		}

		IsoccerService.deleteBatchIds(delIds);

		return ApiResult.ok();
	}
}
