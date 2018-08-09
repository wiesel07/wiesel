package com.wiesel.system.service.impl;

import com.wiesel.common.exception.CommonException;
import com.wiesel.system.entity.User;
import com.wiesel.system.entity.UserRole;
import com.wiesel.system.mapper.UserMapper;
import com.wiesel.system.mapper.UserRoleMapper;
import com.wiesel.system.service.IUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wuj
 * @since 2018-07-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	private UserRoleMapper userRoleMapper;

	@Transactional
	@Override
	public void deleteUser(Long userId) {

		// 删除用户角色信息
		EntityWrapper<UserRole> userRoleWrapper = new EntityWrapper<>();
		userRoleWrapper.eq(UserRole.USER_ID, userId);

		if (userRoleMapper.delete(userRoleWrapper) <= 0) {
			throw new CommonException("用户角色信息删除失败");
		}

		// 删除用户信息
		if (this.baseMapper.deleteById(userId) <= 0) {
			throw new CommonException("用户删除失败");
		}

	}
	
	@Transactional
	@Override
	public void batchDeleteUser(List<Long> userIds) {

		// 删除用户角色信息
		EntityWrapper<UserRole> userRoleWrapper = new EntityWrapper<>();
		userRoleWrapper.in(UserRole.USER_ID, userIds);

		if (userRoleMapper.delete(userRoleWrapper) <= 0) {
			throw new CommonException("用户角色信息删除失败");
		}

		// 删除用户信息
		if (this.baseMapper.deleteBatchIds(userIds) <= 0) {
			throw new CommonException("用户删除失败");
		}

	}

}
