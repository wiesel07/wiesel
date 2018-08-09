package com.wiesel.system.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.wiesel.system.controller.req.RoleReq;
import com.wiesel.system.entity.Role;

/**
 * 角色
 * 
 * @author wuj
 * @email 502393098@qq.com
 * @date 2018-07-23 17:23:28
 */
public interface IRoleService extends IService<Role>{
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：新增角色
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param roleReq  角色请求实体
	 * @param userId  用户ID
	 * @return
	 *
	 * @date   创建时间：2018年7月31日
	 * @author 作者：wuj
	 */
	public void  addRole(RoleReq roleReq,Long userId);


	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：修改角色
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param roleReq  角色请求实体
	 * @param userId  用户ID
	 * @return
	 *
	 * @date   创建时间：2018年7月31日
	 * @author 作者：wuj
	 */
	public void  updateRole(RoleReq roleReq,Long userId);
	
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据角色Id删除角色
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param roleId
	 * @return
	 *
	 * @date   创建时间：2018年8月2日
	 * @author 作者：wuj
	 */
	public void deleteRole(Long roleId);
	
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据角色Id集合批量删除角色
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param roleIds
	 * @return
	 *
	 * @date   创建时间：2018年8月2日
	 * @author 作者：wuj
	 */
	public void batchDeleteRole(List<Long> roleIds);
	
	
}
