package repositoriomineria;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
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
import java.sql.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
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
 * @author Ocampo Mora
 */
public class VerSimulador extends javax.swing.JFrame {
    
    PantallaPrincipal pp;
    ButtonGroup group = new ButtonGroup();
    final JFileChooser fc = new JFileChooser();
    FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
    File file = null;
    String id_simulador = ""; 
    String areas_actuales[] = {"-", "-", "-", "-", "-"};
    JDateChooser calendar = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
    JTextFieldDateEditor editor = (JTextFieldDateEditor) calendar.getDateEditor();
    String nombreViejo = "";
    String ipAddress;
    String rolUsuario;

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/cascoIcon.png"));
        return retValue;
    }
        
    public VerSimulador(PantallaPrincipal p, String nombreSimulador, String ip, String rol) {
        super(nombreSimulador);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(getIconImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.ipAddress = ip;
        this.rolUsuario = rol;
        
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
            editor.setDisabledTextColor(Color.black);
            calendar.setEnabled(false);
            botonActualizar.setVisible(false);
            botonEliminar.setVisible(false);
        }
        
        id_simulador = new Simuladores(ipAddress).getIDSimulador(nombreSimulador);
        
        textNombre.setHorizontalAlignment(SwingConstants.CENTER);
        
        textRequerimientos.setLineWrap(true);
        textRequerimientos.setWrapStyleWord(true);
        
        radioSi.setActionCommand("Si");
        group.add(radioSi);
        radioNo.setActionCommand("No");
        group.add(radioNo);
        
        List areas = new List();
        areas = new Consultas(ipAddress).getListAreas();
        
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
                
        jPanel1.add(calendar, new AbsoluteConstraints(590, 510, 105, 14));
        editor.setEditable(false);
     
        this.displayData();
        
        nombreViejo = textNombre.getText().trim();
        
        this.pp = p;
        
        this.pack();
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                pp.setEnabled(true);
            }
        });
    }
    
    public void displayData(){
        String consulta = "SELECT simuladores.* " 
                        + "FROM simuladores " 
                        + "WHERE simuladores.id_simulador = '" + id_simulador + "' ";
        
        try{
            Connection cn = new Conexion(ipAddress).conectar();
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
                group.clearSelection();
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
                
                calendar.setDate(rs.getDate("fecha_consulta"));                
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
            Connection cn = new Conexion(ipAddress).conectar();
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
        labelLogo = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textRequerimientos = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textCosto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textCaracteristicas = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        botonImagen = new javax.swing.JButton();
        radioSi = new javax.swing.JRadioButton();
        radioNo = new javax.swing.JRadioButton();
        comboBoxArea1 = new javax.swing.JComboBox<>();
        comboBoxArea2 = new javax.swing.JComboBox<>();
        comboBoxArea3 = new javax.swing.JComboBox<>();
        comboBoxArea4 = new javax.swing.JComboBox<>();
        comboBoxArea5 = new javax.swing.JComboBox<>();
        botonActualizar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        textLink = new javax.swing.JTextArea();
        botonEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(247, 242, 216));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(labelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 218, 200));

        textNombre.setBackground(new java.awt.Color(255, 255, 51));
        textNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 218, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("REQUISITOS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        textRequerimientos.setBackground(new java.awt.Color(255, 255, 51));
        textRequerimientos.setColumns(20);
        textRequerimientos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textRequerimientos.setRows(5);
        jScrollPane1.setViewportView(textRequerimientos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 37, 217, 218));

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("¿TIENE TUTORIAL?");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setText("COSTO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, -1, -1));

        textCosto.setBackground(new java.awt.Color(255, 255, 51));
        textCosto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel1.add(textCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 174, 112, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setText("CARACTERISTICAS");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, -1, -1));

        textCaracteristicas.setBackground(new java.awt.Color(255, 255, 51));
        textCaracteristicas.setColumns(20);
        textCaracteristicas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textCaracteristicas.setRows(5);
        jScrollPane2.setViewportView(textCaracteristicas);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 217, 191));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setText("FECHA DE CONSULTA");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 470, -1, -1));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setText("LINK");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setText("ÁREAS");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, -1, -1));

        botonImagen.setBackground(new java.awt.Color(253, 193, 1));
        botonImagen.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonImagen.setText("Elegir imagen");
        botonImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonImagenActionPerformed(evt);
            }
        });
        jPanel1.add(botonImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        radioSi.setBackground(new java.awt.Color(247, 242, 216));
        radioSi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        radioSi.setText("Si");
        jPanel1.add(radioSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 77, -1, -1));

        radioNo.setBackground(new java.awt.Color(247, 242, 216));
        radioNo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        radioNo.setText("No");
        jPanel1.add(radioNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(681, 77, -1, -1));

        comboBoxArea1.setBackground(new java.awt.Color(255, 255, 51));
        comboBoxArea1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboBoxArea1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboBoxArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, -1, -1));

        comboBoxArea2.setBackground(new java.awt.Color(255, 255, 51));
        comboBoxArea2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboBoxArea2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboBoxArea2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, -1, -1));

        comboBoxArea3.setBackground(new java.awt.Color(255, 255, 51));
        comboBoxArea3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboBoxArea3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboBoxArea3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, -1, -1));

        comboBoxArea4.setBackground(new java.awt.Color(255, 255, 51));
        comboBoxArea4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboBoxArea4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboBoxArea4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 500, -1, -1));

        comboBoxArea5.setBackground(new java.awt.Color(255, 255, 51));
        comboBoxArea5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        comboBoxArea5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboBoxArea5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, -1, -1));

        botonActualizar.setBackground(new java.awt.Color(253, 193, 1));
        botonActualizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonActualizar.setText("Actualizar");
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(botonActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 620, -1, -1));

        textLink.setBackground(new java.awt.Color(255, 255, 51));
        textLink.setColumns(20);
        textLink.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textLink.setRows(5);
        jScrollPane3.setViewportView(textLink);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, 240, -1));

        botonEliminar.setBackground(new java.awt.Color(255, 0, 0));
        botonEliminar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(botonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 620, -1, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("MXN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 175, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("$");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 175, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            InputStream fis = new Consultas(ipAddress).getLogo(id_simulador);
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
            
            java.sql.Date fechaSQL = new java.sql.Date(calendar.getDate().getTime());
            String fecha = fechaSQL.toString();

            String update = "UPDATE simuladores "
                          + "SET logo = ?, nombre_simulador = ?, requerimientos = ?, "
                          + "tutorial = ?, costo = ?, caracteristicas = ?, link = ?, "
                          + "fecha_consulta = ? "
                          + "WHERE id_simulador = '" + id_simulador + "'";
            
            String checkSimuladores = "SELECT * FROM simuladores WHERE nombre_simulador = '" + nombre + "'";
            
            try{
                Connection cn = new Conexion(ipAddress).conectar();
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
                        DefaultTableModel model = (DefaultTableModel) pp.tablaSimuladores.getModel();
                        model.setRowCount(0);
                        pp.verTabla();
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
            
            Connection cn = new Conexion(ipAddress).conectar();
            
            PreparedStatement pstUpdateAreas = cn.prepareStatement(updateAreas);
            PreparedStatement pstInsertAreas = cn.prepareStatement(insertAreas);
            PreparedStatement pstCheck = cn.prepareStatement(check);
            
            ResultSet rsCheck;
            
            for(int i = 0; i < areas.size(); i++){
                String idArea = "";
                if(areas.get(i).toString().equals("-") && !areas.get(i).equals(areas_actuales[i])){
                    idArea = new Areas(ipAddress).getIDArea(areas_actuales[i]);
                    eliminarArea(idArea);
                    areas_actuales[i] = areas.get(i).toString();
                }
                else{
                    idArea = new Areas(ipAddress).getIDArea(areas.get(i).toString());
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
                        String idAreaOriginal = new Areas(ipAddress).getIDArea(areas_actuales[i]);
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
            
            Connection cn = new Conexion(ipAddress).conectar();
            PreparedStatement pstDelete = cn.prepareStatement(delete);
            PreparedStatement pstCheck = cn.prepareStatement(check);
            ResultSet rsCheck = pstCheck.executeQuery();
            
            int yes_no = JOptionPane.showConfirmDialog(null, 
                            "¿Seguro que quiere eliminar una o más áreas de este simulador?", "Alerta", 
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (yes_no == 0) {
                if (rsCheck.next()) {
                    pstDelete.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel) pp.tablaSimuladores.getModel();
                    model.setRowCount(0);
                    pp.verTabla();
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
        fc.setFileFilter(imageFilter);
        fc.setAcceptAllFileFilterUsed(false);
        try{
            int returnVal = fc.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                file = fc.getSelectedFile();
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
            
            Connection cn = new Conexion(ipAddress).conectar();
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
                    DefaultTableModel model = (DefaultTableModel) pp.tablaSimuladores.getModel();
                    model.setRowCount(0);
                    pp.verTabla();
                    pp.setEnabled(true);
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
            
            Connection cn = new Conexion(ipAddress).conectar();
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
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
