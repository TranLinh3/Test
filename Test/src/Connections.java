import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    public static java.sql.Connection getMssql() throws SQLException {
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=exam";
        String username ="sa";
        String password ="123";
        java.sql.Connection connection = DriverManager.getConnection(dbURL,username,password);
        return connection;
    }
}