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
import com.ruoyi.framework.modelmapper.BeanConverter;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.web.restcontroller.system.models.SysDictDataModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 数据字典信息
 * 
 * @author ruoyi
 */
@Api("数据字典")
@RestController
@RequestMapping("/system/dict/data")
@Validated
public class SysDictDataController extends BaseController
{
    @Autowired
    private ISysDictDataService dictDataService;

    @PostMapping("/list")
    @RequiresPermissions("system:dict:list")
    public AjaxResult list(@RequestBody SysDictData dictData)
    {
        return success(dictDataService.selectDictDataList(dictData));
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @ApiOperation("新增字典数据")
    @RequiresPermissions("system:dict:create")
    @PostMapping("/create")
    public AjaxResult create(@RequestBody @Validated(SysDictDataModel.Create.class) SysDictDataModel dict)
    {
        return success(dictDataService.insertDictData(BeanConverter.convert(SysDictData.class, dict)));
    }
    
    /**
     * 字典类型精确查询
     */
    @ApiOperation("字典精确查询")
    @RequiresPermissions("system:dict:list")
    @PostMapping("/getby")
    @ResponseBody
    public AjaxResult getby(@RequestBody SysDictData model)
    {
        return success(dictDataService.selectDictData(BeanConverter.convert(SysDictData.class, model)));
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @ApiOperation("更新字典数据")
    @RequiresPermissions("system:dict:update")
    @PostMapping("/update")
    public AjaxResult update(@RequestBody @Validated(SysDictDataModel.Update.class) SysDictDataModel model)
    {
    	SysDictData entity = BeanConverter.convert(SysDictData.class, model);
        return success(dictDataService.updateDictData(entity));
    }
    

    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dict:delete")
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delete(String ids)
    {
        return success(dictDataService.deleteDictDataByIds(ids));
    }
}