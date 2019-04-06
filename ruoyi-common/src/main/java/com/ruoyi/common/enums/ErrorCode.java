package com.ruoyi.common.enums;

public enum ErrorCode {
	
	//部门管理相关错误信息
	DeptNameDuplicated(1000, "部门名称重复"),
	//职位管理相关错误信息
	PostNameDuplicated(2000, "职位名称重复"),
	PostCodeDuplicated(2001, "职位编码重复"),
	//字典管理
	DictTypeDuplicated(3000, "字典类型重复"),
	//参数配置
	ConfigKeyDuplicated(4000, "参数键名重复"),
	//参数配置
    ChildMenuExistedDeleteNoyAllowed(5000, "存在子菜单，不允许删除"),
    MenuUsedDeleteNoyAllowed(5001, "菜单已分配，不允许删除"),
    MenuNameDuplicated(5002, "菜单名重复"),
    //角色管理
    RoleNameDuplicated(6000, "角色名称重复"),
    RoleKeyDuplicated(6001, "权限字符重复"),
    // 用户管理
    LoginNameDuplicated(7000, "登录账号重复"),
    PhoneDuplicated(7001, "手机号码重复"),
    EmailDuplicated(7002, "邮箱重复"),
	OK(0, "正常"), Error(-1, "错误"),  NewToken(1, "重新签发jwt"),TokenError(2, "Token异常"),LoginError(3, "用户名或者密码错误"),
	ValidateError(4, "校验错误"),NoRecord(5, "未查找到记录"), ServerError(6, "服务器内部错误 ");
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
