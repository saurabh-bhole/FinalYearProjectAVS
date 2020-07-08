<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>You Voted To</title>
<link rel="stylesheet" href="./youvotedto.css" >
</head>
<body>
<%
String CID=(String)request.getAttribute("cid");
String CNAME=(String)request.getAttribute("cname");
String CPARTY=(String)request.getAttribute("cparty");
%>
<form action="./ConfirmVote?cid3=<%= CID %>"class="form-ele" method="post">
<label><%= CID %></label><br>
<label><%= CNAME %></label><br>
<label><%= CPARTY %></label><br>
<input type="submit" value="CONFIRM">
</form>
</body>
</html>