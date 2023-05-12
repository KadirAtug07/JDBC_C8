package JDBC.utils;

import java.sql.*;

public class DatabaseUtil {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;


    public static void setConnection() {
        String url=ConfigReader.getProperties("DbUrl");
        String userName=ConfigReader.getProperties("userName");
        String password=ConfigReader.getProperties("password");

        try {
            connection= DriverManager.getConnection(url,userName,password);
            System.out.println("Connection is sucsessful");
        }catch (SQLException s){
            s.printStackTrace();
            System.out.println("Connection is failed");
        }
    }
    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }catch (SQLException s) {
            System.out.println("Exception is occurred while closing database");
        }
    }
    public static ResultSet executeQuery(String query) {
        try {
            statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet=statement.executeQuery(query);
        }catch (SQLException s){
            System.out.println("An Error occurred while executing query");
        }
        return resultSet;
    }
}
