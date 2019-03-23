package com.ruoyi.web.restcontroller.system;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.web.restcontroller.system.models.SysDeptModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("部门管理")
@RestController
@RequestMapping("/system/dept")
@Validated
public class SysDeptController extends BaseController
{
   @Autowired
   private ISysDeptService deptService;

   @ApiOperation("部门列表")
   @RequiresPermissions("system:dept:list")
   @PostMapping("/list")
   @ResponseBody
   public List<SysDept> list(@RequestBody SysDept dept)
   {
       List<SysDept> deptList = deptService.selectDeptList(dept);
       return deptList;
   }

   /**
    * 新增保存部门
    */
   @Log(title = "部门管理", businessType = BusinessType.INSERT)
   @ApiOperation("新增部门")
   @RequiresPermissions("system:dept:add")
   @PostMapping("/add")
   public AjaxResult add(@RequestBody @Validated(SysDeptModel.Create.class) SysDeptModel dept)
   {
	   SysDept condition = new SysDept();
	   condition.setParentId(dept.getParentId());
	   condition.setDeptName(dept.getDeptName());
	   List<SysDept> list = deptService.selectDeptList(condition);
	   ApiAssert.isEmpty(ErrorCode.DeptNameDuplicated, list);
	   SysDept entity = BeanConverter.convert(SysDept.class, dept);
	   deptService.insertDept(entity);
	   return this.success();
   }

   /**
    * 部门精确查询
    */
   @ApiOperation("部门精确查询")
   @RequiresPermissions("system:dept:getby")
   @PostMapping("/getby")
   public AjaxResult getby(@RequestBody @Validated SysDept dept)
   {
       return this.success(deptService.selectDeptList(dept));
   }

   /**
    * 保存
    */
   @ApiOperation("更新部门")
   @Log(title = "部门管理", businessType = BusinessType.UPDATE)
   @RequiresPermissions("system:dept:update")
   @PostMapping("/update")
   public AjaxResult update(@RequestBody @Validated(SysDeptModel.Update.class) SysDeptModel model)
   {
	   SysDept condition = new SysDept();
	   condition.setParentId(model.getParentId());
	   condition.setDeptName(model.getDeptName());
	   List<SysDept> list = deptService.selectDeptList(condition);
	   if (null != list && list.stream().anyMatch(s-> s.getDeptId() != model.getDeptId())) {
		return error(ErrorCode.DeptNameDuplicated);
	   }
	   SysDept dept = BeanConverter.convert(SysDept.class, model);
	   deptService.updateDept(dept);
       return success();
   }

   /**
    * 删除
    */
   @ApiOperation("删除部门")
   @Log(title = "部门管理", businessType = BusinessType.DELETE)
   @RequiresPermissions("system:dept:remove")
   @PostMapping("/remove")
   public AjaxResult remove( Long deptId)
   {
       if (deptService.selectDeptCount(deptId) > 0)
       {
           return error("存在下级部门,不允许删除");
       }
       if (deptService.checkDeptExistUser(deptId))
       {
           return error("部门存在用户,不允许删除");
       }
       deptService.deleteDeptById(deptId);
       return this.success();
   }
}