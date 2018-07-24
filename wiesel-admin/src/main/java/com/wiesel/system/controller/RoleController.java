package com.wiesel.system.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wiesel.common.base.entity.PageReq;
import com.wiesel.common.base.entity.PageResp;
import com.wiesel.common.controller.BaseController;
import com.wiesel.common.utils.R;
import com.wiesel.system.entity.Role;
import com.wiesel.system.service.IRoleService;

import javassist.expr.NewArray;

/**
 * 角色
 * 
 * @author wuj
 * @email 502393098@qq.com
 * @date 2018-07-23 17:23:28
 */
 
@Controller
@RequestMapping("/sys/role")
public class RoleController extends BaseController {
	String prefix = "system/role";
	
	@Autowired
	private IRoleService roleService;
	
	@RequiresPermissions("sys:role:role")
	@GetMapping()
	String role() {
		return prefix + "/role";
	}
	
	
	@RequiresPermissions("sys:role:role")
	@GetMapping("/list")
	@ResponseBody()
	R list(PageReq<Role> pageReq) {
		
		Page<Role> page=new Page<Role>(pageReq.getPageNo(),pageReq.getPageSize());
		EntityWrapper<Role> wrapper = new EntityWrapper<Role>();
		
		PageResp<Role> pageResp = new PageResp<Role>();
		
		pageResp.setRows( roleService.selectPage(page,wrapper).getRecords());
		pageResp.setTotal(roleService.selectCount(wrapper));
		return R.ok(pageResp);
	}
}
