package com.wiesel.system.controller.req;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.experimental.Accessors;

/**
*
* @ClassName 类名：DeptReq
* @Description 功能说明：角色新增请求实体
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
public class DeptReq {

	private Long deptId;
	/**
	 * 上级部门ID，一级部门为0
	 */
	@NotBlank(message = "上级部门不能为空")
	private Long parentId;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer orderNum;
	/**
	 * 部门状态（0正常 1停用）
	 */
	private Integer delFlag;
}
