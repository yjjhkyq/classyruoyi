package com.ruoyi.web.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.ErrorCode;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.modelmapper.BeanConverter;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.ApiAssert;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.enums.SysConstants;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.controller.system.models.SysChangeMyPwdModel;
import com.ruoyi.web.controller.system.models.SysUserModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("用户信息")
@RestController
@Validated
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysConfigService configService;
    
    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody SysUserModel model)
    {
        return success(userService.selectUserList(this.<SysUser>getPage(), BeanConverter.convert(SysUser.class, model)));
    }

    @RequiresPermissions("system:user:list")
    @ApiOperation("用户精确查询")
    @PostMapping("/getby")
    public AjaxResult getby(@RequestBody SysUserModel user)
    {
        return success(userService.selectUserById(user.getUserId()));
    }
    
    /**
     * 新增保存用户
     */
    @RequiresPermissions("system:user:create")
    @ApiOperation("新增保存用户")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult create(@RequestBody @Validated(SysUserModel.Create.class) SysUserModel model)
    {
    	SysUser user = BeanConverter.convert(SysUser.class, model);
        if (StringUtils.isNotNull(user.getUserId()) && SysUser.isAdmin(user.getUserId()))
        {
            throw new BusinessException("不允许修改超级管理员用户");
        }
        ApiAssert.isTrue(ErrorCode.EmailDuplicated, userService.checkEmailUnique(user));
        ApiAssert.isTrue(ErrorCode.PhoneDuplicated, userService.checkPhoneUnique(user));
    	SysConfig config = new SysConfig();
        config.setConfigKey(SysConstants.ConfigKey_InitPassword);
        String password = (configService.selectConfigBy(config)).get(0).getConfigValue();
        user.setPassword(password);
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return success(userService.insertUser(user));
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("system:user:update")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult update(@RequestBody @Validated(SysUserModel.Update.class) SysUserModel model)
    {
    	SysUser user = BeanConverter.convert(SysUser.class, model);
        if (StringUtils.isNotNull(user.getUserId()) && SysUser.isAdmin(user.getUserId()))
        {
            throw new BusinessException("不允许修改超级管理员用户");
        }
        ApiAssert.isTrue(ErrorCode.EmailDuplicated, userService.checkEmailUnique(user));
        ApiAssert.isTrue(ErrorCode.PhoneDuplicated, userService.checkPhoneUnique(user));
        SysUser existedEntity = userService.selectUserById(model.getUserId());
        user.setSalt(existedEntity.getSalt());
        user.setLoginName(existedEntity.getLoginName());
        user.setPassword(existedEntity.getPassword());
        return success(userService.updateUser(user));
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(@RequestBody @Validated(SysUserModel.ResetPassword.class) SysUserModel model)
    {
    	SysUser user = BeanConverter.convert(SysUser.class, model);
    	SysConfig config = new SysConfig();
        config.setConfigKey(SysConstants.ConfigKey_InitPassword);
        String password = (configService.selectConfigBy(config)).get(0).getConfigValue();
        user.setPassword(password);
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        return toAjax(userService.resetUserPwd(user));
    }

    @RequiresPermissions("system:user:delete")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delete(String ids)
    {
       return success(userService.deleteUserByIds(ids));
    }

    /**
     * 用户状态修改
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:update")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(@RequestBody SysUserModel model)
    {
        return success(userService.changeStatus(BeanConverter.convert(SysUser.class, model)));
    }
    
    @GetMapping("/myProfile")
    public AjaxResult myProfile()
    {
    	return success(userService.selectUserById(getUserId())); 
    }
    
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/changeMyPwd")
    public AjaxResult changePwd(@RequestBody @Validated SysChangeMyPwdModel model)
    {
    	if (!model.getConfirmPwd().equals(model.getNewPassword())) {
			throw new BusinessException("确认密码输入错误");
		}
        SysUser user = getSysUser();
        if (StringUtils.isNotEmpty(model.getNewPassword()) && passwordService.matches(user, model.getOldPassword()))
        {
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), model.getNewPassword(), user.getSalt()));
            if (userService.resetUserPwd(user) > 0)
            {
                return success();
            }
            return error();
        }
        else
        {
            return error("修改密码失败，旧密码错误");
        }
    }
    
    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateMyInfo")
    @ResponseBody
    public AjaxResult update(SysUser user)
    {
        SysUser currentUser = userService.selectUserById(getSysUser().getUserId());
        currentUser.setUserName(user.getUserName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhonenumber(user.getPhonenumber());
        currentUser.setSex(user.getSex());
        if (userService.updateUserInfo(currentUser) > 0)
        {
            return success();
        }
        return error();
    }
    
    @GetMapping("/checkMyPassword")
    @ResponseBody
    public boolean checkPassword(String password)
    {
        SysUser user = getSysUser();
        if (passwordService.matches(user, password))
        {
            return true;
        }
        return false;
    }
}
