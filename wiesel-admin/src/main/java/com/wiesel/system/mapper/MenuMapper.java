package com.wiesel.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wiesel.system.entity.Menu;

/**
*
* @ClassName 类名：MenuMapper
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
public interface MenuMapper extends BaseMapper<Menu> {
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据用户ID获取用户权限
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param id
	 * @return
	 *
	 * @date   创建时间：2018年7月4日
	 * @author 作者：wuj
	 */
	List<String> listUserPerms(Long id);
	
	/**
	 * 
	 * <p>函数名称：        </p>
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
	List<Menu> listMenuByUserId(Long id);
}
