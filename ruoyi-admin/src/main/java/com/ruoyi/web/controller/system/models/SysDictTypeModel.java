package com.ruoyi.web.controller.system.models;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("字典类型")
public class SysDictTypeModel extends BaseModel{
	   /** 字典主键 */
	@ApiModelProperty("字典主键")
    private Long dictId;

    /** 字典名称 */
	@ApiModelProperty("字典名称")
	@NotBlank(groups = {Create.class, Update.class}, message="请输入字典名称")
    private String dictName;

    /** 字典类型 */
	@ApiModelProperty("字典类型 ")
	@NotBlank(groups = {Create.class, Update.class}, message="请输入字典类型")
    private String dictType;

    /** 状态（0正常 1停用） */
	@ApiModelProperty("状态 0=正常,1=停用")
    private String status;

    public Long getDictId()
    {
        return dictId;
    }

    public void setDictId(Long dictId)
    {
        this.dictId = dictId;
    }

    public String getDictName()
    {
        return dictName;
    }

    public void setDictName(String dictName)
    {
        this.dictName = dictName;
    }

    public String getDictType()
    {
        return dictType;
    }

    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public interface Create{
		
	}
    
    public interface Update{
		
	}
}
