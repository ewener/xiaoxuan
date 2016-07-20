package com.asiainfo.dmcweb.wabacus.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.wabacus.system.ReportRequest;
import com.wabacus.system.intercept.AbsPageInterceptor;

public class GlobalPageInterceptor extends AbsPageInterceptor {
	
	@Override
	 public void doStart(ReportRequest rrequest)
	    {
		String date = new SimpleDateFormat("YYYY-MM-DD").format(new Date());
		rrequest.getRequest().getSession().setAttribute("currentDate", date);
		
	   	String province=rrequest.getStringAttribute("province","");
  	    String city=rrequest.getStringAttribute("city","");
  	    String channel=rrequest.getStringAttribute("channel","");
  	    String employee=rrequest.getStringAttribute("employee","");
  	    
	
		  if(rrequest.isLoadedByAjax())
		  {
			  //后续操作切换，不延迟
			  System.out.println("不进行延迟操作！");
			  rrequest.setAttribute("report_lazydisplaydata","false");
		  }else
		  {
			  //第一次访问此页面，延迟操作
			  if(province.equals("")&&city.equals("")&&channel.equals("")&&employee.equals(""))
			  {//如果四个条件框都没有输入值，则延迟加载
				  System.out.println("第一次进入页面，延迟操作！");
				  rrequest.setAttribute("report_lazydisplaydata","true");
			  }
		  } 

	};
	
    public void doEnd(ReportRequest rrequest)
    {
    	System.out.println("执行页面拦截器end");
    }
}
