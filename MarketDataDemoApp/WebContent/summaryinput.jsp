<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Summary DataFile Service Demo</title>
</head>
<body>
<%@ include file="header.jsp"%>
<center>
<h1><font color="grey">Summary DataFile Service Demo</font></h1>
<h2>Input the event set id of the data file which you want to see
summary information.</h2><br><br>

<form action="MarketDataDemoApp">
<b>Event Set ID:&nbsp;&nbsp;&nbsp;&nbsp;</b>  <input type="text" name="eventsetid" placeholder="abc-abc-123" required><br><br><br>
  <input type="hidden" name="action" value="summary">
  <input type="submit" value="Submit Request to Service">
</form> 



</center>
<%@ include file="footer.jsp"%>
</body>
</html>