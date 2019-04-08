package com.ruoyi.web.controller.monitor.models;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.quartz.util.CronUtils;
import com.ruoyi.web.controller.system.models.BaseModel;
import com.ruoyi.web.controller.system.models.SysDeptModel.Create;
import com.ruoyi.web.controller.system.models.SysDeptModel.Update;

import io.swagger.annotations.ApiModel;

@ApiModel
public class SysJobModel extends BaseModel{
	 /** 任务ID */
    private Long jobId;

    /** 任务名称 */
    @NotBlank(groups= {Create.class, Update.class}, message = "请输入部门名称！")
    private String jobName;

    /** 任务组名 */
    @NotBlank(groups= {Create.class, Update.class}, message = "请输入部门名称！")
    private String jobGroup;

    /** 任务方法 */
    @NotBlank(groups= {Create.class, Update.class}, message = "请输入部门名称！")
    private String methodName;

    /** 方法参数 */
    private String methodParams;

    /** cron执行表达式 */
    @NotBlank(groups= {Create.class, Update.class}, message = "请输入部门名称！")
    private String cronExpression;

    /** cron计划策略 */
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

    /** 任务状态（0正常 1暂停） */
    private String status;

    public Long getJobId()
    {
        return jobId;
    }

    public void setJobId(Long jobId)
    {
        this.jobId = jobId;
    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getJobGroup()
    {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup)
    {
        this.jobGroup = jobGroup;
    }

    public String getMethodName()
    {
        return methodName;
    }

    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    public String getMethodParams()
    {
        return methodParams;
    }

    public void setMethodParams(String methodParams)
    {
        this.methodParams = methodParams;
    }

    public String getCronExpression()
    {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }

    public Date getNextValidTime()
    {
        if (StringUtils.isNotEmpty(cronExpression))
        {
            return CronUtils.getNextExecution(cronExpression);
        }
        return null;
    }

    public String getMisfirePolicy()
    {
        return misfirePolicy;
    }

    public void setMisfirePolicy(String misfirePolicy)
    {
        this.misfirePolicy = misfirePolicy;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public interface Create {
		
	}
    
    public interface Update {
		
	}
}
