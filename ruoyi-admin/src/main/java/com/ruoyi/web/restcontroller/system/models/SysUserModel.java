package com.ruoyi.web.restcontroller.system.models;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
@ApiModel("用户信息")
public class SysUserModel extends BaseModel{
	/** 用户ID */
    private Long userId;

    /** 部门ID */
    @NotNull(groups= {Create.class, Update.class}, message = "必填！")
    private Long deptId;

    /** 部门父ID */
    private Long parentId;

    /** 登录名称 */
    @NotBlank(groups= {Create.class, Update.class, ResetPassword.class}, message = "必填！")
    private String loginName;

    /** 用户名称 */
    @NotBlank(groups= {Create.class, Update.class}, message = "必填！")
    private String userName;

    /** 用户邮箱 */
    @NotBlank(groups= {Create.class, Update.class}, message = "必填！")
    private String email;

    /** 手机号码 */
    @NotBlank(groups= {Create.class, Update.class}, message = "必填！")
    private String phonenumber;

    /** 用户性别 */
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    private String password;

    /** 盐加密 */
    private String salt;

    /** 帐号状态（0正常 1停用） */
    private Integer status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登陆IP */
    private String loginIp;

    /** 最后登陆时间 */
    private Date loginDate;

    /** 部门对象 */
    private SysDeptModel dept;
    private List<SysRoleModel> roles;
    private List<SysPostModel> posts;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhonenumber()
    {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getLoginIp()
    {
        return loginIp;
    }

    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public Date getLoginDate()
    {
        return loginDate;
    }

    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    public SysDeptModel getDept()
    {
        if (dept == null)
        {
            dept = new SysDeptModel();
        }
        return dept;
    }

    public void setDept(SysDeptModel dept)
    {
        this.dept = dept;
    }

    public List<SysRoleModel> getRoles()
    {
        return roles;
    }

    public void setRoles(List<SysRoleModel> roles)
    {
        this.roles = roles;
    }

    public Long[] getRoleIds()
    {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds)
    {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds()
    {
        return postIds;
    }

    public void setPostIds(Long[] postIds)
    {
        this.postIds = postIds;
    }
    
    public List<SysPostModel> getPosts() {
		return posts;
	}

	public void setPosts(List<SysPostModel> posts) {
		this.posts = posts;
	}

	public interface Create {
    	
    }
    
    public interface Update {
		
	}
    
    public interface ResetPassword{
    	
    }
    
   
}
