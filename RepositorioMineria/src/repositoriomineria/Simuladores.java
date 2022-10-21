package repositoriomineria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * @author YGT
 */
public class Simuladores {
    
    String id_simulador = "", nombre_simulador = "", requisitos = "", tutorial = ""; 
    String costo = "", caracteristicas = "", link = "", fecha_consulta = "";
    String ipAddress;
    
    public Simuladores(String ip){
        this.ipAddress = ip;
    }
    
    public String getIDSimulador(String nombre){
        String consultaID = "select id_simulador from simuladores where nombre_simulador = '" + nombre + "'";
        try{
            Connection cn = new Conexion(ipAddress).conectar();
            PreparedStatement obtenerIDSimulador = cn.prepareStatement(consultaID);
            ResultSet rs = obtenerIDSimulador.executeQuery();
            while(rs.next()){
                id_simulador = rs.getString("id_simulador");
            }
            
            cn.close();
            obtenerIDSimulador.close();
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return id_simulador;
    }
    
}
