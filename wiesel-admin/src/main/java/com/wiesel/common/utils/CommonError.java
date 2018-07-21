package com.wiesel.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/** 
*
* @ClassName   类名：CommonError 
* @Description 功能说明：
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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommonError {

	/**
	 * 错误编码
	 */
	private String code;

	/**
	 * 错误信息
	 */
    private String msg;
}
