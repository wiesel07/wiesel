package com.wiesel.system.controller.req;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：MenuReq
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月9日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月9日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Data
@Accessors(chain = true)
public class MenuReq {

	private Long menuId;
	/**
	 * 父菜单ID，一级菜单为0
	 */
	@NotBlank(message = "上级菜单不能为空")
	private Long parentId;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 菜单URL
	 */
	private String url;
	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	private String perms;
	/**
	 * 类型 0：目录 1：菜单 2：按钮
	 */
	private Integer type;
	/**
	 * 菜单图标
	 */
	private String icon;
	/**
	 * 排序
	 */
	private Integer orderNum;

}
