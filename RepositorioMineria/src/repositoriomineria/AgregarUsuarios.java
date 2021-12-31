package repositoriomineria;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFrame;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class AgregarUsuarios extends javax.swing.JFrame {

    /**
     * Creates new form FormUsuarios
     */
    
    String ipAddress;
    
    public AgregarUsuarios(String ip) {
        super("Crear cuenta");
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(getIconImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.ipAddress = ip;
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
        botonRegistrarse = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(247, 242, 216));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(247, 242, 216));
        jPanel1.setForeground(new java.awt.Color(240, 240, 240));
        jPanel1.setLayout(new java.awt.GridLayout(6, 2, -100, 30));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("Nombre(s):");
        jPanel1.add(jLabel2);

        textNombres.setBackground(new java.awt.Color(255, 255, 51));
        textNombres.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textNombres);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("Apellido(s):");
        jPanel1.add(jLabel3);

        textApellidos.setBackground(new java.awt.Color(255, 255, 51));
        textApellidos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textApellidos);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setText("Nombre de usuario:");
        jPanel1.add(jLabel4);

        textUsername.setBackground(new java.awt.Color(255, 255, 51));
        textUsername.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textUsername);

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setText("Contraseña:");
        jPanel1.add(jLabel5);

        textContrasena.setBackground(new java.awt.Color(255, 255, 51));
        textContrasena.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textContrasena);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setText("Teléfono celular:");
        jPanel1.add(jLabel6);

        textTelefono.setBackground(new java.awt.Color(255, 255, 51));
        textTelefono.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textTelefono);

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setText("Correo electrónico:");
        jPanel1.add(jLabel7);

        textCorreo.setBackground(new java.awt.Color(255, 255, 51));
        textCorreo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textCorreo);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 420, 440));

        botonRegistrarse.setBackground(new java.awt.Color(253, 193, 1));
        botonRegistrarse.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        botonRegistrarse.setText("Registrarse");
        botonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarseActionPerformed(evt);
            }
        });
        getContentPane().add(botonRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, -1, -1));

        jPanel2.setBackground(new java.awt.Color(247, 242, 216));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("TODOS LOS CAMPOS SON OBLIGATORIOS");
        jPanel2.add(jLabel1);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarseActionPerformed
        try{
            String nombres = textNombres.getText().trim();
            String apellidos = textApellidos.getText().trim();
            String username = textUsername.getText().trim();
            String contrasena = textContrasena.getText().trim();
            String telefono = textTelefono.getText().trim();
            String correo = textCorreo.getText().trim();
            
            if(nombres.length() != 0 && apellidos.length() != 0 && username.length() != 0 && contrasena.length() != 0
               && telefono.length() != 0 && correo.length() != 0){
                String checkUsername = "SELECT * FROM usuarios WHERE username = '" + username + "'";
                String checkTelefono = "SELECT * FROM usuarios WHERE telefono = '" + telefono + "'";
                String checkCorreo = "SELECT * FROM usuarios WHERE correo = '" + correo + "'";

                Connection cn = new Conexion(ipAddress).conectar();

                PreparedStatement pstCheckUsername = cn.prepareStatement(checkUsername);
                PreparedStatement pstCheckTelefono = cn.prepareStatement(checkTelefono);
                PreparedStatement pstCheckCorreo = cn.prepareStatement(checkCorreo);

                ResultSet rsCheckUsername = pstCheckUsername.executeQuery();
                ResultSet rsCheckTelefono = pstCheckTelefono.executeQuery();
                ResultSet rsCheckCorreo = pstCheckCorreo.executeQuery();

                if(!rsCheckUsername.next()){
                    if(!rsCheckTelefono.next()){
                        if(Validaciones.validatePhone(telefono)){
                            if(!rsCheckCorreo.next()){
                                if(Validaciones.validateEmail(correo)){
                                    if(Validaciones.validatePassword(contrasena)){

                                        String insertUser = "INSERT INTO usuarios values(?,?,?,?,?,?,?,?)";

                                        PreparedStatement pstInsertUser = cn.prepareStatement(insertUser);

                                        pstInsertUser.setString(1, "0");
                                        pstInsertUser.setString(2, nombres);
                                        pstInsertUser.setString(3, apellidos);
                                        pstInsertUser.setString(4, username);
                                        pstInsertUser.setString(5, contrasena);
                                        pstInsertUser.setString(6, telefono);
                                        pstInsertUser.setString(7, correo);
                                        pstInsertUser.setString(8, "Usuario");

                                        pstInsertUser.executeUpdate();

                                        JOptionPane.showMessageDialog(this, "Usuario creado.");

                                        textNombres.setText("");
                                        textApellidos.setText("");
                                        textUsername.setText("");
                                        textContrasena.setText("");
                                        textTelefono.setText("");
                                        textCorreo.setText("");
                                        textCorreo.setText("");

                                        cn.close();
                                        pstInsertUser.close();
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(this, "Contraseña no válida. Debe haber mínimo 8 "
                                                + "caracteres, una o más mayúsculas y minúsculas, y uno o más números");
                                        textContrasena.setText(""); 
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(this, "Correo no válido.");
                                    textCorreo.setText("");
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(this, "Ya existe un usuario con este correo.");
                                textCorreo.setText("");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Teléfono no válido");
                            textTelefono.setText("");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Ya existe un usuario con este teléfono.");
                        textTelefono.setText("");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Ya existe este nombre de usuario.");
                    textUsername.setText("");
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
    }//GEN-LAST:event_botonRegistrarseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegistrarse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField textApellidos;
    private javax.swing.JTextField textContrasena;
    private javax.swing.JTextField textCorreo;
    private javax.swing.JTextField textNombres;
    private javax.swing.JTextField textTelefono;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}
