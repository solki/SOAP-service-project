<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Import DataFile Service Demo</title>
</head>
<body>
<%@ include file="header.jsp"%>
<center>
<h1><font color="grey">Import DataFile Service Demo</font></h1>
<h2>Your request is to import the data file from the following URL:</h2>
<font color="grey">${url}</font><br> 
<h2>The original data source will be filtered based on time from
<font color="grey">${startdate}</font> to 
<font color="grey">${enddate}</font></h2>
<h2>with security code as <font color="grey">${sec}</font></h2><br>


<h2>Response of the service for your input is: </h2>
	<h3>The result data file's event set ID is: <font color="grey">${eventsetid}</font></h3><br>
<form action="importinput.jsp">
<input type="submit" value="Try again"/>
</form><br>

</center>
<%@ include file="footer.jsp"%>
</body>
</html>