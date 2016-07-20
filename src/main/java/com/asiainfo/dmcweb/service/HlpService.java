package com.asiainfo.dmcweb.service;

import com.asiainfo.dmcweb.entity.SysBtn;
import com.asiainfo.dmcweb.entity.SysMenu;
import com.asiainfo.dmcweb.entity.User;
import com.asiainfo.dmcweb.util.HttpUtil;

import net.sf.json.JSONObject;

public abstract  class HlpService implements IService{

		protected JSONObject httpPost(String url,String jsonData)
		{
			return HttpUtil.post(SERVER_URL+url, jsonData);
		}
		
		protected User parseJsonToUser(JSONObject userJson) {
			return (User) JSONObject.toBean(userJson, User.class);
		}
		
		protected SysMenu parseJsonToSysMenu(JSONObject j) {
			return (SysMenu) JSONObject.toBean(j, SysMenu.class);
		}
		
		protected SysBtn parseJsonToSysBtn(JSONObject j) {
			return (SysBtn) JSONObject.toBean(j, SysBtn.class);
		}
}
