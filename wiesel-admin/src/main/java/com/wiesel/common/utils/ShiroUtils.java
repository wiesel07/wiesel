package com.wiesel.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.wiesel.system.entity.User;

import lombok.experimental.UtilityClass;

/**
 *
 * @ClassName 类名：ShiroUtils
 * @Description 功能说明：Shiro工具类
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年7月4日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年7月4日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@UtilityClass
public class ShiroUtils {

	public Subject getSubjct() {
		return SecurityUtils.getSubject();
	}

	public User getUser() {
		Object object = getSubjct().getPrincipal();
		return (User) object;
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public void logout() {
		getSubjct().logout();
	}

}
