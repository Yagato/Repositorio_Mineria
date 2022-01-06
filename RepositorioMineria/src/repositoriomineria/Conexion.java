package repositoriomineria;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */

import java.net.InetAddress;
import java.sql.*;

public class Conexion {
    
    static String ip_Address;
    
    public Conexion(String ip){
        Conexion.ip_Address = ip;
    }
    
    public static InetAddress getHostIP(String host){
       InetAddress ip = null;
       
       try{
           ip = InetAddress.getByName(host); 
           return ip;
       }
       catch(Exception e){
           e.printStackTrace();
       }
              
       return null;
    }
    
    public static Connection conectar(){       
        
        try{
            //Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/repositoriomineria","root","");
            Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip_Address+":3306/repositoriomineria",
                    "username","password");
            return cn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return(null);
    }
    
    
    
}
