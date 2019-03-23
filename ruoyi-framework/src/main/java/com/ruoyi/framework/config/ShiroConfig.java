package com.ruoyi.framework.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ruoyi.framework.shiro.realm.AModularRealmAuthenticator;
import com.ruoyi.framework.shiro.realm.RealmManager;
import com.ruoyi.framework.shiro.web.filter.ASubjectFactory;
import com.ruoyi.framework.shiro.web.filter.ShiroFilterChainManager;

/**
 * 权限配置加载
 * 
 * @author ruoyi
 */
@Configuration
public class ShiroConfig {
	public static final String PREMISSION_STRING = "perms[\"{0}\"]";

	//
	// // Session超时时间，单位为毫秒（默认30分钟）
	// @Value("${shiro.session.expireTime}")
	// private int expireTime;
	//
	// // 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
	// @Value("${shiro.session.validationInterval}")
	// private int validationInterval;
	//
	// // 验证码开关
	// @Value("${shiro.user.captchaEnabled}")
	// private boolean captchaEnabled;
	//
	// // 验证码类型
	// @Value("${shiro.user.captchaType}")
	// private String captchaType;
	//
	// // 设置Cookie的域名
	// @Value("${shiro.cookie.domain}")
	// private String domain;
	//
	// // 设置cookie的有效访问路径
	// @Value("${shiro.cookie.path}")
	// private String path;
	//
	// // 设置HttpOnly属性
	// @Value("${shiro.cookie.httpOnly}")
	// private boolean httpOnly;
	//
	// // 设置Cookie的过期时间，秒为单位
	// @Value("${shiro.cookie.maxAge}")
	// private int maxAge;
	//
	// // 登录地址
	// @Value("${shiro.user.loginUrl}")
	// private String loginUrl;
	//
	// // 权限认证失败地址
	// @Value("${shiro.user.unauthorizedUrl}")
	// private String unauthorizedUrl;
	//
	//
	// /**
	// * 返回配置文件流 避免ehcache配置文件一直被占用，无法完全销毁项目重新部署
	// */
	// protected InputStream getCacheManagerConfigFileInputStream()
	// {
	// String configFile = "classpath:ehcache/ehcache-shiro.xml";
	// InputStream inputStream = null;
	// try
	// {
	// inputStream = ResourceUtils.getInputStreamForPath(configFile);
	// byte[] b = IOUtils.toByteArray(inputStream);
	// InputStream in = new ByteArrayInputStream(b);
	// return in;
	// }
	// catch (IOException e)
	// {
	// throw new ConfigurationException(
	// "Unable to obtain input stream for cacheManagerConfigFile [" + configFile +
	// "]", e);
	// }
	// finally
	// {
	// IOUtils.closeQuietly(inputStream);
	// }
	// }
	//
	// /**
	// * 自定义Realm
	// */
	// @Bean
	// public UserRealm userRealm(EhCacheManager cacheManager)
	// {
	// UserRealm userRealm = new UserRealm();
	// userRealm.setCacheManager(cacheManager);
	// return userRealm;
	// }
	//
	// /**
	// * 自定义sessionDAO会话
	// */
	// @Bean
	// public OnlineSessionDAO sessionDAO()
	// {
	// OnlineSessionDAO sessionDAO = new OnlineSessionDAO();
	// return sessionDAO;
	// }
	//
	// /**
	// * 自定义sessionFactory会话
	// */
	// @Bean
	// public OnlineSessionFactory sessionFactory()
	// {
	// OnlineSessionFactory sessionFactory = new OnlineSessionFactory();
	// return sessionFactory;
	// }
	//
	// /**
	// * 自定义sessionFactory调度器
	// */
	// @Bean
	// public SpringSessionValidationScheduler sessionValidationScheduler()
	// {
	// SpringSessionValidationScheduler sessionValidationScheduler = new
	// SpringSessionValidationScheduler();
	// // 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
	// sessionValidationScheduler.setSessionValidationInterval(validationInterval *
	// 60 * 1000);
	// // 设置会话验证调度器进行会话验证时的会话管理器
	// sessionValidationScheduler.setSessionManager(sessionValidationManager());
	// return sessionValidationScheduler;
	// }
	//
	//
	//
	// /**
	// * 安全管理器
	// */
	// @Bean
	// public SecurityManager securityManager(UserRealm userRealm)
	// {
	// DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	// // 设置realm.
	// securityManager.setRealm(userRealm);
	// // 记住我
	// securityManager.setRememberMeManager(rememberMeManager());
	// // 注入缓存管理器;
	// securityManager.setCacheManager(getEhCacheManager());
	// // session管理器
	// securityManager.setSessionManager(sessionManager());
	// return securityManager;
	// }
	//
	// /**
	// * 退出过滤器
	// */
	// public LogoutFilter logoutFilter()
	// {
	// LogoutFilter logoutFilter = new LogoutFilter();
	// logoutFilter.setLoginUrl(loginUrl);
	// return logoutFilter;
	// }
	//
	// /**
	// * Shiro过滤器配置
	// */
	// @Bean
	// public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager
	// securityManager)
	// {
	// ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
	// // Shiro的核心安全接口,这个属性是必须的
	// shiroFilterFactoryBean.setSecurityManager(securityManager);
	// // 身份认证失败，则跳转到登录页面的配置
	// shiroFilterFactoryBean.setLoginUrl(loginUrl);
	// // 权限认证失败，则跳转到指定页面
	// shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
	// // Shiro连接约束配置，即过滤链的定义
	// LinkedHashMap<String, String> filterChainDefinitionMap = new
	// LinkedHashMap<>();
	// // 对静态资源设置匿名访问
	// filterChainDefinitionMap.put("/favicon.ico**", "anon");
	// filterChainDefinitionMap.put("/ruoyi.png**", "anon");
	// filterChainDefinitionMap.put("/css/**", "anon");
	// filterChainDefinitionMap.put("/docs/**", "anon");
	// filterChainDefinitionMap.put("/fonts/**", "anon");
	// filterChainDefinitionMap.put("/img/**", "anon");
	// filterChainDefinitionMap.put("/ajax/**", "anon");
	// filterChainDefinitionMap.put("/js/**", "anon");
	// filterChainDefinitionMap.put("/ruoyi/**", "anon");
	// filterChainDefinitionMap.put("/druid/**", "anon");
	// filterChainDefinitionMap.put("/captcha/captchaImage**", "anon");
	// // 退出 logout地址，shiro去清除session
	// filterChainDefinitionMap.put("/logout", "logout");
	// // 不需要拦截的访问
	// filterChainDefinitionMap.put("/login", "anon,captchaValidate");
	// filterChainDefinitionMap.put("/systemlogin/login", "anon");
	// // 系统权限列表
	// //
	// filterChainDefinitionMap.putAll(SpringUtils.getBean(IMenuService.class).selectPermsAll());
	//
	// Map<String, Filter> filters = new LinkedHashMap<>();
	// filters.put("onlineSession", onlineSessionFilter());
	// filters.put("syncOnlineSession", syncOnlineSessionFilter());
	// filters.put("captchaValidate", captchaValidateFilter());
	// // 注销成功，则跳转到指定页面
	// filters.put("logout", logoutFilter());
	// shiroFilterFactoryBean.setFilters(filters);
	//
	// // 所有请求需要认证
	// filterChainDefinitionMap.put("/**", "user,onlineSession,syncOnlineSession");
	// shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	//
	// return shiroFilterFactoryBean;
	// }
	//
	// /**
	// * 自定义在线用户处理过滤器
	// */
	// @Bean
	// public OnlineSessionFilter onlineSessionFilter()
	// {
	// OnlineSessionFilter onlineSessionFilter = new OnlineSessionFilter();
	// onlineSessionFilter.setLoginUrl(loginUrl);
	// return onlineSessionFilter;
	// }
	//
	// /**
	// * 自定义在线用户同步过滤器
	// */
	// @Bean
	// public SyncOnlineSessionFilter syncOnlineSessionFilter()
	// {
	// SyncOnlineSessionFilter syncOnlineSessionFilter = new
	// SyncOnlineSessionFilter();
	// return syncOnlineSessionFilter;
	// }
	//
	// /**
	// * 自定义验证码过滤器
	// */
	// @Bean
	// public CaptchaValidateFilter captchaValidateFilter()
	// {
	// CaptchaValidateFilter captchaValidateFilter = new CaptchaValidateFilter();
	// captchaValidateFilter.setCaptchaEnabled(captchaEnabled);
	// captchaValidateFilter.setCaptchaType(captchaType);
	// return captchaValidateFilter;
	// }
	//
	// /**
	// * cookie 属性设置
	// */
	// public SimpleCookie rememberMeCookie()
	// {
	// SimpleCookie cookie = new SimpleCookie("rememberMe");
	// cookie.setDomain(domain);
	// cookie.setPath(path);
	// cookie.setHttpOnly(httpOnly);
	// cookie.setMaxAge(maxAge * 24 * 60 * 60);
	// return cookie;
	// }
	//
	// /**
	// * 记住我
	// */
	// public CookieRememberMeManager rememberMeManager()
	// {
	// CookieRememberMeManager cookieRememberMeManager = new
	// CookieRememberMeManager();
	// cookieRememberMeManager.setCookie(rememberMeCookie());
	// cookieRememberMeManager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));
	// return cookieRememberMeManager;
	// }
	//
 
	/**
	 * Shiro过滤器配置
	 * 
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
			ShiroFilterChainManager filterChainManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setFilters(filterChainManager.initGetFilters());
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainManager.initGetFilterChain());
		return shiroFilterFactoryBean;
	}
   
	/**
	 * 安全管理器
	 */
	@Bean
	public SecurityManager securityManager(RealmManager realmManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setAuthenticator(new AModularRealmAuthenticator());
		DefaultSubjectDAO subjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
		DefaultSessionStorageEvaluator evaluator = (DefaultSessionStorageEvaluator) subjectDAO
				.getSessionStorageEvaluator();
		ASubjectFactory subjectFactory = new ASubjectFactory(evaluator);
		securityManager.setSubjectFactory(subjectFactory);
		securityManager.setRealms(realmManager.initGetRealm());
		SecurityUtils.setSecurityManager(securityManager);
		return securityManager;
	}

	/**
	 * 开启Shiro注解通知器
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
