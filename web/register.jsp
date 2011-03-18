<%--
    Document   : index
    Created on : 17 Mar, 2011, 3:57:55 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Home</title>
    </head>
    <body>
        <h3>Register Home</h3>
        
<%
out.println("token-------"+session.getAttribute("token"));
%>

<form id="form" name="form" method="post" action="/twentyat-register-test/ProcessRequest">
    <table border="1">
        <thead> 
            <tr>
                <th colspan="2">Register Params</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>E-Mail: </td>
                <td><input type="text" id="txt_email" name="txt_email"/>
                </td>
            </tr>
            <tr> 
                <td>UUID: </td>
                <td><input type="text" id="txt_uuid" name="txt_uuid"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit"></td>               
            </tr>
        </tbody>
  
    </table>

</form>
        </body>
</html>
