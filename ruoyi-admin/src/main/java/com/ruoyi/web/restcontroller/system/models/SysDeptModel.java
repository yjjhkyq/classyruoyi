package com.ruoyi.web.restcontroller.system.models;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 部门
 * @author lsy
 *
 */
@ApiModel
public class SysDeptModel extends BaseModel{
	 /** 部门ID */
	@ApiModelProperty("部门ID")
	private Long deptId;
	
    /** 父部门ID */
	@ApiModelProperty("父部门ID")
	private Long parentId;

    /** 祖级列表 */
	@ApiModelProperty("祖级列表")
	private String ancestors;

    /** 部门名称 */
	@ApiModelProperty("部门名称")
	@NotBlank(groups= {Create.class, Update.class}, message = "请输入部门名称！")
	private String deptName;

    /** 显示顺序 */
	@ApiModelProperty("显示顺序 ")
	private Integer orderNum;

    /** 负责人 */
	@ApiModelProperty("负责人")
	private String leader;

    /** 联系电话 */
	@ApiModelProperty("联系电话")
	private String phone;
    
    /** 邮箱 */
	@ApiModelProperty("邮箱 ")
	private String email;

    /** 部门状态:0正常,1停用 */
	@ApiModelProperty("部门状态 ")
	private Integer status;

    /** 父部门名称 */
	@ApiModelProperty("父部门名称")
	private String parentName;

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

    public String getAncestors()
    {
        return ancestors;
    }

    public void setAncestors(String ancestors)
    {
        this.ancestors = ancestors;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public Integer getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getLeader()
    {
        return leader;
    }

    public void setLeader(String leader)
    {
        this.leader = leader;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }
    
    public interface Create
    {
    	
    }
    
    public interface Update
    {
    	
    }

}
