package com.wiesel.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

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
@ImportResource(locations = {"classpath:/config/mybatis/spring-mybatis.xml"})
public class MybatisPlusConfig {

//	@Value("${mybatisPlus.globalConfig.idType}")
//	private Integer idType;// 主键类型 0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
//
//	@Value("${mybatisPlus.globalConfig.fieldStrategy}")
//	private Integer fieldStrategy; // 字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
//
//	@Value("${mybatisPlus.globalConfig.dbColumnUnderline}")
//	private Boolean dbColumnUnderline; // 驼峰下划线转换
//
//	@Value("${mybatisPlus.globalConfig.isRefresh}")
//	private Boolean isRefresh; // 刷新mapper 调试神器
//
//	@Value("${mybatisPlus.globalConfig.isCapitalMode}")
//	private Boolean isCapitalMode; // 数据库大写下划线转换
//
//	@Value("${mybatisPlus.globalConfig.logicDeleteValue}")
//	private String logicDeleteValue; // 逻辑删除配置
//
//	@Value("${mybatisPlus.globalConfig.logicNotDeleteValue}")
//	private String logicNotDeleteValue;// 逻辑删除配置
//	// mybatisPlus全局配置
//
//	@Bean(name = "globalConfig")
//	public GlobalConfiguration globalConfig() {
//		GlobalConfiguration globalConfig = new GlobalConfiguration();
//		if (!StringUtils.isBlank(idType)) {
//			globalConfig.setIdType(idType); // 主键类型
//		}
//		if (!StringUtils.isBlank(fieldStrategy)) {
//			// globalConfig.setFieldStrategy(fieldStrategy); //字段策略
//		}
//		if (!StringUtils.isBlank(dbColumnUnderline)) {
//			globalConfig.setDbColumnUnderline(dbColumnUnderline); // 驼峰下划线转换
//		}
//		if (!StringUtils.isBlank(isRefresh)) {
//			// globalConfig.setRefresh(isRefresh); //刷新mapper 调试神器
//		}
//		if (!StringUtils.isBlank(isCapitalMode)) {
//			globalConfig.setCapitalMode(isCapitalMode); // 数据库大写下划线转换
//		}
//		if (!StringUtils.isBlank(logicDeleteValue)) {
//			// globalConfig.setLogicDeleteValue(logicDeleteValue); //逻辑删除配置
//		}
//		if (!StringUtils.isBlank(logicNotDeleteValue)) {
//			// globalConfig.setLogicNotDeleteValue(logicNotDeleteValue); //逻辑删除配置
//		}
//		return globalConfig;
//	}
//
//	@DependsOn("basisDataSource")
//	@Bean(name = "sqlSessionFactory")
//	public SqlSessionFactory sqlSessionFactory(@Qualifier(value = "globalConfig") GlobalConfiguration globalConfig,
//			@Qualifier(value = "basisDataSource") DruidDataSource dataSource) throws Exception {
//
//		String mapperLocations = "classpath:mapper/**/*.xml";
//		String configLocation = "classpath:config/mybatis/mybatis-config.xml";
//		String typeAliasesPackage = "com.wiesel.**.entity";
//		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//		sqlSessionFactory.setDataSource(dataSource); // 数据源
//		sqlSessionFactory.setGlobalConfig(globalConfig); // 全局配置
//		Interceptor[] interceptor = { new PaginationInterceptor() };
//		sqlSessionFactory.setPlugins(interceptor); // 分页插件
//		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		try {
//			// 自动扫描Mapping.xml文件
//			sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
//			sqlSessionFactory.setConfigLocation(resolver.getResource(configLocation));
//			sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);
//			return sqlSessionFactory.getObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//	}
//
//	// MyBatis 动态扫描
//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer() {
//		log.info("初始化MapperScannerConfigurer");
//		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		String basePackage = "com.wiesel.**.mapper";
//		mapperScannerConfigurer.setBasePackage(basePackage);
//		return mapperScannerConfigurer;
//	}
//
//	// 配置事务管理
//	@DependsOn("basisDataSource")
//	@Bean(name = "transactionManager")
//	public DataSourceTransactionManager transactionManager(
//			@Qualifier(value = "basisDataSource") DruidDataSource dataSource) {
//		log.info("初始化DataSourceTransactionManager");
//		return new DataSourceTransactionManager(dataSource);
//	}
	
//	  /**
//     * 性能分析拦截器，不建议生产使用
//     */
//    @Bean
//    public PerformanceInterceptor performanceInterceptor(){
//        return new PerformanceInterceptor();
//    }
	
	
    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        /*
//         * 【测试多租户】 SQL 解析处理拦截器<br>
//         * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
//         */
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//        TenantSqlParser tenantSqlParser = new TenantSqlParser();
//        tenantSqlParser.setTenantHandler(new TenantHandler() {
//            @Override
//            public Expression getTenantId() {
//                return new LongValue(1L);
//            }
//
//            @Override
//            public String getTenantIdColumn() {
//                return "tenant_id";
//            }
//
//            @Override
//            public boolean doTableFilter(String tableName) {
//                // 这里可以判断是否过滤表
//                /*if ("user".equals(tableName)) {
//                    return true;
//                }*/
//                return false;
//            }
//        });
//
//        sqlParserList.add(tenantSqlParser);
//        paginationInterceptor.setSqlParserList(sqlParserList);
////        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
////            @Override
////            public boolean doFilter(MetaObject metaObject) {
////                MappedStatement ms = PluginUtils.getMappedStatement(metaObject);
////                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
////                if ("com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId())) {
////                    return true;
////                }
////                return false;
////            }
////        });
//        return paginationInterceptor;
//    }
}
