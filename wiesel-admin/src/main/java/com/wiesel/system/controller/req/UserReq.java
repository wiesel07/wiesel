package com.wiesel.system.controller.req;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：UserReq
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
public class UserReq {
	private Long userId;
	/**
	 * 用户名
	 */
	private String username;
	private String name;
	/**
	 * 密码
	 */
	private String password;
	private Long deptId;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 状态 0:禁用，1:正常
	 */
	private Integer status;
	/**
	 * 创建用户id
	 */
	private Long userIdCreate;

	/**
	 * 性别
	 */
	private Long sex;
	/**
	 * 出身日期
	 */
	private Date birth;
	private Long picId;
	/**
	 * 现居住地
	 */
	private String liveAddress;
	/**
	 * 爱好
	 */
	private String hobby;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 所在城市
	 */
	private String city;
	/**
	 * 所在地区
	 */
	private String district;
	
}
