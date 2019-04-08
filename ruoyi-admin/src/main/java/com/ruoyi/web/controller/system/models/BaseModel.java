package com.ruoyi.web.controller.system.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class BaseModel {
	 /** 创建者 */
	@ApiModelProperty("账号")
	@TableField(fill = FieldFill.INSERT)
	private String createBy;

    /** 创建时间 */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;

    /** 更新者 */
    @ApiModelProperty("更新者")
    private String updateBy;

    /** 更新时间 */
    @ApiModelProperty("更新时间")
    private Date updateTime;
    
    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 搜索值 */
    private String searchValue;
    
    /** 请求参数 */
    private Map<String, Object> params;
    
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
