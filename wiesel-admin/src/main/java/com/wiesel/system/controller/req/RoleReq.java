package com.wiesel.system.controller.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：RoleReq
 * @Description 功能说明：角色新增请求实体
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年7月31日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年7月31日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Data
@Accessors(chain = true)
public class RoleReq {

	private Long roleId;
	// 角色名称
	@NotBlank(message = "角色名称不能为空")
	private String roleName;
	// 角色标识
	@NotBlank(message = "角色标识不能为空")
	private String roleSign;
	// 备注
	private String remark;
	// 菜单ID
	private List<String> menuIds;
}
