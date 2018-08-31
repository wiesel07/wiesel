package com.wiesel.common.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @ClassName 类名：AutoLoaderListener
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月24日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月24日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Slf4j
@WebListener
public class AutoLoaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		log.info("项目启动初始化完成:" + DateUtil.now());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		log.info("项目关闭完成:" + DateUtil.now());
	}

}
