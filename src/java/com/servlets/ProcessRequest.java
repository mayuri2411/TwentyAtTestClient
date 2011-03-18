/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author root
 */
public class ProcessRequest extends HttpServlet {
       private HttpConnection httpConnection = HttpConnection.getInstance();
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        String token=(String)session.getAttribute("token");
        String uuid=(String)request.getParameter("txt_uuid");
        String email=(String)request.getParameter("txt_email");

        String registerResponse = processRegister(token, email, uuid);
        PrintWriter out = response.getWriter();
        
        try {
           //  TODO output your page here
            out.println("<html>");
            out.println("<head>");  
            out.println("<title>Servlet ProcessRequest</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("token---"+token);
            out.println("email---"+email);
            out.println("uuid---"+uuid);
            try{
            JSONObject json = new JSONObject(registerResponse);
            JSONObject statusJSON = json.getJSONObject("status");
            String message = statusJSON.getString("message");
            out.println("Message==="+message);
            if("ok".equals(message)){ 
                out.println("Message is ok"); 
                session.setAttribute("registerdata", registerResponse);
             response.sendRedirect("http://localhost:8084/twentyat-register-test/displayRegisterData.jsp");

           /*
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

            */
	   }///end of if ok 


            }catch(JSONException jse){
            jse.printStackTrace();
            } catch (Exception e){
            e.printStackTrace();
            }

            out.println("</body>");
            out.println("</html>");




        } finally { 
            out.close();
        }


    } 

    public String processRegister(String token, String email, String uuid){
        System.out.println("========Inside Process Register==========");
        System.out.println("token=="+token+" email---"+email+" uuid---"+uuid);
 
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        params.put("email", email);
        params.put("uuid", uuid);
       // String message=null;
        String response =null;
        try{ 
        response = httpConnection.execute("register", params);
        //JSONObject json = new JSONObject(response);
	//JSONObject statusJSON = json.getJSONObject("status");
	//message = statusJSON.getString("message");

       // System.out.println("message in process request ==="+message);
        }catch(JSONException jse){
            jse.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
       // return message;
       return response;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
