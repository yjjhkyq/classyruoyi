package com.ruoyi.web.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ruoyi.common.enums.ErrorCode;
import com.ruoyi.framework.modelmapper.BeanConverter;
import com.ruoyi.framework.util.ApiAssert;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.web.controller.system.models.SysPostModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("岗位管理")
@RestController
@RequestMapping("/system/post")
@Validated
public class SysPostController extends BaseController
{
    @Autowired
    private ISysPostService postService;

    @ApiOperation("岗位搜索")
    @RequiresPermissions("system:post:list")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(@RequestBody SysPost post)
    {
    	return success(postService.selectPostList(getPage(), post));
    }
    
    @ApiOperation("岗位搜索")
    @RequiresPermissions("system:post:list")
    @PostMapping("/listAll")
    @ResponseBody
    public AjaxResult listAll(@RequestBody SysPost post)
    {
    	return success(postService.selectPostList(getAllPage(), post));
    }


    @ApiOperation("岗位精确查询")
    @RequiresPermissions("system:post:delete")
    @Log(title = "岗位精确查询", businessType = BusinessType.DELETE)
    @PostMapping("/getby")
    @ResponseBody
    public AjaxResult getby(@RequestBody SysPost post) throws Exception
    {
        return success(postService.selectPostById(post.getPostId()));
    }

    /**
     * 新增保存岗位
     */
    @ApiOperation("新增岗位")
    @RequiresPermissions("system:post:create")
    @Log(title = "新增岗位", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ResponseBody
    public AjaxResult create(@RequestBody @Validated(SysPostModel.Create.class)SysPostModel model)
    {
    	SysPost post = BeanConverter.convert(SysPost.class, model);
    	ApiAssert.isTrue(ErrorCode.PostNameDuplicated, postService.checkPostNameUnique(post)); 
    	ApiAssert.isTrue(ErrorCode.PostCodeDuplicated, postService.checkPostNameUnique(post)); 
        return success(postService.insertPost(post));
    }
    
    @ApiOperation("删除岗位")
    @RequiresPermissions("system:post:delete")
    @Log(title = "删除岗位", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delete(String ids) throws Exception
    {
        return success(postService.deletePostByIds(ids));
    }

    /**
     * 更新岗位
     */
    @ApiOperation("更新岗位")
    @RequiresPermissions("system:post:update")
    @Log(title = "更新岗位", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody @Validated(SysPostModel.Update.class)SysPostModel model)
    {
    	SysPost post = BeanConverter.convert(SysPost.class, model);
    	ApiAssert.isTrue(ErrorCode.PostNameDuplicated, postService.checkPostNameUnique(post)); 
    	ApiAssert.isTrue(ErrorCode.PostCodeDuplicated, postService.checkPostNameUnique(post)); 
        return toAjax(postService.updatePost(post));
    }
}