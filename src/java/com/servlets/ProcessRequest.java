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
