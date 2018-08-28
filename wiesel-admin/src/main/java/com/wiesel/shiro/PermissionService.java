package com.wiesel.shiro;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @ClassName 类名：PermissionService
 * @Description 功能说明：允许js调用 thymeleaf 实现按钮权限可见性
 *  <p>
 *  Thymeleaf also allows accessing beans registered at your Spring
 *  Application Context in the standard way defined by Spring EL,
 *  which is using the syntax @beanName, for example:
 *  <div th:text="${@permission.hasPermi()}">...</div>
 *  </p>
 ************************************************************************
 * @date 创建日期：2018年8月28日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月28日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Service("permService")
public class PermissionService {

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据传入的权限标识符，返回按钮显影class
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param permission
	 * @return
	 *
	 * @date   创建时间：2018年8月28日
	 * @author 作者：wuj
	 */
	public String hasPermi(String permission) {
		return isPermitted(permission) ? "" : "hidden";
	}

	private boolean isPermitted(String permission) {
		return SecurityUtils.getSubject().isPermitted(permission);
	}
}
