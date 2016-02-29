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
<h2>Your request is getting the file with event set id 
<font color="grey">${baseid}</font> converted from <font color="grey">AUD</font>
to <font color="grey">${target}</font> </h2>
<h2>Response of the service for your input is: </h2>
	<h3>Result Event Set ID: <font color="grey">${eventsetid}</font></h3>
	
<form action="convertinput.jsp">
<input type="submit" value="Try again"/>
</form><br>


</center>
<%@ include file="footer.jsp"%>
</body>
</html>