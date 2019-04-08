package com.ruoyi.web.controller.system;


import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.framework.util.JsonWebTokenUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.web.controller.system.models.LoginModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("user login")
@RestController
@RequestMapping("/system/account")
@Validated
public class AccountController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	@Autowired
    private StringRedisTemplate redisTemplate;
	
	/**
	 * 过滤器已经做了验证，此处只需要签发token就行
	 * @param model
	 * @return
	 */
	@ApiOperation("user login")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody @Validated LoginModel model)
    {
		String token =  JsonWebTokenUtil.generate(this.getUserId());
        // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}
        redisTemplate.opsForValue().set(ShiroConstants.TOKEN_GET + this.getUserId(), token, JsonWebTokenUtil.EXPIRE, TimeUnit.SECONDS);
        return this.success(token);
    }
	
	@ApiOperation("user logout")
    @PostMapping("/logout")
    public AjaxResult logout()
    {
		redisTemplate.delete(ShiroConstants.TOKEN_GET + this.getUserId());
        return this.success();
    }
	
}
