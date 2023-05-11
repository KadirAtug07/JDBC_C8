package JDBC.DAY01;

import java.sql.*;

public class JDBC_02 {


    public static void main(String[] args) throws SQLException {

        String dbUrl="jdbc:oracle:thin:@ec2-54-147-179-30.compute-1.amazonaws.com:1521:XE";
        String userName="hr";
        String password="hr";
        Connection connection=null;

        try{
            connection= DriverManager.getConnection(dbUrl,userName,password);
            System.out.println("Connection is created");
        }catch (SQLException s){
            s.printStackTrace();
            System.out.println("Connection is failed");
        }

        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("Select * from Employees");

        //print all employee names and employee last names
        while (resultSet.next()){
            System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
        }

        System.out.println("*****************************************************************");

        //print all country id and country names from countries table
        ResultSet resultSet1=statement.executeQuery("Select * from countries");

        while (resultSet1.next()) {
            System.out.println(resultSet1.getString("Country_id") + " " + resultSet1.getString("country_name"));
        }

      connection.close();
        statement.close();
        resultSet.close();
        resultSet1.close();



    }
}
