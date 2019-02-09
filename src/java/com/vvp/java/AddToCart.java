/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vvp.java;

import com.oracle.jrockit.jfr.Producer;
import static com.vvp.java.Products.initData;
import com.vvp.java.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shaswat's Pc
 */
public class AddToCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ArrayList objCart = null;
    public void init(){
        Products.initData();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        PrintWriter out = response.getWriter();
        try {                
                HttpSession session = request.getSession();
                String check = (String) session.getAttribute("isAuth");
                
                int pid = Integer.parseInt(request.getParameter("pid"));
                int qty = Integer.parseInt(request.getParameter("qty"));
                if(check!=null && check.equals("true"))
                {
                    if(session.getAttribute("cart")==null)
                        objCart = new ArrayList();
                    else{
                        objCart = (ArrayList) session.getAttribute("cart");
                    }
                    Products p = (Products) Products.products.get(new Integer(pid));
                    if(qty>p.getStock())
                    {
                        out.println("Out of Stock");
                        qty=0;
                        pid=0;
                    }
                    
                    else
                    {
                        boolean change = true;
                        
                        SelectedProduct sp = new SelectedProduct(pid,qty);                        
                        SelectedProduct temp;
                        int stockLeft;
                        for(int i =0;i<objCart.size();i++)
                        {
                           temp = (SelectedProduct) objCart.get(i);
                           if(pid == temp.pid)
                           {
                               Products pr = (Products) Products.products.get(new Integer(temp.pid));
                               int stock = pr.getStock();
                               stockLeft = stock - temp.qty;
                               if(stockLeft<qty)
                               {
                                   out.println("Max Stock available is: "+stockLeft);
                                   change = false;
                                   break;
                               }
                               else
                               {
                                    temp.qty += qty;
                                    objCart.set(i,temp);
                                    change = false;
                                    break;
                               }
                           }
                           else{change = true;}
                        }
                        if(change==true)
                        {
                            objCart.add(sp);
                        }
                        session.setAttribute("cart", objCart);
                        response.sendRedirect("productPage.html");
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
