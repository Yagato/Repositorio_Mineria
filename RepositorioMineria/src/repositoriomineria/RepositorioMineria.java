package repositoriomineria;

import com.formdev.flatlaf.FlatDarkLaf;
import gui.MainScreenFrame;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 * @author Ocampo Mora
 */


public class RepositorioMineria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //FlatDarkLaf.setup();
        //new InicioSesion().setVisible(true);
        //new PantallaPrincipal("1", "Yagato", "MainAdmin", "192.168.0.4").setVisible(true);
        new MainScreenFrame("1", "Yagato", "MainAdmin").setVisible(true);
        //new MainScreenFrame("37", "Adminn", "Admin").setVisible(true);
        //new MainScreenFrame("13", "Uno", "Usuario").setVisible(true);
    }
    
}