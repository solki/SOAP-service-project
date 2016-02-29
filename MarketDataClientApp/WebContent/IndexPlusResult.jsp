<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result</title>
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
			*
			<%=List.get(i)%>

			<%
				}
				}
			%>
		</p>
		<h2>
			<font color="grey">Run Composed Market Data Service</font>
		</h2>
		Choose Composition Options
		<form id="adv_search_from" name="adv_search_from" action="control"
			method="get">
			<select id="Composition" name="action">
				<option value="composition1">importMarketData -
					convertMarketData - downloadFile</option>
				<option value="composition2">importMarketData -
					summaryMarketData</option>
				<option value="composition3">convertMarketData -
					summaryMarketData</option>
			</select> <input type="submit" value="Run the services" title="run" />
		</form>

		<p>
		<h2>
			<font color="grey">Composed Service Run Response</font>
		</h2>
			<strong>Summary:</strong> <%=request.getAttribute("output") %>
	</center>
</body>
</html>