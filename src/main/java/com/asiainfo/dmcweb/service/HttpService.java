package com.asiainfo.dmcweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.asiainfo.dmcweb.constant.Constants;
import com.asiainfo.dmcweb.entity.SysBtn;
import com.asiainfo.dmcweb.entity.SysMenu;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class HttpService  extends HlpService{

	public  JSONObject loginCheck(JSONObject jsonData) throws Exception {
		JSONObject result = httpPost(LOGIN_CHECK, jsonData.toString());
		if ((boolean) result.get(Constants.SUCCESS) == true) {
			JSONObject fromObject = JSONObject.fromObject(result.get(Constants.DATA));
			if (!fromObject.isEmpty()) {
				JSONObject userJson = JSONObject.fromObject(fromObject.get("userInfo"));
				result.put("user", new JSONObject().fromObject(parseJsonToUser(userJson)));
			}
		}
		return result;
	}

	public  List<SysMenu> getRootMenu() throws Exception {
		List<SysMenu> sysMenuList = null;
		JSONObject result = httpPost(GET_ROOT_MENU, "");
		if ((boolean) result.get(Constants.SUCCESS) == true) {
			JSONObject jsonObject = JSONObject.fromObject(result.get(Constants.DATA));
			if (jsonObject.isEmpty())
				return sysMenuList;
			JSONArray jsonArr = JSONArray.fromObject(jsonObject.get("rootMenu"));
			sysMenuList = new ArrayList<>();
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject j = jsonArr.getJSONObject(i);
				sysMenuList.add(parseJsonToSysMenu(j));
			}
		}
		return sysMenuList;
	}

	public  List<SysMenu> getChildMenu() throws Exception {
		List<SysMenu> sysMenuList = null;
		JSONObject result = httpPost(GET_CHILD_MENU, "");
		if ((boolean) result.get(Constants.SUCCESS) == true) {
			JSONObject jsonObject = JSONObject.fromObject(result.get(Constants.DATA));
			if (jsonObject.isEmpty())return sysMenuList;
			JSONArray jsonArr = JSONArray.fromObject(jsonObject.get("childMenu"));
			if (jsonArr.isEmpty())return sysMenuList;
			sysMenuList = new ArrayList<>();
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject j = jsonArr.getJSONObject(i);
				sysMenuList.add(parseJsonToSysMenu(j));
			}
		}
		return sysMenuList;
	}
	
	public  List<SysBtn> getSysBtn()throws Exception{
		
		List<SysBtn> sysBtnList = null;
		JSONObject result = httpPost(GET_SYS_BTN, "");
		if ((boolean) result.get(Constants.SUCCESS) == true) {
			JSONObject jsonObject = JSONObject.fromObject(result.get(Constants.DATA));
			if (jsonObject.isEmpty())return sysBtnList;
			JSONArray jsonArr = JSONArray.fromObject(jsonObject.get("sysBtn"));
			if (jsonArr.isEmpty())return sysBtnList;
			sysBtnList = new ArrayList<>();
			for (int i = 0; i < jsonArr.size(); i++) {
				JSONObject j = jsonArr.getJSONObject(i);
				sysBtnList.add(parseJsonToSysBtn(j));
			}
		}
		return sysBtnList;
		
	}

	public JSONObject getRoleListByPagination(JSONObject json) throws Exception {
		JSONObject result = httpPost(GET_ROLE_LIST_BY_PAGINATION, json.toString());
		JSONObject newjson = new JSONObject();
		if ((boolean) result.get(Constants.SUCCESS) == true) {
			JSONObject jsonObject = JSONObject.fromObject(result.get(Constants.DATA));
			if (jsonObject.isEmpty())return newjson ;
			JSONArray jonArr = JSONArray.fromObject(jsonObject.get("roleList"));
			newjson.put("rows", jonArr);
			newjson.put("total", jsonObject.get("roleCount"));
		}
		return newjson;
	}

	public JSONObject getUserListByPagination(JSONObject json) throws Exception {
		JSONObject result = httpPost(GET_USER_LIST_BY_PAGINATION, json.toString());
		JSONObject newjson = new JSONObject();
		if ((boolean) result.get(Constants.SUCCESS) == true) {
			JSONObject jsonObject = JSONObject.fromObject(result.get(Constants.DATA));
			if (jsonObject.isEmpty())return newjson ;
			JSONArray jonArr = JSONArray.fromObject(jsonObject.get("userList"));
			newjson.put("rows", jonArr);
			newjson.put("total", jsonObject.get("userCount"));
		}
		System.out.println("newjson:"+newjson.toString());
		return newjson;
	}

	public JSONObject getChildMenuById(JSONObject json) throws Exception {
		JSONObject result = httpPost(GET_AUTH_MENU_BY_ID_LIST, json.toString());
		JSONObject newjson = new JSONObject();
		if ((boolean) result.get(Constants.SUCCESS) == true) {
			JSONObject jsonObject = JSONObject.fromObject(result.get(Constants.DATA));
			if (jsonObject.isEmpty())return newjson ;
			JSONArray jonArr = JSONArray.fromObject(jsonObject.get("authChildMenuList"));
			newjson.put("rows", jonArr);
			newjson.put("total", jsonObject.get("menuCount"));
		}
		return newjson;
	}

	public JSONObject getAuthBtnById(JSONObject json) throws Exception {
		JSONObject result = httpPost(GET_AUTH_BTN_BY_ID_LIST, json.toString());
		JSONObject newjson = new JSONObject();
		if ((boolean) result.get(Constants.SUCCESS) == true) {
			JSONObject jsonObject = JSONObject.fromObject(result.get(Constants.DATA));
			if (jsonObject.isEmpty())return newjson ;
			JSONArray jonArr = JSONArray.fromObject(jsonObject.get("authBtnList"));
			newjson.put("rows", jonArr);
			newjson.put("total", jsonObject.get("btnCount"));
		}
		return newjson;
	}

	public JSONObject addUser(JSONObject json) throws Exception {
		 JSONObject result = httpPost(ADD_USER, json.toString());
		 JSONObject jsonObject = JSONObject.fromObject(result.get(Constants.DATA));
		return jsonObject;
	}

	@Override
	public JSONObject searchUserByEmail(JSONObject json) throws Exception {
		JSONObject result = httpPost(SEARCH_USER_BY_EMAIL, json.toString());
		JSONObject newjson = new JSONObject();
		if ((boolean) result.get(Constants.SUCCESS) == true) {
			JSONObject jsonObject = JSONObject.fromObject(result.get(Constants.DATA));
			if (jsonObject.isEmpty())return newjson ;
			JSONArray jonArr = JSONArray.fromObject(jsonObject.get("userInfo"));
			newjson.put("rows", jonArr);
			newjson.put("total", jsonObject.get("userCount"));
		}
		return newjson;
	}

	public JSONArray getRoleList() throws Exception {
		JSONObject result = httpPost(GET_ROLE_LIST, "");
		JSONArray jonArr = new JSONArray();
		if ((boolean) result.get(Constants.SUCCESS) == true) {
			JSONObject jsonObject = JSONObject.fromObject(result.get(Constants.DATA));
			if (jsonObject.isEmpty())return jonArr ;
			jonArr = JSONArray.fromObject(jsonObject.get("roleList"));
		}
		return jonArr;
	}

	public JSONObject editUser(JSONObject newJson) throws Exception {
		JSONObject result = httpPost(EDIT_USER_INFO_BY_EMAIL, newJson.toString());
		 JSONObject jsonObject = JSONObject.fromObject(result.get(Constants.DATA));
		return jsonObject;
	}

	@Override
	public JSONObject loadAuthList() throws Exception {
		List<SysMenu> childMenu = getChildMenu();
		List<SysBtn> sysBtn = getSysBtn();
		
		return null;
	}

}
