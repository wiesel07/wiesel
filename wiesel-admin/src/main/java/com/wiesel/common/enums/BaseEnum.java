package com.wiesel.common.enums;
/** 
*
* @ClassName   类名：BaseEnum 
* @Description 功能说明：基类枚举接口
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年8月26日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年8月26日   wuj     创建该类功能。
*
***********************************************************************
*</p>
*/ 
public interface BaseEnum<E extends Enum<?>, T> {
	public T getValue();
	public String getDisplayName();

}
