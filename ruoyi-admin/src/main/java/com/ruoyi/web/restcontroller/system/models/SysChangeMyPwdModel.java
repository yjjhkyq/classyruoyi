package com.ruoyi.web.restcontroller.system.models;

import javax.validation.constraints.NotBlank;

public class SysChangeMyPwdModel{
	@NotBlank(message = "必填！")
	private String oldPassword;
	@NotBlank(message = "必填！")
	private String newPassword;
	@NotBlank(message = "必填！")
	private String confirmPwd;
	
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
