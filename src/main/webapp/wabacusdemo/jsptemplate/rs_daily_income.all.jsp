<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="wabacus" prefix="wx"%>
<%@page import="com.wabacus.system.ReportRequest"%>
  <%
  ReportRequest rrequest=(ReportRequest)request.getAttribute("WX_REPORTREQUEST");
  String province="".equals(rrequest.getStringAttribute("province",""))?"全国省份":rrequest.getStringAttribute("province","");
  String city="".equals(rrequest.getStringAttribute("city",""))?"全国市区":rrequest.getStringAttribute("city","");
  String channel="".equals(rrequest.getStringAttribute("channel",""))?"全部渠道":rrequest.getStringAttribute("channel","");
  String employee="".equals(rrequest.getStringAttribute("employee",""))?"全部员工":rrequest.getStringAttribute("employee","");
  String begindate=rrequest.getStringAttribute("begindate","");
  String enddate=rrequest.getStringAttribute("enddate","");
  %>
<html>
<head>
<script language="javascript">
</script>
</head>
<body>

<wx:title></wx:title>
<hr>
<div align="left">
受理省份:<%= province%>   受理市区:<%= city%>   受理渠道:<%=channel %><br>
受理员工:<%= employee%>   开始时间:<%=begindate %>   结束时间:<%=enddate %>
</div>
<hr>
<wx:data></wx:data>
<hr>

</body>
</html>