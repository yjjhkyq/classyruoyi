package com.ruoyi.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.quartz.domain.SysJobLog;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 调度任务日志信息 数据层
 * 
 * @author ruoyi
 */
public interface SysJobLogMapper extends BaseMapper<SysJobLog>
{
    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    public IPage<SysJobLog> selectJobLogList(@Param("pg")IPage<SysJobLog> page, @Param("sm")SysJobLog jobLog);

    /**
     * 清空任务日志
     */
    public void cleanJobLog();
}
