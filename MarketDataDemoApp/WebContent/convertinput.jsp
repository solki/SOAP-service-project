<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DataFile Convert Service Demo</title>
</head>
<body>
<%@ include file="header.jsp"%>
<center>
<h1><font color="grey">DataFile Convert Service Demo</font></h1>
<h2>Input the event set id to be converted and the 
target currency code.</h2><br><br>

<form action="MarketDataDemoApp">
<b>Event Set ID:&nbsp;&nbsp;&nbsp;&nbsp;</b>  <input type="text" name="eventsetid" placeholder="abc-abc-123" required><br><br>
<b>Target Currency Code:&nbsp;&nbsp;</b> <input type="text" name="target" placeholder="USD" required><br><br><br>
  <input type="hidden" name="action" value="convert">
  <input type="submit" value="Submit Request to Service">
</form> 


</center>
<%@ include file="footer.jsp"%>
</body>
</html>