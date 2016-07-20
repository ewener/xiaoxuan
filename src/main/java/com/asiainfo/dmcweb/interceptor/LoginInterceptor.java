package com.asiainfo.dmcweb.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.dmcweb.constant.Constants;
import com.asiainfo.dmcweb.util.SessionUtils;
import com.asiainfo.dmcweb.util.URLUtils;


public class LoginInterceptor implements HandlerInterceptor {

	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		Object islogin = SessionUtils.getAttr(request, Constants.LOGIN_NAME);
		if(islogin!=null)
		{
			if((boolean)islogin==true)
			{
				response.sendRedirect("/dmcWeb/main");
				return false;
			}
		}
		else
		{
			response.sendRedirect("/dmcWeb/login");
			return false;
		}
		return true;
	}




}

