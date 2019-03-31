package com.ruoyi.web.restcontroller.system.models;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 职位视图模型
 * @author lsy
 *
 */
@ApiModel
public class SysPostModel extends BaseModel{
	  /** 岗位序号 */
	@ApiModelProperty(name = "岗位序号")
    private Long postId;

    /** 岗位编码 */
	@ApiModelProperty(name = "岗位编码")
	@NotBlank(groups= {Create.class, Update.class}, message="请输入岗位编码")
    private String postCode;

    /** 岗位名称 */
	@ApiModelProperty(name = "岗位名称")
	@NotBlank(groups= {Create.class, Update.class}, message="请输入岗位名称")
    private String postName;

    /** 岗位排序 */
	@ApiModelProperty(name = "岗位排序")
    private String postSort;

    /** 状态（0正常 1停用） */
	@ApiModelProperty(name = "状态")
    private Integer status;

    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean flag = false;

    public Long getPostId()
    {
        return postId;
    }

    public void setPostId(Long postId)
    {
        this.postId = postId;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    public String getPostName()
    {
        return postName;
    }

    public void setPostName(String postName)
    {
        this.postName = postName;
    }

    public String getPostSort()
    {
        return postSort;
    }

    public void setPostSort(String postSort)
    {
        this.postSort = postSort;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
    
    public interface Create
    {
    	
    }
    
    public interface Update {
		
	}
}
