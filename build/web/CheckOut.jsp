<%-- 
    Document   : checkout
    Created on : 15 Feb, 2019, 4:11:19 PM
    Author     : Shaswat's Pc
--%>

<%@ page import = "java.io.*,java.util.*,java.sql.*" %>
<%@ page import= "javax.servlet.http.*,javax.servlet.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckOut</title>
        <link rel="stylesheet" href="style1.css">
    </head>
    
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
                <div>
                    <form action="CheckOut.jsp" method="get">
                        <table> 
                            <tr>
                                <th>Payment Method </th>                    
                            </tr>
                            <tr>
                                <td>Cash on Delivery </td>
                                <td><input type="radio" name="pm" value="cod" id="pm1"></td>
                            </tr>
                            <tr>
                                <td>Debit Card </td>
                                <td><input type="radio" name="pm" value="dc" id="pm2"></td>
                            </tr>
                            <tr>
                                <td>Credit Card </td>
                                <td><input type="radio" name="pm" value="cc" id="pm3"></td>
                            </tr>
                            <tr>
                                <td><input type="submit" name="submit" value="submit"></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </tr>
                <%
                    String pm1 = request.getParameter("pm");
                    if(pm1!=null && pm1.equals("cod"))
                    {
                        response.sendRedirect("confirm.jsp");
                    }
                    else
                    {
                        out.print("Only COD available");
                    }
                %>
        </table>
    </body>
