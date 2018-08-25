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
import com.wiesel.common.annotation.Log;
import com.wiesel.common.controller.BaseController;
import com.wiesel.common.utils.PasswordHelper;
import com.wiesel.system.controller.req.UserReq;
import com.wiesel.system.entity.Dept;
import com.wiesel.system.entity.Role;
import com.wiesel.system.entity.User;
import com.wiesel.system.entity.UserRole;
import com.wiesel.system.service.IDeptService;
import com.wiesel.system.service.IRoleService;
import com.wiesel.system.service.IUserRoleService;
import com.wiesel.system.service.IUserService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
import wiesel.common.api.ApiResult;
import wiesel.common.base.entity.PageReq;
import wiesel.common.base.entity.PageResp;
import wiesel.common.exception.ApiException;
import wiesel.common.utils.IDUtils;

/**
*
* @ClassName 类名：UserController
* @Description 功能说明：
*              <p>
*              TODO
*              </p>
************************************************************************
* @date 创建日期：2018年7月4日
* @author 创建人：wuj
* @version 版本号：V1.0
*          <p>
***************************          修订记录*************************************
* 
*          2018年7月4日 wuj 创建该类功能。
*
***********************************************************************
*          </p>
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

	@Autowired
	private IDeptService deptService;

	@ApiIgnore
	@RequiresPermissions("sys:user:user")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@Log(value="获取用户列表")
	@ApiOperation(value = "获取用户列表")
	@RequiresPermissions("sys:user:user")
	@GetMapping("/list")
	@ResponseBody()
	public ApiResult<PageResp<User>> list(PageReq<User> pageReq, UserReq userReq) {

		Page<User> page = new Page<User>(pageReq.getPageNo(), pageReq.getPageSize());
		EntityWrapper<User> wrapper = new EntityWrapper<User>();

		if (StrUtil.isNotBlank(userReq.getUsername())) {
			wrapper.eq(User.USERNAME, userReq.getUsername());
		}
		if (ObjectUtil.isNotNull(userReq.getDeptId())) {
			wrapper.eq(User.DEPT_ID, userReq.getDeptId());
		}

		page = userService.selectPage(page, wrapper);

		PageResp<User> pageResp = new PageResp<User>();
		pageResp.setRows(page.getRecords());
		pageResp.setTotal(page.getTotal());
		return ApiResult.ok(pageResp);
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
	public String edit(Model model, @PathVariable("id") String id) {
		Long userId = Long.valueOf(id);
		User user = userService.selectById(userId);
		model.addAttribute("user", user);
		// 获取用户角色信息
		EntityWrapper<UserRole> userRoleWrapper = new EntityWrapper<>();
		userRoleWrapper.eq(UserRole.USER_ID, userId);
		userRoleWrapper.setSqlSelect(UserRole.ROLE_ID);
		List<Object> roleIds = userRoleService.selectObjs(userRoleWrapper);

		List<Role> userRoles = new ArrayList<>();

		if (roleIds.size() > 0) {
			EntityWrapper<Role> roleWrapper = new EntityWrapper<>();
			roleWrapper.in(Role.ROLE_ID, roleIds);
			userRoles = roleService.selectList(roleWrapper);
		}
		// 获取所有角色信息
		List<Role> roles = roleService.selectList(new EntityWrapper<>());
		// 遍历设置用户角色状态
		for (Role role : roles) {
			for (Role userRole : userRoles) {
				if (role.getRoleId().longValue() == userRole.getRoleId().longValue()) {
					role.setFlag(true);
					break;
				}
				role.setFlag(false);
			}
		}
		model.addAttribute("roles", roles);

		// 获取用户部门信息
		Long deptId = user.getDeptId();
		EntityWrapper<Dept> deptWrapper = new EntityWrapper<>();
		deptWrapper.setSqlSelect(Dept.NAME);
		deptWrapper.eq(Dept.DEPT_ID, deptId);
		Object deptName = deptService.selectObj(deptWrapper);
		model.addAttribute("deptName", deptName);
		return prefix + "/edit";
	}

	@Log(value="保存用户")
	@ApiOperation(value = "保存用户")
	@RequiresPermissions("sys:user:add")
	@PostMapping("/save")
	@ResponseBody
	public ApiResult<String> save(UserReq userReq, String[] role) {

		User user = new User();
		BeanUtil.copyProperties(userReq, user);
		user.setUserId(IDUtils.newID()).setUserIdCreate(getUserId());
		PasswordHelper.encryptPassword(user);

		List<Long> roleIds = new ArrayList<>();
		if (role!=null) {
			for (String roleId : role) {
				roleIds.add(Long.valueOf(roleId));
			}
		}
	
		userService.addUser(user, roleIds);
		return ApiResult.ok();
	}

	@Log(value="更新用户")
	@ApiOperation(value = "更新用户")
	@RequiresPermissions("sys:user:edit")
	@PostMapping("/update")
	@ResponseBody
	public ApiResult<String> update(UserReq userReq, String[] role) {
		User user = new User();
		BeanUtil.copyProperties(userReq, user);

		List<Long> roleIds = new ArrayList<>();
		if (role!=null) {
			for (String roleId : role) {
				roleIds.add(Long.valueOf(roleId));
			}
		}
		userService.updateUser(user, roleIds);
		return ApiResult.ok();
	}

	@Log(value="删除用户")
	@ApiOperation(value = "删除用户")
	@RequiresPermissions("sys:user:delete")
	@PostMapping("/delete")
	@ResponseBody
	public ApiResult<String> delete(String id) {
		Long userId = Long.valueOf(id);

		userService.deleteUser(userId);
		return ApiResult.ok();
	}

	@Log(value="批量用户")
	@ApiOperation(value = "批量删除用户")
	@RequiresPermissions("sys:user:batchDelete")
	@PostMapping("/batchDelete")
	@ResponseBody
	public ApiResult<String> batchDelete(@RequestParam("ids[]") String[] ids) {
		List<Long> userIds = new ArrayList<>();
		for (String id : ids) {
			userIds.add(Long.valueOf(id));
		}
		userService.batchDeleteUser(userIds);
		return ApiResult.ok();
	}

	
	@ApiOperation(value = "用户密码修改")
	@RequiresPermissions("sys:user:resetPwd")
	@GetMapping("/resetPwd/{id}")
	public String resetPwd(@PathVariable("id") String id, Model model) {

		Long userId = Long.valueOf(id);

		User user = userService.selectById(userId);
		model.addAttribute("user", user);
		return prefix + "/reset_pwd";
	}

	@Log(value="用户密码修改")
	@ApiOperation(value = "用户密码修改")
	@RequiresPermissions("sys:user:resetPwd")
	@PostMapping("/resetPwd")
	@ResponseBody
	public ApiResult<String> resetPwd(UserReq userReq, String newPassword, String confirmPassword) {
		User user = new User();
		BeanUtil.copyProperties(userReq, user);

		String password = user.getPassword();
		if (StrUtil.isEmpty(newPassword) || StrUtil.isEmpty(confirmPassword) || StrUtil.isEmpty(password)) {
			return ApiResult.error("密码不能为空");
		}
		if (!newPassword.equals(confirmPassword)) {
			return ApiResult.error("两次输入密码不一致");
		}

		PasswordHelper.encryptPassword(user);
		password = user.getPassword();

		EntityWrapper<User> wrapper = new EntityWrapper<>();
		wrapper.eq(User.USER_ID, user.getUserId());
		wrapper.eq(User.PASSWORD, password);

		user.setPassword(newPassword);
		if (!userService.update(user, wrapper)) {
			throw new ApiException("旧密码不正确");
		}
		return ApiResult.ok();
	}

	
	@ApiOperation(value = "校验用户名是否存在")
	@PostMapping("/checkUsername")
	@ResponseBody
	public boolean checkUsername(@RequestParam String username) {

		EntityWrapper<User> wrapper = new EntityWrapper<>();
		wrapper.eq(User.USERNAME, username);

		return !(userService.selectCount(wrapper) > 0);
	}

	// @ResponseBody
	// @PostMapping("/uploadImg")
	// R uploadImg(@RequestParam("avatar_file") MultipartFile file, String
	// avatar_data, HttpServletRequest request) {
	// if ("test".equals(getUsername())) {
	// return R.error(1, "演示系统不允许修改,完整体验请部署程序");
	// }
	// Map<String, Object> result = new HashMap<>();
	// try {
	// result = userService.updatePersonalImg(file, avatar_data, getUserId());
	// } catch (Exception e) {
	// return R.error("更新图像失败！");
	// }
	// if (result != null && result.size() > 0) {
	// return R.ok(result);
	// } else {
	// return R.error("更新图像失败！");
	// }
	// }
}
