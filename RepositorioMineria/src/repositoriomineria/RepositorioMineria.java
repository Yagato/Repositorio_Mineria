package repositoriomineria;

import gui.InicioSesionFrame;
import gui.MainScreenFrame;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class RepositorioMineria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //new InicioSesionFrame().setVisible(true);
        //new PantallaPrincipal("1", "Yagato", "MainAdmin", "192.168.0.4").setVisible(true);
        new MainScreenFrame("1", "Yagatomon", "MainAdmin").setVisible(true);
        //new MainScreenFrame("37", "Adminn", "Admin").setVisible(true);
        //new MainScreenFrame("38", "Probando", "Probando123!").setVisible(true);
    }
    
}