package repositoriomineria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 * @author Ocampo Mora
 */

public class PantallaPrincipal extends javax.swing.JFrame {
    
    String idUsuario = "", username = "", rolUsuario = "";
    JTable tablaSimuladores;
    String headerSimuladores[] = {"Logo", "Nombre", "Costo (MXN)"};
    DefaultTableModel tableModelSimuladores = new DefaultTableModel(headerSimuladores, 0);
    JButton refreshButton = new JButton();
    //JButton searchButton = new JButton("Buscar");
    JTextField searchText = new JTextField(20);
    JComboBox comboBoxAreas = new JComboBox();
    String ipAddress;

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit()
                .getImage(ClassLoader.getSystemResource("imagenes/cascoIcon.png"));
        return retValue;
    }
    
    public PantallaPrincipal(String idUser, String user, String rol, String ip) {
        //super("Pantalla Principal");
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(getIconImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        PantallaPrincipal frame = this;
        
        this.idUsuario = idUser;
        this.username = user;
        this.rolUsuario = rol;
        this.ipAddress = ip;
        
        if(rolUsuario.equals("Usuario")){
            agregarSimuladorMenu.setVisible(false);
            menuAgregarAreas.setVisible(false);
            menuUsuarios.setVisible(false);
            menuManualTecnico.setVisible(false);
        }
        else if(rolUsuario.equals("Admin")){
            menuUsuarios.setVisible(false);
        }
        
        tablaSimuladores = verTabla();
        
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(new JScrollPane(tablaSimuladores), BorderLayout.CENTER);
        jPanel1.revalidate();
        jPanel1.repaint();
        
        tablaSimuladores.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                if(e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)){
                    String nombreSimulador = tablaSimuladores.getValueAt(tablaSimuladores.getSelectedRow(), 1)
                            .toString();
                    new VerSimulador(frame, nombreSimulador, ipAddress, rol).setVisible(true);
                    frame.setEnabled(false);
                }
            }
        });
        
        menuBar.add(new JSeparator());
        
        //searchButton.setBackground(new Color(253,193,1));
        /*searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);*/
        
        //searchText.setBackground(new Color(121,125,130));
        searchText.setText("Buscar...");
        searchText.setFont(new Font("Arial", Font.BOLD, 14));
        searchText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                //searchText.setForeground(Color.WHITE);
                if(searchText.getText().equals("Buscar..."))
                    searchText.setText("");
                super.focusGained(e);
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                //searchText.setForeground(Color.GRAY);
                if(searchText.getText().equals(""))
                    searchText.setText("Buscar...");
                super.focusLost(e);
            }
        });
        
        /*
        searchButton.addActionListener((ActionEvent evt) -> {
            String nombre = searchText.getText().trim();
            nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
            tableModelSimuladores.setRowCount(0);
            buscarSimulador(nombre);
        });
        */
        
        refreshButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        //refreshButton.setBackground(new Color(153,177,251));
        refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
        refreshButton.setBackground(Color.GREEN);
        /*refreshButton.setContentAreaFilled(false);
        refreshButton.setBorderPainted(false);*/
        try {
            Image img = ImageIO.read(getClass().getResource("/imagenes/refresh.png"));
            Image scaledImage = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            refreshButton.setIcon(new ImageIcon(scaledImage));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        refreshButton.addActionListener((ActionEvent evt) -> {
            tableModelSimuladores.setRowCount(0);
            searchText.setText("");
            comboBoxAreas.setSelectedIndex(0);
            this.verTabla();
        }); 
        
        //menuBar.add(searchButton);
        menuBar.add(searchText);
        
        List areas = new List();
        areas = new Consultas(ipAddress).getListAreas();
        
        //comboBoxAreas.setBackground(new Color(255,255,255));
        comboBoxAreas.setFont(new Font("Arial", Font.BOLD, 14));
        comboBoxAreas.setBackground(new Color(204, 204, 204));
        comboBoxAreas.setForeground(Color.BLACK);
        comboBoxAreas.removeAllItems();
        comboBoxAreas.addItem("-");
                
        for(int i = 0; i < areas.getItemCount(); i++){
            comboBoxAreas.addItem(areas.getItem(i));
        }
        
        comboBoxAreas.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nombreArea = comboBoxAreas.getSelectedItem().toString();
                if(!nombreArea.equals("-")){
                    tableModelSimuladores.setRowCount(0);
                    filtrarPorAreas(nombreArea);
                    //comboBoxAreas.removeItem("-");
                }
            }
        });
        
        menuBar.add(comboBoxAreas);
        menuBar.add(refreshButton);
        //menuBar.add(Box.createHorizontalGlue());
    }
    
    public JTable renderTable() {
        JTable tabla = new JTable(){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, 
                                                tableColumn.getPreferredWidth()));
                return component;
            }
        };
        TablaImagen imgRenderer = new TablaImagen();
        imgRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabla.setDefaultRenderer(Object.class, imgRenderer);
        tabla.setRowHeight(60);
        tabla.setDefaultEditor(Object.class, null);
        tabla.setFillsViewportHeight(true);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setFont(new Font("Arial", Font.BOLD, 16));
        tabla.setBackground(new Color(74, 74, 80));
        tabla.setForeground(Color.WHITE);
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setBackground(new Color(114,137,218));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        
        return tabla;
    }
    
    public JTable verTabla(){
        JTable tabla = renderTable();
        
        String consulta = "SELECT logo, nombre_simulador, costo "
                    + "FROM simuladores "
                    + "GROUP BY simuladores.id_simulador";
        
        try{
            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Object[] fila = new Object[4];
                
                Image dimg = null;
                try{
                    BufferedImage im = ImageIO.read(rs.getBinaryStream("logo"));
                    dimg = im.getScaledInstance(110, 64, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(dimg);
                    fila[0] = new JLabel(icon);
                }
                catch(Exception e){
                    fila[0] = "";
                }
                
                fila[1] = rs.getString("nombre_simulador");
                
                if(rs.getString("costo").equals("") || rs.getString("costo").equals("0"))
                    fila[2] = "GRATIS";
                else
                    fila[2] = rs.getString("costo");
                                
                tableModelSimuladores.addRow(fila);
            }
            
            tabla.setModel(tableModelSimuladores);
            cn.close();
            pst.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return tabla;
    }
    
    public JTable buscarSimulador(String nombre){
        JTable tabla = renderTable();
        
        String consulta = "SELECT logo, nombre_simulador, costo "
                    + "FROM simuladores "
                    + "WHERE nombre_simulador = '" + nombre + "' "
                    + "GROUP BY simuladores.id_simulador";
        
        try{
            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Object[] fila = new Object[4];
                
                Image dimg = null;
                try{
                    BufferedImage im = ImageIO.read(rs.getBinaryStream("logo"));
                    dimg = im.getScaledInstance(110, 64, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(dimg);
                    fila[0] = new JLabel(icon);
                }
                catch(Exception e){
                    fila[0] = "";
                }
                
                fila[1] = rs.getString("nombre_simulador");
                
                if(rs.getString("costo").equals("") || rs.getString("costo").equals("0"))
                    fila[2] = "GRATIS";
                else
                    fila[2] = rs.getString("costo");
                                
                tableModelSimuladores.addRow(fila);
            }
            
            tabla.setModel(tableModelSimuladores);
            cn.close();
            pst.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return tabla;
    }
    
    public JTable filtrarPorAreas(String area){
        JTable tabla = renderTable();
        
        String id_area = new Areas(ipAddress).getIDArea(area);
        
        String consulta = "SELECT logo, costo, nombre_simulador "
                        + "FROM simuladores, areas, simuladorarea "
                        + "WHERE simuladores.id_simulador = simuladorarea.id_simulador "
                        + "AND simuladorarea.id_area = areas.id_area "
                        + "AND simuladorarea.id_area = '" + id_area + "'";
        
        try{
            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Object[] fila = new Object[4];
                
                Image dimg = null;
                try{
                    BufferedImage im = ImageIO.read(rs.getBinaryStream("logo"));
                    dimg = im.getScaledInstance(110, 64, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(dimg);
                    fila[0] = new JLabel(icon);
                }
                catch(Exception e){
                    fila[0] = "";
                }
                
                fila[1] = rs.getString("nombre_simulador");
                
                if(rs.getString("costo").equals("") || rs.getString("costo").equals("0"))
                    fila[2] = "GRATIS";
                else
                    fila[2] = rs.getString("costo");
                                
                tableModelSimuladores.addRow(fila);
            }
            
            tabla.setModel(tableModelSimuladores);
            cn.close();
            pst.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return tabla;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        menuOpciones = new javax.swing.JMenu();
        agregarSimuladorMenu = new javax.swing.JMenuItem();
        menuAgregarAreas = new javax.swing.JMenuItem();
        menuDatosPersonales = new javax.swing.JMenuItem();
        menuUsuarios = new javax.swing.JMenuItem();
        CerrarSesion = new javax.swing.JMenuItem();
        menuDocumentacion = new javax.swing.JMenu();
        menuManualUsuario = new javax.swing.JMenuItem();
        menuManualTecnico = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(253, 193, 1));
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(74, 75, 80));
        jPanel1.setPreferredSize(new java.awt.Dimension(1150, 600));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        menuBar.setBackground(new java.awt.Color(74, 75, 80));

        menuOpciones.setBackground(new java.awt.Color(74, 75, 80));
        menuOpciones.setForeground(new java.awt.Color(255, 255, 255));
        menuOpciones.setText("Opciones");
        menuOpciones.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        agregarSimuladorMenu.setBackground(new java.awt.Color(74, 75, 80));
        agregarSimuladorMenu.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        agregarSimuladorMenu.setForeground(new java.awt.Color(255, 255, 255));
        agregarSimuladorMenu.setText("Agregar simulador");
        agregarSimuladorMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarSimuladorMenuActionPerformed(evt);
            }
        });
        menuOpciones.add(agregarSimuladorMenu);

        menuAgregarAreas.setBackground(new java.awt.Color(74, 75, 80));
        menuAgregarAreas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuAgregarAreas.setForeground(new java.awt.Color(255, 255, 255));
        menuAgregarAreas.setText("Agregar/Editar areas");
        menuAgregarAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAgregarAreasActionPerformed(evt);
            }
        });
        menuOpciones.add(menuAgregarAreas);

        menuDatosPersonales.setBackground(new java.awt.Color(74, 75, 80));
        menuDatosPersonales.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuDatosPersonales.setForeground(new java.awt.Color(255, 255, 255));
        menuDatosPersonales.setText("Datos personales");
        menuDatosPersonales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDatosPersonalesActionPerformed(evt);
            }
        });
        menuOpciones.add(menuDatosPersonales);

        menuUsuarios.setBackground(new java.awt.Color(74, 75, 80));
        menuUsuarios.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        menuUsuarios.setText("Ver Usuarios");
        menuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUsuariosActionPerformed(evt);
            }
        });
        menuOpciones.add(menuUsuarios);

        CerrarSesion.setBackground(new java.awt.Color(74, 75, 80));
        CerrarSesion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        CerrarSesion.setText("Cerrar Sesion");
        CerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarSesionActionPerformed(evt);
            }
        });
        menuOpciones.add(CerrarSesion);

        menuBar.add(menuOpciones);

        menuDocumentacion.setBackground(new java.awt.Color(74, 75, 80));
        menuDocumentacion.setForeground(new java.awt.Color(255, 255, 255));
        menuDocumentacion.setText("Documentación");
        menuDocumentacion.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        menuManualUsuario.setBackground(new java.awt.Color(74, 75, 80));
        menuManualUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuManualUsuario.setForeground(new java.awt.Color(255, 255, 255));
        menuManualUsuario.setText("Manual de Usuario");
        menuManualUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuManualUsuarioActionPerformed(evt);
            }
        });
        menuDocumentacion.add(menuManualUsuario);

        menuManualTecnico.setBackground(new java.awt.Color(74, 75, 80));
        menuManualTecnico.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menuManualTecnico.setForeground(new java.awt.Color(255, 255, 255));
        menuManualTecnico.setText("Manual Técnico");
        menuManualTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuManualTecnicoActionPerformed(evt);
            }
        });
        menuDocumentacion.add(menuManualTecnico);

        menuBar.add(menuDocumentacion);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarSimuladorMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarSimuladorMenuActionPerformed
        new AgregarSimulador(this, ipAddress).setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_agregarSimuladorMenuActionPerformed

    private void CerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarSesionActionPerformed
        this.dispose();
        new InicioSesion().setVisible(true);
    }//GEN-LAST:event_CerrarSesionActionPerformed

    private void menuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUsuariosActionPerformed
        new VerUsuarios(username, ipAddress, this).setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_menuUsuariosActionPerformed

    private void menuManualUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuManualUsuarioActionPerformed
        openFile("manual_usuario");
    }//GEN-LAST:event_menuManualUsuarioActionPerformed

    private void menuManualTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuManualTecnicoActionPerformed
        openFile("manual_tecnico");
    }//GEN-LAST:event_menuManualTecnicoActionPerformed

    private void openFile(String filename) {
        try{
            Path tempOutput = null;
            tempOutput = Files.createTempFile(filename, ".pdf");
            tempOutput.toFile().deleteOnExit();
            InputStream is = getClass().getResourceAsStream("/documentacion/"+ filename + ".pdf");
            Files.copy(is, tempOutput, StandardCopyOption.REPLACE_EXISTING);
            if(Desktop.isDesktopSupported()){
                Desktop desktop = Desktop.getDesktop();
                if(desktop.isSupported(Desktop.Action.OPEN)){
                    desktop.open(tempOutput.toFile());
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void menuAgregarAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAgregarAreasActionPerformed
        new AgregarAreas(this, ipAddress).setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_menuAgregarAreasActionPerformed

    private void menuDatosPersonalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDatosPersonalesActionPerformed
        new DatosPersonales(idUsuario, username, rolUsuario, this, ipAddress).setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_menuDatosPersonalesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CerrarSesion;
    private javax.swing.JMenuItem agregarSimuladorMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem menuAgregarAreas;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuDatosPersonales;
    private javax.swing.JMenu menuDocumentacion;
    private javax.swing.JMenuItem menuManualTecnico;
    private javax.swing.JMenuItem menuManualUsuario;
    private javax.swing.JMenu menuOpciones;
    private javax.swing.JMenuItem menuUsuarios;
    // End of variables declaration//GEN-END:variables
}
