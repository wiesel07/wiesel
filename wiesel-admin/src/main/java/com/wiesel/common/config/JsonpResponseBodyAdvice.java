package com.wiesel.common.config;

import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/** 
*
* @ClassName   类名：JsonpResponseBodyAdvice 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年7月4日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年7月4日   wuj   创建该类功能。
*
***********************************************************************
*</p>
*/
public class JsonpResponseBodyAdvice extends AbstractJsonpResponseBodyAdvice {
	public JsonpResponseBodyAdvice() {
        super("callback");
    }

}
