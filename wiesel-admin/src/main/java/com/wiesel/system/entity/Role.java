package com.wiesel.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：Role
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
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends Model<Role> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "role_Id", type = IdType.ID_WORKER)
	private Long roleId;
	// 角色名称
	private String roleName;
	// 角色标识
	private String roleSign;
	// 备注
	private String remark;
	// 创建用户id
	private Long userIdCreate;
	// 创建时间
	private Date gmtCreate;
	// 创建时间
	private Date gmtModified;

	public static final String MENU_ID = "role_id";

	public static final String PARENT_ID = "role_name";

	public static final String NAME = "role_sign";

	public static final String URL = "remark";

	public static final String PERMS = "user_id_create";

	public static final String GMT_CREATE = "gmt_create";

	public static final String GMT_MODIFIED = "gmt_modified";

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.roleId;
	}
}
