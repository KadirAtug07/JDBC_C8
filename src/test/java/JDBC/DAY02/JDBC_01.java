package JDBC.DAY02;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBC_01 {


    /*ResultSet Methods

    * next() -> moves to the next row
    * previous() - > moves cursor to the previous
    * first() - > jumps to the first row
    * last() -> jumps to the last row
    * absolute(int row) -> goes to the specific rows
    * beforeFirst() jumps to top of the result table
    * afterLast() - > jumps to the bottom of the result table
    * getRow() -> returns the index of current row
    * getObject() -> reads the data from column
    * */
    /*ResultSet Types
      type_scroll_insensitive: the cursor can scroll forward and backward
      type_scroll_insensitive: the cursor can scroll forward and backward but others can not change your database
      concur_read_only: read-only. this is the default
    * */

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    @BeforeEach
    public void connectDB() {
        String dbUrl="jdbc:oracle:thin:@ec2-54-147-179-30.compute-1.amazonaws.com:1521:XE";
        String userName="hr";
        String password="hr";

        try {
            connection=DriverManager.getConnection(dbUrl,userName,password);
            System.out.println("Connection is successful");
        }catch (SQLException e){
            System.out.println("Connection is failed");
            throw new RuntimeException();
        }
    }
    public static Statement getStatement() throws SQLException {
        return statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    }
    public static ResultSet getResultSet(String tableName) throws SQLException {
        return  resultSet=statement.executeQuery("Select * from "+ tableName);
    }
    @AfterEach
    public void closeDB() throws SQLException {
      connection.close();
      statement.close();
      resultSet.close();
    }

    @Test
    public void test1() throws SQLException {
        getStatement();
        getResultSet("employees");

        //1st row
        resultSet.next();
        System.out.println("Employee ID: " + resultSet.getString(1) + "\n" +
                "First Name: " + resultSet.getString("first_name") );
    }

    @Test
    public void test2() throws SQLException {
        getStatement();
        getResultSet("countries");

        //1st row
        resultSet.next();
        System.out.println("I'm in First Row");
        System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                "Country Name: " + resultSet.getString("country_name"));

        //2nd row
        resultSet.next();
        System.out.println("I'm in Second Row");
        System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                "Country Name: " + resultSet.getString("country_name"));

        //move to first row from second row
        resultSet.previous();
        System.out.println("I'm in First Row by using previous method");
        System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                "Country Name: " + resultSet.getString("country_name"));

        resultSet.next();
        System.out.println("I'm in Second Row");
        System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                "Country Name: " + resultSet.getString("country_name"));

        resultSet.next();
        System.out.println("I'm in 3rd Row");
        System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                "Country Name: " + resultSet.getString("country_name"));

        //move cursor to the first row
        resultSet.first();
        System.out.println("I'm in " + resultSet.getRow() +"st row");
        System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                "Country Name: " + resultSet.getString("country_name"));

        resultSet.next();
        System.out.println("I'm in " + resultSet.getRow() +"nd row");
        System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                "Country Name: " + resultSet.getString("country_name"));

        resultSet.beforeFirst();
        //will go above result table and it'll not read the table
        /*System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                "Country Name: " + resultSet.getString("country_name"));*/

        resultSet.next();
        System.out.println("I'm in " + resultSet.getRow() +"st row");
        System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                "Country Name: " + resultSet.getString("country_name"));

        resultSet.next();
        resultSet.next();
        resultSet.next();
        System.out.println("***********Wrong Answer**************");
        //I'm in 4th row now
        //I need to print all country names from first row to the last row
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2) + " row number: " + resultSet.getRow());
        }
        System.out.println("***********Right Answer**************");
        resultSet.beforeFirst();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2) + " row number: " + resultSet.getRow());
        }
        System.out.println("*******************************************");
        //i'm in first row
        resultSet.first();
        //I need to move to last row
        resultSet.last();
        System.out.println("i'm in " + resultSet.getRow());
        System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                "Country Name: " + resultSet.getString("country_name"));

        resultSet.afterLast();
        //no result since i'm out of table in the below
        /*System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                "Country Name: " + resultSet.getString("country_name"));*/

        resultSet.next();
        resultSet.next();
        resultSet.next();
        resultSet.next();
        resultSet.next();
        resultSet.previous();
        System.out.println("i'm in " + resultSet.getRow());
        System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                "Country Name: " + resultSet.getString("country_name"));

        //i'm in first row
        resultSet.first();
        System.out.println();
        System.out.println("********** Print Country Names from last row to the first row******************");
        resultSet.afterLast();
        while (resultSet.previous()) {
            System.out.println("i'm in " + resultSet.getRow());
            System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                    "Country Name: " + resultSet.getString("country_name"));
        }

        //I'm in first row
        resultSet.first();
        //I'll check if i'm in first row
        System.out.println(resultSet.isFirst());
        if (resultSet.isFirst()){
            System.out.println("Country ID: " + resultSet.getString(1) + "\n" +
                    "Country Name: " + resultSet.getString("country_name"));
        }

        resultSet.last();
        System.out.println(resultSet.isLast());

        resultSet.beforeFirst();
        System.out.println(resultSet.isBeforeFirst());

        resultSet.afterLast();
        System.out.println(resultSet.isAfterLast());

    }




}
