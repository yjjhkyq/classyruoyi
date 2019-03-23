package com.ruoyi.common.exception;

import com.ruoyi.common.enums.ErrorCode;

/**
 * 业务异常
 * 
 * @author ruoyi
 */
public class BusinessException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    protected final String message;
    protected int errorCode;
    
    public BusinessException(String message)
    {
    	this.errorCode = ErrorCode.ServerError.getCode();
        this.message = message;
    }

    public BusinessException(ErrorCode errorCode )
    {
    	this.errorCode = errorCode.getCode();
    	this.message = errorCode.getInfo();
    }
    
    
    public int getErrorCode() {
		return errorCode;
	}

	@Override
    public String getMessage()
    {
        return message;
    }
}
