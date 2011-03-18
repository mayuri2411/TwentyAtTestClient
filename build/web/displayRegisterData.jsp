<%--
    Document   : index
    Created on : 17 Mar, 2011, 3:57:55 PM
    Author     : root
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Data Display</title>
    </head>
    <body>
        <h3>register data display....</h3>

<%
 
    String registerResponse=(String)session.getAttribute("registerdata");
    out.println("registerResponse===="+registerResponse);
    if(registerResponse!=null){
        out.println("Register Response Not Null");
    } 
     JSONObject json = new JSONObject(registerResponse);

     JSONObject statusJSON = json.getJSONObject("status");
            String message = statusJSON.getString("message");
            out.println("Message==="+message);
            
            JSONObject profileJSON = json.getJSONObject("profile");
            String userId = profileJSON.getString("twentyatUserId");
            String middleName = profileJSON.getString("middleName");
            String region = profileJSON.getString("region");
            String streetAddress = profileJSON.getString("streetAddress");
            String lastName = profileJSON.getString("lastName");
            Long facebookId = profileJSON.getLong("facebookId");
            String friendlyName = profileJSON.getString("friendlyName");
            String photo = profileJSON.getString("photo");
            String country = profileJSON.getString("country");
            Boolean isActive = profileJSON.getBoolean("isActive");
            String postalCode = profileJSON.getString("postalCode");
            String mobilePhone = profileJSON.getString("mobilePhone");
            String email1 = profileJSON.getString("email");
            String locality = profileJSON.getString("locality");
            String firstName = profileJSON.getString("firstName");
            JSONArray groupArray = profileJSON.getJSONArray("twentyatGroups");
            List groups = new ArrayList();
            int groupSize = groupArray.length();
            for(int i=0;i<groupSize;i++)
            {
                JSONObject groupJSON = groupArray.getJSONObject(i);
                Integer groupId = groupJSON.getInt("twentyatGroupId");
                String groupName = groupJSON.getString("groupName");
                String twentyAtUserId = groupJSON.getString("twentyatUserId");
                JSONArray friendsMappingArray = groupJSON.getJSONArray("friendsMappings");
                int friendsMappingLenght = friendsMappingArray.length();
                for(int j=0;j<friendsMappingLenght;j++)
                {
                        JSONObject friendMapping = friendsMappingArray.getJSONObject(j);
                }
             }

            JSONArray contactArray = profileJSON.getJSONArray("contactPersons");
	    List contacts = new ArrayList();
            int contactArrayLenght = contactArray.length();
            for(int i=0;i<contactArrayLenght;i++)
            {
                    JSONObject contactJSON = contactArray.getJSONObject(i);
            }
            JSONArray messageArray = profileJSON.getJSONArray("messages");
	    List messages = new ArrayList();
            int messagesLenght = messageArray.length();
            for(int i=0;i<messagesLenght;i++)
            {
                    JSONObject messageJSON = messageArray.getJSONObject(i);
            }

    
%>
 
<p>First Name:<%=firstName%> </p>
<p>Last Name:<%=lastName%> </p>
<p>Middle Name:<%=middleName%> </p>
<p>TwentAy UserId:<%=userId%> </p> 
<p>Region: <%=region%> </p>
<p>email: <%=email1%> </p>
<p>Is Active: <%=isActive%> </p>
<p>email <%=email1%> </p>
<p>Photo: <img src="<%=photo%>">
<p>Friendly Name <%=friendlyName %> </p> 





    </body>
</html>