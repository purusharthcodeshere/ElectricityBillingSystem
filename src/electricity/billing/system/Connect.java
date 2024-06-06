package electricity.billing.system;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class Connect {

    Connection connection;
    Statement statement;

    Connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root",
                    "VeryStrongPassword!@#$4321");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
