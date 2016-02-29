<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Import DataFile Service Demo</title>

<meta charset="utf-8">
<title>jQuery UI Datepicker - Default functionality</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
$(function() {
	$( "#datepicker1" ).datepicker();
});

$(function() {
	$( "#datepicker2" ).datepicker();
});
	
</script>
</head>
<body>
<%@ include file="header.jsp"%>
<center>
<h1><font color="grey">Import DataFile Service Demo</font></h1>
<h2>Input the information of the data file which you want to import.</h2><br><br>

<form action="MarketDataDemoApp">
<b>Security Code:&nbsp;&nbsp;&nbsp;&nbsp;</b>  <input type="text" name="sec" placeholder="WSSS" required><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MM/DD/YYYY<br>
<b>Start Date:&nbsp;&nbsp;&nbsp;&nbsp;</b>  <input type="text" id="datepicker1" name="startdate" placeholder="MM/DD/YYYY" required><br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MM/DD/YYYY<br>
<b>End Date:&nbsp;&nbsp;&nbsp;&nbsp;</b>  <input type="text" id="datepicker2" name="enddate" placeholder="MM/DD/YYYY" required><br><br><br>
<b>Data Source URL:&nbsp;&nbsp;&nbsp;&nbsp;</b>  <input type="text" name="url" placeholder="http://..../MarketData03.csv" required><br><br><br>

  <input type="hidden" name="action" value="import">
  <input type="submit" value="Submit Request to Service">
</form> 


</center>
<%@ include file="footer.jsp"%>
</body>
</html>