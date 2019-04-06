package com.ruoyi.web.restcontroller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.web.restcontroller.system.models.SysMenuModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单信息
 * 
 * @author ruoyi
 */
@Api("菜单信息")
@Validated
@RequestMapping("/system/menu")
@RestController
public class SysMenuController extends BaseController {
	@Autowired
	private ISysMenuService menuService;

	@ApiOperation("菜单列表")
	@RequiresPermissions("system:menu:list")
	@PostMapping("/list")
	public AjaxResult list(@RequestBody SysMenuModel model) {
		return success(menuService.selectMenuList(BeanConverter.convert(SysMenu.class, model)));
	}

	/**
	 * 删除菜单
	 */
	@Log(title = "菜单管理", businessType = BusinessType.DELETE)
	@ApiOperation("菜单管理")
	@RequiresPermissions("system:menu:delete")
	@PostMapping("/delete")
	public AjaxResult delete(Long menuId) {
		ApiAssert.isFalse(ErrorCode.ChildMenuExistedDeleteNoyAllowed, menuService.selectCountMenuByParentId(menuId) > 0);
		ApiAssert.isFalse(ErrorCode.MenuUsedDeleteNoyAllowed, menuService.selectCountRoleMenuByMenuId(menuId) > 0);
		return success(menuService.deleteMenuById(menuId));
	}

	/**
	 * 新增保存菜单
	 */
	@Log(title = "菜单管理", businessType = BusinessType.INSERT)
	@ApiOperation("新增保存菜单")
	@RequiresPermissions("system:menu:create")
	@PostMapping("/create")
	public AjaxResult create(@RequestBody @Validated(SysMenuModel.Create.class) SysMenuModel model) {
		SysMenu entity = BeanConverter.convert(SysMenu.class, model);
		ApiAssert.isTrue(ErrorCode.MenuNameDuplicated, menuService.checkMenuNameUnique(entity));
		return success(menuService.insertMenu(entity));
	}

	 /**
     * 菜单精确查询
     */
    @ApiOperation("菜单精确查询")
    @RequiresPermissions("system:menu:list")
    @PostMapping("/getby")
    public AjaxResult getby(@RequestBody SysMenuModel model)
    {
    	SysMenu entity = BeanConverter.convert(SysMenu.class, model);
        return success(menuService.selectMenuById(entity.getMenuId()));
    }
    
	/**
	 * 修改保存菜单
	 */
	@Log(title = "菜单管理", businessType = BusinessType.UPDATE)
	@ApiOperation("更新菜单")
	@RequiresPermissions("system:menu:update")
	@PostMapping("/update")
	public AjaxResult update(@RequestBody @Validated(SysMenuModel.Update.class) SysMenuModel model) {
		SysMenu entity = BeanConverter.convert(SysMenu.class, model);
		ApiAssert.isTrue(ErrorCode.MenuNameDuplicated, menuService.checkMenuNameUnique(entity));
		return success(menuService.updateMenu(entity));
	}

	/**
	 * 加载角色菜单列表树
	 */
	@ApiOperation("加载角色菜单列表树")
	@GetMapping("/roleMenuTreeData")
	@ResponseBody
	public AjaxResult roleMenuTreeData(SysRole role) {
		return success(menuService.roleMenuTreeData(role));
	}

	/**
	 * 加载所有菜单列表树
	 */
	@GetMapping("/menuTreeData")
	@ApiOperation("加载所有菜单列表树")
	@ResponseBody
	public AjaxResult menuTreeData(SysRole role) {
		return success(menuService.menuTreeData());
	}
}
