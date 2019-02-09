/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vvp.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shaswat's Pc
 */
public class Checkout extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
                HttpSession session = request.getSession();
                String check = (String) session.getAttribute("isAuth");
                if(check!=null && check.equals("true"))
                {
                    out.println("<a href = \"logout\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:20px\">Log Out</a>\n" +                        
                                "<a style = \"border-size:1; border-style: solid; background-color: white; border-color: black; border-radius: 5px;color:black;text-decoration: none;position:absolute; top:10px; right:80px\">Check Out</a>\n"+
                                "<a href = \"viewcart\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:155px\">View Cart</a>\n"+
                                "<a href = \"productPage.html\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:227px\">Product Page</a>");
                    
                    String name = request.getParameter("pname");
                    String add = request.getParameter("add");
                    String no = request.getParameter("ph");
                    String pm = request.getParameter("pm");
                    if((name.equals(""))||(add.equals(""))||(no.equals(""))||(pm == null)){
                        out.println("Please Fill in all the details<br>Thank you");
                        //out.println("<br>"+name+"<br>"+add+"<br>"+no+"<br>"+pm+"");
                        out.println("<a href = \"checkOut.html\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:45px; left:80px\">Go Back</a>");
                    }
                    else
                    {
                        if(pm.equals("cod"))
                        {
                            ArrayList objCart =(ArrayList) session.getAttribute("cart");
                            if(objCart == null)
                            {
                                out.println("Cart is Empty :(");
                            }
                            else{
                                out.println("Your Order:<br>");
                                for(int i=0;i<objCart.size();i++)
                                {
                                    SelectedProduct temp = (SelectedProduct) objCart.get(i);
                                    Products p = (Products) Products.products.get(new Integer(temp.pid));
                                    p.stock -= temp.qty;
                                    Products.products.put(new Integer(temp.pid),p);
                                    out.println(temp.qty+" "+p.getProductName()+"<br>");
                                }
                                session.removeAttribute("cart");
                                out.println("Has Been Place :D");
                            }
                        }
                        else
                        {
                            out.println("Coming Soon, Only COD available right now :)");
                        }
                    }
                }
                else
                {
                        response.sendRedirect("login.html");
                }
        } finally {
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
        processRequest(request, response);
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
        processRequest(request, response);
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
