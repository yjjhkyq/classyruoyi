package com.ruoyi.web.controller.system.models;


import javax.validation.constraints.NotBlank;
import io.swagger.annotations.ApiModel;

@ApiModel("参数配置")
public class SysConfigModel extends BaseModel{
	
	 /** 参数主键 */
    private Long configId;

    /** 参数名称 */
    @NotBlank(groups = {Create.class, Update.class}, message="必填")
    private String configName;

    /** 参数键名 */
    @NotBlank(groups = {Create.class, Update.class}, message="必填")
    private String configKey;

    /** 参数键值 */
    @NotBlank(groups = {Create.class, Update.class}, message="必填")
    private String configValue;

    /** 系统内置（Y是 N否） */
    private String configType;

    public Long getConfigId()
    {
        return configId;
    }

    public void setConfigId(Long configId)
    {
        this.configId = configId;
    }

    public String getConfigName()
    {
        return configName;
    }

    public void setConfigName(String configName)
    {
        this.configName = configName;
    }

    public String getConfigKey()
    {
        return configKey;
    }

    public void setConfigKey(String configKey)
    {
        this.configKey = configKey;
    }

    public String getConfigValue()
    {
        return configValue;
    }

    public void setConfigValue(String configValue)
    {
        this.configValue = configValue;
    }

    public String getConfigType()
    {
        return configType;
    }

    public void setConfigType(String configType)
    {
        this.configType = configType;
    }
    
    public interface Create{
    	
    }
    
    public interface Update {
		
	}
}
