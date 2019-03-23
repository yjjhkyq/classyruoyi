package com.ruoyi.framework.shiro.web.filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.ErrorCode;
import com.ruoyi.framework.util.ServletUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* *
 * @Author tomsun28
 * @Description 基于 用户名密码 的认证过滤器
 * @Date 20:18 2018/2/10
 */
public class PasswordFilter extends AccessControlFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordFilter.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		LOGGER.debug("isAccessAllowed");
		Subject subject = getSubject(request, response);
		// 如果其已经登录，再此发送登录请求
		if (null != subject && subject.isAuthenticated()) {
			return true;
		}
		// 拒绝，统一交给 onAccessDenied 处理
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// 判断是否是登录请求
		AuthenticationToken authenticationToken = createPasswordToken(request);
		Subject subject = getSubject(request, response);
		try {
			subject.login(authenticationToken);
			return true;
		} catch (AuthenticationException e) {
			LOGGER.warn(authenticationToken.getPrincipal() + "::" + e.getMessage());
			// 返回response告诉客户端认证失败

			ServletUtils.responseWrite(AjaxResult.error(ErrorCode.LoginError), response);
			return false;
		} catch (Exception e) {
			LOGGER.error(authenticationToken.getPrincipal() + "::认证异常::" + e.getMessage(), e);
			// 返回response告诉客户端认证失败
			ServletUtils.responseWrite(AjaxResult.error(ErrorCode.LoginError), response);
			return false;
		}
	 
	}

	private AuthenticationToken createPasswordToken(ServletRequest request) {
		Map<String, String> map = ServletUtils.getRequestBodyMap(request);
		String appId = map.get("username");
		String password = map.get("password");
		LOGGER.debug(appId + password);
		return new UsernamePasswordToken(appId, password);
	}
}
