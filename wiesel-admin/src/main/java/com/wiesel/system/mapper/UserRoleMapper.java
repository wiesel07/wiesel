package com.wiesel.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wiesel.system.entity.UserRole;


/**
*
* @ClassName 类名：UserRoleMapper
* @Description 功能说明：
*              <p>
*              TODO
*              </p>
************************************************************************
* @date 创建日期：2018年7月23日
* @author 创建人：wuj
* @version 版本号：V1.0
*          <p>
***************************          修订记录*************************************
* 
*          2018年7月23日 wuj 创建该类功能。
*
***********************************************************************
*          </p>
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
