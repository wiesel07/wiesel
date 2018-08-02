package com.wiesel.common.exception;

import com.wiesel.common.utils.ErrorCode;

/** 
*
* @ClassName   类名：CommonException 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年8月2日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年8月2日   wuj   创建该类功能。
*
***********************************************************************
*</p>
*/
public class CommonException   extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6626582979827692814L;

	private String code;

    private String msg;
    
    public CommonException(String code,String msg){
		super(code + "-" + msg);
    	this.code = code;
    	this.msg = msg;
    }

    public CommonException(Throwable cause) {
        super(cause);
    }
    
    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }   

    public CommonException(String message) {
        super(message);
    }    
    
    public CommonException(ErrorCode errorCode, Object... args){
        super(errorCode.getCode()+"-"+String.format(errorCode.getMsg(),args));
        this.code = errorCode.getCode();
        this.msg = String.format(errorCode.getMsg(),args);
    }

    public CommonException(ErrorCode errorCode) {
        super(errorCode.getCode()+"-"+errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }   
    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	@Override
	public String toString() {
		return "CommonException [code=" + code + ", msg=" + msg + "]";
	}
}