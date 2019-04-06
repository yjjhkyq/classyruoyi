package com.ruoyi.web.restcontroller.system;

import java.util.List;

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
import com.ruoyi.common.enums.ErrorCode;
import com.ruoyi.framework.modelmapper.BeanConverter;
import com.ruoyi.framework.util.ApiAssert;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.web.restcontroller.system.models.SysRoleModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api("角色管理")
@RestController
@Validated
@RequestMapping("/system/role")
public class SysRoleController extends BaseController{
	@Autowired
    private ISysRoleService roleService;

    @RequiresPermissions("system:role:list")
    @ApiOperation("角色列表")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(@RequestBody SysRoleModel role)
    {
        return success(roleService.selectRoleList(this.<SysRole>getPage(), BeanConverter.convert(SysRole.class, role)));
    }

    @RequiresPermissions("system:role:list")
    @ApiOperation("角色列表")
    @PostMapping("/listAll")
    @ResponseBody
    public AjaxResult listAll(@RequestBody SysRoleModel role)
    {
        return success(roleService.selectRoleList(this.<SysRole>getAllPage(), BeanConverter.convert(SysRole.class, role)));
    }
    /**
     * 新增保存角色
     */
    @ApiOperation("新增保存角色")
    @RequiresPermissions("system:role:create")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ResponseBody
    public AjaxResult create(@RequestBody @Validated(SysRoleModel.Create.class)SysRoleModel model)
    {
    	SysRole entity = BeanConverter.convert(SysRole.class, model);
    	ApiAssert.isTrue(ErrorCode.RoleNameDuplicated, roleService.checkRoleNameUnique(entity));
    	ApiAssert.isTrue(ErrorCode.RoleKeyDuplicated, roleService.checkRoleKeyUnique(entity));
        return success(roleService.insertRole(entity));

    }
    
    /**
     * 角色精确查询
     */
    @ApiOperation("字典类型精确查询")
    @RequiresPermissions("system:role:list")
    @PostMapping("/getby")
    @ResponseBody
    public AjaxResult getby(Long roleId)
    {
        return success(roleService.selectRoleById(roleId));
    }
 
    /**
     * 修改保存角色
     */
    @ApiOperation("更新角色")
    @RequiresPermissions("system:role:update")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody @Validated(SysRoleModel.Update.class) SysRoleModel model)
    {
        SysRole entity = BeanConverter.convert(SysRole.class, model);
        ApiAssert.isTrue(ErrorCode.RoleNameDuplicated, roleService.checkRoleNameUnique(entity));
    	ApiAssert.isTrue(ErrorCode.RoleKeyDuplicated, roleService.checkRoleKeyUnique(entity));
        return success(roleService.updateRole(entity));

    }

//    /**
//     * 新增数据权限
//     */
//    @GetMapping("/rule/{roleId}")
//    public String rule(@PathVariable("roleId") Long roleId, ModelMap mmap)
//    {
//        mmap.put("role", roleService.selectRoleById(roleId));
//        return prefix + "/rule";
//    }
//
//    /**
//     * 修改保存数据权限
//     */
//    @RequiresPermissions("system:role:edit")
//    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
//    @PostMapping("/rule")
//    @Transactional(rollbackFor = Exception.class)
//    @ResponseBody
//    public AjaxResult ruleSave(SysRole role)
//    {
//        role.setUpdateBy(ShiroUtils.getLoginName());
//        return toAjax(roleService.updateRule(role));
//    }

    @ApiOperation("删除角色")
    @RequiresPermissions("system:role:delete")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delete(String ids)
    {
        return success(roleService.deleteRoleByIds(ids));
     
    }

    /**
     * 角色状态修改
     */
    @ApiOperation("角色状态修改")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:update")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(@RequestBody SysRoleModel model)
    {
        return toAjax(roleService.changeStatus(BeanConverter.convert(SysRole.class, model)));
    }
    
    @RequiresPermissions("system:role:list")
    @ApiOperation("获取角色拥有的菜单列表")
    @PostMapping("/menu/list")
    @ResponseBody
    public AjaxResult getMenuBy(Long roleId)
    {
        return success(roleService.selectMenuBy(roleId));
    }
    
    @ApiOperation("给角色设置菜单权限")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:update")
    @PostMapping("/menu/setRoleMenu")
    @ResponseBody
    public AjaxResult setRoleMenu(Long roleId, @RequestBody List<SysMenu> menus)
    {
        return toAjax(roleService.setRoleMenu(roleId, menus));
    }
    
    
    
    
}
