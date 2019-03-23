package com.ruoyi.framework.shiro.web.filter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class ShiroFilterChainManager {
	@Autowired
	private StringRedisTemplate _redisTemplate;
    // 初始化获取过滤链
    public Map<String,Filter> initGetFilters() {
        Map<String,Filter> filters = new LinkedHashMap<>();
        PasswordFilter passwordFilter = new PasswordFilter();
        filters.put("auth",passwordFilter);
        BJwtFilter jwtFilter = new BJwtFilter();
        jwtFilter.setRedisTemplate(_redisTemplate);
        filters.put("jwt",jwtFilter);
        return filters;
    }
	
    // 初始化获取过滤链规则
    public Map<String,String> initGetFilterChain() {
        Map<String,String> filterChain = new LinkedHashMap<>();
        // -------------anon 默认过滤器忽略的URL
        List<String> defalutAnon = Arrays.asList("/swagger-ui.html","/webjars/**","/v2/**","/swagger-resources/**");
        defalutAnon.forEach(ignored -> filterChain.put(ignored,"anon"));
        filterChain.put("/system/account/login", "auth");
        filterChain.put("/**", "jwt");
        // -------------auth 默认需要认证过滤器的URL 走auth--PasswordFilter
//        List<String> defalutAuth = Arrays.asList("/**");
//        defalutAuth.forEach(auth -> filterChain.put(auth,"jwt"));
        // -------------dynamic 动态URL
//        if (shiroFilterRulesProvider != null) {
//            List<RolePermRule> rolePermRules = this.shiroFilterRulesProvider.loadRolePermRules();
//            if (null != rolePermRules) {
//                rolePermRules.forEach(rule -> {
//                    StringBuilder Chain = rule.toFilterChain();
//                    if (null != Chain) {
//                        filterChain.putIfAbsent(rule.getUrl(),Chain.toString());
//                    }
//                });
//            }
//        }
        return filterChain;
    }
}
