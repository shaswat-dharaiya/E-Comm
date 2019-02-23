/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vvp.java;

import com.vvp.java.Products;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*  ;
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
public class ViewCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void init(){
        Products.initData();
    }
    public int[] deserializeCokie(String CookieString)
    {
        String[] userCk = CookieString.split("_");
        int[] data=new int[userCk.length];
        for(int i =0;i<userCk.length;i++)
        {
                data[i] = Integer.parseInt(userCk[i]);
        }
        return data;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ArrayList objCart = null;
            HttpSession session = request.getSession();
            int usid = (Integer) session.getAttribute("uid");
            String uid = Integer.toString(usid);
            String cookieData = (String) session.getAttribute("userData");
            StringBuffer tempData = new StringBuffer(cookieData);            
            int[] productQty = deserializeCokie(cookieData);            
            
            out.println("<a href = \"logout\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:20px\">Log Out</a>\n" +                        
                        "<a style = \"border-size:1; border-style: solid; background-color: white; border-color: black; border-radius: 5px;color:black;text-decoration: none;position:absolute; top:10px; right:155px\">View Cart</a>\n"+
                        "<a href = \"productPage.html\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:227px\">Product Page</a>");
            objCart = (ArrayList) session.getAttribute("cart");
            
            if(objCart == null && cookieData.equals("0_0")){
                out.println("Your cart is empty");
                out.println("<a style = \"border-size:1; border-style: solid; background-color: white; border-color: black; border-radius: 5px;color:black;text-decoration: none;position:absolute; top:10px; right:80px\">Check Out</a>\n");
            }   
            else
            {                
                if(objCart == null && !cookieData.equals("0_0"))
                {
                    objCart = new ArrayList();
                    for(int i =0;i<productQty.length;i++)
                    {
                        SelectedProduct sp = new SelectedProduct(i+1,productQty[i]);
                        objCart.add(sp);
                        session.setAttribute("cart", objCart);
                    }
                }
                out.println("<a href = \"CheckOut.jsp\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:80px\">Check Out</a>");
                double grandTotal = 0;
                int qnty = 0;
                out.println("<table border = 1><tr><td>Sr. No</td><td>Product Name</td><td>Quantity</td><td>Price</td></tr>");
                for(int i =0;i<objCart.size();i++)
                {
                    SelectedProduct temp = (SelectedProduct) objCart.get(i);
                    Products p = (Products) Products.products.get(new Integer(temp.pid));
                    String pName = p.getProductName();
                    qnty = temp.getQty();
                    session.setAttribute("qnty",Integer.toString(qnty));
                    productQty[i] = qnty;
                    double price = p.getPrice();
                    price *= qnty;
                    grandTotal += (double) price;   
                    out.println("<tr><td>"+(i+1)+"</td><td> "+pName+"</td><td>"+qnty+"</td><td>"+price+"</td>");
                }
                out.println("<tr><td colspan = 3></td><td>Grand Total = "+grandTotal+"</td></table>");
            }
            for(int i=0;i<productQty.length;i++)
            {
                int pos = (i*2);
                char val = (char) (productQty[i]+48); //Applicable for qty ranging between 0-9
                tempData.setCharAt(pos,val);
            }   
            Cookie finalCk = new Cookie(uid,tempData.toString());
            response.addCookie(finalCk);
            finalCk.setMaxAge(30*86400);
        }catch(Exception e)
        {out.print("<br>"+e);} 
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
