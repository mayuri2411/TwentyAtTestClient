<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONException"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.servlets.HttpConnection"%>
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

       //session.setAttribute("token", request.getParameter("token"));
        String token=request.getParameter("token");
        out.println("login token-------"+token);
        HttpConnection httpConnection = HttpConnection.getInstance();
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        String loginResponse =null;
                               
          try{
            loginResponse = httpConnection.execute("login", params);
            JSONObject json = new JSONObject(loginResponse);
            JSONObject statusJSON = json.getJSONObject("status");
            String message = statusJSON.getString("message"); 
            out.println("Message==="+message);
            if("ok".equals(message)){
             out.println("Message is ok");            
             session.setAttribute("logindata", loginResponse);
             response.sendRedirect("http://localhost:8084/twentyat-register-test/displayLoginData.jsp");
            }///end of if ok
            
          } catch(JSONException jse){
            jse.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
	//response.sendRedirect("http://localhost:8084/twentyat-register-test/register.jsp");
%>

</body>
</html>