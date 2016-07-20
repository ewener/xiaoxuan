package com.asiainfo.dmcweb.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import com.asiainfo.dmcweb.constant.Constants;

import net.sf.json.JSONObject;

public class HttpUtil {
	
	public static JSONObject post(String url, String jsonData) {
		StringBuffer sb = new StringBuffer();
		HttpClient httpClient = new HttpClient();
		// 设置cookie策略
		System.setProperty("apache.commons.httpclient.cookiespec", "COMPATIBILITY");
		PostMethod postMethod = new PostMethod(url);
		JSONObject result = new JSONObject();
		try {
			InputStream in = new ByteArrayInputStream(jsonData.getBytes("UTF-8"));
			postMethod.setRequestBody(in);
			HttpClientParams params = new HttpClientParams();
			params.setConnectionManagerTimeout(10000L);
			httpClient.setParams(params);
			// 设置编码,解决中文乱码问题
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			// 方法调用
			int statusCode  = httpClient.executeMethod(postMethod);
			if(statusCode==404){//无效请求
				throw new Exception("无效请求,请检查路径是否合法！");
			}
			// 获取二进制的byte流
			byte[] b = postMethod.getResponseBody();
			String str = new String(b, "UTF-8");
			result.put(Constants.SUCCESS, true);
			result.put(Constants.DATA, str);
			System.out.println("######statusCode："+statusCode+"  resultJson:"+result+"######");
			return result;
		}catch (Exception e) {
			result.put(Constants.SUCCESS, false);
			result.put(Constants.DATA, e.getMessage());
		} finally {
			postMethod.releaseConnection();// 释放连接
		}
		return result;
	}
	
	public static void main(String[] args) {
//		String result = HttpUtil.post(URLUtils.get("serverUrl")+"login/login_check", "{'email':'daixuanwu@qq.com','pwd':'1234'}");
	}
}
