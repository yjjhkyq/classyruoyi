package com.ruoyi.framework.shiro.realm;

import io.jsonwebtoken.MalformedJwtException;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;

import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.framework.shiro.token.JwtToken;
import com.ruoyi.framework.shiro.web.filter.BJwtFilter;
import com.ruoyi.framework.util.JsonWebTokenUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* *
 * @Author tomsun28
 * @Description 
 * @Date 18:07 2018/3/3
 */
public class JwtRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(JwtRealm.class);
    private ISysMenuService _menuService;
    private ISysRoleService _roleService;
    private ISysUserService _userService;
    private StringRedisTemplate _redisTemplate;
    public JwtRealm(ISysMenuService menuService, ISysRoleService roleService, ISysUserService userService,
    		StringRedisTemplate redisTemplate)
    {
    	this._menuService = menuService;
    	this._roleService = roleService;
    	this._userService = userService;
    	this._redisTemplate = redisTemplate;
    }
    
    public Class<?> getAuthenticationTokenClass() {
        // 此realm只支持jwtToken
        return JwtToken.class;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    	logger.debug("info............");
    	try {
    		
    	SysUser user = ShiroUtils.getSysUser();
    	logger.debug("info............" + user.toString());
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        }
        else
        {
            roles = _roleService.selectRoleKeys(user.getUserId());
            menus = _menuService.selectPermsByUserId(user.getUserId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);
        }
        logger.debug("info............" + info.toString());
        return info;
    	}
    	catch(Exception e) 
    	{
    		logger.error("exception:" + e.getMessage());
    		throw e;
    	}
    }

    /**
     * 登陆 
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof JwtToken)) {
            return null;
        }
        long uid = JsonWebTokenUtil.getUid(authenticationToken.getPrincipal().toString());
        logger.debug("user id in jwt realm cached:" + _redisTemplate.opsForValue().get(ShiroConstants.TOKEN_GET + uid));
        if(!authenticationToken.getPrincipal().equals(_redisTemplate.opsForValue().get(ShiroConstants.TOKEN_GET + uid))) {
        	throw new ConcurrentAccessException();  
        }
        SysUser user =  _userService.selectUserById(uid);
        logger.debug("user id in jwt realm:" + uid);
        return new SimpleAuthenticationInfo(user,null,this.getName());
    }
}
