package com.ruoyi.web.controller.monitor;

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
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.service.ISysOperLogService;

import io.swagger.annotations.Api;

/**
 * 操作日志记录
 * 
 * @author ruoyi
 */
@Api("操作日志记录")
@RestController
@RequestMapping("/monitor/operlog")
@Validated
public class SysOperlogController extends BaseController
{

    @Autowired
    private ISysOperLogService operLogService;

    @RequiresPermissions("monitor:operlog:list")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(@RequestBody SysOperLog operLog)
    {
        return success(operLogService.selectOperLogList(this.<SysOperLog>getPage(), operLog));
    }

    @RequiresPermissions("monitor:operlog:delete")
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return success(operLogService.deleteOperLogByIds(ids));
    }

    @RequiresPermissions("monitor:operlog:list")
    @PostMapping("/getby")
    public AjaxResult getby(Long operId)
    {
        return success(operLogService.selectOperLogById(operId));
    }
    
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:operlog:delete")
    @PostMapping("/clean")
    public AjaxResult clean()
    {
        operLogService.cleanOperLog();
        return success();
    }
}
