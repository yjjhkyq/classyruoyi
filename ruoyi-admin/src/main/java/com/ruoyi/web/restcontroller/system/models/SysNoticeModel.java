package com.ruoyi.web.restcontroller.system.models;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

@ApiModel("公告")
public class SysNoticeModel extends BaseModel{
	 /** 公告ID */
    private Long noticeId;
    
    /** 公告标题 */
    @NotBlank(groups = {Create.class, Update.class}, message="必填")
    private String noticeTitle;
    
    /** 公告类型（1通知 2公告） */
    private String noticeType;
    
    /** 公告内容 */
    private String noticeContent;
    
    /** 公告状态（0正常 1关闭） */
    private Integer status;

    public Long getNoticeId()
    {
        return noticeId;
    }

    public void setNoticeId(Long noticeId)
    {
        this.noticeId = noticeId;
    }

    public void setNoticeTitle(String noticeTitle)
    {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeTitle()
    {
        return noticeTitle;
    }

    public void setNoticeType(String noticeType)
    {
        this.noticeType = noticeType;
    }

    public String getNoticeType()
    {
        return noticeType;
    }

    public void setNoticeContent(String noticeContent)
    {
        this.noticeContent = noticeContent;
    }

    public String getNoticeContent()
    {
        return noticeContent;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    
    public interface Create {
		
	}
    
    public interface Update {
    	
    }
    
}
