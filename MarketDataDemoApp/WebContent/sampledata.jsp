<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sample Input/Output Data</title>
</head>
<body>
<%@ include file="header.jsp"%>
<center>
<h1>Sample Input/Output Data</h1><br>
<h2>Case 1: <font color="grey">To import and filter a Market Data file based on your own interest.</font></h2>
<h3>Service/Operation to invoke: <font color="grey">ImportDownloadService/importMarketData</font></h3>
<h3>Input Data:</h3>
<table style="width:50%" border="1">
  <tr>
    <th>Security Code</th>
    <td>WSSS</td>
  </tr>
  <tr>
    <th>Start Date</th>
    <td>04/08/2002</td>
  </tr>
  <tr>
    <th>End Date</th>
    <td>04/09/2002</td>
  </tr>
  <tr>
    <th>Data Source URL</th>
    <td>https://www.cse.unsw.edu.au/~cs9322/ass/common/files_csv_spec/MarketData03.csv</td>
  </tr>
</table>
<h3>Result Data:</h3>
<table style="width:50%" border="1">
  <tr>
    <th>Event Set ID</th>
    <td>ass1-6c0d6c1</td>
  </tr>
</table><br><br>


<h2>Case 2: <font color="grey">To download the file over internet.</font></h2>
<h3>Service/Operation to invoke: <font color="grey">ImportDownloadService/downloadFile</font></h3>
<h3>Input Data:</h3>
<table style="width:50%" border="1">
  <tr>
    <th>Evenet Set ID</th>
    <td>ass1-6c0d6c1</td>
  </tr>
  <tr>
    <th>File Type</th>
    <td>CSV</td>
  </tr>
</table>
<h3>Result Data:</h3>
<table style="width:50%" border="1">
  <tr>
    <th>Download Link</th>
    <td>http://localhost:8080/ass1-6c0d6c1.csv</td>
  </tr>
</table><br><br>

<h2>Case 3: <font color="grey">To get currency rate between two currency codes.</font></h2>
<h3>Service/Operation to invoke: <font color="grey">SOAPCurrencyConvertService/yahooExchangeRate</font></h3>
<h3>Input Data:</h3>
<table style="width:50%" border="1">
  <tr>
    <th>Base Currency Code</th>
    <td>AUD</td>
  </tr>
  <tr>
    <th>Target Currency Code</th>
    <td>USD</td>
  </tr>
</table>
<h3>Result Data:</h3>
<table style="width:50%" border="1">
  <tr>
    <th>Currency Rate</th>
    <td>0.7014</td>
  </tr>
  <tr>
    <th>Data Generated Time</th>
    <td>03-09-2015</td>
  </tr>
</table><br><br>

<h2>Case 4: <font color="grey">To convert all price information present in a market data file to another currency.</font></h2>
<h3>Service/Operation to invoke: <font color="grey">MarketDataUtilService/convertMarketData</font></h3>
<h3>Input Data:</h3>
<table style="width:50%" border="1">
  <tr>
    <th>Event Set ID</th>
    <td>ass1-888ae9a</td>
  </tr>
  <tr>
    <th>Target Currency Code</th>
    <td>USD</td>
  </tr>
</table>
<h3>Result Data:</h3>
<table style="width:50%" border="1">
  <tr>
    <th>Event Set ID</th>
    <td>ass1-0a949dc</td>
  </tr>
</table><br><br>

<h2>Case 5: <font color="grey">To get a quick summary information about the content of a market data file associated with the given event set id.</font></h2>
<h3>Service/Operation to invoke: <font color="grey">MarketDataUtilService/summaryMarketData</font></h3>
<h3>Input Data:</h3>
<table style="width:50%" border="1">
  <tr>
    <th>Event Set ID</th>
    <td>ass1-0a949dc</td>
  </tr>
</table>
<h3>Result Data:</h3>
<table style="width:50%" border="1">
  <tr>
    <th>Event Set ID</th>
    <td>ass1-0a949dc</td>
  </tr>
  <tr>
    <th>Security Code</th>
    <td>WSSS</td>
  </tr>
  <tr>
    <th>Start Date</th>
    <td>08-04-2002</td>
  </tr>
  <tr>
    <th>End Date</th>
    <td>09-04-2002</td>
  </tr>
  <tr>
    <th>Currency Code</th>
    <td>USD</td>
  </tr>
  <tr>
    <th>Number of records</th>
    <td>2298</td>
  </tr>
</table><br><br>


</center>
<%@ include file="footer.jsp"%>
</body>
</html>