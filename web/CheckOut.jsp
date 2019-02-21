<%-- 
    Document   : checkout
    Created on : 15 Feb, 2019, 4:11:19 PM
    Author     : Shaswat's Pc
--%>

<%@ page import = "java.io.*,java.util.*,java.sql.*" %>
<%@ page import= "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="com.vvp.java.Checkout" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

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
    <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
     url = "jdbc:mysql://localhost:3306/ecomm_login"
     user = "root" password = ""/>

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
                <%@include file= "checkOut.html" %>
                <%
                    String pm1 = request.getParameter("pm");
                    if(pm1!=null && pm1.equals("cod"))
                    {
                        response.sendRedirect("checkout");
                    }
                %>
         
        </table>
    </body>
</html>
