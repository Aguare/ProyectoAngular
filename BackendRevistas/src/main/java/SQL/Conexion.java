package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marco
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/AppRevistas";
    private String user = "adminAguare";
    private String password = "Aadmin_1!";
    private static Connection conexion = null;

    public static Connection Conexion() {
        if (conexion == null) {
            new Conexion();
        }
        return conexion;
    }

    private Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, user, password);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("No se ha podido generar la conexion");
        }
    }
}
