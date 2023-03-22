package gui;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import database.Areas;
import database.Conexion;
import database.Consultas;
import repositoriomineria.RepositorioMineria;
import database.Simuladores;

/**
 *
 * @author Lenovo
 */
public class AgregarSimuladorPanel extends javax.swing.JPanel {

    private Simuladores simuladores;
    private Areas objetoAreas;

    private final JFileChooser fileChoooser = new JFileChooser();
    private FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
    private File file = null;

    private JDateChooser dateChooser = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
    private JTextFieldDateEditor textFieldDateEditor = (JTextFieldDateEditor) dateChooser.getDateEditor();

    private JComboBox comboBoxAreas = new JComboBox();

    public AgregarSimuladorPanel() {
        initComponents();

        this.objetoAreas = new Areas();
        this.simuladores = new Simuladores();

        comboTutorial.removeAllItems();
        comboTutorial.addItem("Si");
        comboTutorial.addItem("No");

        comboBoxAreas = new Consultas().getAreas();
        comboBoxAreas.setFont(new Font("Arial", Font.BOLD, 14));
        comboBoxAreas.setBounds(340, 170, 150, 25);
        jLabelFondo.add(comboBoxAreas);

        textReq.setLineWrap(true);
        textReq.setWrapStyleWord(true);

        textCaracteristicas.setLineWrap(true);
        textCaracteristicas.setWrapStyleWord(true);

        dateChooser.setBounds(340, 590, 250, 22);
        jLabelFondo.add(dateChooser);
        textFieldDateEditor.setEditable(false);
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
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboTutorial = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textCosto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textCaracteristicas = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        botonAbrir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        textLink = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textReq = new javax.swing.JTextArea();
        botonAgregar = new javax.swing.JButton();
        jLabelFondo = new javax.swing.JLabel();

        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(750, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(74, 75, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(246, 63, 63));
        jLabel9.setText("Fecha de consulta:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 590, -1, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tutorial:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, -1, -1));

        comboTutorial.setBackground(new java.awt.Color(204, 204, 204));
        comboTutorial.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboTutorial.setForeground(new java.awt.Color(0, 0, 0));
        comboTutorial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboTutorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(246, 63, 63));
        jLabel10.setText("Los campos en color rojo son obligatorios");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Costo:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, -1, 30));

        textCosto.setBackground(new java.awt.Color(204, 204, 204));
        textCosto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textCosto.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(textCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 370, 190, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Características:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, -1, -1));

        textCaracteristicas.setBackground(new java.awt.Color(204, 204, 204));
        textCaracteristicas.setColumns(20);
        textCaracteristicas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textCaracteristicas.setForeground(new java.awt.Color(0, 0, 0));
        textCaracteristicas.setRows(5);
        jScrollPane2.setViewportView(textCaracteristicas);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, 370, 120));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Link:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 540, -1, -1));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(246, 63, 63));
        jLabel1.setText("Nombre del simulador:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        textNombre.setBackground(new java.awt.Color(204, 204, 204));
        textNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textNombre.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 370, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(246, 63, 63));
        jLabel2.setText("Area:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Logo");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 63, -1, 40));

        botonAbrir.setBackground(new java.awt.Color(51, 51, 255));
        botonAbrir.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        botonAbrir.setForeground(new java.awt.Color(255, 255, 255));
        botonAbrir.setText("Abrir imagen");
        botonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrirActionPerformed(evt);
            }
        });
        jPanel1.add(botonAbrir, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Requerimientos:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, -1, -1));

        textLink.setBackground(new java.awt.Color(204, 204, 204));
        textLink.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textLink.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(textLink, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 540, 250, -1));

        textReq.setBackground(new java.awt.Color(204, 204, 204));
        textReq.setColumns(20);
        textReq.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textReq.setForeground(new java.awt.Color(0, 0, 0));
        textReq.setRows(5);
        jScrollPane1.setViewportView(textReq);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 201, 370, 120));

        botonAgregar.setBackground(new java.awt.Color(0, 153, 0));
        botonAgregar.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        botonAgregar.setForeground(new java.awt.Color(255, 255, 255));
        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(botonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 630, -1, -1));

        jLabelFondo.setBackground(new java.awt.Color(74, 75, 80));
        jLabelFondo.setForeground(new java.awt.Color(74, 75, 80));
        jPanel1.add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 690));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 690));
    }// </editor-fold>//GEN-END:initComponents

    private void botonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAbrirActionPerformed
        fileChoooser.setFileFilter(imageFilter);
        fileChoooser.setAcceptAllFileFilterUsed(false);
        try {
            int returnVal = fileChoooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fileChoooser.getSelectedFile();
                botonAbrir.setText(file.getName());
            }
        } catch (HeadlessException e) {
            e.getStackTrace();
        }
    }//GEN-LAST:event_botonAbrirActionPerformed

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed

        try {
            File image;
            InputStream fis = null;
            if (file != null) {
                image = new File(file.getAbsolutePath());
                fis = new FileInputStream(image);
            }
            String nombre = textNombre.getText().trim();
            String area = comboBoxAreas.getSelectedItem().toString();
            String requerimientos = textReq.getText();
            String tutorial = comboTutorial.getSelectedItem().toString();
            String costo = textCosto.getText().trim();
            String caracteristicas = textCaracteristicas.getText();
            String link = textLink.getText().trim();
            java.sql.Date fechaSQL = new java.sql.Date(dateChooser.getDate().getTime());
            String fecha = fechaSQL.toString();

            String insertarSimuladores = "INSERT INTO simuladores VALUES(?,?,?,?,?,?,?,?,?)";

            if (!nombre.isEmpty() && !area.isEmpty()) {
                try {
                    String checkSimuladores = "SELECT * FROM simuladores WHERE nombre_simulador = '" + nombre + "'";
                    Connection cn = new Conexion().conectar();
                    PreparedStatement pstSimuladores = cn.prepareStatement(insertarSimuladores);
                    PreparedStatement pstCheckSimuladores = cn.prepareStatement(checkSimuladores);

                    ResultSet rsCheckSimuladores = pstCheckSimuladores.executeQuery();

                    if (!rsCheckSimuladores.next()) {
                        pstSimuladores.setString(1, "0");

                        if (file != null) {
                            pstSimuladores.setBinaryStream(2, fis, (int) file.length());
                        } else {
                            pstSimuladores.setBinaryStream(2, RepositorioMineria.class.getResourceAsStream("/imagenes/no_image.jpg"));
                        }

                        pstSimuladores.setString(3, nombre);
                        pstSimuladores.setString(4, requerimientos);
                        pstSimuladores.setString(5, tutorial);
                        pstSimuladores.setString(6, costo);
                        pstSimuladores.setString(7, caracteristicas);
                        pstSimuladores.setString(8, link);
                        pstSimuladores.setString(9, fecha);

                        pstSimuladores.executeUpdate();

                        JOptionPane.showMessageDialog(this, "Simulador agregado.");

                        insertarArea(nombre);

                        botonAbrir.setText("Abrir imagen");
                        textNombre.setText("");
                        textReq.setText("");
                        textCosto.setText("");
                        textCaracteristicas.setText("");
                        textLink.setText("");

                        cn.close();
                        pstSimuladores.close();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya existe ese simulador");
                        textNombre.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Faltan llenar campos importantes");
                System.out.println(area);
                System.out.println(nombre);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Faltan llenar campos importantes");
            e.printStackTrace();
        }
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void insertarArea(String nombre) {
        try {
            Connection cn = new Conexion().conectar();
            String n = nombre;
            String area = comboBoxAreas.getSelectedItem().toString();
            String id_simulador = simuladores.getIDSimulador(n);
            String id_area = objetoAreas.getIDArea(area);

            PreparedStatement pstInsertarArea = cn.prepareStatement("insert into simuladorarea values(?,?)");

            pstInsertarArea.setString(1, id_simulador);
            pstInsertarArea.setString(2, id_area);

            pstInsertarArea.executeUpdate();

            cn.close();
            pstInsertarArea.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAbrir;
    private javax.swing.JButton botonAgregar;
    private javax.swing.JComboBox<String> comboTutorial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textCaracteristicas;
    private javax.swing.JTextField textCosto;
    private javax.swing.JTextField textLink;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextArea textReq;
    // End of variables declaration//GEN-END:variables
}
