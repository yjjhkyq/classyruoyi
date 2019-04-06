package com.ruoyi.web.restcontroller.system.models;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;


import io.swagger.annotations.ApiModel;

@ApiModel("菜单")
public class SysMenuModel extends BaseModel {
	 /** 菜单ID */
    private Long menuId;
    
    /** 菜单名称 */
    @NotBlank(groups = {Create.class, Update.class}, message="必填")
    private String menuName;
    
    /** 父菜单名称 */
    private String parentName;
    
    /** 父菜单ID */
    private Long parentId;
    
    /** 显示顺序 */
    private String orderNum;
    
    /** 菜单URL */
    private String url;
    
    /** 类型:0目录,1菜单,2按钮 */
    @NotBlank(groups = {Create.class, Update.class}, message="必填")
    private String menuType;
    
    /** 菜单状态:0显示,1隐藏 */
    private String visible;
    
    /** 权限字符串 */
    private String perms;
    
    /** 菜单图标 */
    private String icon;
    
    /** 子菜单 */
    private List<SysMenuModel> children = new ArrayList<SysMenuModel>();

    public Long getMenuId()
    {
        return menuId;
    }

    public void setMenuId(Long menuId)
    {
        this.menuId = menuId;
    }

    public String getMenuName()
    {
        return menuName;
    }

    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getMenuType()
    {
        return menuType;
    }

    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public String getVisible()
    {
        return visible;
    }

    public void setVisible(String visible)
    {
        this.visible = visible;
    }

    public String getPerms()
    {
        return perms;
    }

    public void setPerms(String perms)
    {
        this.perms = perms;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public List<SysMenuModel> getChildren()
    {
        return children;
    }

    public void setChildren(List<SysMenuModel> children)
    {
        this.children = children;
    }
    
    public interface Create{
    	
    }
    
    public interface Update{
		
	}
}
