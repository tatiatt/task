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


public class EmployeeEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/EditEmployees.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            EmployeeDao daoObject = new EmployeeDao();
            int id = Integer.parseInt(request.getParameter("id"));

            String enteredId = request.getParameter("personalId");
            if (!daoObject.getPersonalId(id).equals(enteredId) && daoObject.alreadyInDatabase(enteredId)) {
                request.setAttribute("samePersonalId" + id, "samePersonalId" + id);
                request.getRequestDispatcher("/WEB-INF/EditEmployees.jsp").forward(request, response);
                return;
            }


            Employee employeeToEdit = new Employee();
            // all these values are required, so we need to check salary and salary currency
            employeeToEdit.setFirstName(request.getParameter("firstName").trim());
            employeeToEdit.setLastName(request.getParameter("lastName").trim());
            employeeToEdit.setPersonalId(request.getParameter("personalId").trim());
            employeeToEdit.setBirthDate(request.getParameter("birthDate").trim());
            employeeToEdit.setNationality(request.getParameter("nationality").trim());



            if(!request.getParameter("salary").equals("")){
                try {
                    double d = Double.parseDouble(request.getParameter("salary"));
                    if(d<0){
                        request.setAttribute("salaryNegative"+id,"salaryNegative");
                        request.getRequestDispatcher("/WEB-INF/EditEmployees.jsp").forward(request, response);
                        return;
                    }
                    employeeToEdit.setSalary(d);
                }catch (NumberFormatException e) {
                    request.setAttribute("salaryInvalid"+id,"salaryInvalid");
                    request.getRequestDispatcher("/WEB-INF/EditEmployees.jsp").forward(request, response);
                    return;
                }
            }

            employeeToEdit.setSalaryCurrency(request.getParameter("salaryCurrency").trim());
            try{
                daoObject.editEmployee(employeeToEdit, id);
                List<String> numbers = new ArrayList<>();
                for (int i = 0; i < daoObject.numberOfPhones(id); i++) {
                    numbers.add(request.getParameter(String.valueOf(id + i)));
                }
                daoObject.editPhoneNumber(id, numbers);
            }catch (SQLException e){
                request.setAttribute("baseError"+id,"baseError"+id);
                request.getRequestDispatcher("/WEB-INF/EditEmployees.jsp").forward(request,response);
                return;
            }


            request.getRequestDispatcher("/WEB-INF/EditEmployees.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
