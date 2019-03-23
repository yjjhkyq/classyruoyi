package com.ruoyi.web.restcontroller.system.models;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@ApiModel
public class LoginModel {
	@ApiModelProperty("账号")
    @NotBlank(message = "用户名不能为空！")
	private String username;
	@ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空！")
	private String password;
	private Boolean rememberMe;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	 
}
