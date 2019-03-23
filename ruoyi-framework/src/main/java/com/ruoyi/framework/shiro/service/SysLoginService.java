package com.ruoyi.framework.shiro.service;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ValidateUtil;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.shiro.realm.PasswordRealm;
import com.ruoyi.framework.util.MessageUtils;
import com.ruoyi.framework.util.ServletUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.exception.user.CaptchaException;
import com.ruoyi.framework.web.exception.user.UserBlockedException;
import com.ruoyi.framework.web.exception.user.UserDeleteException;
import com.ruoyi.framework.web.exception.user.UserNotExistsException;
import com.ruoyi.framework.web.exception.user.UserPasswordNotMatchException;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;

/**
 * 登录校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysLoginService
{
	private static final Logger logger = LoggerFactory.getLogger(SysLoginService.class);
	
    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysUserService userService;
    
    /**
     * 登录
     * @throws Exception 
     */
    public SysUser login(String username, String password) throws Exception
    {
        // 验证码校验
//        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
//        {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
//            throw new CaptchaException();
//        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        SysUser user = userService.selectUserByLoginName(username);
        if (user == null && ValidateUtil.maybeMobilePhoneNumber(username))
        {
            user = userService.selectUserByPhoneNumber(username);
        }

        if (user == null && ValidateUtil.maybeEmail(username))
        {
            user = userService.selectUserByEmail(username);
        }
        
        if (user == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
            throw new UserNotExistsException();
        }
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.delete")));
            throw new UserDeleteException();
        }
        
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark())));
            throw new UserBlockedException();
        }
        passwordService.validate(user, password);
        logger.debug("validate success");
        //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        //recordLoginInfo(user);
        return user;
    }

    public String genenateToken(String userName) {
    	return new Md5Hash(userName + UUID.randomUUID().toString()).toHex().toString();
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user)
    {
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}
