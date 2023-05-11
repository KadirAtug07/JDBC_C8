package JDBC.DAY01;

import java.sql.*;


public class JDBC_01 {


    public static void main(String[] args) throws SQLException {

        //create connection
        Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@ec2-54-147-179-30.compute-1.amazonaws.com:1521:XE"
                ,"hr","hr");

        //create statement
        Statement statement=connection.createStatement();

        //execute query and store it into resultSet
        ResultSet resultSet=statement.executeQuery("SELECT * FROM EMPLOYEES");


        // this will not get a result since ResultSet.Next was not called
        //System.out.println("First Column Value: " + resultSet.getString(1));

        //cursor will point to the first row of the resultSet
        resultSet.next();
        //print the 1st column of the 1st row
        System.out.println("First Column Value by index: " + resultSet.getString(1));
        //print the 1st column of 1st row by column name
        System.out.println("First column Value by column name: " + resultSet.getString("Employee_id"));


        //print first name of 2nd row in employee table with both by index and column value
         //we need to move cursor to the 2nd row
         resultSet.next();
        System.out.println("First Name: " + resultSet.getString(2));
        System.out.println("First name: " + resultSet.getString("first_name"));

        //print first name and last name of 5th row
        //cursor will move to 3rd row
        resultSet.next();
        //cursor will move to 4th row
        resultSet.next();
        //cursor will move to 5th row
        resultSet.next();
        System.out.println("First Name: " + resultSet.getString("first_name") + "\n"
                + "Last Name: " + resultSet.getString("last_name"));

        System.out.println(resultSet.getFetchSize());
         //we can't use this one resultSet.getFetchSize() here, we'll see it in ResultSetMetaData
        //print the whole values of 5th row
         for (int i=1 ; i<=11 ; i++) {
             System.out.print(resultSet.getString(i));
             System.out.print(" ");
         }

         connection.close();
         statement.close();
         resultSet.close();


    }


}
