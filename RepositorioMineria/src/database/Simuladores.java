package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class Simuladores {

    private String id_simulador = "";

    public Simuladores() {
    }

    public String getIDSimulador(String nombre) {
        String consultaID = "SELECT id_simulador FROM simuladores WHERE nombre_simulador = '" + nombre + "'";
        try {
            Connection cn = new Conexion().conectar();
            PreparedStatement obtenerIDSimulador = cn.prepareStatement(consultaID);
            ResultSet rs = obtenerIDSimulador.executeQuery();
            while (rs.next()) {
                id_simulador = rs.getString("id_simulador");
            }

            cn.close();
            obtenerIDSimulador.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_simulador;
    }

}
