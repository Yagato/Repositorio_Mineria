package repositoriomineria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class Passwords {
    
    public static String cipher(String password, String salt) {
        return DigestUtils.sha256Hex(password + salt);
    }
    
    public static String getPassword(String id_usuario) {
        String password = "";
        String query = "SELECT contrasenia FROM usuarios WHERE id_usuario = '" + id_usuario + "'";
        
        try{
            Connection cn = new Conexion().conectar();
            PreparedStatement obtenerIDArea = cn.prepareStatement(query);
            ResultSet rs = obtenerIDArea.executeQuery();
            while(rs.next()){
                password = rs.getString("contrasenia");
            }
            
            cn.close();
            obtenerIDArea.close();
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return password;
    }

}
