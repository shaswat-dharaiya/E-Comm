<%-- 
    Document   : checkout
    Created on : 15 Feb, 2019, 4:11:19 PM
    Author     : Shaswat's Pc
--%>

<%@page import="com.vvp.java.Products.*"%>
<%@page import="com.vvp.java.SelectedProduct"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.http.*,javax.servlet.*" %>
<%@page import="com.vvp.java.Checkout" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckOut</title>
    </head>
        <body>
        <a href = "logout" style = "border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:20px">Log Out</a>
        <a style = "border-size:1; border-style: solid; background-color: white; border-color: black; border-radius: 5px;color:black;text-decoration: none;position:absolute; top:10px; right:80px">Check Out</a>
        <a href = "viewcart" style = "border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:155px">View Cart</a>
        <a href = "productPage.html" style = "border-size:1; border-style: solid; background-color: black; border-color: black; border-radius:5px;color:white;text-decoration: none;position:absolute; top:10px; right:227px">Product Page</a>
            <div>
                 <table border = 1>
                    <tr><th>Mr. ${name}</th></tr>
                    <tr><th>${Add}</th></tr>
                    <tr><th>${pno}</th></tr>
                    <%@include file= "checkOut.html" %>
                    <%
                        String pm1 = request.getParameter("pm");
                        if(pm1!=null && pm1.equals("cod"))
                        {
                            response.sendRedirect("checkout");
                        }
                        else{
                            out.println("Cashless Methods Coming Soon :)");
                        }
                    %>
                </table>
            </div>
        </body>
    
</html>
