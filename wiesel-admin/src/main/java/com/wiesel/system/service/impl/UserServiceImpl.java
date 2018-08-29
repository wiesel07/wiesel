package com.wiesel.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wiesel.common.enums.ErrorCode;
import com.wiesel.system.entity.User;
import com.wiesel.system.entity.UserRole;
import com.wiesel.system.mapper.UserMapper;
import com.wiesel.system.mapper.UserRoleMapper;
import com.wiesel.system.service.IUserService;

import cn.hutool.core.collection.CollectionUtil;
import wiesel.common.enums.ApiErrorCode;
import wiesel.common.exception.ApiException;

/**
*
* @ClassName 类名：UserServiceImpl
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
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Transactional
	@Override
	public void deleteUser(Long userId) {

		// 删除用户角色信息
		EntityWrapper<UserRole> userRoleWrapper = new EntityWrapper<>();
		userRoleWrapper.eq(UserRole.USER_ID, userId);

		userRoleMapper.delete(userRoleWrapper);

		// 删除用户信息
		if (this.baseMapper.deleteById(userId) <= 0) {
			throw new ApiException(ApiErrorCode.DB_DELETE_FAIL);
		}

	}

	@Transactional
	@Override
	public void batchDeleteUser(List<Long> userIds) {

		// 删除用户角色信息
		EntityWrapper<UserRole> userRoleWrapper = new EntityWrapper<>();
		userRoleWrapper.in(UserRole.USER_ID, userIds);

		userRoleMapper.delete(userRoleWrapper);

		// 删除用户信息
		if (this.baseMapper.deleteBatchIds(userIds) <= 0) {
			throw new ApiException(ApiErrorCode.DB_DELETE_FAIL);
		}

	}

	@Transactional
	@Override
	public void addUser(User user, List<Long> roleIds) {
		
		EntityWrapper<User> wrapper = new EntityWrapper<>();
		wrapper.eq(User.USERNAME, user.getUsername());
		if (this.baseMapper.selectCount(wrapper)>0) {
			throw new ApiException(ErrorCode.USERNAME_EXIST);
		}
		
		List<UserRole> userRoles = new ArrayList<>();
		Long userId = user.getUserId();
		for (Long roleId : roleIds) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			userRoles.add(userRole);
		}
		if (CollectionUtil.isNotEmpty(userRoles)) {
			// 新增用户角色信息
			userRoleMapper.insertBatchUserRole(userRoles);
		}
	
		// 新增用户信息
		if (this.baseMapper.insert(user) <= 0) {
			throw new ApiException(ApiErrorCode.DB_INSERT_FAIL);
		}
	}

	
	@Transactional
	@Override
	public void updateUser(User user, List<Long> roleIds) {

		Long userId = user.getUserId();
		// 删除旧的用户角色信息
		EntityWrapper<UserRole> userRoleWrapper = new EntityWrapper<>();
		userRoleWrapper.eq(UserRole.USER_ID, userId);
		userRoleMapper.delete(userRoleWrapper);

		List<UserRole> userRoles = new ArrayList<>();

		for (Long roleId : roleIds) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			userRoles.add(userRole);
		}
		if (CollectionUtil.isNotEmpty(userRoles)) {
			// 新增用户角色信息
			userRoleMapper.insertBatchUserRole(userRoles);
		}

		// 新增用户信息
		if (this.baseMapper.updateById(user) <= 0) {
			throw new ApiException(ApiErrorCode.DB_INSERT_FAIL);
		}

	}

}
