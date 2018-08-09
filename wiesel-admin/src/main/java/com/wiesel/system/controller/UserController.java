package com.wiesel.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.sql.visitor.functions.If;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.wiesel.common.base.entity.PageReq;
import com.wiesel.common.base.entity.PageResp;
import com.wiesel.common.controller.BaseController;
import com.wiesel.common.exception.CommonException;
import com.wiesel.common.utils.PasswordHelper;
import com.wiesel.common.utils.R;
import com.wiesel.system.controller.req.UserReq;
import com.wiesel.system.entity.Role;
import com.wiesel.system.entity.User;
import com.wiesel.system.entity.UserRole;
import com.wiesel.system.service.IRoleService;
import com.wiesel.system.service.IUserRoleService;
import com.wiesel.system.service.IUserService;
import com.wiesel.system.service.impl.UserRoleServiceImpl;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.expr.NewArray;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wuj
 * @since 2018-07-04
 */
@Api("用户表相应接口")
@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController {

	private String prefix = "system/user";

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserRoleService userRoleService;

	@RequiresPermissions("sys:user:user")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@ApiOperation(value = "获取用户列表")
	@RequiresPermissions("sys:user:user")
	@GetMapping("/list")
	@ResponseBody()
	R list(PageReq<User> pageReq) {

		Page<User> page = new Page<User>(pageReq.getPageNo(), pageReq.getPageSize());
		EntityWrapper<User> wrapper = new EntityWrapper<User>();

		PageResp<User> pageResp = new PageResp<User>();

		page = userService.selectPage(page, wrapper);
		pageResp.setRows(page.getRecords());
		pageResp.setTotal(page.getTotal());
		return R.ok(pageResp);
	}

	@ApiOperation(value = "添加用户")
	@RequiresPermissions("sys:user:add")
	@GetMapping("/add")
	String add(Model model) {
		EntityWrapper<Role> roleWrapper = new EntityWrapper<>();
		List<Role> roles = roleService.selectList(roleWrapper);
		model.addAttribute("roles", roles);
		return prefix + "/add";
	}

	@ApiOperation(value = "编辑用户")
	@RequiresPermissions("sys:user:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") String id) {
		Long userId = Long.valueOf(id);
		User user = userService.selectById(userId);
		model.addAttribute("user", user);
		// 获取用户角色信息
		EntityWrapper<UserRole> userRoleWrapper = new EntityWrapper<>();
		userRoleWrapper.eq(UserRole.USER_ID, userId);
		List<UserRole> userRoles = userRoleService.selectList(userRoleWrapper);
		List<Long>roleIds = new ArrayList<>();
		for (UserRole userRole : userRoles) {
			roleIds.add(userRole.getRoleId());
		}
		List<Role> roles = new ArrayList<>();
		if (roleIds.size()>0) {
			EntityWrapper<Role> roleWrapper = new EntityWrapper<>();
			roleWrapper.in(Role.ROLE_ID, roleIds);
			roles= roleService.selectList(roleWrapper);
		}
		model.addAttribute("roles", roles);
		return prefix + "/edit";
	}

	@ApiOperation(value = "保存用户")
	@RequiresPermissions("sys:user:add")
	@PostMapping("/save")
	@ResponseBody
	R save(UserReq userReq) {
		User user = new User();
		BeanUtil.copyProperties(userReq, user);
		PasswordHelper.encryptPassword(user);

		if (!userService.insert(user)) {
			throw new CommonException("用户新增失败");
		}
		return R.ok();
	}

	@ApiOperation(value = "更新用户")
	@RequiresPermissions("sys:user:edit")
	@PostMapping("/update")
	@ResponseBody
	R update(UserReq userReq) {
		User user = new User();
		BeanUtil.copyProperties(userReq, user);
		PasswordHelper.encryptPassword(user);

		if (!userService.updateById(user)) {
			throw new CommonException("用户更新失败");
		}
		return R.ok();
	}

	@ApiOperation(value = "删除用户")
	@RequiresPermissions("sys:user:remove")
	@PostMapping("/delete")
	@ResponseBody
	R delete(String id) {
		Long userId = Long.valueOf(id);
		
		userService.deleteUser(userId);
		return R.ok();
	}

	
	@ApiOperation(value = "批量删除用户")
	@RequiresPermissions("sys:user:batchDelete")
	@PostMapping("/batchDelete")
	@ResponseBody
	R batchDelete(@RequestParam("ids[]") String[] ids) {
		List<Long> userIds = new ArrayList<>();
		for (String id : ids) {
			userIds.add(Long.valueOf(id));
		}
		userService.batchDeleteUser(userIds);
		return R.ok();
	}


	@ApiOperation(value = "用户密码修改")
	@RequiresPermissions("sys:user:resetPwd")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") String id, Model model) {

		Long userId = Long.valueOf(id);
		
		User user = userService.selectById(userId);
		model.addAttribute("user", user);
		return prefix + "/reset_pwd";
	}

	@ApiOperation(value = "用户密码修改")
	@RequiresPermissions("sys:user:resetPwd")
	@PostMapping("/resetPwd")
	@ResponseBody
	R resetPwd(UserReq userReq) {
		User user = new User();
		BeanUtil.copyProperties(userReq, user);
	
		if (!userService.updateById(user)) {
			throw new CommonException("用户密码修改失败");
		}
		return R.ok();
	}

	

//	@GetMapping("/tree")
//	@ResponseBody
//	public Tree<DeptDO> tree() {
//		Tree<DeptDO> tree = new Tree<DeptDO>();
//		tree = userService.getTree();
//		return tree;
//	}

	@GetMapping("/treeView")
	String treeView() {
		return prefix + "/userTree";
	}

	
//	@ResponseBody
//	@PostMapping("/uploadImg")
//	R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
//		if ("test".equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
//		Map<String, Object> result = new HashMap<>();
//		try {
//			result = userService.updatePersonalImg(file, avatar_data, getUserId());
//		} catch (Exception e) {
//			return R.error("更新图像失败！");
//		}
//		if (result != null && result.size() > 0) {
//			return R.ok(result);
//		} else {
//			return R.error("更新图像失败！");
//		}
//	}
}
