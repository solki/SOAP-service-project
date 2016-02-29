<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of Error Codes</title>
</head>
<body>
<%@ include file="header.jsp"%>
<center>
<h1>List of Error Codes</h1>

<h2><font color="grey">ImportDownloadService</font></h2>
<table border="1" style="width:60%" >
  <tr>
    <th>Error Code</th>
    <th>Error Description</th>
  </tr>
  <tr>
    <td>InvalidEventSetId</td>
    <td>This indicates the input event set id is not valid.
    This could be raised in downloadFile operations
    which requires a valid event set id as input.</td>
  </tr>
  <tr>
    <td>InvalidURL</td>
    <td>This could be raised when an invalid source file download
    link being provided. The fault is thrown by operation importMarketData.</td>
  </tr>
  <tr>
    <td>InvalidSECCode</td>
    <td>The fault is raised when security code is not valid. The security 
    usually is in upper case like WSSS. The fault is raised by operation
    importMarketData.</td>
  </tr>
  <tr>
    <td>InvalidDates</td>
    <td>This happens when an invalid start date or an end date or an 
    incorrect start date and end date pair being provided. The fault is only 
    thrown by operation importMarketData.</td>
  </tr>
  <tr>
    <td>InvalidFileType</td>
    <td>This error occurs in operation downloadFile which requires a valid 
    file type. The correct valid type could only be one of XML, CSV and HTML.</td>
  </tr>
  <tr>
    <td>ProgramError</td>
    <td>This is general error regarding to program error.</td>
  </tr>
</table>



<h2><font color="grey">SOAPCurrencyConvertService</font></h2>
<table border="1" style="width:60%" >
  <tr>
    <th>Error Code</th>
    <th>Error Description</th>
  </tr>
  <tr>
    <td>InvalidBaseCode</td>
    <td>The input base currency code is not correct.
    The currency code must be 3 upper characters.
    For example, us dollar is USD and au dollar is AUD.</td>
  </tr>
  <tr>
    <td>InvalidTargetCode</td>
    <td>The input target currency code is not correct.
    The currency code must be 3 upper characters.
    For example, us dollar is USD and au dollar is AUD.</td>
  </tr>
  <tr>
    <td>ConnectionIOError</td>
    <td>This is usually caused by failed connection to remote
    service. It could be the remote yahoo service is not available or
    the internet connection on localhost is not working.</td>
  </tr>
  <tr>
    <td>NoValidResult</td>
    <td>This result is caused by remote yahoo service unable to
    provide valid result based on your input. It could yahoo service's
    limitation or your input currency code is not correct.</td>
  </tr>
  <tr>
    <td>ProgramError</td>
    <td>This is general error regarding to program error.</td>
  </tr>
</table>

<h2><font color="grey">MarketDataUtilService</font></h2>
<table border="1" style="width:60%" >
  <tr>
    <th>Error Code</th>
    <th>Error Description</th>
  </tr>
  <tr>
    <td>InvalidEventSetId</td>
    <td>This is indicated that your input event set id is not valid and
    program could not locate the file associated with your id. This could
    be raised by both convertMarketData and summaryMarketData operation
    since they both require a valid event set id.</td>
  </tr>
  <tr>
    <td>InvalidTargetCode</td>
    <td>This is caused by invalid input target code of convertMarketData operation.
    convertMarketData always convert AUD to another currency which could not be
    AUD its own.</td>
  </tr>
  <tr>
    <td>AlreadyBeingConvered</td>
    <td>This indicates that in the operation convertMarketData, the data file associated
    with the event set id has already been converted to another currency and can not be
    further converted.</td>
  </tr>
  <tr>
    <td>ProgramError</td>
    <td>This is general error regarding to program error.</td>
  </tr>
</table>


</center>
<%@ include file="footer.jsp"%>
</body>
</html>