package com.wiesel.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wiesel.common.base.entity.PageReq;
import com.wiesel.common.base.entity.PageResp;
import com.wiesel.common.controller.BaseController;
import com.wiesel.common.utils.R;
import com.wiesel.system.controller.req.RoleReq;
import com.wiesel.system.entity.Role;
import com.wiesel.system.service.IRoleService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 角色
 * 
 * @author wuj
 * @email 502393098@qq.com
 * @date 2018-07-23 17:23:28
 */

@ApiModel(value = "角色管理相应接口")
@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController {
	String prefix = "system/role";

	@Autowired
	private IRoleService roleService;

	@ApiIgnore
	@RequiresPermissions("sys:role:role")
	@GetMapping()
	String role() {
		return prefix + "/role";
	}

	@ApiOperation(value = "获取当前登录用户角色信息")
	@RequiresPermissions("sys:role:role")
	@GetMapping("/list")
	@ResponseBody()
	R list(PageReq<Role> pageReq) {

		Page<Role> page = new Page<Role>(pageReq.getPageNo(), pageReq.getPageSize());
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();

		PageResp<Role> pageResp = new PageResp<Role>();

		pageResp.setRows(roleService.selectPage(page, wrapper).getRecords());
		pageResp.setTotal(roleService.selectCount(wrapper));
		return R.ok(pageResp);
	}

	@ApiOperation(value = "添加角色")
	@RequiresPermissions("sys:role:add")
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}

	@ApiOperation(value = "保存角色")
	@RequiresPermissions("sys:role:add")
	@PostMapping("/save")
	@ResponseBody()
	R save(RoleReq roleReq) {
		roleService.addRole(roleReq, getUserId());
		return R.ok();

	}
}
