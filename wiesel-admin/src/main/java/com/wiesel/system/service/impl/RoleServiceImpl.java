package com.wiesel.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wiesel.common.utils.IDUtils;
import com.wiesel.system.controller.req.RoleReq;
import com.wiesel.system.entity.Role;
import com.wiesel.system.entity.RoleMenu;
import com.wiesel.system.mapper.RoleMapper;
import com.wiesel.system.service.IRoleService;

import cn.hutool.core.bean.BeanUtil;

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

	@Transactional
	@Override
	public int addRole(RoleReq roleReq, Long userId) {
		Role role = new Role();
		BeanUtil.copyProperties(roleReq, role);
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
		return result;
	}

}
