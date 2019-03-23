package com.ruoyi.framework.shiro.realm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.ruoyi.framework.shiro.service.SysLoginService;
import com.ruoyi.framework.shiro.token.JwtToken;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;

/* *
 * @Author tomsun28
 * @Description realm管理器
 * @Date 17:52 2018/3/3
 */
@Component
public class RealmManager {

	private SysLoginService _loginService;
	private ISysMenuService _menuService;
	private ISysRoleService _roleService;
	private ISysUserService _userService;
	private StringRedisTemplate _redisTemplate; 
	@Autowired
	public RealmManager(SysLoginService loginService, ISysMenuService menuService, ISysRoleService roleService,
			ISysUserService userService, StringRedisTemplate redisTemplate) {
		_loginService = loginService;
		this._menuService = menuService;
		this._roleService = roleService;
		this._userService = userService;
		this._redisTemplate = redisTemplate;
	}

	public List<Realm> initGetRealm() {
		List<Realm> realmList = new LinkedList<>();
		// ----- password
		PasswordRealm passwordRealm = new PasswordRealm(_loginService);
		passwordRealm.setAuthenticationTokenClass(UsernamePasswordToken.class);
		passwordRealm.setCredentialsMatcher(new CredentialsMatcher() {

			@Override
			public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
				return true;
			}
		});
		realmList.add(passwordRealm);
		// ----- jwt
		JwtRealm jwtRealm = new JwtRealm(_menuService, _roleService, _userService, this._redisTemplate);
		jwtRealm.setAuthenticationTokenClass(JwtToken.class);
		jwtRealm.setCredentialsMatcher(new CredentialsMatcher() {
			@Override
			public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
				return true;
			}
		});
		realmList.add(jwtRealm);
		return Collections.unmodifiableList(realmList);
	}
}
