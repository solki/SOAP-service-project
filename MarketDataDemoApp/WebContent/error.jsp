<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<%@ include file="header.jsp"%>
<center>
<h2>Service response with error, check the <a href="errorcode.jsp">error code page</a> for more information.</h2>
<h3>Fault Message: <font color="grey">${faultmsg}</font></h3>
<h3>Error Code: <font color="grey">${errcode}</font></h3>
<h3>Error Message: <font color="grey">${errmsg}</font></h3>

</center>
<%@ include file="footer.jsp"%>
</body>
</html>