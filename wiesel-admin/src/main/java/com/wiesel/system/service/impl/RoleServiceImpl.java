package com.wiesel.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wiesel.system.controller.req.RoleReq;
import com.wiesel.system.entity.Role;
import com.wiesel.system.entity.RoleMenu;
import com.wiesel.system.entity.UserRole;
import com.wiesel.system.mapper.RoleMapper;
import com.wiesel.system.mapper.RoleMenuMapper;
import com.wiesel.system.mapper.UserRoleMapper;
import com.wiesel.system.service.IRoleService;

import cn.hutool.core.bean.BeanUtil;
import wiesel.common.exception.ApiException;
import wiesel.common.utils.IDUtils;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author wuj
 * @since 2018-07-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Transactional
	@Override
	public void addRole(RoleReq roleReq, Long userId) {
		Role role = new Role();
		BeanUtil.copyProperties(roleReq, role);
		
		//判断该角色标识是否已存在
		EntityWrapper<Role>  wrapper = new EntityWrapper<>();
		wrapper.eq(Role.ROLE_SIGN, role.getRoleSign());
		if (this.baseMapper.selectCount(wrapper)>0) {
			throw new ApiException("角色标识【"+role.getRoleSign()+"】已存在");
		}
		
		Long roleId = IDUtils.newID();
		role.setRoleId(roleId);
		role.setUserIdCreate(userId);
		Integer result = this.baseMapper.insert(role);

		List<String> menuIds = roleReq.getMenuIds();
		List<RoleMenu> roleMenus = new ArrayList<>();
		for (String menuId : menuIds) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setMenuId(Long.valueOf(menuId));
			roleMenu.setRoleId(roleId);
			roleMenus.add(roleMenu);
		}

		if (roleMenus.size() > 0) {
			this.baseMapper.insertBatchRoleMenu(roleMenus);
		}
	}

	@Transactional
	@Override
	public void deleteRole(Long roleId) {

		EntityWrapper<UserRole> userRoleWrapper = new EntityWrapper<>();
		userRoleWrapper.eq(UserRole.ROLE_ID, roleId);

		// 判断该角色是否已被用户使用
		if (userRoleMapper.selectCount(userRoleWrapper) > 0) {
			throw new ApiException("角色已被使用");
		} else {
			// 删除角色的菜单信息
			EntityWrapper<RoleMenu> roleMenuWrapper = new EntityWrapper<>();
			roleMenuWrapper.eq(RoleMenu.ROLE_ID, roleId);
			roleMenuMapper.delete(roleMenuWrapper);

			// 删除角色
			this.baseMapper.deleteById(roleId);
		}
	}

	@Transactional
	@Override
	public void batchDeleteRole(List<Long> roleIds) {
		EntityWrapper<UserRole> userRoleWrapper = new EntityWrapper<>();
		userRoleWrapper.in(UserRole.ROLE_ID, roleIds);

		// 判断该角色是否已被用户使用
		if (userRoleMapper.selectCount(userRoleWrapper) > 0) {
			throw new ApiException("角色已被使用");
		} else {
			// 删除角色的菜单信息
			EntityWrapper<RoleMenu> roleMenuWrapper = new EntityWrapper<>();
			roleMenuWrapper.in(RoleMenu.ROLE_ID, roleIds);
			roleMenuMapper.delete(roleMenuWrapper);

			// 删除角色
			this.baseMapper.deleteBatchIds(roleIds);
		}
	}

	@Transactional
	@Override
	public void updateRole(RoleReq roleReq, Long userId) {
		Role role = new Role();
		BeanUtil.copyProperties(roleReq, role);
		Long roleId = role.getRoleId();

		Integer result = this.baseMapper.updateById(role);
		if (result != 1) {
			throw new ApiException("角色信息不存在");
		}

		// 删除该角色旧的菜单信息
		EntityWrapper<RoleMenu> wrapper = new EntityWrapper<>();
		wrapper.eq(RoleMenu.ROLE_ID, roleId);
		roleMenuMapper.delete(wrapper);

		// 角色菜单信息新增
		List<String> menuIds = roleReq.getMenuIds();
		List<RoleMenu> roleMenus = new ArrayList<>();
		for (String menuId : menuIds) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setMenuId(Long.valueOf(menuId));
			roleMenu.setRoleId(roleId);
			roleMenus.add(roleMenu);
		}

		if (roleMenus.size() > 0) {
			this.baseMapper.insertBatchRoleMenu(roleMenus);
		}
	}

}
