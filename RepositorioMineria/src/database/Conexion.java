package database;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection conectar() {

        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/repositoriomineria", "root", "");
            return cn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (null);
    }
}
