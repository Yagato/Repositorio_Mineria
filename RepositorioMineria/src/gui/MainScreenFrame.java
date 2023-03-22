package gui;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;
import javaswingdev.drawer.EventDrawer;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class MainScreenFrame extends javax.swing.JFrame {

    private DrawerController drawerController;
    private String idUsuario = "", username = "", rolUsuario = "";

    public MainScreenFrame(String idUser, String user, String rol) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(getIconImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.idUsuario = idUser;
        this.username = user;
        this.rolUsuario = rol;

        showBaseDeDatos();

        if (rolUsuario.equals("Usuario")) {
            drawerController = Drawer.newDrawer(this)
                    .header(new HeaderPanel())
                    .space(5)
                    .addChild(new DrawerItem("Base de Datos").build())
                    .addChild(new DrawerItem("Datos Personales").build())
                    .addChild(new DrawerItem("Manual de Usuario").build())
                    .addFooter(new DrawerItem("Salir").build())
                    .event(new EventDrawer() {
                        @Override
                        public void selected(int i, DrawerItem di) {
                            switch (i) {
                                case 0 ->
                                    showBaseDeDatos();
                                case 1 ->
                                    showDatosPersonales();
                                case 2 ->
                                    openPDF("manual_usuario.pdf");
                                case 3 ->
                                    salir();
                                default -> {
                                }
                            }
                        }
                    })
                    .build();
        } else if (rolUsuario.equals("Admin")) {
            drawerController = Drawer.newDrawer(this)
                    .header(new JLabel("Bienvenido"))
                    .space(5)
                    .addChild(new DrawerItem("Base de Datos").build())
                    .addChild(new DrawerItem("Agregar Simulador").build())
                    .addChild(new DrawerItem("Agregar Areas").build())
                    .addChild(new DrawerItem("Datos Personales").build())
                    .addChild(new DrawerItem("Manual de Usuario").build())
                    .addFooter(new DrawerItem("Salir").build())
                    .event(new EventDrawer() {
                        @Override
                        public void selected(int i, DrawerItem di) {
                            switch (i) {
                                case 0 ->
                                    showBaseDeDatos();
                                case 1 ->
                                    showAgregarSimulador();
                                case 2 ->
                                    showAgregarAreas();
                                case 3 ->
                                    showDatosPersonales();
                                case 4 ->
                                    openPDF("manual_usuario.pdf");
                                case 5 ->
                                    salir();
                                default -> {
                                }
                            }
                        }
                    })
                    .build();
        } else if (rolUsuario.equals("MainAdmin")) {
            drawerController = Drawer.newDrawer(this)
                    .header(new HeaderPanel())
                    .space(5)
                    .addChild(new DrawerItem("Base de Datos").build())
                    .addChild(new DrawerItem("Agregar Simulador").build())
                    .addChild(new DrawerItem("Agregar Areas").build())
                    .addChild(new DrawerItem("Datos Personales").build())
                    .addChild(new DrawerItem("Ver Usuarios").build())
                    .addChild(new DrawerItem("Manual de Usuario").build())
                    .addChild(new DrawerItem("Manual Tecnico").build())
                    .addFooter(new DrawerItem("Salir").build())
                    .event(new EventDrawer() {
                        @Override
                        public void selected(int i, DrawerItem di) {
                            switch (i) {
                                case 0 ->
                                    showBaseDeDatos();
                                case 1 ->
                                    showAgregarSimulador();
                                case 2 ->
                                    showAgregarAreas();
                                case 3 ->
                                    showDatosPersonales();
                                case 4 ->
                                    showVerUsuarios();
                                case 5 ->
                                    openPDF("manual_usuario.pdf");
                                case 6 ->
                                    openPDF("manual_tecnico.pdf");
                                case 7 ->
                                    salir();
                                default -> {
                                }
                            }
                        }
                    })
                    .build();
        }

    }

    private void showBaseDeDatos() {
        DatabaseMenuPanel dbMenuPanel = new DatabaseMenuPanel(this, rolUsuario);
        screensPanel.removeAll();
        screensPanel.add(dbMenuPanel);
        screensPanel.revalidate();
    }

    private void showAgregarSimulador() {
        AgregarSimuladorPanel agregarSimuladorPanel = new AgregarSimuladorPanel();
        screensPanel.removeAll();
        screensPanel.add(agregarSimuladorPanel);
        screensPanel.revalidate();
    }

    private void showAgregarAreas() {
        AgregarAreasPanel agregarAreasPanel = new AgregarAreasPanel();
        screensPanel.removeAll();
        screensPanel.add(agregarAreasPanel);
        screensPanel.revalidate();
    }

    private void showDatosPersonales() {
        DatosPersonalesPanel datosPersonalesPanel = new DatosPersonalesPanel(idUsuario, username, rolUsuario, this);
        screensPanel.removeAll();
        screensPanel.add(datosPersonalesPanel);
        screensPanel.revalidate();
    }

    private void showVerUsuarios() {
        VerUsuariosPanel verUsuariosPanel = new VerUsuariosPanel(username);
        screensPanel.removeAll();
        screensPanel.add(verUsuariosPanel);
        screensPanel.revalidate();
    }

    private void salir() {
        this.dispose();
        new InicioSesionFrame().setVisible(true);
    }

    private void openPDF(String filename) {
        try {
            Path tempOutput = null;
            tempOutput = Files.createTempFile(filename, ".pdf");
            tempOutput.toFile().deleteOnExit();
            InputStream is = getClass().getResourceAsStream("/documentacion/" + filename);
            Files.copy(is, tempOutput, StandardCopyOption.REPLACE_EXISTING);
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.OPEN)) {
                    desktop.open(tempOutput.toFile());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        masterPanel = new javax.swing.JPanel();
        openMenuPanel = new javax.swing.JPanel();
        btnMenu = new javax.swing.JButton();
        screensPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(74, 75, 80));
        setPreferredSize(new java.awt.Dimension(900, 720));

        masterPanel.setBackground(new java.awt.Color(74, 75, 80));
        masterPanel.setLayout(new java.awt.BorderLayout());

        openMenuPanel.setBackground(new java.awt.Color(74, 75, 80));

        btnMenu.setBackground(new java.awt.Color(102, 102, 102));
        btnMenu.setForeground(new java.awt.Color(255, 255, 255));
        btnMenu.setText("Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout openMenuPanelLayout = new javax.swing.GroupLayout(openMenuPanel);
        openMenuPanel.setLayout(openMenuPanelLayout);
        openMenuPanelLayout.setHorizontalGroup(
            openMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(openMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMenu)
                .addContainerGap(7, Short.MAX_VALUE))
        );
        openMenuPanelLayout.setVerticalGroup(
            openMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(openMenuPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnMenu)
                .addContainerGap(676, Short.MAX_VALUE))
        );

        masterPanel.add(openMenuPanel, java.awt.BorderLayout.LINE_START);

        screensPanel.setBackground(new java.awt.Color(74, 75, 80));
        screensPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        screensPanel.setLayout(new java.awt.GridLayout(1, 1));
        masterPanel.add(screensPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(masterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(masterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        if (drawerController.isShow()) {
            drawerController.hide();
        } else {
            drawerController.show();
        }
    }//GEN-LAST:event_btnMenuActionPerformed

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit()
                .getImage(ClassLoader.getSystemResource("imagenes/cascoIcon.png"));
        return retValue;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenu;
    private javax.swing.JPanel masterPanel;
    private javax.swing.JPanel openMenuPanel;
    private javax.swing.JPanel screensPanel;
    // End of variables declaration//GEN-END:variables
}
