package com.wiesel.common.enums;

import wiesel.common.api.IErrorCode;

/**
 *
 * @ClassName 类名：ErrorCode
 * @Description 功能说明:错误枚举类
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年7月21日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年7月21日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */

public enum ErrorCode implements IErrorCode {

	/**
	 * 成功
	 */
	SUCCESS("0", "成功"),

	/**
	 * 失败
	 */
	FAILED("1", "失败"), 
	DB_INSERT_FAIL("000001", "数据库插入失败！"), 
	DB_UPDATE_FAIL("000002", "数据库更新失败！"), 
	DB_DELETE_FAIL("000003", "数据库删除失败！"), 
	LOGIN_FAIL("000011", "用户或密码错误!"), 
	USERNAME_EXIST("0800012", "用户名称已存在!"),
	ACCOUNT_EXIST("0800013", "用户或密码错误!"),
	PARAM_IS_NULL("0800014","参数不能为空")
	;

	private final String code;
	private final String msg;

	ErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：根据编码返回对应的枚举信息，若无则返回成功的枚举信息
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param code
	 * @return
	 *
	 * @date 创建时间：2018年8月24日
	 * @author 作者：wujian
	 */
	public static ErrorCode fromCode(String code) {
		ErrorCode[] ecs = ErrorCode.values();
		for (ErrorCode ec : ecs) {
			if (ec.getCode().equalsIgnoreCase(code)) {
				return ec;
			}
		}
		return SUCCESS;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return msg;
	}

	@Override
	public String toString() {
		return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
	}
}
