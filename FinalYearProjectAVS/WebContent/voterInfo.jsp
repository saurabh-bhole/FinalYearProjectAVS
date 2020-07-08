<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Information</title>
<link rel="stylesheet" href="./voterInfo.css" >
<center><h1>Your Information</h1></center>
</head>
<body>
<div class="vinfo">
<p class="idt">Voter ID: ${ID}</p>
<p class="">Name: ${NAME}</p>
<p class="">Aadhar UID: ${AADHAR}</p>
<p class="">Mobile Number: ${MOBILE}</p>
<p class="">Constituency: ${CONSTITUENCY}</p>
<p class="">Ward: ${WARD}</p>
<p class="">Village: ${VILLAGE}</p>
<p class="">Taluka: ${TALUKA}</p>
<p class="">City: ${CITY}</p>
<p class="">District: ${DISTRICT}</p>
<p class="">State: ${STATE}</p>
<p class="">Pincode: ${PINCODE}</p>
<form action="./Candidates" method="post" class="form-info">
	<center><input type="submit" value="Next"></center>
</form>
</div>
</body>
</html>