package com.wiesel.common.config;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @ClassName 类名：ApplicationContextRegister
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年7月3日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年7月3日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Component
public class ApplicationContextRegister implements ApplicationContextAware {
	private static Logger logger = LoggerFactory.getLogger(ApplicationContextRegister.class);
	private static ApplicationContext APPLICATION_CONTEXT;

	/**
	 * 设置spring上下文
	 * 
	 * @param applicationContext
	 *            spring上下文
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.debug("ApplicationContext registed-->{}", applicationContext);
		APPLICATION_CONTEXT = applicationContext;
	}

	/**
	 * 获取容器
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return APPLICATION_CONTEXT;
	}

	/**
	 * 获取容器对象
	 * 
	 * @param type
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(Class<T> type) {
		assertContextInjected();
		return APPLICATION_CONTEXT.getBean(type);
	}

	
	/**
	 * 从静态变量APPLICATION_CONTEXT中得到Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) APPLICATION_CONTEXT.getBean(name);
	}
 
 
	/**
	 * 清除SpringContextHolder中的ApplicationContext为Null.
	 */
	public static void clearApplicationContext() {
		APPLICATION_CONTEXT = null;
	}
	
	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		Validate.validState(APPLICATION_CONTEXT != null,
				"applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
	}
}
