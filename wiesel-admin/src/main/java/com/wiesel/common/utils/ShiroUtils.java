package com.wiesel.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.wiesel.system.entity.User;

/** 
*
* @ClassName   类名：ShiroUtils 
* @Description 功能说明：Shiro工具类
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
public class ShiroUtils {

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }
    public static User getUser() {
        Object object = getSubjct().getPrincipal();
        return (User)object;
    }
    public static Long getUserId() {
        return getUser().getUserId();
    }
    public static void logout() {
        getSubjct().logout();
    }

}
