package JDBC.DAY02;

import JDBC.utils.DatabaseUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBC_02 extends DatabaseUtil {


    @BeforeEach
    public void lunchDatabase() {
        DatabaseUtil.setConnection();
    }
    @AfterEach
    public void tearDown() {
        DatabaseUtil.closeConnection();
    }
    @Test
    public void Test1() throws SQLException {
        ResultSet resultSet=DatabaseUtil.executeQuery("Select * from employees");
        resultSet.next();
        System.out.println("First Name: " + resultSet.getString("first_name"));

        /*
        * ResultSetMetaData - > it's an object that used to get information about properties of the column
        * such as column name,count of columns,types
        * */

        ResultSetMetaData resultSetMetaData= resultSet.getMetaData();

        System.out.println("Column index is: " + resultSet.getFetchSize());

        //in order to get column count we need to use ResultSetMetaData
        System.out.println("Column's size is: " + resultSetMetaData.getColumnCount());

        //print name of the second column name
        String columnName=resultSetMetaData.getColumnName(2);
        System.out.println(columnName);

        System.out.println("******************************");
        System.out.println("************Print All Column Names with for loop******************");
        int columnCount= resultSetMetaData.getColumnCount();
        for (int i=1 ; i<=columnCount ; i++) {
            System.out.print(resultSetMetaData.getColumnName(i) + "\t");
        }
        System.out.println();
        for (int i=1 ; i<=columnCount ; i++) {
            System.out.print(resultSet.getString(i) + "\t");
        }
        System.out.println();
        System.out.println("******************************");
        System.out.println("************Print All Column Names with ArrayList******************");
        List<String> employeeColumnNames= new ArrayList<>();
        for (int i=1; i<=columnCount ; i++){
            employeeColumnNames.add(resultSetMetaData.getColumnName(i));
        }
        System.out.println(employeeColumnNames);
    }


}
