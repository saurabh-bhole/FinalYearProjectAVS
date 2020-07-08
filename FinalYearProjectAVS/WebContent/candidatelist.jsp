<%@page import="java.util.ArrayList"%>
<%@page import="avsweb.ImageDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Candidates</title>
<link rel="stylesheet" href="./cantable.css" >
</head>
<body>
<center>
<%
List<ImageDTO> list=(ArrayList<ImageDTO>)request.getAttribute("list");
%>
<table border="1px">
	<thead>
		<tr><td><b>ID</b></td><td><b>Name</b></td><td><b>Party</b></td><td><b>Symbol</b></td><td><b>Vote Here</b></td></tr>
	</thead>
	<%
	for(int i=0;i<list.size();i++){
		int d=list.size();
		ImageDTO dto=list.get(i);
		String id=dto.getId();
		String name=dto.getName();
		String party=dto.getParty();
		String idEnc=dto.getIdEnc();
	%>
		<tr height="260px"><td width="50px"><%= id %></td><td width="200px"><%= name %></td><td width="200px"><%=party %></td>
		<td width="200"><img src="./CandidateSymbol?cid=<%= idEnc%>" height="100px" width="100px"></td>
		<td width="200"><form action="./YouVotedTo?cid0=<%= idEnc %>" method="post" class="form-ele">
		<input class="votebutton" type="submit" value="VOTE" style="alignment: center;
	width: 120px; border: none; ouline: none; height: 40px; background: #2d5986; color: #fff; font-size:16px;
	border-radius: 20px;"></form></td></tr>
	<%	
	}
	%>
</table>

</center>
</body>
</html>