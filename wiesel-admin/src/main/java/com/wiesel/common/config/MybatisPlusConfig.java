package com.wiesel.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;

import lombok.extern.java.Log;

/**
 *
 * @ClassName 类名：MybatisPlusConfig
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年7月5日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年7月5日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Log
@Configuration
@MapperScan("com.wiesel.**.mapper")
//@ImportResource(locations = { "classpath:/config/mybatis/spring-mybatis.xml" })
public class MybatisPlusConfig {

	//
	// // MyBatis 动态扫描
	// @Bean
	// public MapperScannerConfigurer mapperScannerConfigurer() {
	// log.info("初始化MapperScannerConfigurer");
	// MapperScannerConfigurer mapperScannerConfigurer = new
	// MapperScannerConfigurer();
	// String basePackage = "com.wiesel.**.mapper";
	// mapperScannerConfigurer.setBasePackage(basePackage);
	// return mapperScannerConfigurer;
	// }

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：配置mybatis-plus分页插件
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @return
	 *
	 * @date   创建时间：2018年8月30日
	 * @author 作者：wuj
	 */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
	
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：配置事务管理
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param dataSource
	 * @return
	 *
	 * @date   创建时间：2018年8月30日
	 * @author 作者：wuj
	 */
	@DependsOn("dataSource")
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager(
			@Qualifier(value = "dataSource") DruidDataSource dataSource) {
		log.info("配置事务管理");
		return new DataSourceTransactionManager(dataSource);
	}

	
	
	
	/**
	 * 性能分析拦截器，不建议生产使用
	 */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(3000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(false);
        return performanceInterceptor;
    }
	

	/**
	 * mybatis-plus分页插件<br>
	 * 文档：http://mp.baomidou.com<br>
	 */
	// @Bean
	// public PaginationInterceptor paginationInterceptor() {
	// PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
	// /*
	// * 【测试多租户】 SQL 解析处理拦截器<br>
	// * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
	// */
	// List<ISqlParser> sqlParserList = new ArrayList<>();
	// TenantSqlParser tenantSqlParser = new TenantSqlParser();
	// tenantSqlParser.setTenantHandler(new TenantHandler() {
	// @Override
	// public Expression getTenantId() {
	// return new LongValue(1L);
	// }
	//
	// @Override
	// public String getTenantIdColumn() {
	// return "tenant_id";
	// }
	//
	// @Override
	// public boolean doTableFilter(String tableName) {
	// // 这里可以判断是否过滤表
	// /*if ("user".equals(tableName)) {
	// return true;
	// }*/
	// return false;
	// }
	// });
	//
	// sqlParserList.add(tenantSqlParser);
	// paginationInterceptor.setSqlParserList(sqlParserList);
	//// paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
	//// @Override
	//// public boolean doFilter(MetaObject metaObject) {
	//// MappedStatement ms = PluginUtils.getMappedStatement(metaObject);
	//// // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
	//// if
	// ("com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId()))
	// {
	//// return true;
	//// }
	//// return false;
	//// }
	//// });
	// return paginationInterceptor;
	// }
}
