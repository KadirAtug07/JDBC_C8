package JDBC.DAY02;

import JDBC.utils.DatabaseUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class JDBC_02 extends DatabaseUtil {


    @BeforeEach
    public void lunchDatabase() {
        DatabaseUtil.setConnection();
    }
    @AfterEach
    public void tearDown() {
        DatabaseUtil.closeConnection();
    }


}
