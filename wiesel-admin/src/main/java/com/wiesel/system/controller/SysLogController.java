package com.wiesel.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wiesel.common.controller.BaseController;
import com.wiesel.common.enums.ErrorCode;
import com.wiesel.system.entity.SysLog;
import com.wiesel.system.service.ISysLogService;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
import wiesel.common.api.ApiResult;
import wiesel.common.base.entity.PageReq;
import wiesel.common.base.entity.PageResp;

/**
*
* @ClassName 类名：RoleController
* @Description 功能说明：
*              <p>
*              TODO
*              </p>
************************************************************************
* @date 创建日期：2018年07月04日
* @author 创建人：wuj
* @version 版本号：V1.0
*          <p>
***************************          修订记录*************************************
* 
*          2018年7月23日 wuj 创建该类功能。
*
***********************************************************************
*          </p>
*/
@Api("系统日志接口")
@Controller
@RequestMapping("/sys/sysLog")
public class SysLogController extends BaseController {
	@Autowired
	ISysLogService sysLogService;

	String prefix = "system/sysLog";

	@ApiIgnore
	@RequiresPermissions("sys:sysLog:sysLog")
	@GetMapping()
	String log() {
		return prefix + "/sysLog";
	}

	@ApiOperation(value = "获取日志列表")
	@RequiresPermissions("sys:sysLog:sysLog")
	@GetMapping("/list")
	@ResponseBody()
	ApiResult<PageResp<SysLog>> list(PageReq<SysLog> pageReq, String username) {

		Page<SysLog> page = new Page<SysLog>(pageReq.getPageNo(), pageReq.getPageSize());
		EntityWrapper<SysLog> wrapper = new EntityWrapper<SysLog>();

		if ("admin".equalsIgnoreCase(getUsername())) {
			wrapper.eq(SysLog.USER_ID, getUserId());
		}
		wrapper.orderBy(SysLog.GMT_CREATE, false);
		if (StrUtil.isNotBlank(username)) {
			wrapper.eq(SysLog.USERNAME, username);
		}

		page = sysLogService.selectPage(page, wrapper);

		PageResp<SysLog> pageResp = new PageResp<SysLog>();
		pageResp.setRows(page.getRecords());
		pageResp.setTotal(page.getTotal());
		return ApiResult.ok(pageResp);
	}

	@ApiOperation(value = "删除日志")
	@RequiresPermissions("sys:sysLog:delete")
	@ResponseBody
	@PostMapping("/delete")
	public ApiResult<String> delete(String id) {
		if (StringUtils.isEmpty(id)) {
			return ApiResult.error(ErrorCode.PARAM_IS_NULL);
		}
		sysLogService.deleteById(Long.valueOf(id));
		return ApiResult.ok();
	}

	@ApiOperation(value = "批量删除日志")
	@RequiresPermissions("sys:sysLog:batchDelete")
	@ResponseBody
	@PostMapping("/batchDelete")
	public ApiResult<String> batchDelete(@RequestParam("ids[]") String[] ids) {
		if (ids==null||ids.length <= 0) {
			return ApiResult.error(ErrorCode.PARAM_IS_NULL);
		}
		List<Long> logIds = new ArrayList<>();
		for (String id : ids) {
			logIds.add(Long.valueOf(id));
		}

		if (logIds.size() > 0) {
			sysLogService.deleteBatchIds(logIds);
		}
		return ApiResult.ok();
	}

	@ApiOperation(value = "清空日志")
	@RequiresPermissions("sys:sysLog:clear")
	@ResponseBody
	@PostMapping("/clear")
	public ApiResult<String> clear() {
		EntityWrapper<SysLog> wrapper = new EntityWrapper<SysLog>();
		wrapper.isNotNull(SysLog.ID);
		sysLogService.delete(wrapper);
		return ApiResult.ok();
	}
}
