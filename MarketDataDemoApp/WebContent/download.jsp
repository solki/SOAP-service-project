<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Download File Service Demo</title>
</head>
<body>
<%@ include file="header.jsp"%>
<center>
<h1><font color="grey">Download File Service Demo</font></h1>
<h2>Your request is to download file associated with event set id:</h2>
<h2><font color="grey">${eventsetid}</font> in format <font color="grey">${format}</font></h2>

<h2>Response of the service</h2>
	<h3>The download link for your request file: </h3>
	<h3><a href="${url}">${url}</a></h3>
<form action="downloadinput.jsp">
<input type="submit" value="Try again"/>
</form><br>

</center>
<%@ include file="footer.jsp"%>
</body>
</html>