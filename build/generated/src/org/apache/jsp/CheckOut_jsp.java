package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
import com.vvp.java.Checkout;

public final class CheckOut_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/checkOut.html");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>CheckOut</title>\n");
      out.write("    </head>\n");
      out.write("        <body>\n");
      out.write("        <a href = \"logout\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:20px\">Log Out</a>\n");
      out.write("        <a style = \"border-size:1; border-style: solid; background-color: white; border-color: black; border-radius: 5px;color:black;text-decoration: none;position:absolute; top:10px; right:80px\">Check Out</a>\n");
      out.write("        <a href = \"viewcart\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:155px\">View Cart</a>\n");
      out.write("        <a href = \"productPage.html\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius:5px;color:white;text-decoration: none;position:absolute; top:10px; right:227px\">Product Page</a>\n");
      out.write("        <sql:setDataSource var = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${snapshot}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" driverr=\"com.mysql.jdbc.Driver\" url = \"jdbc:mysql://localhost:3306/ecomm_login\" user=\"root\" password=\"\"/>\n");
      out.write("        <sql:query dataSource = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${snapshot}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" var=\"result\">\n");
      out.write("            Select * from users\n");
      out.write("        </sql:query>\n");
      out.write("            <div>\n");
      out.write("                 <table border = 1>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Mr. <c:out value = \"{row.User_Name}\"></th>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <th><th><c:out value = \"{row.User_Add}\"></th></tr></th>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <th><th><c:out value = \"{row.PhoneNo}\"></th></tr></th>\n");
      out.write("                    </tr>\n");
      out.write("                    ");
      out.write("<!DOCTYPE html>\n");
      out.write("<!--\n");
      out.write("To change this license header, choose License Headers in Project Properties.\n");
      out.write("To change this template file, choose Tools | Templates\n");
      out.write("and open the template in the editor.\n");
      out.write("-->\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Check Out</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <a href = \"logout\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:20px\">Log Out</a>\n");
      out.write("        <a style = \"border-size:1; border-style: solid; background-color: white; border-color: black; border-radius: 5px;color:black;text-decoration: none;position:absolute; top:10px; right:80px\">Check Out</a>\n");
      out.write("        <a href = \"viewcart\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius: 5px;color:white;text-decoration: none;position:absolute; top:10px; right:155px\">View Cart</a>\n");
      out.write("        <a href = \"productPage.html\" style = \"border-size:1; border-style: solid; background-color: black; border-color: black; border-radius:5px;color:white;text-decoration: none;position:absolute; top:10px; right:227px\">Product Page</a>\n");
      out.write("        <div>\n");
      out.write("            <form action=\"CheckOut.jsp\" method=\"get\">\n");
      out.write("                <table> \n");
      out.write("                    <tr>\n");
      out.write("                        <th>Payment Method </th>                    \n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Cash on Delivery </td>\n");
      out.write("                        <td><input type=\"radio\" name=\"pm\" value=\"cod\" id=\"pm1\"></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Debit Card </td>\n");
      out.write("                        <td><input type=\"radio\" name=\"pm\" value=\"dc\" id=\"pm2\"></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Credit Card </td>\n");
      out.write("                        <td><input type=\"radio\" name=\"pm\" value=\"cc\" id=\"pm3\"></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td><input type=\"submit\" name=\"submit\" value=\"submit\"></td>\n");
      out.write("                    </tr>\n");
      out.write("                </table>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("                    ");

                        String pm1 = request.getParameter("pm");
                        if(pm1!=null && pm1.equals("cod"))
                        {
                            response.sendRedirect("checkout");
                        }
                        else{
                            out.println("Cashless Methods Coming Soon :)");
                        }
                    
      out.write("\n");
      out.write("                </table>\n");
      out.write("            </div>\n");
      out.write("        </body>\n");
      out.write("    \n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
