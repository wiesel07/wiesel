package com.wiesel.common.utils;

/** 
*
* @ClassName   类名：ErrorCode 
* @Description 功能说明:错误枚举类
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年7月21日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年7月21日   wuj     创建该类功能。
*
***********************************************************************
*</p>
*/

public enum ErrorCode {

	 LOGIN_FAIL("0800002", "用户或密码错误!"),
	 ACCOUNT_EXIST("0800002", "用户或密码错误!"),
	DB_INSERT_FAIL("001", "数据库插入失败！"),
    DB_UPDATE_FAIL("002", "数据库更新失败！"),
    DB_DELETE_FAIL("003", "数据库删除失败！");
	
	private String code;
    private String msg;
    
    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


	public void setMsg(String msg) {
		this.msg = msg;
	}

}
