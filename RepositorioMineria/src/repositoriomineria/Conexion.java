package repositoriomineria;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */


import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    
    public static Connection conectar(){       
        
        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/repositoriomineria","root","");
           /* Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip_Address+":3306/repositoriomineria",
                    "username","password");*/
          return cn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return(null);
    }
    
    /*
    public static Connection conectar() {
        Dotenv dotenv = Dotenv.configure().load();
        if(dotenv.get("RDS_HOSTNAME") != null) {
            try {
                String dbName = dotenv.get("RDS_DB_NAME");
                String userName = dotenv.get("RDS_USERNAME");
                String password = dotenv.get("RDS_PASSWORD");
                String hostname = dotenv.get("RDS_HOSTNAME");
                String port = dotenv.get("RDS_PORT");
                String jbdcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                Connection cn = DriverManager.getConnection(jbdcUrl);
                return cn;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    */
    
    /*static String ip_Address;
    
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
    }*/
    
    
    
}
