package com.ruoyi.web.restcontroller.system.models;

import javax.validation.constraints.NotBlank;

import com.ruoyi.web.restcontroller.system.models.SysDictTypeModel.Create;
import com.ruoyi.web.restcontroller.system.models.SysDictTypeModel.Update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 字典数据
 * @author lsy
 *
 */
@ApiModel("字典数据")
public class SysDictDataModel extends BaseModel{
	  /** 字典编码 */
	@ApiModelProperty("字典主键")
	private Long dictCode;

    /** 字典排序 */
	@ApiModelProperty("字典排序")
	private Long dictSort;

    /** 字典标签 */
	@ApiModelProperty("字典标签")
	@NotBlank(groups = {Create.class, Update.class}, message="请输入字典标签")
    private String dictLabel;

    /** 字典键值 */
	@ApiModelProperty("字典键值")
	@NotBlank(groups = {Create.class, Update.class}, message="请输入字典键值")
    private String dictValue;

    /** 字典类型 */
	@ApiModelProperty("字典类型")
	@NotBlank(groups = {Create.class, Update.class}, message="请输入字典类型")
    private String dictType;

    /** 样式属性（其他样式扩展） */
	@ApiModelProperty("样式属性（其他样式扩展） ")
    private String cssClass;

    /** 表格字典样式 */
	@ApiModelProperty("表格字典样式")
    private String listClass;

    /** 是否默认（Y是 N否） */
	@ApiModelProperty("是否默认（Y是 N否）")
    private String isDefault;

    /** 状态（0正常 1停用） */
	@ApiModelProperty("状态（0正常 1停用）")
    private Integer status;

    public Long getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(Long dictCode)
    {
        this.dictCode = dictCode;
    }

    public Long getDictSort()
    {
        return dictSort;
    }

    public void setDictSort(Long dictSort)
    {
        this.dictSort = dictSort;
    }

    public String getDictLabel()
    {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel)
    {
        this.dictLabel = dictLabel;
    }

    public String getDictValue()
    {
        return dictValue;
    }

    public void setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
    }

    public String getDictType()
    {
        return dictType;
    }

    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public String getCssClass()
    {
        return cssClass;
    }

    public void setCssClass(String cssClass)
    {
        this.cssClass = cssClass;
    }

    public String getListClass()
    {
        return listClass;
    }

    public void setListClass(String listClass)
    {
        this.listClass = listClass;
    }

    public String getIsDefault()
    {
        return isDefault;
    }

    public void setIsDefault(String isDefault)
    {
        this.isDefault = isDefault;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public interface Create{
		
	}
    
    public interface Update{
		
	}
    
}
