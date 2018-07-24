package com.wiesel.common.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import lombok.extern.java.Log;


/**
 *
 * @ClassName 类名：DruidConfig
 * @Description 功能说明：Druid数据库信息配置加载
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
@Log
@Configuration
public class DruidConfig {
	
//	@Value("${spring.datasource.url}")
//	private String dbUrl;
//
//	@Value("${spring.datasource.username}")
//	private String username;
//
//	@Value("${spring.datasource.password}")
//	private String password;
//
//	@Value("${spring.datasource.driverClassName}")
//	private String driverClassName;
//
//	@Value("${spring.datasource.initialSize}")
//	private int initialSize;
//
//	@Value("${spring.datasource.minIdle}")
//	private int minIdle;
//
//	@Value("${spring.datasource.maxActive}")
//	private int maxActive;
//
//	@Value("${spring.datasource.maxWait}")
//	private int maxWait;
//
//	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
//	private int timeBetweenEvictionRunsMillis;
//
//	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
//	private int minEvictableIdleTimeMillis;
//
//	@Value("${spring.datasource.validationQuery}")
//	private String validationQuery;
//
//	@Value("${spring.datasource.testWhileIdle}")
//	private boolean testWhileIdle;
//
//	@Value("${spring.datasource.testOnBorrow}")
//	private boolean testOnBorrow;
//
//	@Value("${spring.datasource.testOnReturn}")
//	private boolean testOnReturn;
//
//	@Value("${spring.datasource.poolPreparedStatements}")
//	private boolean poolPreparedStatements;
//
//	@Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
//	private int maxPoolPreparedStatementPerConnectionSize;
//
//	@Value("${spring.datasource.filters}")
//	private String filters;
//
//	@Value("{spring.datasource.connectionProperties}")
//	private String connectionProperties;
//
//	@Bean(name = "basisDataSource", initMethod = "init", destroyMethod = "close") // 声明其为Bean实例
//	@Primary // 在同样的DataSource中，首先使用被标注的DataSource
//	public DataSource dataSource() {
//		DruidDataSource datasource = new DruidDataSource();
//
//		datasource.setUrl(this.dbUrl);
//		datasource.setUsername(username);
//		datasource.setPassword(password);
//		datasource.setDriverClassName(driverClassName);
//
//		// configuration
//		datasource.setInitialSize(initialSize);
//		datasource.setMinIdle(minIdle);
//		datasource.setMaxActive(maxActive);
//		datasource.setMaxWait(maxWait);
//		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//		datasource.setValidationQuery(validationQuery);
//		datasource.setTestWhileIdle(testWhileIdle);
//		datasource.setTestOnBorrow(testOnBorrow);
//		datasource.setTestOnReturn(testOnReturn);
//		datasource.setPoolPreparedStatements(poolPreparedStatements);
//		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//		try {
//			datasource.setFilters(filters);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			log.info("druid configuration initialization filter");
//		}
//		datasource.setConnectionProperties(connectionProperties);
//
//		return datasource;
//	}

	/**
	 * 注册一个StatViewServlet 相当于在web.xml中声明了一个servlet
	 */
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("allow", ""); // 白名单
		/** IP黑名单(共同存在时，deny优先于allow) */
		// reg.addInitParameter("deny", "10.211.61.4");

		/** 是否能够重置数据 禁用HTML页面上的“Reset All”功能 */
		// reg.addInitParameter("resetEnable", "false");
		return reg;
	}

	/**
	 * 注册一个：filterRegistrationBean 相当于在web.xml中声明了一个Filter
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		/** 添加过滤规则. */
		filterRegistrationBean.addUrlPatterns("/*");
		/** 监控选项滤器 */
		filterRegistrationBean.addInitParameter("DruidWebStatFilter", "/*");
		/** 添加不需要忽略的格式信息. */
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/monitor/druid/*");
		/** 配置profileEnable能够监控单个url调用的sql列表 */
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		/** 当前的cookie的用户 */
		filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
		/** 当前的session的用户 */
		filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
		return filterRegistrationBean;
	}
}
