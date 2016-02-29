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
<h2>Your request is getting the summary information of the file 
with event set id <font color="grey">${eventsetid}</font></h2>
<h2>Response of the service for your input is: </h2>
	<h3>Summary information of file with EventSetId <font color="grey">${eventsetid}</font></h3>
	<h3>Event Set ID: <font color="grey">${eventsetid}</font></h3>
	<h3>Security Code: <font color="grey">${sec}</font></h3>
	<h3>Start Date: <font color="grey">${startdate}</font></h3>
	<h3>End Date: <font color="grey">${enddate}</font></h3>
	<h3>Currency Code: <font color="grey">${curcode}</font></h3>
	<h3>Number of records: <font color="grey">${lines}</font></h3>
<form action="summaryinput.jsp">
<input type="submit" value="Try again"/>
</form><br>

</center>
<%@ include file="footer.jsp"%>
</body>
</html>