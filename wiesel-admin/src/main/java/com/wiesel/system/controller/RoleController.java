package com.wiesel.system.controller;

import java.util.ArrayList;
import java.util.List;

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

		page = roleService.selectPage(page, wrapper);
		pageResp.setRows(page.getRecords());
		pageResp.setTotal(page.getTotal());
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

	@ApiOperation(value = "编辑角色")
	@RequiresPermissions("sys:role:edit")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		Role role = roleService.selectById(id);
		model.addAttribute("role", role);
		return prefix + "/edit";
	}

	@ApiOperation(value = "修改角色")
	@RequiresPermissions("sys:role:edit")
	@PostMapping("/update")
	@ResponseBody()
	R update(RoleReq roleReq) {
		roleService.updateRole(roleReq, getUserId());
		return R.ok();
	}

	@ApiOperation(value = "删除角色")
	@RequiresPermissions("sys:role:delete")
	@PostMapping("/delete")
	@ResponseBody()
	R delete(String id) {
		roleService.deleteRole(Long.valueOf(id));
		return R.ok();
	}

	@ApiOperation(value = "批量删除角色")
	@RequiresPermissions("sys:role:batchDelete")
	@PostMapping("/batchDelete")
	@ResponseBody
	R batchDelete(@RequestParam("ids[]") String[] ids) {
		List<Long> params = new ArrayList<>();
		for (String id : ids) {
			params.add(Long.valueOf(id));
		}
		roleService.batchDeleteRole(params);
		return R.ok();
	}
}
