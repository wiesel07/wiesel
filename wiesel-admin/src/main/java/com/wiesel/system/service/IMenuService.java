package com.wiesel.system.service;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.service.IService;
import com.wiesel.system.entity.Menu;

import wiesel.common.base.entity.Tree;
import wiesel.common.base.entity.ZtreeNode;

/**
*
* @ClassName 类名：IMenuService
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
public interface IMenuService extends IService<Menu> {
	/**
	 * 
	 * <p>函数名称：listPerms        </p>
	 * <p>功能说明：根据用户ID获取用户权限
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param userId
	 * @return
	 *
	 * @date   创建时间：2018年7月4日
	 * @author 作者：wuj
	 */
	Set<String> listPerms(Long userId);
	

	/**
	 * 
	 * <p>函数名称：listMenuTree        </p>
	 * <p>功能说明：根据用户Id获取菜单信息
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param id
	 * @return
	 *
	 * @date   创建时间：2018年7月23日
	 * @author 作者：wuj
	 */
	 List<Tree<Menu>> listMenuTree(Long id);
	
	
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：获取所有菜单
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @return
	 *
	 * @date   创建时间：2018年7月30日
	 * @author 作者：wuj
	 */
	 List<ZtreeNode> getTree();

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据角色ID获取菜单信息
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param id
	 * @return
	 *
	 * @date   创建时间：2018年8月2日
	 * @author 作者：wuj
	 */
	 List<ZtreeNode> getTree(Long id);
	 
	
}
