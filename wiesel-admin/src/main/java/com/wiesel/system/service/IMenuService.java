package com.wiesel.system.service;

import com.wiesel.system.entity.Menu;

import java.util.Set;

import com.baomidou.mybatisplus.service.IService;

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
	 * <p>函数名称：        </p>
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
}
