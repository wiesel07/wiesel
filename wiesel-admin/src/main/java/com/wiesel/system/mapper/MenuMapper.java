package com.wiesel.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wiesel.system.entity.Menu;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author wuj
 * @since 2018-07-04
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
