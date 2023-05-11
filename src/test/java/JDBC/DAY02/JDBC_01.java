package JDBC.DAY02;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

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
        return statement=connection.createStatement();
    }
    public static ResultSet getResultSet(String tableName) throws SQLException {
        return  resultSet=statement.executeQuery("Select * from"+ tableName);
    }
    @AfterEach
    public void closeDB() throws SQLException {
      connection.close();
      statement.close();
      resultSet.close();
    }


}
