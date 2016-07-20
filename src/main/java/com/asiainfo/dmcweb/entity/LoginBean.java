package com.asiainfo.dmcweb.entity;

public class LoginBean {
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 验证码
	 */
	private String verifyCode;
	/**
	 * 浏览器类型
	 */
	private String browser;
	/**
	 * 操作系统
	 */
	private String os;
	/**
	 * 密码类型1加密串,2明文
	 */
	private int pwdType = 2;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "LoginBean [email=" + email + ", pwd=" + pwd + ", verifyCode=" + verifyCode + ", browser=" + browser
				+ ", os=" + os + ", pwdType=" + pwdType + "]";
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public int getPwdType() {
		return pwdType;
	}
	public void setPwdType(int pwdType) {
		this.pwdType = pwdType;
	}
}
