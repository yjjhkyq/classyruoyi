package com.ruoyi.common.enums;

public enum ErrorCode {
	OK(0, "正常"),  NewToken(1, "重新签发jwt"),TokenError(2, "Token异常"),LoginError(3, "用户名或者密码错误"),
	ValidateError(4, "校验错误"),NoRecord(5, "未查找到记录"), ServerError(6, "服务器内部错误 "),
	
	DeptNameDuplicated(1000, "部门名称重复");

    private final int code;
    private final String info;

    ErrorCode(int code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public int getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
