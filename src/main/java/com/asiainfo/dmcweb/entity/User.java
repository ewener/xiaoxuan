package com.asiainfo.dmcweb.entity;


public class User{
	
	
	private String role;//角色
	private String btn_list;//按钮权限, 按"-"区分
	
	public Integer getId() {
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

	public void setId(Integer id) {
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role= role;
	}
	
}