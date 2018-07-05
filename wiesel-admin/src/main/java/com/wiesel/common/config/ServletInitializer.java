package com.wiesel.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.wiesel.WieselAdminApplication;

/** 
*
* @ClassName   类名：ServletInitializer 
* @Description 功能说明：web容器中进行部署
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
public class ServletInitializer extends SpringBootServletInitializer {
	
	private static final Logger logger = LoggerFactory.getLogger(ServletInitializer.class);
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WieselAdminApplication.class);
	}

//  @Override
//  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//      return builder.sources(this.getClass());
//  }
}
