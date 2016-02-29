<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
<%@ include file="header.jsp"%>
<center>
<h2><font color="grey">ImportDownloadService</font></h2>
The first Web service is called ImportDownloadService 
and it consists of the following two operations.<br>
First one reads a Market Data file that exists outside
of the system, filters the content according to <br>
the given parameters, and produces another Market Data file 
to store it inside the system. <br>
Second one let users download the files over the Internet<br><br>
<form action="importinput.jsp">
<b>importMarketData</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Try it"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="http://localhost:8080/ImportDownloadService/Service1?wsdl">ImportDownloadService.wsdl</a>
</form><br>
<form action="downloadinput.jsp">
<b>downloadFile</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Try it"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="http://localhost:8080/ImportDownloadService/Service1?wsdl">ImportDownloadService.wsdl</a>
</form><br><br>

<h2><font color="grey">SOAPCurrencyConvertService</font></h2>
This service 'wrap' an existing Web-based currency convert service 
and create a SOAP-based service interface for it<br><br>
<form action="yahooinput.jsp">
<b>yahooExchangeRate</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Try it"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="http://localhost:8080/SOAPCurrencyConvertService/SOAPCurrencyConvertService?wsdl">SOAPCurrencyConvertService.wsdl</a>
</form><br>
    
<h2><font color="grey">MarketDataUtilService</font></h2>
MarketDataUtilService implements two operations. One for 
converting all price information present in the file to another currency.<br>
Another one can get a quick summary information about the content 
of a market data file.<br><br>
<form action="convertinput.jsp">
<b>convertMarketData</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Try it"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="http://localhost:8080/MarketDataUtilService/MarketDataUtilService?wsdl">MarketDataUtilService.wsdl</a>
</form><br>
<form action="summaryinput.jsp">
<b>summaryMarketData</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Try it"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="http://localhost:8080/MarketDataUtilService/MarketDataUtilService?wsdl">MarketDataUtilService.wsdl</a>
</form><br>





</center>
<%@ include file="footer.jsp"%>
</body>
</html>