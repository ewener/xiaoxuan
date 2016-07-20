package com.asiainfo.dmcweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asiainfo.dmcweb.base.BaseController;
import com.asiainfo.dmcweb.entity.User;
import com.asiainfo.dmcweb.service.IService;
import com.asiainfo.dmcweb.util.PageUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户、权限管理控制器
 * @author Administrator
 *
 */
@Controller("manger")
public class ManagerController extends BaseController{

	@Autowired
	private IService service;
	
	@ResponseBody
	@RequestMapping("/displayRole") 
	public JSONObject  displayRole(
			@RequestParam(required = false, defaultValue = "1") Integer page,
			 @RequestParam(required = false, defaultValue = "10") Integer rows)throws Exception{
			JSONObject newJson = new JSONObject();
			PageUtil p = PageUtil.getPage(page, rows);
			newJson.put("start", p.getStart());
			newJson.put("end", p.getEnd());
			return service.getRoleListByPagination(newJson);
	}
	
	@ResponseBody
	@RequestMapping("/displayUser") 
	public JSONObject  displayUser(
			 @RequestParam(required = false, defaultValue = "1") Integer page,
			 @RequestParam(required = false, defaultValue = "10") Integer rows)throws Exception{
			JSONObject newJson = new JSONObject();
			PageUtil p = PageUtil.getPage(page, rows);
			newJson.put("start", p.getStart());
			newJson.put("end", p.getEnd());
			System.out.println("##########"+newJson);
			return service.getUserListByPagination(newJson);
	}
	
	@ResponseBody
	@RequestMapping("/searchUserByEmail") 
	public JSONObject  searchUserByEmail(
			 @RequestParam String email)throws Exception{
			JSONObject newJson = new JSONObject();
			newJson.put("email", email);
			System.out.println("##########"+newJson);
			return service.searchUserByEmail(newJson);
	}
	
	@ResponseBody
	@RequestMapping("/displayAuthMenuList") 
	public JSONObject  displayMenu(
			@RequestParam(required = false, defaultValue = "1") Integer page,
			 @RequestParam(required = false, defaultValue = "10") Integer rows,
			 @RequestParam(required = false) String  menuList,
			 @RequestParam(required = false) String  role)throws Exception{
			JSONObject newJson = new JSONObject();
			PageUtil p = PageUtil.getPage(page, rows);
			newJson.put("start", p.getStart());
			newJson.put("end", p.getEnd());
			newJson.put("menuList", menuList);
			newJson.put("role", role);
			System.out.println("#########new json:"+newJson);
		return service.getChildMenuById(newJson); 
	}
	
	@ResponseBody
	@RequestMapping("/displayAuthBtnList") 
	public JSONObject  displayAuthBtn(
			 @RequestParam(required = false, defaultValue = "1") Integer page,
			 @RequestParam(required = false, defaultValue = "10") Integer rows,
			 @RequestParam(required = false) String  btnList,
			 @RequestParam(required = false) String  role)throws Exception{
			JSONObject newJson = new JSONObject();
			PageUtil p = PageUtil.getPage(page, rows);
			newJson.put("start", p.getStart());
			newJson.put("end", p.getEnd());
			newJson.put("btnList", btnList);
			newJson.put("role", role);
			return service.getAuthBtnById(newJson); 
	}
	
	@ResponseBody
	@RequestMapping("/addUser") 
	public JSONObject addUser(@RequestBody User user)throws Exception{
		System.out.println("#######$$$$$$$$$:"+user );
		return service.addUser(JSONObject.fromObject(user));
	}
	
	@ResponseBody
	@RequestMapping("/roleInCombox") 
	public JSONArray getRoleList()throws Exception{
		return service.getRoleList();
	}
	
	@ResponseBody
	@RequestMapping("/editUser") 
	public JSONObject editUser(@RequestBody User user)throws Exception{
		return service.editUser(JSONObject.fromObject(user));
	}
	
	@ResponseBody
	@RequestMapping("/loadAuthList") 
	public JSONObject loadAuthList()throws Exception{
		return service.loadAuthList();
	}
}
