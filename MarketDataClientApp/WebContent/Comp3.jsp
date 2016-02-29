<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Composition3 Input</title>
<meta charset="utf-8">
<title>jQuery UI Datepicker - Default functionality</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
	$(function() {
		$("#datepicker1").datepicker();
	});

	$(function() {
		$("#datepicker2").datepicker();
	});
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
		<%
		String indicator = (String) request.getAttribute("empty");
	%>
	<center>
		<h2>
			<font color="grey">List of Your Current Market Data
				(eventSetIds)</font>
		</h2>
		<p>
			<%
				if (indicator.equals("1")) {
			%>
			No Market Data is Found. Please use
			"importMarketData-convertMarketData-downloadFile" to generate a new
			market data file.
			<%
				} else {
					ArrayList<String> List = (ArrayList<String>) request.getAttribute("setList");
					int number = List.size();
					for (int i = 0; i < number; ++i) {
			%>
			*<%=List.get(i)%>

			<%
				}
				}
			%>
		</p>
		<h1>
			<font color="grey">Convert and Summary DataFile Service</font>
		</h1>
		<h2>Input the information of the data file.</h2>
		<br> <br>

		<form action="control">
			<b>EventSet ID:&nbsp;&nbsp;&nbsp;&nbsp;</b> <input type="text"
				name="eventSetId" required="required" pattern="ass1-[0-9a-z]{7}" placeholder="ass1-4282eda8"><br> <br>
			<b>Target Currency:&nbsp;&nbsp;&nbsp;&nbsp;</b> <input type="text"
				name="currency" required="required" pattern="[A-Z]{3}" placeholder="USD"><br> <br>
			<input type="hidden" name="action" value="getresult3"> <input
				type="submit" value="Submit Request to Service">
		</form>

	</center>
</body>
</html>