package com.ruoyi.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.quartz.domain.SysJob;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 调度任务信息 数据层
 * 
 * @author ruoyi
 */
public interface SysJobMapper extends BaseMapper<SysJob>
{
    /**
     * 查询调度任务日志集合
     * 
     * @param job 调度信息
     * @return 操作日志集合
     */
    public IPage<SysJob> selectJobList(@Param("pg")IPage<SysJob> page,  @Param("sm")SysJob job);
}
