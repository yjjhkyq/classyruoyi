package com.ruoyi.framework.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ruoyi.framework.shiro.service.SysLoginService;
import com.ruoyi.framework.web.exception.user.CaptchaException;
import com.ruoyi.framework.web.exception.user.RoleBlockedException;
import com.ruoyi.framework.web.exception.user.UserBlockedException;
import com.ruoyi.framework.web.exception.user.UserNotExistsException;
import com.ruoyi.framework.web.exception.user.UserPasswordNotMatchException;
import com.ruoyi.framework.web.exception.user.UserPasswordRetryLimitExceedException;
import com.ruoyi.system.domain.SysUser;

/* *
 * @Author lsy
 * @Description 这里是登录认证realm
 */
public class PasswordRealm extends AuthorizingRealm {
	private static final Logger log = LoggerFactory.getLogger(PasswordRealm.class);

	private SysLoginService _loginService;
	public PasswordRealm(SysLoginService loginService)
	{
		this._loginService = loginService;
	}
	// 此Realm只支持PasswordToken
	public Class<?> getAuthenticationTokenClass() {
		return UsernamePasswordToken.class;
	}

	// 这里只需要认证登录，成功之后派发 json web token 授权在那里进行
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		log.debug("doGetAuthenticationInfo");
		return null;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		if (!(authenticationToken instanceof UsernamePasswordToken))
			return null;
		UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
		if (null == upToken.getPrincipal() || null == upToken.getCredentials()) {
			throw new UnknownAccountException();
		}
		String username = (String) upToken.getPrincipal();
		String password = "";
		if (upToken.getPassword() != null) {
			password = new String(upToken.getPassword());
		}
		SysUser user = null;
		try {
			user = _loginService.login(username, password);
		    log.debug("login success"); 
		} catch (CaptchaException e) {
			throw new AuthenticationException(e.getMessage(), e);
		} catch (UserNotExistsException e) {
			throw new UnknownAccountException(e.getMessage(), e);
		} catch (UserPasswordNotMatchException e) {
			throw new IncorrectCredentialsException(e.getMessage(), e);
		} catch (UserPasswordRetryLimitExceedException e) {
			throw new ExcessiveAttemptsException(e.getMessage(), e);
		} catch (UserBlockedException e) {
			throw new LockedAccountException(e.getMessage(), e);
		} catch (RoleBlockedException e) {
			throw new LockedAccountException(e.getMessage(), e);
		} catch (Exception e) {
			log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
			throw new AuthenticationException(e.getMessage(), e);
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, "", getName());
		return info;
	}
}
