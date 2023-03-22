package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class Areas {

    private String id_area = "";

    public Areas() {
    }

    public String getIDArea(String nombre) {
        String consultaID = "SELECT id_area FROM areas WHERE nombre_area = '" + nombre + "'";
        try {
            Connection cn = new Conexion().conectar();
            PreparedStatement obtenerIDArea = cn.prepareStatement(consultaID);
            ResultSet rs = obtenerIDArea.executeQuery();
            while (rs.next()) {
                id_area = rs.getString("id_area");
            }

            cn.close();
            obtenerIDArea.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_area;
    }

}
