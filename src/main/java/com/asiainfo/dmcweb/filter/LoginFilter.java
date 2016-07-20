package com.asiainfo.dmcweb.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import com.asiainfo.dmcweb.constant.Constants;
import com.asiainfo.dmcweb.util.URLUtils;

public class LoginFilter implements Filter {

	/**
	 * 存储已经受权不需要过滤的请求
	 */
	private static Set<String> accreditUrls = new HashSet<String>();
	/**
	 * 不需要过滤的后缀
	 */
	private static Set<String> accreditSuffix = new HashSet<String>();
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		String reqUrl = request.getRequestURI();
		String contextPath = request.getContextPath();
		int index = reqUrl.indexOf(contextPath);
		String page = reqUrl.substring(index + contextPath.length());
		System.out.println("请求url："+reqUrl+"  contextPath:"+contextPath);
		
//		 退出系统
		 Object logouFlag = session.getAttribute(Constants.LOGOUT_FLAG);
		if (logouFlag!=null&&(boolean)logouFlag) {
			this.clear(request, response);
			return;
		}

		String postfix = "";
		int postfixIndex = reqUrl.lastIndexOf(".");
		if (postfixIndex > 0) {
			postfix = reqUrl.substring(postfixIndex);
		}

		System.out.println(reqUrl);
		System.out.println(accreditSuffix.toString()+" "+accreditUrls.toString());
		// 放行访问,如前端后台掉后台，前端jsp加载css，js等脚本,登陆界面需要放行
		if (accreditUrls.contains(page) || accreditSuffix.contains(postfix)) {
			System.out.println("过滤请求："+postfix);
			chain.doFilter(req, resp);
			return;
		}
		// 对不放行的访问，首先判断其是否已经登陆过，有可能这是登陆过后的后续访问操作，也有可能是未经过登陆，直接地址栏输入URL的情况
		if (session.getAttribute("IS_LOGIN")!=null
				&& (Boolean) session.getAttribute("IS_LOGIN")) {
			chain.doFilter(req, resp);
			return;
		} else {
			// 跳转到登录界面
			System.out.println("跳转登录页面");
			response.sendRedirect(Constants.LOGIN_PAGE);
		}
	}

	/**
	 * 当系统退出时调用
	 * 
	 * @param context
	 * @return
	 */
	public void clear(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// 清除各web系统的会话
		req.getSession().invalidate();
		resp.sendRedirect(Constants.LOGIN_PAGE);
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		if (StringUtils.isNotEmpty(URLUtils.get("ACCEDIT_URLS"))) {
			String urls[] = URLUtils.get("ACCEDIT_URLS").split(",");
			for (String url : urls) {
				if (StringUtils.isNotEmpty(url)) {
					accreditUrls.add(url);
				}
			}
		}

		if (StringUtils.isNotEmpty(URLUtils.get("ACCREDIT_SUFFIX"))) {
			String suffixs[] = URLUtils.get("ACCREDIT_SUFFIX").split(",");
			for (String suffix : suffixs) {
				if (StringUtils.isNotEmpty(suffix)) {
					accreditSuffix.add(suffix);
				}
			}
		}
	}

}
