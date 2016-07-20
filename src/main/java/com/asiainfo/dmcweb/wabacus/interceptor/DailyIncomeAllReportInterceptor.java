package com.asiainfo.dmcweb.wabacus.interceptor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.wabacus.config.component.application.report.ReportBean;
import com.wabacus.system.ReportRequest;
import com.wabacus.system.WabacusResponse;
import com.wabacus.system.intercept.AbsInterceptorDefaultAdapter;
import com.wabacus.system.intercept.ReportDataBean;
import com.wabacus.util.Consts;

public class DailyIncomeAllReportInterceptor extends AbsInterceptorDefaultAdapter {
	
	@Override
	public void beforeDisplayReportData(ReportRequest rrequest, ReportBean rbean, ReportDataBean reportDataBean) {
		
		  String begindate=rrequest.getStringAttribute("begindate","");
		   String enddate=rrequest.getStringAttribute("enddate","");
		    String province=rrequest.getStringAttribute("province","");
		    String city=rrequest.getStringAttribute("city","");
 			String channel=rrequest.getStringAttribute("channel","");
		    String employee=rrequest.getStringAttribute("employee","");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化对象
			
			Date begindate_sdf = null;
			Date enddate_sdf = null;
			try
			{
				begindate_sdf = sdf.parse(begindate);
				enddate_sdf = sdf.parse(enddate);
			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
				System.out.println("日期解析错误！！！");
				return;
			}
		
			Calendar calendar = Calendar.getInstance();//日历对象
			calendar.setTime(enddate_sdf);//设置当前日期
			calendar.add(Calendar.MONTH, -1);//月份减一
			
			WabacusResponse wr =  rrequest.getWResponse();
		  if(province.equals("-9999"))
		  {
		  		 wr.getMessageCollector().warn("受理省份不能为空！","{width:160,height:100}",false);
		  		 wr.terminateResponse(Consts.STATECODE_FAILED);
		  }
		  	  if(city.equals("-9999"))
		  {
		  		 wr.getMessageCollector().warn("受理城市不能为空！","{width:160,height:100}",false);
		  		 wr.terminateResponse(Consts.STATECODE_FAILED);
		  }
		  	  if(channel.equals("-9999"))
		  {
		  		 wr.getMessageCollector().warn("受理渠道不能为空！","{width:160,height:100}",false);
		  		 wr.terminateResponse(Consts.STATECODE_FAILED);
		  }
		  	  if(employee.equals("-9999"))
		  {
		  		 wr.getMessageCollector().warn("受理员工不能为空！","{width:160,height:100}",false);
		  		 wr.terminateResponse(Consts.STATECODE_FAILED);
		  }
		  if(calendar.getTime().getTime()>begindate_sdf.getTime())
		  {
		   wr.getMessageCollector().warn("结束日期不能大于开始日期一个月以上","{width:300,height:200}",false);
		  	wr.terminateResponse(Consts.STATECODE_FAILED);//中断后续操作，返回客户端，需要带参数，不带参数的页面出错
		  }
		  if(begindate_sdf.getTime()>enddate_sdf.getTime())
		  {
		   wr.getMessageCollector().warn("开始日期不能大于结束日期","{width:300,height:200}",false);
		  	wr.terminateResponse(Consts.STATECODE_FAILED);//
		  }
		  System.out.println("#######执行beforeDisplayReportData！");
	}
	
	  public void doEnd(ReportRequest rrequest,ReportBean rbean)
	    {
		  System.out.println("执行end");
		  
	    }
}
