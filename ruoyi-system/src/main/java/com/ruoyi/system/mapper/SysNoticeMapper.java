package com.ruoyi.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.system.domain.SysNotice;

/**
 * 公告 数据层
 * 
 * @author ruoyi
 */
public interface SysNoticeMapper extends BaseMapper<SysNotice>
{
    /**
     * 查询公告信息
     * 
     * @param notice 查询条件
     * @return 公告信息
     */
    public List<SysNotice> selectNotice(SysNotice notice);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public IPage<SysNotice> selectNoticeList(@Param("pg")IPage<SysNotice> page, @Param("sm")SysNotice notice);

}