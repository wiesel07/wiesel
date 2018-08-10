package com.wiesel.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.wiesel.system.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wuj
 * @since 2018-07-04
 */
public interface IUserService extends IService<User> {

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：新增用户
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param user
	 *            用户实体
	 * @param roleIds
	 *            角色Id集合
	 *
	 * @date 创建时间：2018年8月10日
	 * @author 作者：wuj
	 */
	void addUser(User user, List<Long> roleIds);

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：更新用户
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param user
	 * @param roleIds
	 *
	 * @date 创建时间：2018年8月10日
	 * @author 作者：wuj
	 */
	void updateUser(User user, List<Long> roleIds);

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：根据用户Id删除用户
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param userId
	 *
	 * @date 创建时间：2018年8月9日
	 * @author 作者：wuj
	 */
	void deleteUser(Long userId);

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：根据用户Id集合批量删除用户
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param userIds
	 *
	 * @date 创建时间：2018年8月9日
	 * @author 作者：wuj
	 */
	void batchDeleteUser(List<Long> userIds);
}
