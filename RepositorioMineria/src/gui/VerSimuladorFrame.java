package gui;

import database.Simuladores;
import database.Conexion;
import database.Consultas;
import database.Areas;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class VerSimuladorFrame extends javax.swing.JFrame {
    
    MainScreenFrame myFrame;
    private DatabaseMenuPanel myPanel;
    ButtonGroup buttonGroup = new ButtonGroup();
    final JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
    File file = null;
    String id_simulador = ""; 
    String areas_actuales[] = {"-", "-", "-", "-", "-"};
    JDateChooser dateChooser = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
    JTextFieldDateEditor textEditor = (JTextFieldDateEditor) dateChooser.getDateEditor();
    String nombreViejo = "";
    String rolUsuario;

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/cascoIcon.png"));
        return retValue;
    }
        
    public VerSimuladorFrame(MainScreenFrame frame, DatabaseMenuPanel panel, String nombreSimulador, String rol) {
        super(nombreSimulador);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(getIconImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.rolUsuario = rol;
        this.myPanel = panel;
        
        if(this.rolUsuario.equals("Usuario")){
            textNombre.setEnabled(false);
            textNombre.setDisabledTextColor(Color.black);
            botonImagen.setVisible(false);
            textRequerimientos.setEnabled(false);
            textRequerimientos.setDisabledTextColor(Color.black);
            radioSi.setEnabled(false);
            radioNo.setEnabled(false);
            textCosto.setEnabled(false);
            textCosto.setDisabledTextColor(Color.black);
            comboBoxArea1.setEnabled(false);
            comboBoxArea2.setEnabled(false);
            comboBoxArea3.setEnabled(false);
            comboBoxArea4.setEnabled(false);
            comboBoxArea5.setEnabled(false);
            UIManager.put("ComboBox.disabledBackground", new Color(212, 212, 210));
            UIManager.put("ComboBox.disabledForeground", Color.BLACK);
            textCaracteristicas.setEnabled(false);
            textCaracteristicas.setDisabledTextColor(Color.black);
            textLink.setEnabled(false);
            textLink.setDisabledTextColor(Color.black);
            textEditor.setDisabledTextColor(Color.black);
            dateChooser.setEnabled(false);
            botonActualizar.setVisible(false);
            botonEliminar.setVisible(false);
        }
        
        id_simulador = new Simuladores().getIDSimulador(nombreSimulador);
        
        textNombre.setHorizontalAlignment(SwingConstants.CENTER);
        
        textRequerimientos.setLineWrap(true);
        textRequerimientos.setWrapStyleWord(true);
        
        radioSi.setActionCommand("Si");
        buttonGroup.add(radioSi);
        radioNo.setActionCommand("No");
        buttonGroup.add(radioNo);
        
        List areas = new List();
        areas = new Consultas().getListAreas();
        
        comboBoxArea1.removeAllItems();
        comboBoxArea2.removeAllItems();
        comboBoxArea3.removeAllItems();
        comboBoxArea4.removeAllItems();
        comboBoxArea5.removeAllItems();
        
        comboBoxArea1.addItem("-");
        comboBoxArea2.addItem("-");
        comboBoxArea3.addItem("-");
        comboBoxArea4.addItem("-");
        comboBoxArea5.addItem("-");
                
        for(int i = 0; i < areas.getItemCount(); i++){
            comboBoxArea1.addItem(areas.getItem(i));
            comboBoxArea2.addItem(areas.getItem(i));
            comboBoxArea3.addItem(areas.getItem(i));
            comboBoxArea4.addItem(areas.getItem(i));
            comboBoxArea5.addItem(areas.getItem(i));
        }
                                 
        textCosto.setHorizontalAlignment(SwingConstants.CENTER);
        
        textCaracteristicas.setLineWrap(true);
        textCaracteristicas.setWrapStyleWord(true);
        
        textLink.setLineWrap(true);
        textLink.setWrapStyleWord(true);
                
        dateChooser.setFont(new Font("Arial", Font.BOLD, 16));
        jPanel1.add(dateChooser, new AbsoluteConstraints(840, 510, 261, 30));
        textEditor.setEditable(false);
     
        this.displayData();
        
        nombreViejo = textNombre.getText().trim();
        
        this.myFrame = frame;
        
        this.pack();
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                myFrame.setEnabled(true);
            }
        });
    }
    
    public void displayData(){
        String consulta = "SELECT simuladores.* " 
                        + "FROM simuladores " 
                        + "WHERE simuladores.id_simulador = '" + id_simulador + "' ";
        
        try{
            Connection cn = new Conexion().conectar();
            PreparedStatement pstConsulta = cn.prepareStatement(consulta);
            ResultSet rs = pstConsulta.executeQuery();
            while(rs.next()){
                textNombre.setText(rs.getString("nombre_simulador"));
                
                try{
                    BufferedImage fis = ImageIO.read(rs.getBinaryStream("logo"));
                    Image dimg = fis.getScaledInstance(labelLogo.getWidth(), 
                            labelLogo.getHeight(), Image.SCALE_SMOOTH);
                    labelLogo.setIcon(new ImageIcon(dimg));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
                textRequerimientos.setText(rs.getString("requerimientos"));
                
                String tutorial = rs.getString("tutorial");
                buttonGroup.clearSelection();
                if(tutorial.equals("Si"))
                    radioSi.setSelected(true);
                else if(tutorial.equals("No"))
                    radioNo.setSelected(true);
                
                String costo = rs.getString("costo");
                
                if(costo.equals("") || costo.equals("0"))
                    textCosto.setText("GRATIS");
                else
                    textCosto.setText(rs.getString("costo"));
                
                displayAreas();
                
                textCaracteristicas.setText(rs.getString("caracteristicas"));
                
                textLink.setText(rs.getString("link"));
                
                dateChooser.setDate(rs.getDate("fecha_consulta"));                
            }
            
            cn.close();
            pstConsulta.close();
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void displayAreas(){
        String consultaAreas = "SELECT * "
                             + "FROM simuladorarea, areas "
                             + "WHERE simuladorarea.id_simulador = '" + id_simulador + "' "
                             + "AND simuladorarea.id_area = areas.id_area";
        
        try{
            Connection cn = new Conexion().conectar();
            PreparedStatement pstConsultaArea = cn.prepareStatement(consultaAreas);
            ResultSet rsArea = pstConsultaArea.executeQuery();
            int i = 1;
            while(rsArea.next()){
                switch(i){
                    case 1: comboBoxArea1.setSelectedItem(rsArea.getString("nombre_area"));
                            areas_actuales[0] = comboBoxArea1.getSelectedItem().toString();
                            break;
                    case 2: comboBoxArea2.setSelectedItem(rsArea.getString("nombre_area"));
                            areas_actuales[1] = comboBoxArea2.getSelectedItem().toString();
                            break;
                    case 3: comboBoxArea3.setSelectedItem(rsArea.getString("nombre_area"));
                            areas_actuales[2] = comboBoxArea3.getSelectedItem().toString();
                            break;
                    case 4: comboBoxArea4.setSelectedItem(rsArea.getString("nombre_area"));
                            areas_actuales[3] = comboBoxArea4.getSelectedItem().toString();
                            break;
                    case 5: comboBoxArea5.setSelectedItem(rsArea.getString("nombre_area"));
                            areas_actuales[4] = comboBoxArea5.getSelectedItem().toString();
                            break;
                    default: break;
                }
                i++;
            }
            
            cn.close();
            pstConsultaArea.close();
            rsArea.close();
        }
        catch(Exception e){
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

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        textCosto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textLink = new javax.swing.JTextArea();
        labelLogo = new javax.swing.JLabel();
        botonImagen = new javax.swing.JButton();
        textNombre = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textRequerimientos = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textCaracteristicas = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        radioSi = new javax.swing.JRadioButton();
        radioNo = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        comboBoxArea1 = new javax.swing.JComboBox<>();
        comboBoxArea2 = new javax.swing.JComboBox<>();
        comboBoxArea3 = new javax.swing.JComboBox<>();
        comboBoxArea4 = new javax.swing.JComboBox<>();
        comboBoxArea5 = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        botonActualizar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(74, 75, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 255));
        jLabel4.setText("COSTO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 410, -1, -1));

        textCosto.setBackground(new java.awt.Color(153, 153, 153));
        textCosto.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        textCosto.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(textCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 410, 140, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 255));
        jLabel6.setText("FECHA DE CONSULTA");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 470, 280, -1));

        jPanel2.setBackground(new java.awt.Color(74, 75, 80));
        jPanel2.setLayout(new java.awt.GridLayout(2, 0, 0, 10));

        jLabel7.setBackground(new java.awt.Color(74, 75, 80));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("LINK");
        jPanel2.add(jLabel7);

        textLink.setBackground(new java.awt.Color(153, 153, 153));
        textLink.setColumns(20);
        textLink.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textLink.setForeground(new java.awt.Color(255, 255, 255));
        textLink.setRows(5);
        jScrollPane3.setViewportView(textLink);

        jPanel2.add(jScrollPane3);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 300, 100));
        jPanel1.add(labelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 300, 310));

        botonImagen.setBackground(new java.awt.Color(0, 51, 255));
        botonImagen.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonImagen.setForeground(new java.awt.Color(255, 255, 255));
        botonImagen.setText("Elegir imagen");
        botonImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonImagenActionPerformed(evt);
            }
        });
        jPanel1.add(botonImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 150, 40));

        textNombre.setBackground(new java.awt.Color(153, 153, 153));
        textNombre.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        textNombre.setForeground(new java.awt.Color(0, 0, 204));
        textNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textNombre.setText("tes");
        jPanel1.add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 300, 40));

        jPanel3.setBackground(new java.awt.Color(74, 75, 80));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("REQUISITOS");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 180, 30));

        textRequerimientos.setBackground(new java.awt.Color(153, 153, 153));
        textRequerimientos.setColumns(20);
        textRequerimientos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textRequerimientos.setForeground(new java.awt.Color(255, 255, 255));
        textRequerimientos.setRows(5);
        jScrollPane1.setViewportView(textRequerimientos);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 410, 260));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 410, 300));

        jPanel4.setBackground(new java.awt.Color(74, 75, 80));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 255));
        jLabel5.setText("CARACTERISTICAS");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        textCaracteristicas.setBackground(new java.awt.Color(153, 153, 153));
        textCaracteristicas.setColumns(20);
        textCaracteristicas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textCaracteristicas.setForeground(new java.awt.Color(255, 255, 255));
        textCaracteristicas.setRows(5);
        jScrollPane2.setViewportView(textCaracteristicas);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 410, 240));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 410, 290));

        jPanel7.setBackground(new java.awt.Color(74, 75, 80));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        radioSi.setBackground(new java.awt.Color(74, 75, 80));
        radioSi.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        radioSi.setForeground(new java.awt.Color(255, 255, 255));
        radioSi.setText("Si");
        radioSi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(radioSi);

        radioNo.setBackground(new java.awt.Color(74, 75, 80));
        radioNo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        radioNo.setForeground(new java.awt.Color(255, 255, 255));
        radioNo.setText("No");
        radioNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(radioNo);

        jPanel7.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 320, 60));

        jLabel3.setBackground(new java.awt.Color(0, 153, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("¿TIENE TUTORIAL?");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 290, 60));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 320, 150));

        jPanel8.setBackground(new java.awt.Color(74, 75, 80));
        jPanel8.setLayout(new java.awt.GridLayout(2, 1));

        jLabel8.setBackground(new java.awt.Color(0, 102, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("ÁREAS");
        jPanel8.add(jLabel8);

        jPanel6.setBackground(new java.awt.Color(74, 75, 80));
        jPanel6.setLayout(new java.awt.GridLayout(3, 2));

        comboBoxArea1.setBackground(new java.awt.Color(204, 204, 204));
        comboBoxArea1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboBoxArea1.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxArea1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(comboBoxArea1);

        comboBoxArea2.setBackground(new java.awt.Color(204, 204, 204));
        comboBoxArea2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboBoxArea2.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxArea2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(comboBoxArea2);

        comboBoxArea3.setBackground(new java.awt.Color(204, 204, 204));
        comboBoxArea3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboBoxArea3.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxArea3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(comboBoxArea3);

        comboBoxArea4.setBackground(new java.awt.Color(204, 204, 204));
        comboBoxArea4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboBoxArea4.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxArea4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(comboBoxArea4);

        comboBoxArea5.setBackground(new java.awt.Color(204, 204, 204));
        comboBoxArea5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboBoxArea5.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxArea5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(comboBoxArea5);

        jPanel8.add(jPanel6);

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 166, 320, 180));

        jPanel9.setBackground(new java.awt.Color(74, 75, 80));
        jPanel9.setLayout(new java.awt.GridLayout(1, 2, 80, 0));

        botonActualizar.setBackground(new java.awt.Color(0, 204, 0));
        botonActualizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonActualizar.setForeground(new java.awt.Color(255, 255, 255));
        botonActualizar.setText("Actualizar");
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });
        jPanel9.add(botonActualizar);

        botonEliminar.setBackground(new java.awt.Color(255, 0, 0));
        botonEliminar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        jPanel9.add(botonEliminar);

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 600, 330, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        try{
            File image;
            InputStream fis = new Consultas().getLogo(id_simulador);
            if(file != null){
                image = new File(file.getAbsolutePath());
                fis = new FileInputStream(image);
            }
            else{
                
            }
            
            String nombre = textNombre.getText().trim();
            
            String requerimientos = textRequerimientos.getText().trim();
            
            String tutorial = "";
            if(radioSi.isSelected())
                tutorial = "Si";
            else
                tutorial = "No";
            
            String costo = textCosto.getText().trim();
            
            ArrayList<String> areas = new ArrayList<>();
            areas.add(comboBoxArea1.getSelectedItem().toString());
            areas.add(comboBoxArea2.getSelectedItem().toString());
            areas.add(comboBoxArea3.getSelectedItem().toString());
            areas.add(comboBoxArea4.getSelectedItem().toString());
            areas.add(comboBoxArea5.getSelectedItem().toString());
            
            String caracteristicas = textCaracteristicas.getText().trim();
            
            String link = textLink.getText().trim();
            
            java.sql.Date fechaSQL = new java.sql.Date(dateChooser.getDate().getTime());
            String fecha = fechaSQL.toString();

            String update = "UPDATE simuladores "
                          + "SET logo = ?, nombre_simulador = ?, requerimientos = ?, "
                          + "tutorial = ?, costo = ?, caracteristicas = ?, link = ?, "
                          + "fecha_consulta = ? "
                          + "WHERE id_simulador = '" + id_simulador + "'";
            
            String checkSimuladores = "SELECT * FROM simuladores WHERE nombre_simulador = '" + nombre + "'";
            
            try{
                Connection cn = new Conexion().conectar();
                PreparedStatement pstUpdate = cn.prepareStatement(update);
                PreparedStatement pstCheckSimuladores = cn.prepareStatement(checkSimuladores);
                
                ResultSet rsCheckSimuladores = pstCheckSimuladores.executeQuery();
                
                if(!rsCheckSimuladores.next() || nombreViejo.equals(nombre)){
                    if(file != null)
                        pstUpdate.setBinaryStream(1, fis, (int) file.length());
                    else
                        pstUpdate.setBinaryStream(1, fis);

                    pstUpdate.setString(2, nombre);
                    pstUpdate.setString(3, requerimientos);
                    pstUpdate.setString(4, tutorial);
                    pstUpdate.setString(5, costo);
                    pstUpdate.setString(6, caracteristicas);
                    pstUpdate.setString(7, link);
                    pstUpdate.setString(8, fecha);

                    if(!nombre.isEmpty() && !areas.isEmpty()){
                        pstUpdate.executeUpdate();
                        actualizarAreas(areas);
                        DefaultTableModel model = (DefaultTableModel) myPanel.tablaSimuladores.getModel();
                        model.setRowCount(0);
                        myPanel.verTabla();
                        JOptionPane.showMessageDialog(null, "Actualizacion exitosa");
                        nombreViejo = nombre;
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Faltan llenar campos importantes");

                    cn.close();
                    pstUpdate.close();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ya existe un simulador con ese nombre");
                    rsCheckSimuladores.close();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_botonActualizarActionPerformed

    public void actualizarAreas(ArrayList areas){
        try{
            String updateAreas = "UPDATE simuladorarea "
                               + "SET id_area = ? "
                               + "WHERE id_simulador = '" + id_simulador + "' "
                               + "AND id_area = ?";
            
            String insertAreas = "INSERT INTO simuladorarea values(?,?)";
            
            String check = "SELECT simuladorarea.* "
                         + "FROM simuladorarea "
                         + "WHERE simuladorarea.id_simulador = '" + id_simulador + "' "
                         + "AND simuladorarea.id_area = ?";
            
            Connection cn = new Conexion().conectar();
            
            PreparedStatement pstUpdateAreas = cn.prepareStatement(updateAreas);
            PreparedStatement pstInsertAreas = cn.prepareStatement(insertAreas);
            PreparedStatement pstCheck = cn.prepareStatement(check);
            
            ResultSet rsCheck;
            
            for(int i = 0; i < areas.size(); i++){
                String idArea = "";
                if(areas.get(i).toString().equals("-") && !areas.get(i).equals(areas_actuales[i])){
                    idArea = new Areas().getIDArea(areas_actuales[i]);
                    eliminarArea(idArea);
                    areas_actuales[i] = areas.get(i).toString();
                }
                else{
                    idArea = new Areas().getIDArea(areas.get(i).toString());
                    pstCheck.setString(1, idArea);

                    rsCheck = pstCheck.executeQuery();

                    if(rsCheck.next()){
                        //No hagas nada, ya existe ese registro
                    }
                    else if(areas_actuales[i].equals("-") && !areas.get(i).equals(areas_actuales[i])){
                        pstInsertAreas.setString(1, id_simulador);
                        pstInsertAreas.setString(2, idArea);
                        pstInsertAreas.executeUpdate();
                        areas_actuales[i] = areas.get(i).toString();
                    }
                    else{
                        String idAreaOriginal = new Areas().getIDArea(areas_actuales[i]);
                        pstUpdateAreas.setString(1, idArea);
                        pstUpdateAreas.setString(2, idAreaOriginal);
                        pstUpdateAreas.executeUpdate();
                        areas_actuales[i] = areas.get(i).toString();
                    }
                    
                    if(i == areas.size() - 1)
                        rsCheck.close();
                }
                
            }
            cn.close();
            pstUpdateAreas.close();
            pstInsertAreas.close();
            pstCheck.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void eliminarArea(String id_area){
        try{
            String delete = "DELETE FROM simuladorarea "
                          + "WHERE id_simulador = '" + id_simulador + "' "
                          + "AND id_area = '" + id_area + "'";
            
            String check = "SELECT * "
                         + "FROM simuladorarea "
                         + "WHERE id_simulador = '" + id_simulador + "' "
                         + "AND id_area = '" + id_area + "'";
            
            Connection cn = new Conexion().conectar();
            PreparedStatement pstDelete = cn.prepareStatement(delete);
            PreparedStatement pstCheck = cn.prepareStatement(check);
            ResultSet rsCheck = pstCheck.executeQuery();
            
            int yes_no = JOptionPane.showConfirmDialog(null, 
                            "¿Seguro que quiere eliminar una o más áreas de este simulador?", "Alerta", 
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (yes_no == 0) {
                if (rsCheck.next()) {
                    pstDelete.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel) myPanel.tablaSimuladores.getModel();
                    model.setRowCount(0);
                    myPanel.verTabla();
                }
                JOptionPane.showMessageDialog(null, "Área(s) desasignada(s) exitosamente.");
            }
            
            cn.close();
            pstDelete.close();
            pstCheck.close();
            rsCheck.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void botonImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonImagenActionPerformed
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        try{
            int returnVal = fileChooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                botonImagen.setText(file.getName());
            }
        }
        catch(HeadlessException e){
            e.getStackTrace();
        }
    }//GEN-LAST:event_botonImagenActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        try{
            String checkSimulador = "SELECT * FROM simuladores WHERE id_simulador = '" + id_simulador + "'";
            String deleteSimulador = "DELETE FROM simuladores WHERE id_simulador = '" + id_simulador + "'";
            
            Connection cn = new Conexion().conectar();
            PreparedStatement pstDeleteSimulador = cn.prepareStatement(deleteSimulador);
            PreparedStatement pstCheck = cn.prepareStatement(checkSimulador);
            
            ResultSet rs = pstCheck.executeQuery();
            
            if(rs.next()){
                int yes_no = JOptionPane.showConfirmDialog(null, 
                            "¿Seguro que quiere eliminar este simulador?", "Alerta", JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                
                if (yes_no == 0){
                    eliminarAreasSimuladores();
                    pstDeleteSimulador.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Simulador eliminado exitosamente.");
                    DefaultTableModel model = (DefaultTableModel) myPanel.tablaSimuladores.getModel();
                    model.setRowCount(0);
                    myPanel.verTabla();
                    myFrame.setEnabled(true);
                    this.dispose();
                }
            }
            
            cn.close();
            pstDeleteSimulador.close();
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void eliminarAreasSimuladores(){
        try{
            String checkSimuladorArea = "SELECT * FROM simuladorarea WHERE id_simulador = '" + id_simulador + "'";
            String deleteSimuladorArea = "DELETE FROM simuladorarea WHERE id_simulador = '" + id_simulador + "'";
            
            Connection cn = new Conexion().conectar();
            PreparedStatement pstDeleteSimuladorArea = cn.prepareStatement(deleteSimuladorArea);
            PreparedStatement pstCheck = cn.prepareStatement(checkSimuladorArea);
            
            ResultSet rs = pstCheck.executeQuery();
            
            if(rs.next()){
                pstDeleteSimuladorArea.executeUpdate();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonImagen;
    private javax.swing.JComboBox<String> comboBoxArea1;
    private javax.swing.JComboBox<String> comboBoxArea2;
    private javax.swing.JComboBox<String> comboBoxArea3;
    private javax.swing.JComboBox<String> comboBoxArea4;
    private javax.swing.JComboBox<String> comboBoxArea5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JRadioButton radioNo;
    private javax.swing.JRadioButton radioSi;
    private javax.swing.JTextArea textCaracteristicas;
    private javax.swing.JTextField textCosto;
    private javax.swing.JTextArea textLink;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextArea textRequerimientos;
    // End of variables declaration//GEN-END:variables
}
