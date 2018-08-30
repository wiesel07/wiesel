package com.wiesel.common.config.properties;

import org.crazycake.shiro.RedisManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

/** 
*
* @ClassName   类名：RedisProperties 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年8月30日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年8月30日   wuj   创建该类功能。
*
***********************************************************************
*</p>
*/
@Component
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisProperties {

	private String host = "localhost";

	private String password;


	private int port = 6379;


	private int timeout =1000;

	public void config(RedisManager redisManager) {
		if (StrUtil.isNotBlank(password)) {
			redisManager.setPassword(password);
		}
		redisManager.setHost(host);
		redisManager.setPort(port);
    	redisManager.setTimeout(timeout);	
	}
}

