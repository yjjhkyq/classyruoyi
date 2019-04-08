package com.ruoyi.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jsoup.Connection.Base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.system.domain.SysOperLog;

/**
 * 操作日志 数据层
 * 
 * @author ruoyi
 */
public interface SysOperLogMapper extends BaseMapper<SysOperLog>
{
    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    public IPage<SysOperLog> selectOperLogList(@Param("pg")IPage<SysOperLog> page, @Param("sm")SysOperLog operLog);
    
    /**
     * 清空操作日志
     */
    public void cleanOperLog();
}
