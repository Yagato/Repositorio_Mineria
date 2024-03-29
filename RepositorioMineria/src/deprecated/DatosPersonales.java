package deprecated;

import gui.InicioSesionFrame;
import security.Validaciones;
import database.Passwords;
import database.Conexion;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

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

        if (rolUser.equals("MainAdmin")) {
            botonBorrar.setVisible(false);
        }

        this.verDatos();

        this.nombresViejos = textNombres.getText().trim();
        this.apellidosViejos = textApellidos.getText().trim();
        this.usernameViejo = textUsername.getText().trim();
        this.telefonoViejo = textTelefono.getText().trim();
        this.correoViejo = textCorreo.getText().trim();

        this.pp = p;

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textNombres = new javax.swing.JTextField();
        btnActualizarNombres = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        textApellidos = new javax.swing.JTextField();
        btnActualizarApellidos = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        textUsername = new javax.swing.JTextField();
        btnActualizarUsername = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        btnActualizarPassword = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        textTelefono = new javax.swing.JTextField();
        btnActualizarTelefono = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        textCorreo = new javax.swing.JTextField();
        btnActualizarCorreo = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        textRol = new javax.swing.JTextField();
        botonBorrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(74, 75, 80));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(74, 75, 80));
        jPanel1.setForeground(new java.awt.Color(240, 240, 240));
        jPanel1.setLayout(new java.awt.GridLayout(7, 3, 10, 30));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre(s):");
        jPanel1.add(jLabel2);

        textNombres.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textNombres);

        btnActualizarNombres.setBackground(new java.awt.Color(0, 51, 255));
        btnActualizarNombres.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnActualizarNombres.setText("Actualizar");
        btnActualizarNombres.setPreferredSize(new java.awt.Dimension(20, 22));
        btnActualizarNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarNombresActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizarNombres);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido(s):");
        jPanel1.add(jLabel3);

        textApellidos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textApellidos);

        btnActualizarApellidos.setBackground(new java.awt.Color(0, 51, 255));
        btnActualizarApellidos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnActualizarApellidos.setText("Actualizar");
        btnActualizarApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarApellidosActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizarApellidos);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre de usuario:");
        jPanel1.add(jLabel4);

        textUsername.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textUsername);

        btnActualizarUsername.setBackground(new java.awt.Color(0, 51, 255));
        btnActualizarUsername.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnActualizarUsername.setText("Actualizar");
        btnActualizarUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarUsernameActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizarUsername);

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Contraseña:");
        jPanel1.add(jLabel5);

        passwordField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(passwordField);

        btnActualizarPassword.setBackground(new java.awt.Color(0, 51, 255));
        btnActualizarPassword.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnActualizarPassword.setText("Actualizar");
        btnActualizarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizarPassword);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Teléfono celular:");
        jPanel1.add(jLabel6);

        textTelefono.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textTelefono);

        btnActualizarTelefono.setBackground(new java.awt.Color(0, 51, 255));
        btnActualizarTelefono.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnActualizarTelefono.setText("Actualizar");
        btnActualizarTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTelefonoActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizarTelefono);

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Correo electrónico:");
        jPanel1.add(jLabel7);

        textCorreo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textCorreo);

        btnActualizarCorreo.setBackground(new java.awt.Color(0, 51, 255));
        btnActualizarCorreo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnActualizarCorreo.setText("Actualizar");
        btnActualizarCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCorreoActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizarCorreo);

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Rol:");
        jPanel1.add(jLabel8);

        textRol.setEditable(false);
        textRol.setBackground(new java.awt.Color(0, 0, 0));
        textRol.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textRol.setForeground(new java.awt.Color(255, 255, 255));
        textRol.setToolTipText("Este campo no es editable");
        jPanel1.add(textRol);

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 740, 410));

        botonBorrar.setBackground(new java.awt.Color(255, 0, 0));
        botonBorrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonBorrar.setForeground(new java.awt.Color(255, 255, 255));
        botonBorrar.setText("Borrar cuenta");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });
        jPanel2.add(botonBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 460, 187, 32));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void verDatos() {
        try {
            String datos = "SELECT * FROM usuarios WHERE username = '" + user + "'";

            Connection cn = new Conexion().conectar();

            PreparedStatement pstDatos = cn.prepareStatement(datos);

            ResultSet rsDatos = pstDatos.executeQuery();

            while (rsDatos.next()) {
                textNombres.setText(rsDatos.getString("nombres"));
                textApellidos.setText(rsDatos.getString("apellidos"));
                textUsername.setText(rsDatos.getString("username"));
                textTelefono.setText(rsDatos.getString("telefono"));
                textCorreo.setText(rsDatos.getString("correo"));
                textRol.setText(rsDatos.getString("rol"));
            }

            cn.close();
            pstDatos.close();
            rsDatos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed

        int yes_no = JOptionPane.showConfirmDialog(null,
                "¿Seguro que quiere eliminar su cuenta?", "Alerta", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (yes_no == 0) {
            try {
                String deleteUser = "DELETE FROM usuarios WHERE id_usuario = '" + id_usuario + "'";

                Connection cn = new Conexion().conectar();
                PreparedStatement pstDelete = cn.prepareStatement(deleteUser);

                pstDelete.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cuenta eliminada exitosamente.");

                cn.close();
                pstDelete.close();

                this.dispose();
                this.pp.dispose();
                new InicioSesionFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_botonBorrarActionPerformed

    private void btnActualizarNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarNombresActionPerformed
        String nombres = textNombres.getText().trim();

        if (nombres.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo vacío.");
            return;
        }

        if (nombres.equals(nombresViejos)) {
            JOptionPane.showMessageDialog(this, "No se han cambiado datos.");
            return;
        }

        int yes_no = JOptionPane.showConfirmDialog(null,
                "¿Seguro que quiere actualizar sus nombres?", "Alerta", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (yes_no == 1) {
            return;
        }

        try {
            String checkUser = "SELECT * FROM usuarios WHERE id_usuario = '" + id_usuario + "'";

            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(checkUser);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String updateQuery = "UPDATE usuarios SET nombres = ? WHERE id_usuario = " + id_usuario + "";
                PreparedStatement pstUpdateNombre = cn.prepareStatement(updateQuery);

                pstUpdateNombre.setString(1, nombres);

                pstUpdateNombre.executeUpdate();

                JOptionPane.showMessageDialog(this, "Nombres actualizados.");

                pstUpdateNombre.close();

                this.nombresViejos = textNombres.getText().trim();
            }

            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnActualizarNombresActionPerformed

    private void btnActualizarApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarApellidosActionPerformed
        String apellidos = textApellidos.getText().trim();

        if (apellidos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo vacío.");
            return;
        }

        if (apellidos.equals(apellidosViejos)) {
            JOptionPane.showMessageDialog(this, "No se han cambiado datos.");
            return;
        }

        int yes_no = JOptionPane.showConfirmDialog(null,
                "¿Seguro que quiere actualizar sus apellidos?", "Alerta", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (yes_no == 1) {
            return;
        }

        try {
            String checkUser = "SELECT * FROM usuarios WHERE id_usuario = '" + id_usuario + "'";

            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(checkUser);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String updateQuery = "UPDATE usuarios SET apellidos = ? WHERE id_usuario = " + id_usuario + "";
                PreparedStatement pstUpdateNombre = cn.prepareStatement(updateQuery);

                pstUpdateNombre.setString(1, apellidos);

                pstUpdateNombre.executeUpdate();

                JOptionPane.showMessageDialog(this, "Datos actualizados.");

                pstUpdateNombre.close();

                this.apellidosViejos = textApellidos.getText().trim();
            }

            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnActualizarApellidosActionPerformed

    private void btnActualizarUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarUsernameActionPerformed
        String username = textUsername.getText().trim();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo vacío.");
            return;
        }

        if (username.equals(usernameViejo)) {
            JOptionPane.showMessageDialog(this, "No se han cambiado datos.");
            return;
        }

        int yes_no = JOptionPane.showConfirmDialog(null,
                "¿Seguro que quiere actualizar su nombre de usuario?", "Alerta", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (yes_no == 1) {
            return;
        }

        String password = confirmPassword();
        String hashedPassword = Passwords.cipher(password, usernameViejo);

        if (!hashedPassword.equals(Passwords.getPassword(id_usuario))) {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta.");
            return;
        }

        try {
            String checkUsername = "SELECT * FROM usuarios WHERE username = '" + username + "'";

            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(checkUsername);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                String updateQuery = "UPDATE usuarios SET username = ?, contrasenia = ? WHERE id_usuario = " + id_usuario + "";
                PreparedStatement pstUpdateUsername = cn.prepareStatement(updateQuery);

                String newHashedPassword = Passwords.cipher(password, username);

                pstUpdateUsername.setString(1, username);
                pstUpdateUsername.setString(2, newHashedPassword);

                pstUpdateUsername.executeUpdate();

                JOptionPane.showMessageDialog(this, "Datos actualizados.");

                pstUpdateUsername.close();

                this.usernameViejo = textUsername.getText().trim();
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe un usuario con este nombre de usuario.");
            }

            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnActualizarUsernameActionPerformed

    private void btnActualizarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPasswordActionPerformed
        String password = passwordField.getText().trim();
        String hashedNewPassword = Passwords.cipher(password, usernameViejo);

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo vacío.");
            this.passwordField.setText("");
            return;
        }

        if (!Validaciones.validatePassword(password)) {
            JOptionPane.showMessageDialog(this, "Contraseña no válida. Debe haber mínimo "
                    + "8 caracteres, una o más mayúsculas y minúsculas, y uno "
                    + "o más números");
            this.passwordField.setText("");
            return;
        }

        int yes_no = JOptionPane.showConfirmDialog(null,
                "¿Seguro que quiere actualizar su contraseña?", "Alerta", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (yes_no == 1) {
            this.passwordField.setText("");
            return;
        }

        if (hashedNewPassword.equals(Passwords.getPassword(id_usuario))) {
            JOptionPane.showMessageDialog(this, "Misma contraseña.");
            this.passwordField.setText("");
            return;
        }

        String confirmPassword = confirmPassword();
        String hashedConfirmPassword = Passwords.cipher(confirmPassword, usernameViejo);

        if (!hashedNewPassword.equals(hashedConfirmPassword)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.");
            this.passwordField.setText("");
            return;
        }

        try {
            String checkUser = "SELECT * FROM usuarios WHERE id_usuario = '" + id_usuario + "'";

            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(checkUser);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String updateQuery = "UPDATE usuarios SET contrasenia = ? WHERE id_usuario = " + id_usuario + "";
                PreparedStatement pstUpdatePassword = cn.prepareStatement(updateQuery);

                pstUpdatePassword.setString(1, hashedNewPassword);

                pstUpdatePassword.executeUpdate();

                JOptionPane.showMessageDialog(this, "Datos actualizados.");

                pstUpdatePassword.close();

                this.passwordField.setText("");
            }

            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnActualizarPasswordActionPerformed

    private void btnActualizarTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTelefonoActionPerformed
        String telefono = textTelefono.getText().trim();

        if (telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo vacío.");
            return;
        }

        if (telefono.equals(telefonoViejo)) {
            JOptionPane.showMessageDialog(this, "No se han cambiado datos.");
            return;
        }

        if (!Validaciones.validatePhone(telefono)) {
            JOptionPane.showMessageDialog(this, "Telefono no válido.");
            return;
        }

        int yes_no = JOptionPane.showConfirmDialog(null,
                "¿Seguro que quiere actualizar su telefono?", "Alerta", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (yes_no == 1) {
            return;
        }

        try {
            String checkTelefono = "SELECT * FROM usuarios WHERE telefono = '" + telefono + "'";

            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(checkTelefono);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                String updateQuery = "UPDATE usuarios SET telefono = ? WHERE id_usuario = " + id_usuario + "";
                PreparedStatement pstUpdateTelefono = cn.prepareStatement(updateQuery);

                pstUpdateTelefono.setString(1, telefono);

                pstUpdateTelefono.executeUpdate();

                JOptionPane.showMessageDialog(this, "Teléfono actualizado.");

                pstUpdateTelefono.close();

                this.telefonoViejo = textTelefono.getText().trim();
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe alguien con este teléfono.");
            }

            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnActualizarTelefonoActionPerformed

    private void btnActualizarCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCorreoActionPerformed
        String correo = textCorreo.getText().trim();

        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo vacío.");
            return;
        }

        if (correo.equals(correoViejo)) {
            JOptionPane.showMessageDialog(this, "No se han cambiado datos.");
            return;
        }

        if (!Validaciones.validateEmail(correo)) {
                JOptionPane.showMessageDialog(this, "Correo no válido.");
            return;
        }

        int yes_no = JOptionPane.showConfirmDialog(null,
                "¿Seguro que quiere actualizar su correo?", "Alerta", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (yes_no == 1) {
            return;
        }

        try {
            String checkCorreo = "SELECT * FROM usuarios WHERE correo = '" + correo + "'";

            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(checkCorreo);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                String updateQuery = "UPDATE usuarios SET correo = ? WHERE id_usuario = " + id_usuario + "";
                PreparedStatement pstUpdateCorreo = cn.prepareStatement(updateQuery);

                pstUpdateCorreo.setString(1, correo);

                pstUpdateCorreo.executeUpdate();

                JOptionPane.showMessageDialog(this, "Correo actualizado.");

                pstUpdateCorreo.close();

                this.correoViejo = textCorreo.getText().trim();
            } else {
                JOptionPane.showMessageDialog(this, "Ya existe alguien con este correo.");
            }

            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnActualizarCorreoActionPerformed

    public String confirmPassword() {
        JPasswordField myPasswordField = new JPasswordField(24);
        Box box = Box.createHorizontalBox();
        box.add(myPasswordField);

        int x = JOptionPane.showConfirmDialog(null, box, "Confirme contraseña", JOptionPane.OK_CANCEL_OPTION);

        if (x == JOptionPane.OK_OPTION) {
            return myPasswordField.getText().trim();
        }

        return null;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton btnActualizarApellidos;
    private javax.swing.JButton btnActualizarCorreo;
    private javax.swing.JButton btnActualizarNombres;
    private javax.swing.JButton btnActualizarPassword;
    private javax.swing.JButton btnActualizarTelefono;
    private javax.swing.JButton btnActualizarUsername;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField textApellidos;
    private javax.swing.JTextField textCorreo;
    private javax.swing.JTextField textNombres;
    private javax.swing.JTextField textRol;
    private javax.swing.JTextField textTelefono;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}
