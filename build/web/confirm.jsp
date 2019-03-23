<%-- 
    Document   : confirm
    Created on : 23 Mar, 2019, 1:25:36 PM
    Author     : shaswat
--%>

<%@page import="com.vvp.java.Products"%>
<%@page import="com.vvp.java.SelectedProduct"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*" %>
<%@ page import= "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckOut</title>
        <link rel="stylesheet" href="style1.css">
    </head>
    <body>
    <a href = "productPage.html" class="class1">Product Page</a>
    <a href = "viewcart" class="class1">View Cart</a>
    <a class="class2">Check Out</a>
    <a href = "logout" class="class1">Log Out</a>
    
    <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
     url = "jdbc:mysql://localhost:3308/EComm_login"
     user = "root" password = ""/>
    <%--url = "jdbc:mysql://localhost:3306/ecomm_login" For windows--%>


    <sql:query dataSource = "${snapshot}" var = "result">
     SELECT * from users WHERE User_ID = ?
     <sql:param value = "${uid}"></sql:param>
    </sql:query>
        <table border =  "1">
            <tr>
                <th>Name</th>
                <c:forEach var = "row" items = "${result.rows}">
                    <td><c:out value = "${row.User_Name}"/></td>
                </c:forEach>
            </tr>
            <tr>    
                <th>Address</th>
                <c:forEach var = "row" items = "${result.rows}">
                    <td><c:out value = "${row.User_Address}"/></td>
                </c:forEach>
             </tr>
             <tr>   
                <th>Phone No.</th>
                <c:forEach var = "row" items = "${result.rows}">
                    <td><c:out value = "${row.PhoneNo}"/></td>
                </c:forEach>
            </tr>
            <tr>
                <%
                    int uid = (Integer)session.getAttribute("uid");
                    ArrayList objCart =(ArrayList) session.getAttribute("cart");
                    if(objCart == null)
                    {
                        out.println("<br><table border = 1><tr>Cart is Empty :(</tr>");
                        out.println("<a href = \"productPage.html\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:200px; left:227px\">Continue Shopping</a>");
                    }
                    else
                    {
                        out.println("<br><table border = 1><tr>Your Order</tr>:");
                        out.println("<tr><td>Product Name</td><td>Quantity</td></tr>");
                        for(int i=0;i<objCart.size();i++)
                        {
                            SelectedProduct temp = (SelectedProduct) objCart.get(i);
                            Products p = (Products) Products.products.get(new Integer(temp.pid));
                            p.stock -= temp.qty;
                            Products.products.put(new Integer(temp.pid),p);
                            if(temp.qty!=0)
                            out.println("<tr><td>"+p.getProductName()+"</td><td>"+temp.qty+"</td></tr><br>");
                        }
                        session.removeAttribute("cart");
                        String userData = "0_0";
                        session.setAttribute("userData",userData);
                        Cookie ck = new Cookie(Integer.toString(uid),"0_0");
                        ck.setMaxAge(30*86400);
                        response.addCookie(ck);
                        out.println("Has Been Place :D");
                        out.println("<a href = \"productPage.html\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:200px; left:227px\">Continue Shopping</a>");   
                    }
                out.println("</table>");
                %>
            </tr>
            </table>
            
    </body>

