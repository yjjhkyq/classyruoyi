package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jsoup.Connection.Base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.system.domain.SysDictType;

/**
 * 字典表 数据层
 * 
 * @author ruoyi
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictType>
{
    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    public IPage<SysDictType> selectDictTypeList(@Param("pg")IPage<SysDictType> page, @Param("sm")SysDictType dictType);

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    public SysDictType checkDictTypeUnique(String dictType);
}
