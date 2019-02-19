/*
 * To change tdis license header, choose License Headers in Project Properties.
 * To change tdis template file, choose Tools | Templates
 * and open tde template in tde editor.
 */
package com.vvp.java;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.*;
import javax.servlet.*;;
import javax.servlet.http.*;

/**
 *
 * @autdor Shaswat's Pc
 */
public class Checkout extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @tdrows ServletException if a servlet-specific error occurs
     * @tdrows IOException if an I/O error occurs
     * @tdrows java.sql.SQLException
     * @tdrows java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
                HttpSession session = request.getSession();
                String check = (String) session.getAttribute("isAuth");
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomm_login","root","");
                Statement stmt = con.createStatement();
                int usid = (Integer) session.getAttribute("uid");
                ResultSet rs = stmt.executeQuery("select * from users where User_ID ="+usid+";");
                String Name = null,Add = null,pno = null;
                while(rs.next())
                {
                    Name = rs.getString(4);
                    session.setAttribute("name",Name);
                    Add = rs.getString(5);
                    session.setAttribute("Add",Add);
                    pno = rs.getString(6);
                    session.setAttribute("pno",pno);   
                }
                if(check!=null && check.equals("true"))
                {
                    out.println("<a href = \"logout\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:20px\">Log Out</a>\n" +                        
                                "<a style = \"border-size:1; border-style: solid; background-color: white; border-color: black; border-radius: 5px;color:black;text-decoration: none;position:absolute; top:10px; right:80px\">Check Out</a>\n"+
                                "<a href = \"viewcart\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:155px\">View Cart</a>\n"+
                                "<a href = \"productPage.html\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:227px\">Product Page</a>");
                    
                    out.println("Mr."+Name+"<br>"+Add+"<br>"+pno+"<br>");                    
                     
                        ArrayList objCart =(ArrayList) session.getAttribute("cart");
                        if(objCart == null)
                        {
                            out.println("Cart is Empty :(");
                        }
                        else{
                            out.println("Your Order:");
                            out.println("<table border = 1><tr><td>Product Name</td><td>Quantity</td></tr>");
                            for(int i=0;i<objCart.size();i++)
                            {
                                SelectedProduct temp = (SelectedProduct) objCart.get(i);
                                Products p = (Products) Products.products.get(new Integer(temp.pid));
                                p.stock -= temp.qty;
                                Products.products.put(new Integer(temp.pid),p);
                                out.println("<tr><td>"+p.getProductName()+"</td><td>"+temp.qty+"</td></tr><br>");
                            }
                            out.println("</table>");
                            session.removeAttribute("cart");
                            out.println("Has Been Place :D");
                        }
                    
                }
                else
                {
                        response.sendRedirect("login.html");
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet metdods. Click on the + sign on tde left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @tdrows ServletException if a servlet-specific error occurs
     * @tdrows IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Checkout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @tdrows ServletException if a servlet-specific error occurs
     * @tdrows IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Checkout.class.getName()).log(Level.SEVERE, null, ex);
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
