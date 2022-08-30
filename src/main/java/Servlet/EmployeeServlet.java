package Servlet;

import Dao.EmployeeDao;
import Model.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String personalId = request.getParameter("personalId").trim();
        String birthDate = request.getParameter("birthDate").trim();
        String nationality = request.getParameter("nationality").trim();
        String salary = request.getParameter("salary").trim();
        String salaryCurrency = request.getParameter("salaryCurrency").trim();

        if(request.getParameter("phoneNum") != null){


            request.setAttribute("firstName",firstName);
            request.setAttribute("lastName",lastName);
            request.setAttribute("personalId",personalId);
            request.setAttribute("birthDate",birthDate);
            request.setAttribute("nationality", nationality);
            request.setAttribute("salary",salary);
            request.setAttribute("salaryCurrency",salaryCurrency);
            request.setAttribute("count",request.getParameter("phoneNumberCount"));
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

        }
        if(request.getParameter("addEmployee") != null){



            Employee newEmployee = new Employee();
            newEmployee.setFirstName(firstName);
            newEmployee.setLastName(lastName);
            newEmployee.setPersonalId(personalId);
            newEmployee.setBirthDate(birthDate);
            newEmployee.setNationality(nationality);

            if(!salary.equals("")){
                try {
                    double d = Double.parseDouble(salary);
                    if(d<0){
                        request.setAttribute("salaryNegative","salaryNegative");
                        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                        return;
                    }
                    newEmployee.setSalary(d);

                } catch (NumberFormatException e) {
                    request.setAttribute("salaryInvalid","salaryInvalid");
                    request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                    return;
                }
            }
            if(!salaryCurrency.equals("")){
                newEmployee.setSalaryCurrency(salaryCurrency);
            }

            int numberOfPhones = Integer.parseInt(request.getParameter("phoneNumberCount"));
            if(numberOfPhones!= 0){
                List<String> phones = new ArrayList<>(numberOfPhones);
                for(int i=0 ; i< numberOfPhones; i++){
                    String currNum = "+" + request.getParameter("firstPart"+i)+request.getParameter("secondPart"+i);
                    phones.add(currNum);

                }
                newEmployee.setPhoneNumbers(phones);
            }


            try {
                EmployeeDao daoObj = new EmployeeDao();
                if(daoObj.alreadyInDatabase(personalId)){
                    request.setAttribute("sameId","sameId");
                    request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                    return;
                }
                daoObj.registerEmployee(newEmployee);

            } catch (SQLException | ClassNotFoundException e) {

                request.setAttribute("databaseError","databaseError");
                request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                return;
            }
            request.getRequestDispatcher("/WEB-INF/EditEmployees.jsp").forward(request, response);
        }



    }
}
