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
        int[] data = null;
        if(CookieString!=null)
        {
            String[] userCk = CookieString.split("_");
            data=new int[userCk.length];
            for(int i =0;i<userCk.length;i++)
            {
                    data[i] = Integer.parseInt(userCk[i]);
            }
        }
        return data;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String cssTag = "<html><head><link rel=\"stylesheet\" href=\"button1.css\"></head>";
            ArrayList objCart = null;
            HttpSession session = request.getSession();
            int usid = (Integer) session.getAttribute("uid");
            String uid = Integer.toString(usid);
            String cookieData = (String) session.getAttribute("userData");
            StringBuffer tempData = new StringBuffer(cookieData);            
            int[] productQty = deserializeCokie(cookieData);            
            out.print("<html><head>\n" +
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "<title>CheckOut</title>\n" +
                        "<link rel=\"stylesheet\" href=\"style1.css\">\n" +
                        "</head><body>");
            out.print("<a href = \"productPage.html\"class=\"class1\">Product Page</a>\n" +
                        "<a class=\"class2\">View Cart</a>\n");
                        
            objCart = (ArrayList) session.getAttribute("cart");
            
            if(objCart == null && cookieData.equals("0_0")){
                out.print("<a class=\"class2\">Check Out</a>\n" +
                        "<a href = \"logout\" class=\"class1\">Log Out</a>");
                out.print("<p>Your cart is empty</p>");
                
            }   
            else
            {                
                out.print("<a href = \"CheckOut.jsp\" class=\"class1\">Check Out</a>\n" +
                        "<a href = \"logout\" class=\"class1\">Log Out</a>");
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
                double grandTotal = 0;
                int qnty = 0;
                out.println("<table border = 1><tr><td>Product Name</td><td>Quantity</td><td>Price</td></tr>");
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
                    if(qnty!=0)
                        out.println("<tr><td> "+pName+"</td><td>"+qnty+"</td><td>"+price+"</td>");
                }
                out.println("<tr><td colspan = 2></td><td>Grand Total = "+grandTotal+"</td></table>");
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
            out.print("</body></html>");
        }catch(Exception e)
        {
            HttpSession session = request.getSession();
            int usid = (Integer) session.getAttribute("uid");
            String uid = Integer.toString(usid);
            
            out.print(uid+"<br>"+e);
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
