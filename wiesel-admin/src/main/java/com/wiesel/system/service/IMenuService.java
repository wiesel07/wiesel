package com.wiesel.system.service;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.service.IService;
import com.wiesel.common.base.entity.Tree;
import com.wiesel.common.base.entity.ZtreeNode;
import com.wiesel.system.entity.Menu;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author wuj123
 * @since 2018-07-04
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
	public List<Tree<Menu>> listMenuTree(Long id);
//
//	/**
//	 * 
//	 * <p>函数名称：        </p>
//	 * <p>功能说明：根据用户角色获取菜单
//	 *
//	 * </p>
//	 *<p>参数说明：</p>
//	 * @param id
//	 * @return
//	 *
//	 * @date   创建时间：2018年7月30日
//	 * @author 作者：wuj
//	 */
//	public Tree<Menu> getTree(Long id);
	
	
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
	public List<ZtreeNode> getTree();

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
	public List<ZtreeNode> getTree(Long id);
	
}
