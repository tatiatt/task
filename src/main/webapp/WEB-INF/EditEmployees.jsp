<%@ page import="Model.Employee" %>
<%@ page import="Dao.EmployeeDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<%
    EmployeeDao daoObject = new EmployeeDao();
    ResultSet allEmployees = daoObject.getAllEmployees();
%>
<form action="/" method="get">
    <input type="submit" value="Back to add"  class="btn btn-secondary">
</form>

<% while (allEmployees.next()) { %>
<div class="d-flex p-2 justify-content-center " style="margin-top: 50px;">
    <form action="edit" method="post">
        <% if (request.getAttribute("samePersonalId" + allEmployees.getInt("id")) != null) { %>
        <h1>Same personal id</h1>
        <% } %>

        <input type="hidden" value="<%=allEmployees.getInt("id")%>" name="id">
        <label for="firstName"> First name :</label>
        <input type="text" id="firstName" name="firstName" required value=<%= allEmployees.getString("first_name")%>> <br/>

        <label for="lastName"> Last name :</label>
        <input type="text" id="lastName" name="lastName" required value=<%= allEmployees.getString("last_name")%>> <br/>


        <label for="personalId"> Personal id :</label>
        <input type="text" id="personalId" name="personalId" required value=<%= allEmployees.getString("personal_id")%>>
        <br/>

        <label for="birthDate"> Birthday :</label>
        <input type="date" id="birthDate" name="birthDate" required value=<%= allEmployees.getDate("birth_date")%>> <br/>


        <label for="nationality"> Nationality :</label>
        <input type="text" id="nationality" name="nationality" required value=<%= allEmployees.getString("nationality")%>>
        <br/>


        <label for="salary"> Salary :</label>
        <input type="text" id="salary" name="salary" value=<%= allEmployees.getDouble("salary") %>> <br/>

        <% if(request.getAttribute("salaryInvalid"+allEmployees.getInt("id")) != null){ %>
        <p style="color:red">salary is invalid, must be number</p>
        <%} %>

        <% if(request.getAttribute("salaryNegative"+allEmployees.getInt("id")) != null){ %>
        <p style="color:red">salary is negative</p>
        <%} %>

        <label for="salaryCurrency"> Salary Currency :</label>
        <input type="text" id="salaryCurrency" name="salaryCurrency" value=<%= allEmployees.getString("salary_currency")== null ? "" :allEmployees.getString("salary_currency") %>>
        <br/>

        <%ResultSet numbers = daoObject.getAllNumberForEmployee(allEmployees.getInt("id")); %>
        <%int i = 0;%>
        <% while (numbers.next()) { %>
        <label for=<%=allEmployees.getInt("id")+i%>> <%="Number" + (i+1) %>  : </label>
        <input type="tel" name=<%=allEmployees.getInt("id")+i%> value=<%=numbers.getString("phone_number")%> required
               placeholder="number"> <br/>

        <%i++;%>
        <%}%>

        <% if(request.getAttribute("baseError"+allEmployees.getInt("id")) != null){ %>
        <p style="color:red">Some information is incorrect, database error</p>
        <%} %>
        <input type="submit" value="edit" class="btn btn-primary">
    </form>
</div>

<br/>
<%}%>
</body>
</html>
