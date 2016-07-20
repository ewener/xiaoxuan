package com.asiainfo.dmcweb.util;


import javax.servlet.http.HttpServletRequest;

import com.asiainfo.dmcweb.constant.Constants;
import com.asiainfo.dmcweb.entity.User;


/**
 * 
 * Cookie 工具类
 *
 */
public final class SessionUtils {
	
	
	/**
	  * 设置session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static void setAttr(HttpServletRequest request,String key,Object value){
		 request.getSession(true).setAttribute(key, value);
	 }
	 
	 
	 /**
	  * 获取session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static Object getAttr(HttpServletRequest request,String key){
		 return request.getSession(true).getAttribute(key);
	 }
	 
	 /**
	  * 删除Session值
	  * @param request
	  * @param key
	  */
	 public static void removeAttr(HttpServletRequest request,String key){
		 request.getSession(true).removeAttribute(key);
	 }
	 
	 /**
	  * 设置用户信息 到session
	  * @param request
	  * @param user
	  */
	 public static void setUser(HttpServletRequest request,User user){
		 request.getSession(true).setAttribute(Constants.SESSION_USER, user);
	 }
	 
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static User getUser(HttpServletRequest request){
		return (User)request.getSession(true).getAttribute(Constants.SESSION_USER);
	 }
	 
	 
	 /**
	  * 从session 移除user
	  * @param request
	  * @return SysUser
	  */
	 public static void removeUser(HttpServletRequest request){
		removeAttr(request, Constants.SESSION_USER);
		removeAttr(request,Constants.LOGIN_NAME);
	 }
	 
	 
	 /**
	  * 设置验证码 到session
	  * @param request
	  * @param user
	  */
	 public static void setValidateCode(HttpServletRequest request,String validateCode){
		 request.getSession(true).setAttribute(Constants.SESSION_VALIDATECODE, validateCode);
	 }
	 
	 
	 /**
	  * 从session中获取验证码
	  * @param request
	  * @return SysUser
	  */
	 public static String getValidateCode(HttpServletRequest request){
		return (String)request.getSession(true).getAttribute(Constants.SESSION_VALIDATECODE);
	 }
	 
	 
	 /**
	  * 从session中获删除验证码
	  * @param request
	  * @return SysUser
	  */
	 public static void removeValidateCode(HttpServletRequest request){
		removeAttr(request, Constants.SESSION_VALIDATECODE);
	 }

	 /**
	  * 设置登录成功标示
	  * @param request
	  * @param iS_LOGIN
	  */
	public static void setLoginFlag(HttpServletRequest request, boolean iS_LOGIN) {
		request.getSession(true).setAttribute(Constants.LOGIN_NAME, iS_LOGIN);
	}
	 
	
}