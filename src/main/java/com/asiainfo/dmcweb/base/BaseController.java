package com.asiainfo.dmcweb.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.dmcweb.constant.Constants;
import com.asiainfo.dmcweb.editor.MyEditor;
import com.asiainfo.dmcweb.util.HtmlUtil;

import net.sf.json.JSONObject;


public class BaseController{
	
	/**
	 * 
	 * @param binder
	 */
   @InitBinder  
   protected void initBinder(WebDataBinder binder) {  
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));  
		 binder.registerCustomEditor(int.class,new MyEditor()); 
   }  
	 
	 /**
	  * 获取IP地址
	  * @param request
	  * @return
	  */
	 public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	 
	
	public ModelAndView forword(String viewName,Map context){
		return new ModelAndView(viewName,context); 
	}
	
	public ModelAndView error(String errMsg){
		return new ModelAndView("error"); 
	}
	
	/**
	 *
	 * 提示成功信息
	 *
	 * @param message
	 *
	 */
	public JSONObject sendSuccessMessage(HttpServletResponse response,  String message) {
		JSONObject resutlSucessData = new JSONObject();
		resutlSucessData.put(Constants.SUCCESS, true);
		resutlSucessData.put(Constants.MSG, message);
		return resutlSucessData;
	}

	/**
	 *
	 * 提示失败信息
	 *
	 * @param message
	 *
	 */
	public JSONObject sendFailureMessage(HttpServletResponse response,String message) {
		JSONObject resutlFailureData = new JSONObject();
		resutlFailureData.put(Constants.SUCCESS, false);
		resutlFailureData.put(Constants.MSG, message);
		return resutlFailureData;
	}	
}
