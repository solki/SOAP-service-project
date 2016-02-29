<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Composition1 Input</title>
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
	<center>
		<h1>
			<font color="grey">Import Convert and Download DataFile Service</font>
		</h1>
		<h2>Input the information of the data file.</h2>
		<br> <br>

		<form action="control">
			<b>Security Code:&nbsp;&nbsp;&nbsp;&nbsp;</b> <input type="text"
				name="sec" required="required" pattern="[A-Z]{3,4}" placeholder="WSSS"><br>
			<br> <b>Start Date:&nbsp;&nbsp;&nbsp;&nbsp;</b> <input
				type="text" id="datepicker1" name="startdate" required="required"
				placeholder="MM/DD/YYYY"><br> <br> <b>End
				Date:&nbsp;&nbsp;&nbsp;&nbsp;</b> <input type="text" id="datepicker2"
				name="enddate" required="required" placeholder="MM/DD/YYYY"><br>
			<br> <b>Data Source URL:&nbsp;&nbsp;&nbsp;&nbsp;</b> <input
				type="text" name="url" required="required"
				placeholder="http://adage.cse.unsw.edu.au:8080/MarketData03.csv"><br>
			<br> <b>Target Currency:&nbsp;&nbsp;&nbsp;&nbsp;</b> <input
				type="text" name="currency" required="required" pattern="[A-Z]{3}" placeholder="USD"><br>
			<br> <b>Download File Type:&nbsp;&nbsp;&nbsp;&nbsp;</b> <select
				id="fileType" name="filetype">
				<option value="CSV">CSV</option>
				<option value="XML">XML</option>
				<option value="HTML">HTML</option>
			</select><br> <br> <input type="hidden" name="action"
				value="getresult1"> <input type="submit"
				value="Submit Request to Service">
		</form>

	</center>
</body>
</html>