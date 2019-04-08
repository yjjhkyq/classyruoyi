package com.ruoyi.web.controller.monitor;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.modelmapper.BeanConverter;
import com.ruoyi.framework.util.ApiAssert;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.service.ISysJobService;
import com.ruoyi.web.controller.monitor.models.SysJobModel;

import io.swagger.annotations.Api;

import com.ruoyi.framework.web.base.BaseController;

/**
 * 调度任务信息操作处理
 * 
 * @author ruoyi
 */
@Api("调度任务信息")
@RestController
@RequestMapping("/monitor/job")
@Validated
public class SysJobController extends BaseController
{

    @Autowired
    private ISysJobService jobService;

    @RequiresPermissions("monitor:job:list")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(@RequestBody SysJobModel model)
    {
        return success(jobService.selectJobList(this.<SysJob>getPage(), BeanConverter.convert(SysJob.class, model)));
    }

    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @RequiresPermissions("monitor:job:delete")
    @PostMapping("/delete")
    public AjaxResult delete(String ids)
    {
    	jobService.deleteJobByIds(ids);
    	return success();
    }
    
    @RequiresPermissions("monitor:job:list")
    @PostMapping("/getby")
    public AjaxResult getby(Long jobId)
    {
        return success(jobService.selectJobById(jobId));
    }
    
    /**
     * 任务调度状态修改
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:changeStatus")
    @PostMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysJobModel model)
    {
    	SysJob job = jobService.selectJobById(model.getJobId());
    	job.setStatus(model.getStatus());
        return toAjax(jobService.changeStatus(BeanConverter.convert(SysJob.class, job)));
    }

    /**
     * 任务调度立即执行一次
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:changeStatus")
    @PostMapping("/run")
    public AjaxResult run(Long jobId)
    {
    	SysJob job = jobService.selectJobById(jobId);
        return success(jobService.run(job));
    }

    /**
     * 新增保存调度
     */
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @RequiresPermissions("monitor:job:create")
    @PostMapping("/create")
    public AjaxResult create(@RequestBody @Validated(SysJobModel.Create.class)SysJobModel model)
    {
    	ApiAssert.isTrue("cron表达式错误", jobService.checkCronExpressionIsValid(model.getCronExpression()));
        return success(jobService.insertJobCron(BeanConverter.convert(SysJob.class, model)));
    }

    /**
     * 修改保存调度
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @RequiresPermissions("monitor:job:update")
    @PostMapping("/update")
    public AjaxResult update(@RequestBody @Validated(SysJobModel.Update.class)SysJobModel model)
    {
    	ApiAssert.isTrue("cron表达式错误", jobService.checkCronExpressionIsValid(model.getCronExpression()));
        return success(jobService.updateJobCron(BeanConverter.convert(SysJob.class, model)));
    }
}
