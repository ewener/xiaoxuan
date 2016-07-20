package com.asiainfo.dmcweb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asiainfo.dmcweb.entity.SysBtn;
import com.asiainfo.dmcweb.entity.SysMenu;
import com.asiainfo.dmcweb.util.URLUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public interface IService {

	static final  String LOGIN_CHECK = "login/login_check";
	static final  String GET_ROOT_MENU = "main/getRootMenu";
	static final  String GET_CHILD_MENU = "main/getChildMenu";
	static final  String GET_SYS_BTN= "main/getSysBtn";
	static final  String GET_ROLE_LIST_BY_PAGINATION="main/getRoleListByPagination";
	static final  String GET_USER_LIST_BY_PAGINATION="main/getUserListByPagination";
	static final  String GET_AUTH_MENU_BY_ID_LIST="main/getChildMenuByIdList";
	static final  String GET_AUTH_BTN_BY_ID_LIST="main/getAuthBtnByIdList";
	static final String SERVER_URL = URLUtils.get("serverUrl");
	static final  String ADD_USER="main/addUser";
	static final  String SEARCH_USER_BY_EMAIL="main/getUserByEmail";
	static final  String GET_ROLE_LIST="main/getRoleList";
	static final  String EDIT_USER_INFO_BY_EMAIL="main/updateUserInfoByEmail";
	static final  String LOAD_AUTH_LIST="main/getAuthList";
	
	public  JSONObject loginCheck(JSONObject jsonData) throws Exception;
	
	public  List<SysMenu> getRootMenu() throws Exception;
	
	public  List<SysMenu> getChildMenu() throws Exception;
	
	public  List<SysBtn> getSysBtn()throws Exception;
	
	public JSONObject getRoleListByPagination(JSONObject json)throws Exception;
	
	public JSONObject getUserListByPagination(JSONObject json)throws Exception;
	
	public JSONObject getChildMenuById(JSONObject menuId)throws Exception;
	
	public JSONObject getAuthBtnById(JSONObject btnId)throws Exception;

	public JSONObject addUser(JSONObject json)throws Exception;

	public JSONObject searchUserByEmail(JSONObject newJson)throws Exception;

	public JSONArray getRoleList()throws Exception;

	public JSONObject editUser(JSONObject newJson)throws Exception;

	public JSONObject loadAuthList()throws Exception;

}
