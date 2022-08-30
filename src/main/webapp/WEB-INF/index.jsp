<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<form action="edit" method="get">
    <input type="submit" value="See employees" name="seeAndEdit" class="btn btn-secondary">
</form>
<div class="d-flex p-2 justify-content-center " style="margin-top: 50px" >

<form action="" method="post">

    <label for="firstName"> First name :</label>
    <input type="text" id="firstName" name="firstName" required value=<%= request.getAttribute("firstName") != null ? request.getAttribute("firstName") : ""%>  > <br/>

    <label for="lastName"> Last name :</label>
    <input type="text" id="lastName"  name="lastName" required value=<%= request.getAttribute("lastName") != null ? request.getAttribute("lastName") : ""%> > <br/>


    <label for="personalId"> Personal id :</label>
    <input type="text" id="personalId" name="personalId" required value=<%= request.getAttribute("personalId") != null ? request.getAttribute("personalId") : ""%>> <br/>

    <% if(request.getAttribute("sameId") != null){ %>
    <p style="color:red">Person with this id already in database</p>
    <%} %>
    <label for="birthDate"> Birthday :</label>
    <input type="date" id="birthDate" name="birthDate" required value=<%= request.getAttribute("birthDate") != null ? request.getAttribute("birthDate") : ""%> > <br/>


    <label for="nationality"> Nationality :</label>
    <input type="text" id="nationality" name="nationality"  required value=<%= request.getAttribute("nationality") != null ? request.getAttribute("nationality") : ""%> > <br/>


    <label for="salary"> Salary :</label>
    <input type="text" id="salary" name="salary" value=<%= request.getAttribute("salary") != null ? request.getAttribute("salary") : ""%> > <br/>

    <% if(request.getAttribute("salaryInvalid") != null){ %>
    <p style="color:red">salary is invalid, must be number</p>
    <%} %>

    <% if(request.getAttribute("salaryNegative") != null){ %>
    <p style="color:red">salary is negative</p>
    <%} %>

    <label for="salaryCurrency"> Salary Currency :</label>
    <input type="text" id="salaryCurrency" name="salaryCurrency" value=<%= request.getAttribute("salaryCurrency") != null ? request.getAttribute("salaryCurrency") : ""%>> <br/>

    <label for="phoneNumberCount"> Enter count of phone numbers: </label>
    <input type="text" id="phoneNumberCount" name ="phoneNumberCount" value=<%= request.getAttribute("count") != null ? request.getAttribute("count") : 0%>>
        <input type="submit" value="OK" name="phoneNum" /> <br/>



        <% if (request.getAttribute("count") != null){
            for(int i = 0; i< Integer.parseInt((String) request.getAttribute("count")); i++ ) { %>
                <label for="<%=i%>" > <%="Number" + (i+1) %> : +</label>
                <input type="tel" id="<%="firstPart"+i%>" name="<%="firstPart"+i%>" required placeholder="code" >
                <input type="tel" id="<%="secondPart"+i%>"  name ="<%="secondPart"+i%>" required placeholder="number" > <br/>

        <% }
        }%>

    <% if(request.getAttribute("databaseError") != null){ %>
    <p style="color:red">Some information is incorrect</p>
    <%} %>
    <input type="submit" value="Submit" name="addEmployee" class="btn btn-success" />


</form>
</div>

</body>
</html>
