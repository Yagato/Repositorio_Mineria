package database;

import java.awt.List;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class Consultas {

    public Consultas() {

    }

    public JComboBox getAreas() {

        JComboBox comboAreas = new JComboBox();
        comboAreas.removeAllItems();

        try {
            Connection cn = new Conexion().conectar();
            PreparedStatement pstAreas = cn.prepareStatement("select nombre_area from areas");
            ResultSet rs = pstAreas.executeQuery();

            while (rs.next()) {
                comboAreas.addItem(rs.getString("nombre_area"));
            }

            cn.close();
            pstAreas.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comboAreas;
    }

    public List getListAreas() {
        List areas = new List();

        try {
            Connection cn = new Conexion().conectar();
            PreparedStatement pstAreas = cn.prepareStatement("select nombre_area from areas");
            ResultSet rs = pstAreas.executeQuery();

            while (rs.next()) {
                areas.add(rs.getString("nombre_area"));
            }

            cn.close();
            pstAreas.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return areas;
    }

    public InputStream getLogo(String id_simulador) {
        InputStream is = null;

        String consulta = "SELECT simuladores.logo "
                + "FROM simuladores "
                + "WHERE simuladores.id_simulador = '" + id_simulador + "' ";

        try {
            Connection cn = new Conexion().conectar();
            PreparedStatement pstConsulta = cn.prepareStatement(consulta);
            ResultSet rs = pstConsulta.executeQuery();

            while (rs.next()) {
                is = rs.getBinaryStream("logo");
            }

            cn.close();
            pstConsulta.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return is;
    }

}
