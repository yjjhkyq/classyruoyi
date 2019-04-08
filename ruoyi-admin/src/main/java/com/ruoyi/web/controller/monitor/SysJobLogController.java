package com.ruoyi.web.controller.monitor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.ruoyi.quartz.domain.SysJobLog;
import com.ruoyi.quartz.service.ISysJobLogService;

import io.swagger.annotations.Api;

/**
 * 调度日志操作处理
 * 
 * @author ruoyi
 */
@Api("调度日志操作处理")
@RestController
@RequestMapping("/monitor/jobLog")
@Validated
public class SysJobLogController extends BaseController
{
    @Autowired
    private ISysJobLogService jobLogService;

    @RequiresPermissions("monitor:job:list")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody SysJobLog jobLog)
    {
        return success(jobLogService.selectJobLogList(this.<SysJobLog>getPage(), jobLog));
    }

    @Log(title = "调度日志", businessType = BusinessType.DELETE)
    @RequiresPermissions("monitor:job:delete")
    @PostMapping("/delete")
    public AjaxResult delete(String ids)
    {
        return success(jobLogService.deleteJobLogByIds(ids));
    }

    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:job:delete")
    @PostMapping("/clean")
    public AjaxResult clean()
    {
        jobLogService.cleanJobLog();
        return success();
    }
}
