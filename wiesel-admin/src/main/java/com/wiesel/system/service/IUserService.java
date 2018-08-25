package com.wiesel.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.wiesel.system.entity.User;


/**
*
* @ClassName 类名：IUserService
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
