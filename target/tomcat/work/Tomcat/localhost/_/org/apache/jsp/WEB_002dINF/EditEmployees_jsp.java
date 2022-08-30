/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2022-08-30 06:13:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Model.Employee;
import Dao.EmployeeDao;
import java.sql.SQLException;
import java.sql.ResultSet;

public final class EditEmployees_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\r\n");
      out.write("    <title>Title</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");

    EmployeeDao daoObject = new EmployeeDao();
    ResultSet allEmployees = daoObject.getAllEmployees();

      out.write("\r\n");
      out.write("<form action=\"/\" method=\"get\">\r\n");
      out.write("    <input type=\"submit\" value=\"Back to add\"  class=\"btn btn-secondary\">\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
 while (allEmployees.next()) { 
      out.write("\r\n");
      out.write("<div class=\"d-flex p-2 justify-content-center \" style=\"margin-top: 50px;\">\r\n");
      out.write("    <form action=\"edit\" method=\"post\">\r\n");
      out.write("        ");
 if (request.getAttribute("samePersonalId" + allEmployees.getInt("id")) != null) { 
      out.write("\r\n");
      out.write("        <h1>Same personal id</h1>\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <input type=\"hidden\" value=\"");
      out.print(allEmployees.getInt("id"));
      out.write("\" name=\"id\">\r\n");
      out.write("        <label for=\"firstName\"> First name :</label>\r\n");
      out.write("        <input type=\"text\" id=\"firstName\" name=\"firstName\" required value=");
      out.print( allEmployees.getString("first_name"));
      out.write("> <br/>\r\n");
      out.write("\r\n");
      out.write("        <label for=\"lastName\"> Last name :</label>\r\n");
      out.write("        <input type=\"text\" id=\"lastName\" name=\"lastName\" required value=");
      out.print( allEmployees.getString("last_name"));
      out.write("> <br/>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <label for=\"personalId\"> Personal id :</label>\r\n");
      out.write("        <input type=\"text\" id=\"personalId\" name=\"personalId\" required value=");
      out.print( allEmployees.getString("personal_id"));
      out.write(">\r\n");
      out.write("        <br/>\r\n");
      out.write("\r\n");
      out.write("        <label for=\"birthDate\"> Birthday :</label>\r\n");
      out.write("        <input type=\"date\" id=\"birthDate\" name=\"birthDate\" required value=");
      out.print( allEmployees.getDate("birth_date"));
      out.write("> <br/>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <label for=\"nationality\"> Nationality :</label>\r\n");
      out.write("        <input type=\"text\" id=\"nationality\" name=\"nationality\" required value=");
      out.print( allEmployees.getString("nationality"));
      out.write(">\r\n");
      out.write("        <br/>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <label for=\"salary\"> Salary :</label>\r\n");
      out.write("        <input type=\"text\" id=\"salary\" name=\"salary\" value=");
      out.print( allEmployees.getDouble("salary") );
      out.write("> <br/>\r\n");
      out.write("\r\n");
      out.write("        ");
 if(request.getAttribute("salaryInvalid"+allEmployees.getInt("id")) != null){ 
      out.write("\r\n");
      out.write("        <p style=\"color:red\">salary is invalid, must be number</p>\r\n");
      out.write("        ");
} 
      out.write("\r\n");
      out.write("\r\n");
      out.write("        ");
 if(request.getAttribute("salaryNegative"+allEmployees.getInt("id")) != null){ 
      out.write("\r\n");
      out.write("        <p style=\"color:red\">salary is negative</p>\r\n");
      out.write("        ");
} 
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <label for=\"salaryCurrency\"> Salary Currency :</label>\r\n");
      out.write("        <input type=\"text\" id=\"salaryCurrency\" name=\"salaryCurrency\" value=");
      out.print( allEmployees.getString("salary_currency")== null ? "" :allEmployees.getString("salary_currency") );
      out.write(">\r\n");
      out.write("        <br/>\r\n");
      out.write("\r\n");
      out.write("        ");
ResultSet numbers = daoObject.getAllNumberForEmployee(allEmployees.getInt("id")); 
      out.write("\r\n");
      out.write("        ");
int i = 0;
      out.write("\r\n");
      out.write("        ");
 while (numbers.next()) { 
      out.write("\r\n");
      out.write("        <label for=");
      out.print(allEmployees.getInt("id")+i);
      out.write('>');
      out.write(' ');
      out.print("Number" + (i+1) );
      out.write("  : </label>\r\n");
      out.write("        <input type=\"tel\" name=");
      out.print(allEmployees.getInt("id")+i);
      out.write(" value=");
      out.print(numbers.getString("phone_number"));
      out.write(" required\r\n");
      out.write("               placeholder=\"number\"> <br/>\r\n");
      out.write("\r\n");
      out.write("        ");
i++;
      out.write("\r\n");
      out.write("        ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("        ");
 if(request.getAttribute("baseError"+allEmployees.getInt("id")) != null){ 
      out.write("\r\n");
      out.write("        <p style=\"color:red\">Some information is incorrect, database error</p>\r\n");
      out.write("        ");
} 
      out.write("\r\n");
      out.write("        <input type=\"submit\" value=\"edit\" class=\"btn btn-primary\">\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<br/>\r\n");
}
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}