package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class Simuladores {

    String id_simulador = "", nombre_simulador = "", requisitos = "", tutorial = "";
    String costo = "", caracteristicas = "", link = "", fecha_consulta = "";

    public Simuladores() {
    }

    public String getIDSimulador(String nombre) {
        String consultaID = "select id_simulador from simuladores where nombre_simulador = '" + nombre + "'";
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
