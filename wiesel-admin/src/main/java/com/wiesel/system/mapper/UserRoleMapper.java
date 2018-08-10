package com.wiesel.system.mapper;

import com.wiesel.system.entity.RoleMenu;
import com.wiesel.system.entity.UserRole;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author wu
 * @since 2018-07-04
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：批量新增用户角色
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param userRoles
	 *
	 * @date   创建时间：2018年8月10日
	 * @author 作者：wuj
	 */
	void insertBatchUserRole(@Param("userRoles")List<UserRole> userRoles);
}
