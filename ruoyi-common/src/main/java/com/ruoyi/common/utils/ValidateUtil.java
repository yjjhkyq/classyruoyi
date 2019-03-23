package com.ruoyi.common.utils;

import com.ruoyi.common.constant.UserConstants;
/**
 * 校验工具类
 * @author lsy
 *
 */
public class ValidateUtil {
    /**
     * 字符串是否是email格式
     * @param value
     * @return true 是email格式，反之false
     */
	public static boolean maybeEmail(String value)
    {
        if (!value.matches(UserConstants.EMAIL_PATTERN))
        {
            return false;
        }
        return true;
    }

	/**
	 * 字符串是否是电话格式
	 * @param value 
	 * @return true是电话格式，反之false
	 */
    public static boolean maybeMobilePhoneNumber(String value)
    {
        if (!value.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
        {
            return false;
        }
        return true;
    }

}
