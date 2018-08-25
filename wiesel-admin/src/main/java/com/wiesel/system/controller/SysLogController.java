package com.wiesel.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.visitor.functions.If;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.wiesel.common.annotation.Log;
import com.wiesel.common.controller.BaseController;
import com.wiesel.system.controller.req.UserReq;
import com.wiesel.system.entity.SysLog;
import com.wiesel.system.entity.User;
import com.wiesel.system.service.ISysLogService;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import wiesel.common.api.ApiResult;
import wiesel.common.base.entity.PageReq;
import wiesel.common.base.entity.PageResp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author wuj
 * @since 2018-07-04
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {
	@Autowired
	ISysLogService sysLogService;

	String prefix = "system/log";

	@GetMapping()
	String log() {
		return prefix + "/sysLog";
	}

	@ApiOperation(value = "获取日志列表")
	@GetMapping("/list")
	@ResponseBody()
	public ApiResult<PageResp<SysLog>> list(PageReq<SysLog> pageReq) {

		Page<SysLog> page = new Page<SysLog>(pageReq.getPageNo(), pageReq.getPageSize());
		EntityWrapper<SysLog> wrapper = new EntityWrapper<SysLog>();

		if ("admin".equalsIgnoreCase(getUsername())) {
			wrapper.eq(SysLog.USER_ID, getUserId());
		}

		page = sysLogService.selectPage(page, wrapper);

		PageResp<SysLog> pageResp = new PageResp<SysLog>();
		pageResp.setRows(page.getRecords());
		pageResp.setTotal(page.getTotal());
		return ApiResult.ok(pageResp);
	}

	@ResponseBody
	@PostMapping("/remove")
	ApiResult<String> remove(String id) {
		sysLogService.deleteById(Long.valueOf(id));
		return ApiResult.ok();
	}

	@ResponseBody
	@PostMapping("/batchRemove")
	ApiResult<String> batchRemove(@RequestParam("ids[]") String[] ids) {

		List<Long> logIds = new ArrayList<>();
		for (String id : ids) {
			logIds.add(Long.valueOf(id));
		}

		if (logIds.size() > 0) {
			sysLogService.deleteBatchIds(logIds);
		}
		return ApiResult.ok();
	}
}
