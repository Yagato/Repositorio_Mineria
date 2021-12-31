/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoriomineria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author Lenovo
 */
public class VerUsuarios extends javax.swing.JFrame {

    String headerUsuarios[] = {"Nombre", "Username", "Teléfono", "Correo", "Rol"};
    DefaultTableModel tableModelUsuarios = new DefaultTableModel(headerUsuarios, 0);
    JTable tablaUsuarios;
    String rolAntiguo = "";
    String user = "";
    String ipAddress;
    PantallaPrincipal pp;
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit()
                .getImage(ClassLoader.getSystemResource("imagenes/cascoIcon.png"));
        return retValue;
    }
    
    public VerUsuarios(String username, String ip, PantallaPrincipal p) {
        super("Usuarios del sistema");
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(getIconImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.user = username;
        this.ipAddress = ip;
        this.pp = p;
        
        comboBoxRol.removeAllItems();
        
        comboBoxRol.addItem("-");
        comboBoxRol.addItem("MainAdmin");
        comboBoxRol.addItem("Admin");
        comboBoxRol.addItem("Usuario");
        
        tablaUsuarios = verTablaUsuarios();
        jPanel1.setLayout(new BorderLayout());
        //JScrollPane jScroll = new JScrollPane(tablaUsuarios);
        jPanel1.add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);
        jPanel1.revalidate();
        jPanel1.repaint();
        
        tablaUsuarios.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                textUsername.setText(tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 1).toString());
                rolAntiguo = tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 4).toString();
                comboBoxRol.setSelectedItem(rolAntiguo);
                comboBoxRol.removeItem("-");
                if(rolAntiguo.equals("MainAdmin") && contarMainAdmins() <= 1 || user.equals(textUsername.getText().trim())){
                    comboBoxRol.setEnabled(false);
                    botonEliminarUsuario.setEnabled(false);
                    botonActualizarRol.setEnabled(false);
                }
                else{
                   comboBoxRol.setEnabled(true); 
                   botonEliminarUsuario.setEnabled(true);
                   botonActualizarRol.setEnabled(true);
                }
            }
        });
        
        if(contarUsuarios() == 1){
            botonEliminarUsuario.setEnabled(false);
        }
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                pp.setEnabled(true);
            }
        });
    }
    
    private int contarMainAdmins(){
        int contador = 0;
        
        try{
            String consulta = "SELECT * FROM usuarios WHERE rol = 'MainAdmin'";
            
            Connection cn = new Conexion(ipAddress).conectar();
            PreparedStatement pst = cn.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                contador++;
            }
            
            cn.close();
            pst.close();
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return contador;
    }
    
    private int contarUsuarios(){
        int contador = 0;
        
        try{
            String consulta = "SELECT * FROM usuarios";
            
            Connection cn = new Conexion(ipAddress).conectar();
            PreparedStatement pst = cn.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                contador++;
            }
            
            cn.close();
            pst.close();
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return contador;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboBoxRol = new javax.swing.JComboBox<>();
        botonActualizarRol = new javax.swing.JButton();
        botonEliminarUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(2, 1));

        jPanel1.setBackground(new java.awt.Color(247, 242, 216));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 798, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(247, 242, 216));
        jPanel2.setLayout(new java.awt.GridLayout(3, 2, 20, 10));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Usuario seleccionado:");
        jPanel2.add(jLabel1);

        textUsername.setEditable(false);
        textUsername.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel2.add(textUsername);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Rol:");
        jPanel2.add(jLabel2);

        comboBoxRol.setBackground(new java.awt.Color(255, 255, 51));
        comboBoxRol.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboBoxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(comboBoxRol);

        botonActualizarRol.setBackground(new java.awt.Color(253, 193, 1));
        botonActualizarRol.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonActualizarRol.setText("Actualizar rol");
        botonActualizarRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarRolActionPerformed(evt);
            }
        });
        jPanel2.add(botonActualizarRol);

        botonEliminarUsuario.setBackground(new java.awt.Color(255, 0, 0));
        botonEliminarUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonEliminarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        botonEliminarUsuario.setText("Eliminar usuario");
        botonEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarUsuarioActionPerformed(evt);
            }
        });
        jPanel2.add(botonEliminarUsuario);

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActualizarRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarRolActionPerformed
        String username = textUsername.getText().trim();
        String rol = comboBoxRol.getSelectedItem().toString();
        
        if(rolAntiguo.equals("")){
            JOptionPane.showMessageDialog(null, "Seleccione un usuario.");
        }
        else if(rolAntiguo.equals(rol)){
            JOptionPane.showMessageDialog(null, "No se seleccionó un rol distinto.");
        }
        else{
            try{
                String updateRol = "UPDATE usuarios SET rol = ? WHERE username = '" + username + "'";
                
                Connection cn = new Conexion(ipAddress).conectar();
                PreparedStatement pstUpdateRol = cn.prepareStatement(updateRol);
                
                pstUpdateRol.setString(1, rol);
                pstUpdateRol.executeUpdate();
                
                rolAntiguo = rol;
                
                cn.close();
                pstUpdateRol.close();
                
                tableModelUsuarios.setRowCount(0);
                verTablaUsuarios();
                
                JOptionPane.showMessageDialog(null, "Rol actualizado correctamente.");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_botonActualizarRolActionPerformed

    private void botonEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarUsuarioActionPerformed
        
        if(rolAntiguo.equals("")){
            JOptionPane.showMessageDialog(null, "Seleccione un usuario.");
        }
        else{
            int yes_no = JOptionPane.showConfirmDialog(null, 
                                    "¿Seguro que quiere eliminar este usuario?", "Alerta", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE);

            if(yes_no == 0){
                String username = textUsername.getText().trim();

                try{
                    String deleteUser = "DELETE FROM usuarios WHERE username = '" + username + "'";

                    Connection cn = new Conexion(ipAddress).conectar();
                    PreparedStatement pstDelete = cn.prepareStatement(deleteUser);

                    pstDelete.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");

                    cn.close();
                    pstDelete.close();

                    tableModelUsuarios.setRowCount(0);
                    verTablaUsuarios();

                    rolAntiguo = "";
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_botonEliminarUsuarioActionPerformed

    private JTable verTablaUsuarios(){
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
        tabla.setDefaultEditor(Object.class, null);
        tabla.setFillsViewportHeight(true);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setRowHeight(50);
        tabla.setFont(new Font("Arial", Font.BOLD, 14));
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setBackground(new Color(253,193,1));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        
        String consulta = "SELECT CONCAT(nombres, ' ', apellidos) AS nombre, username, telefono, correo, rol "
                        + "FROM usuarios";
        
        try{
            Connection cn = new Conexion(ipAddress).conectar();
            PreparedStatement pstConsulta = cn.prepareStatement(consulta);
            ResultSet rsConsulta = pstConsulta.executeQuery();
            
            while(rsConsulta.next()){
                Object fila[] = new Object[5];
                
                fila[0] = rsConsulta.getString("nombre");
                fila[1] = rsConsulta.getString("username");
                fila[2] = rsConsulta.getString("telefono");
                fila[3] = rsConsulta.getString("correo");
                fila[4] = rsConsulta.getString("rol");
                
                tableModelUsuarios.addRow(fila);
            }
            tabla.setModel(tableModelUsuarios);
            cn.close();
            pstConsulta.close();
            rsConsulta.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return tabla;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizarRol;
    private javax.swing.JButton botonEliminarUsuario;
    private javax.swing.JComboBox<String> comboBoxRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}
