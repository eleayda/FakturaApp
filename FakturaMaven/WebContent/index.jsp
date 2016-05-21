<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="gapi" scope="session" class="app.Main"/>

    <% 
      String w1= request.getParameter("p1"); 
      String lbl0=gapi.gapiGetFirstLabel();
    %>

    <p> The 1-st lable is <%= lbl0 %> </p>
	
</body>
</html>