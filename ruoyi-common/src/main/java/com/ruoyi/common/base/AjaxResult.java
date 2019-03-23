package com.ruoyi.common.base;

import java.util.HashMap;

import com.ruoyi.common.enums.ErrorCode;

/**
 * 操作消息提醒
 * 
 * @author ruoyi
 */
public class AjaxResult extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 Message 对象
     */
    public AjaxResult()
    {
    }

    /**
     * 返回错误消息
     * 
     * @return 错误消息
     */
    public static AjaxResult error()
    {
        return error(1, "操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(String msg)
    {
        return error(500, msg);
    }

    /**
     * 返回错误消息
     * 
     * @param code 错误码
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(int code, String msg)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }
    
    public static AjaxResult error(ErrorCode code)
    {
        return ajaxResult(code, null);
    }
    
    
    public static AjaxResult ajaxResult(ErrorCode code, Object data)
    {
        AjaxResult json = new AjaxResult();
        json.put("code", code.getCode());
        json.put("msg", code.getInfo());
        json.put("data", data);
        return json;
    }
    
    public static AjaxResult success(Object data)
    {
    	return ajaxResult(ErrorCode.OK, data);
    }
    
    
    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static AjaxResult success()
    {
        return AjaxResult.ajaxResult(ErrorCode.OK, null);
    }

    /**
     * 返回成功消息
     * 
     * @param key 键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public AjaxResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
