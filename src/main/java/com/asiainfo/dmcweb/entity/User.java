package com.asiainfo.dmcweb.entity;


public class User{
	
		private Integer id;//   id主键	private String email;//   邮箱也是登录帐号	private String pwd;//   登录密码	private String nickName;//   昵称
	private String role;//角色	private java.sql.Timestamp loginTime;//   最后登录时间	private String menu_list;//用户权限, 按"-"区分
	private String btn_list;//按钮权限, 按"-"区分
	
	public Integer getId() {	    return this.id;	}	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", pwd=" + pwd + ", nickName=" + nickName + ", role=" + role
				+ ", loginTime=" + loginTime + ", menu_list=" + menu_list + ", btn_list=" + btn_list + "]";
	}

	public String getMenu_list() {
		return menu_list;
	}

	public void setMenu_list(String menu_list) {
		this.menu_list = menu_list;
	}

	public String getBtn_list() {
		return btn_list;
	}

	public void setBtn_list(String btn_list) {
		this.btn_list = btn_list;
	}

	public void setId(Integer id) {	    this.id=id;	}	public String getEmail() {	    return this.email;	}	public void setEmail(String email) {	    this.email=email;	}	public String getPwd() {	    return this.pwd;	}	public void setPwd(String pwd) {	    this.pwd=pwd;	}	public String getNickName() {	    return this.nickName;	}	public void setNickName(String nickName) {	    this.nickName=nickName;	}		public java.sql.Timestamp getLoginTime() {	    return this.loginTime;	}	public void setLoginTime(java.sql.Timestamp loginTime) {	    this.loginTime=loginTime;	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role= role;
	}	
	
}
