package com.wiesel.common.controller;

import org.springframework.stereotype.Controller;

import com.wiesel.common.utils.ShiroUtils;
import com.wiesel.system.entity.User;

/** 
*
* @ClassName   类名：BaseController 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年7月6日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年7月6日   wuj   创建该类功能。
*
***********************************************************************
*</p>
*/
@Controller
public class BaseController {
	public User getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}
