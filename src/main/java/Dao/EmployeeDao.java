package Dao;


import Model.Employee;

import java.sql.*;
import java.util.List;

public class EmployeeDao {

    private Connection connection;

    private final String INSERT_EMPLOYEE ="INSERT INTO EmployeeTable" +
            "  (first_name,  last_name, personal_id, birth_date, nationality, salary,salary_currency) VALUES " +
            " (?, ?, ?, ?, ?,?,?);";

    private final String INSERT_PHONE = "INSERT INTO PhoneNumbers"+
            "(id, phone_number) VALUES" +
            "(?,?)";

    private final String EDIT_EMPLOYEE ="UPDATE EmployeeTable SET " +
            "first_name =" + " ? " +"," + " last_name =" + " ? " +  "," +" personal_id =" + " ?"+ "," +
            " birth_date =" + " ? "  + "," +" nationality =" + " ? " + "," + " salary =" + " ? " + "," +
            " salary_currency = " + " ? " + "WHERE id= " + "?";



    private final String DELETE_OLD_NUMBERS = "DELETE FROM PhoneNumbers WHERE id =  " + "?";

    private final String GET_ALL = "SELECT * FROM EmployeeTable";

    public EmployeeDao() throws SQLException, ClassNotFoundException {
        //enter password
        connection =  DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employees", "root", "");

    }
    public void registerEmployee(Employee currEmployee) throws ClassNotFoundException, SQLException {

        PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE);
        statement.setString(1,currEmployee.getFirstName());
        statement.setString(2,currEmployee.getLastName());
        statement.setString(3,currEmployee.getPersonalId());
        statement.setString(4,currEmployee.getBirthDate());
        statement.setString(5,currEmployee.getNationality());
        statement.setDouble(6,currEmployee.getSalary());

        if(alreadyInDatabase(currEmployee.getPersonalId())){
            return;
        }
        if(currEmployee.getSalaryCurrency() == null){
            statement.setNull(7, Types.NULL);
        } else{
            statement.setString(7,currEmployee.getSalaryCurrency());

        }

        int res = statement.executeUpdate();
        if(res < 0){
            return;
        }

        if(currEmployee.getPhoneNumbers() != null){
            PreparedStatement statementForPhones = connection.prepareStatement(INSERT_PHONE);
            for (int i=0; i< currEmployee.getPhoneNumbers().size(); i++){
                statementForPhones.setInt(1,indexOfLastAdded());
                statementForPhones.setString(2, currEmployee.getPhoneNumbers().get(i));
                statementForPhones.executeUpdate();
            }
        }


    }

    private int indexOfLastAdded() throws SQLException {
        String getValues = "select count(*) from EmployeeTable";
        Statement newStatement = connection.createStatement();
        ResultSet rs = newStatement.executeQuery(getValues);
        rs.next();
        return  rs.getInt(1);
    }


    public  boolean alreadyInDatabase(String personal_id) throws SQLException {
        String findInBase =  "select count(*) from EmployeeTable where personal_id = "+surroundWithSingleQuotes(personal_id);
        Statement newStatement = connection.createStatement();
        ResultSet rs = newStatement.executeQuery(findInBase);
        rs.next();
        return  rs.getInt(1)==1;
    }

    public String getPersonalId(int id) throws SQLException {
        String searchString = "select personal_id from EmployeeTable where id =" +(id);
        Statement stm = connection.createStatement();
        ResultSet answer = stm.executeQuery(searchString);
        answer.next();

        return answer.getString(1);

    }
    public static String surroundWithSingleQuotes(String value){
        return "'" +
                value +
                "'";
    }


    public ResultSet getAllEmployees() throws SQLException {
        Statement newStatement = connection.createStatement();
        return newStatement.executeQuery(GET_ALL);
    }

    public ResultSet getAllNumberForEmployee(int id) throws SQLException {
        String findPhones = "SELECT * from PhoneNumbers where id = " +id;
        Statement newStatement = connection.createStatement();
        return newStatement.executeQuery(findPhones);
    }

    public int numberOfPhones(int id) throws SQLException {
        ResultSet set = getAllNumberForEmployee(id);
        int i=0;
        while(set.next()){
            i++;
        }
        return i;
    }

    public void editPhoneNumber(int id, List<String> numbers) throws SQLException {
        PreparedStatement editNumber = connection.prepareStatement(DELETE_OLD_NUMBERS);
        editNumber.setInt(1,id);
        editNumber.executeUpdate();

        PreparedStatement addNewPhone = connection.prepareStatement(INSERT_PHONE);

        for(String currNum : numbers){
            addNewPhone.setInt(1,id);
            addNewPhone.setString(2,currNum);
            addNewPhone.executeUpdate();
        }

    }

    public void editEmployee(Employee currEmployee, int id) throws SQLException {

        PreparedStatement editEmp = connection.prepareStatement(EDIT_EMPLOYEE);
        editEmp.setString(1,currEmployee.getFirstName());
        editEmp.setString(2,currEmployee.getLastName());
        editEmp.setString(3,currEmployee.getPersonalId());
        editEmp.setString(4,currEmployee.getBirthDate());
        editEmp.setString(5, currEmployee.getNationality());
        editEmp.setDouble(6,currEmployee.getSalary());
        editEmp.setString(7,currEmployee.getSalaryCurrency());
        editEmp.setInt(8,id);
        editEmp.executeUpdate();
    }
}
