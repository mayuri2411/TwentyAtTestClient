<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Redirecting....</title>
</head>
<body> 
<%


        session.setAttribute("token", request.getParameter("token"));
        out.println("token-------"+session.getAttribute("token"));
	response.sendRedirect("http://localhost:8084/twentyat-register-test/register.jsp");

%>
</body>
</html>