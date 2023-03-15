package repositoriomineria;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class DatosPersonales extends javax.swing.JFrame {
    
    PantallaPrincipal pp;

    String id_usuario = "", user = "", rolUser = "";
    
    String nombresViejos;
    String apellidosViejos;
    String usernameViejo;
    String contrasenaVieja;
    String telefonoViejo;
    String correoViejo;
    
    String ipAddress;
    /**
     * Creates new form DatosPersonales
     */
    public DatosPersonales(String idUsuario, String username, String rol, PantallaPrincipal p, String ip) {
        super("Datos Personales");
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(getIconImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.id_usuario = idUsuario;
        this.user = username;
        this.rolUser = rol;
        this.ipAddress = ip;
        
        if(rolUser.equals("MainAdmin"))
            botonBorrar.setVisible(false);
        
        this.verDatos();
        
        this.nombresViejos = textNombres.getText().trim();
        this.apellidosViejos = textApellidos.getText().trim();
        this.usernameViejo = textUsername.getText().trim();
        this.contrasenaVieja = textContrasena.getText().trim();
        this.telefonoViejo = textTelefono.getText().trim();
        this.correoViejo = textCorreo.getText().trim();
        
        this.pp = p;
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                pp.setEnabled(true);
            }
        });
        
    }
    
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit()
                .getImage(ClassLoader.getSystemResource("imagenes/cascoIcon.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonActualizar = new javax.swing.JButton();
        botonBorrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textNombres = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textApellidos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textUsername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textContrasena = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        textCorreo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        textRol = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonActualizar.setBackground(new java.awt.Color(0, 51, 255));
        botonActualizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonActualizar.setForeground(new java.awt.Color(255, 255, 255));
        botonActualizar.setText("Actualizar Datos");
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(botonActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 450, -1, -1));

        botonBorrar.setBackground(new java.awt.Color(255, 0, 0));
        botonBorrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonBorrar.setForeground(new java.awt.Color(255, 255, 255));
        botonBorrar.setText("Borrar cuenta");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });
        getContentPane().add(botonBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 470, -1, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setForeground(new java.awt.Color(240, 240, 240));
        jPanel1.setLayout(new java.awt.GridLayout(7, 2, -100, 30));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("Nombre(s):");
        jPanel1.add(jLabel2);

        textNombres.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textNombres);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("Apellido(s):");
        jPanel1.add(jLabel3);

        textApellidos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textApellidos);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setText("Nombre de usuario:");
        jPanel1.add(jLabel4);

        textUsername.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textUsername);

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setText("Contraseña:");
        jPanel1.add(jLabel5);

        textContrasena.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textContrasena);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setText("Teléfono celular:");
        jPanel1.add(jLabel6);

        textTelefono.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textTelefono);

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setText("Correo electrónico:");
        jPanel1.add(jLabel7);

        textCorreo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textCorreo);

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setText("Rol:");
        jPanel1.add(jLabel8);

        textRol.setEditable(false);
        textRol.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textRol);

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 440, 410));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void verDatos(){
        try{
            String datos = "SELECT * FROM usuarios WHERE username = '" + user + "'";
            
            Connection cn = new Conexion().conectar();
            
            PreparedStatement pstDatos = cn.prepareStatement(datos);
            
            ResultSet rsDatos = pstDatos.executeQuery();
            
            while(rsDatos.next()){
                textNombres.setText(rsDatos.getString("nombres"));
                textApellidos.setText(rsDatos.getString("apellidos"));
                textUsername.setText(rsDatos.getString("username"));
                textContrasena.setText(rsDatos.getString("contrasenia"));
                textTelefono.setText(rsDatos.getString("telefono"));
                textCorreo.setText(rsDatos.getString("correo"));
                textRol.setText(rsDatos.getString("rol"));
            }
            
            cn.close();
            pstDatos.close();
            rsDatos.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        
        String nombres = textNombres.getText().trim();
        String apellidos = textApellidos.getText().trim();
        String username = textUsername.getText().trim();
        String contrasena = textContrasena.getText().trim();
        String telefono = textTelefono.getText().trim();
        String correo = textCorreo.getText().trim();
        
        
        if(nombres.equals(nombresViejos) && apellidos.equals(apellidosViejos) && username.equals(usernameViejo)
           && contrasena.equals(contrasenaVieja) && telefono.equals(telefonoViejo) && correo.equals(correoViejo)){
            
            JOptionPane.showMessageDialog(this, "No se han cambiado datos.");
            
        }
        else{
            try{
                if(nombres.length() != 0 && apellidos.length() != 0 && username.length() != 0 && contrasena.length() != 0
                   && telefono.length() != 0 && correo.length() != 0){
                    
                    String checkUsername = "SELECT * FROM usuarios WHERE username = '" + username + "'";
                    String checkTelefono = "SELECT * FROM usuarios WHERE telefono = '" + telefono + "'";
                    String checkCorreo = "SELECT * FROM usuarios WHERE correo = '" + correo + "'";

                    Connection cn = new Conexion().conectar();

                    PreparedStatement pstCheckUsername = cn.prepareStatement(checkUsername);
                    PreparedStatement pstCheckTelefono = cn.prepareStatement(checkTelefono);
                    PreparedStatement pstCheckCorreo = cn.prepareStatement(checkCorreo);

                    ResultSet rsCheckUsername = pstCheckUsername.executeQuery();
                    ResultSet rsCheckTelefono = pstCheckTelefono.executeQuery();
                    ResultSet rsCheckCorreo = pstCheckCorreo.executeQuery();

                    if(!rsCheckUsername.next() || username.equals(usernameViejo)){
                        if(!rsCheckTelefono.next() || telefono.equals(telefonoViejo)){
                            if(Validaciones.validatePhone(telefono)){
                                if(!rsCheckCorreo.next() || correo.equals(correoViejo)){
                                    if(Validaciones.validateEmail(correo)){
                                        if(Validaciones.validatePassword(contrasena)){

                                            String updateUser = "UPDATE usuarios "
                                                              + "SET nombres = ?, apellidos = ?, username = ?, "
                                                              + "contrasenia = ?, telefono = ?, correo = ? "
                                                              + "WHERE id_usuario = '" + id_usuario + "'";

                                            PreparedStatement pstUpdateUser = cn.prepareStatement(updateUser);

                                            pstUpdateUser.setString(1, nombres);
                                            pstUpdateUser.setString(2, apellidos);
                                            pstUpdateUser.setString(3, username);
                                            pstUpdateUser.setString(4, contrasena);
                                            pstUpdateUser.setString(5, telefono);
                                            pstUpdateUser.setString(6, correo);

                                            pstUpdateUser.executeUpdate();

                                            JOptionPane.showMessageDialog(this, "Datos actualizados.");

                                            cn.close();
                                            pstUpdateUser.close();
                                            
                                            this.nombresViejos = textNombres.getText().trim();
                                            this.apellidosViejos = textApellidos.getText().trim();
                                            this.usernameViejo = textUsername.getText().trim();
                                            this.contrasenaVieja = textContrasena.getText().trim();
                                            this.telefonoViejo = textTelefono.getText().trim();
                                            this.correoViejo = textCorreo.getText().trim();
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(this, "Contraseña no válida. Debe haber mínimo "
                                                    + "8 caracteres, una o más mayúsculas y minúsculas, y uno "
                                                    + "o más números");
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(this, "Correo no válido.");
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(this, "Ya existe un usuario con este correo.");
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(this, "Teléfono no válido");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Ya existe un usuario con este teléfono.");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Ya existe este nombre de usuario.");
                    }
                    pstCheckUsername.close();
                    pstCheckTelefono.close();
                    pstCheckCorreo.close();

                    rsCheckUsername.close();
                    rsCheckTelefono.close();
                    rsCheckCorreo.close();
                }
                else{
                    JOptionPane.showMessageDialog(this, "No puede haber datos vacíos.");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_botonActualizarActionPerformed

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed
        
        int yes_no = JOptionPane.showConfirmDialog(null, 
                            "¿Seguro que quiere eliminar su cuenta?", "Alerta", JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
        if(yes_no == 0){
            try{
                String deleteUser = "DELETE FROM usuarios WHERE id_usuario = '" + id_usuario + "'";

                Connection cn = new Conexion().conectar();
                PreparedStatement pstDelete = cn.prepareStatement(deleteUser);
                
                pstDelete.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cuenta eliminada exitosamente.");
                
                cn.close();
                pstDelete.close();
                
                this.dispose();
                this.pp.dispose();
                new InicioSesion().setVisible(true);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_botonBorrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonBorrar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField textApellidos;
    private javax.swing.JTextField textContrasena;
    private javax.swing.JTextField textCorreo;
    private javax.swing.JTextField textNombres;
    private javax.swing.JTextField textRol;
    private javax.swing.JTextField textTelefono;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}
