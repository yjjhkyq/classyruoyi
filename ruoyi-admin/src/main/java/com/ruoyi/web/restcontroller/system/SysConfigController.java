package com.ruoyi.web.restcontroller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.ErrorCode;
import com.ruoyi.framework.modelmapper.BeanConverter;
import com.ruoyi.framework.util.ApiAssert;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.web.restcontroller.system.models.SysConfigModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 参数配置 信息操作处理
 * @author lsy
 *
 */
@Api("参数配置")
@RestController
@RequestMapping("/system/config")
@Validated
public class SysConfigController extends BaseController{
	 @Autowired
	 private ISysConfigService configService;
	 
    /**
     * 查询参数配置列表
     */
    @ApiOperation("查询参数配置列表")
	@RequiresPermissions("system:config:list")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody SysConfigModel model)
    {
        return success(configService.selectConfigList(BeanConverter.convert(SysConfig.class, model)));
    }
    
    /**
     * 新增保存参数配置
     */
    @RequiresPermissions("system:config:add")
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    public AjaxResult create(@RequestBody @Validated(SysConfigModel.Create.class) SysConfigModel model)
    {
    	SysConfig entity = BeanConverter.convert(SysConfig.class, model);
    	ApiAssert.isTrue(ErrorCode.ConfigKeyDuplicated, configService.checkConfigKeyUnique(entity));
        return success(configService.insertConfig(entity));
    }
    
    /**
     * 修改保存参数配置
     */
    @RequiresPermissions("system:config:update")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody @Validated(SysConfigModel.Update.class) SysConfigModel model)
    {
    	SysConfig entity = BeanConverter.convert(SysConfig.class, model);
    	ApiAssert.isTrue(ErrorCode.ConfigKeyDuplicated, configService.checkConfigKeyUnique(entity));
        return success(configService.updateConfig(entity));
    }
    
    /**
     * 参数配置精确查询
     */
    @ApiOperation("字典类型精确查询")
    @RequiresPermissions("system:config:getby")
    @PostMapping("/getby")
    @ResponseBody
    public AjaxResult getby(@RequestBody SysConfigModel model)
    {
        return success(configService.selectConfigBy(BeanConverter.convert(SysConfig.class, model)));
    }
    
    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:config:delete")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delete(String ids)
    {
        return success(configService.deleteConfigByIds(ids));
    }
}
