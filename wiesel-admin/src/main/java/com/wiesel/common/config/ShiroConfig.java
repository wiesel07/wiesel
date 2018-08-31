package com.wiesel.common.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wiesel.common.config.properties.RedisProperties;
import com.wiesel.common.constant.GlobalConstant;
import com.wiesel.shiro.UserRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;

/**
 *
 * @ClassName 类名：ShiroConfig
 * @Description 功能说明：shiro配置信息
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
@Slf4j
@Configuration
public class ShiroConfig {

	@Value("${spring.cache.type}")
	private String cacheType;

	@Value("${server.session-timeout}")
	private int tomcatTimeout;

	// LifecycleBeanPostProcessor将Initializable和Destroyable的实现类统一
	// 在其内部自动分别调用了Initializable.init()和Destroyable.destroy()方法
	// ，从而达到管理shiro bean生命周期的目的
	@Bean
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
	 *
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	 /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
	@Bean
	ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		 filterChainDefinitionMap.put("/login","anon");
		filterChainDefinitionMap.put("/lib/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/docs/**", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/upload/**", "anon");
		filterChainDefinitionMap.put("/files/**", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		//filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/blog", "anon");
		filterChainDefinitionMap.put("/blog/open/**", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(userRealm());
		// 自定义缓存实现 使用redis
		log.info("shiro自定义缓存实现【" + cacheType+"】");
		if (GlobalConstant.CACHE_TYPE_REDIS.equals(cacheType)) {
			securityManager.setCacheManager(cacheManager());
		} else {

			// CacheManager cacheManager= securityManager.getCacheManager();
			securityManager.setCacheManager(ehCacheManager());
		}
		// 自定义session管理
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	@Bean
	UserRealm userRealm() {
		UserRealm userRealm = new UserRealm();
		return userRealm;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 *
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	// redis配置初始化
	@Resource
    private RedisProperties redisProperties;
	
	/**
	 * 配置shiro redisManager
	 *
	 * @return
	 */
	@Bean
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisProperties.config(redisManager);
		return redisManager;
	}

	/**
	 * cacheManager 缓存 redis实现 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	@Bean
	public SessionDAO sessionDAO() {
		if (GlobalConstant.CACHE_TYPE_REDIS.equals(cacheType)) {
			return redisSessionDAO();
		} else {
			return new MemorySessionDAO();
		}
	}

	/**
	 * shiro session的管理
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		log.info(DateUtil.now()+"shiro session的管理");
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(tomcatTimeout * 1000);
		sessionManager.setSessionDAO(sessionDAO());
		Collection<SessionListener> listeners = new ArrayList<SessionListener>();
		listeners.add(new OnlineSessionListener());
		sessionManager.setSessionListeners(listeners);
		return sessionManager;
	}


	@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManager(CacheManager.create());
		// em.setCacheManagerConfigFile("classpath:config/ehcache.xml");
		return em;

	}

	/**
     * 密码匹配凭证管理器
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
       // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }
}
