/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vvp.java;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shaswat's Pc
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        try {
            String uname = request.getParameter("username");
            String pwd = request.getParameter("password");

            HttpSession session = request.getSession();
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomm_login","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            String usr = null,psd = null;
            int uid = 0;
            while(rs.next() )
            {   
                uid = rs.getInt(1);
                usr = rs.getString(2);
                psd = rs.getString(3);
                if(usr.equals(uname) && psd.equals(pwd))
                {
                    //out.println(uid+"<br>"+usr+"<br>"+psd);
                    session.setAttribute("uid",uid);   
                    break;
                }
            }
            
            if(uname.equals(usr) && pwd.equals(psd))
            {
                session.setAttribute("isAuth", "true");
                Cookie[] ck = request.getCookies();
                for(int i=0;i<ck.length;i++)
                {
                    if(ck[i].getName().equals(Integer.toString(uid)))
                    {
                        String productQty = ck[i].getValue();
                        if(productQty!=null)
                        {
                            session.setAttribute("userData",productQty);
                            break;
                        }
                        else
                        {
                            break;
                        }
                    }
                }
                response.sendRedirect("productPage.html");
            }
            else
            {
                session.setAttribute("isAuth", "false");
                response.sendRedirect("login.html");
            }
        }
        catch(Exception e)
            {
                e.printStackTrace();
            }
        finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
