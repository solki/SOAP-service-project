<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Currency Convert Service Demo</title>
</head>
<body>
<%@ include file="header.jsp"%>
<center>
<h1><font color="grey">Currency Convert Service Demo</font></h1>
<h2>Input the base and target currency code (AUD, USD, CNY etc.) and get 
the result currency exchange rate.</h2><br><br>

<form action="MarketDataDemoApp">
<b>Base Currency Code:&nbsp;&nbsp;&nbsp;&nbsp;</b>  <input type="text" name="base" placeholder="AUD" required><br><br>
<b>Target Currency Code:&nbsp;&nbsp;</b> <input type="text" name="target" placeholder="USD" required><br><br><br>
  <input type="hidden" name="action" value="yahoo">
  <input type="submit" value="Submit Request to Service">
</form> 


</center>
<%@ include file="footer.jsp"%>
</body>
</html>