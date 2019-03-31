package com.ruoyi.web.restcontroller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ruoyi.common.enums.ErrorCode;
import com.ruoyi.framework.modelmapper.BeanConverter;
import com.ruoyi.framework.shiro.web.filter.BJwtFilter;
import com.ruoyi.framework.util.ApiAssert;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysDictType;
import com.ruoyi.system.service.ISysDictTypeService;
import com.ruoyi.web.restcontroller.system.models.SysDictTypeModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 数据字典信息
 * 
 * @author ruoyi
 */
@Api("字典类型管理")
@RestController
@RequestMapping("/system/dict")
@Validated
public class SysDictTypeController extends BaseController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(SysDictTypeController.class);
	
    @Autowired
    private ISysDictTypeService dictTypeService;

    @PostMapping("/list")
    @RequiresPermissions("system:dict:list")
    @ResponseBody
    public AjaxResult list(@RequestBody SysDictType dictType)
    {
    	LOGGER.info("page:" + getPage().getCurrent() + "  " + getPage().getSize());
    	return success(dictTypeService.selectDictTypeList(this.<SysDictType>getPage(), dictType));
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @ApiOperation("新增字典类型")
    @RequiresPermissions("system:dict:create")
    @PostMapping("/create")
    @ResponseBody
    public AjaxResult create(@RequestBody @Validated(SysDictTypeModel.Create.class) SysDictTypeModel dict)
    {
    	SysDictType entity = BeanConverter.convert(SysDictType.class, dict);
    	ApiAssert.isTrue(ErrorCode.DictTypeDuplicated, dictTypeService.checkDictTypeUnique(entity));
    	return success(dictTypeService.insertDictType(entity));
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @ApiOperation("更新字典类型")
    @RequiresPermissions("system:dict:update")
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody @Validated(SysDictTypeModel.Update.class) SysDictTypeModel dict)
    {
    	SysDictType entity = BeanConverter.convert(SysDictType.class, dict);
    	ApiAssert.isTrue(ErrorCode.DictTypeDuplicated, dictTypeService.checkDictTypeUnique(entity));
        return success(dictTypeService.updateDictType(entity));
    }

    /**
     * 字典类型精确查询
     */
    @ApiOperation("字典类型精确查询")
    @RequiresPermissions("system:dict:getby")
    @PostMapping("/getby")
    @ResponseBody
    public AjaxResult getby(@RequestBody SysDictTypeModel model)
    {
        return success(dictTypeService.selectDictTypeById(model.getDictId()));
    }
    
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @ApiOperation("删除字典类型")
    @RequiresPermissions("system:dict:delete")
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delete(String ids) throws Exception
    {
    	return success(dictTypeService.deleteDictTypeByIds(ids));
    }
}
