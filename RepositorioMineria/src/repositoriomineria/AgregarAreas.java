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
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class AgregarAreas extends javax.swing.JFrame {
    
    String headerAreas[] = {"Areas"};
    DefaultTableModel tableModelAreas = new DefaultTableModel(headerAreas, 0);
    JTable tablaAreas;
    String nombreAntiguo = "";
    PantallaPrincipal pp;
    String ipAddress;

    /**
     * Creates new form AgregarAreas
     */
    public AgregarAreas(PantallaPrincipal p, String ip) {
        super("Agregar Áreas");
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(getIconImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.ipAddress = ip;
        
        tablaAreas = verTablaAreas();
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(new JScrollPane(tablaAreas), BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
        
        tablaAreas.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                textAreaSeleccionada.setText(tablaAreas.getValueAt(tablaAreas.getSelectedRow(), 0).toString());
                nombreAntiguo = textAreaSeleccionada.getText().trim();
            }
        });
        
        this.pp = p;
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                pp.setEnabled(true);
            }
        });
        
        //this.pack();
    }
    
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit()
                .getImage(ClassLoader.getSystemResource("imagenes/cascoIcon.png"));
        return retValue;
    }
    
    public JTable verTablaAreas(){
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
        tabla.setRowHeight(20);
        tabla.setFont(new Font("Arial", Font.BOLD, 14));
        tabla.setForeground(Color.WHITE);
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setBackground(new Color(114,137,218));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tabla.getTableHeader().setForeground(Color.WHITE);
        
        String consulta = "SELECT nombre_area FROM areas";
        
        try{
            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Object fila[] = new Object[1];
                
                fila[0] = rs.getString("nombre_area");
                
                tableModelAreas.addRow(fila);
            }
            tabla.setModel(tableModelAreas);
            cn.close();
            pst.close();
            rs.close();
        }
        catch(Exception e){
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
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textAreaSeleccionada = new javax.swing.JTextField();
        textNuevaArea = new javax.swing.JTextField();
        botonActualizarArea = new javax.swing.JButton();
        botonAgregar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 1));

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 201, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);

        jPanel4.setLayout(new java.awt.GridLayout(4, 2, 100, 10));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Área seleccionada");
        jPanel4.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nueva Área");
        jPanel4.add(jLabel2);

        textAreaSeleccionada.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textAreaSeleccionada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(textAreaSeleccionada);

        textNuevaArea.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textNuevaArea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(textNuevaArea);

        botonActualizarArea.setBackground(new java.awt.Color(51, 51, 255));
        botonActualizarArea.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        botonActualizarArea.setForeground(new java.awt.Color(255, 255, 255));
        botonActualizarArea.setText("Actualizar");
        botonActualizarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarAreaActionPerformed(evt);
            }
        });
        jPanel4.add(botonActualizarArea);

        botonAgregar.setBackground(new java.awt.Color(0, 204, 0));
        botonAgregar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        botonAgregar.setForeground(new java.awt.Color(255, 255, 255));
        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });
        jPanel4.add(botonAgregar);

        botonEliminar.setBackground(new java.awt.Color(255, 0, 0));
        botonEliminar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        botonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        jPanel4.add(botonEliminar);

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActualizarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarAreaActionPerformed
        String nombreArea = textAreaSeleccionada.getText().trim();
        if(!nombreArea.isEmpty() && !nombreAntiguo.isEmpty()){
            try {
                String check = "SELECT * from areas WHERE BINARY nombre_area = '" + nombreArea + "'";
                String consulta = "UPDATE areas SET nombre_area = '" + nombreArea + "' "
                        + "WHERE nombre_area = '" + nombreAntiguo + "'";
                Connection cn = new Conexion().conectar();
                PreparedStatement pstUpdate = cn.prepareStatement(consulta);
                PreparedStatement pstCheck = cn.prepareStatement(check);
                
                ResultSet rsCheck = pstCheck.executeQuery();
                
                if(!rsCheck.next()){
                    int yes_no = JOptionPane.showConfirmDialog(null, 
                                "¿Seguro que quiere cambiar el nombre de esa área?", "Alerta", JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                    
                    if(yes_no == 0){
                        pstUpdate.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Área actualizada correctamente.");
                        tableModelAreas.setRowCount(0);
                        verTablaAreas();
                        DefaultTableModel model = (DefaultTableModel) pp.tablaSimuladores.getModel();
                        model.setRowCount(0);
                        pp.verTabla();
                        pp.comboBoxAreas.removeItem(nombreAntiguo);
                        pp.comboBoxAreas.addItem(nombreArea);
                        
                        textAreaSeleccionada.setText("");
                        nombreAntiguo = "";
                        
                        cn.close();
                        pstUpdate.close();
                        pstCheck.close();
                        rsCheck.close();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Ya existe esa área.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
             JOptionPane.showMessageDialog(this, "Debe seleccionar alguno de los simuladores en la lista.");
        }
    }//GEN-LAST:event_botonActualizarAreaActionPerformed

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        String nombreArea = textNuevaArea.getText().trim();
        
        if(!nombreArea.isEmpty()){
            try{
                String check = "SELECT * from areas WHERE nombre_area = '" + nombreArea + "'";
                String insertArea = "INSERT INTO areas values(?,?)";
                
                Connection cn = new Conexion().conectar();
                PreparedStatement pstCheck = cn.prepareStatement(check);
                PreparedStatement pstInsertArea = cn.prepareStatement(insertArea);
                
                ResultSet rsCheck = pstCheck.executeQuery();
                
                if(!rsCheck.next()){
                    pstInsertArea.setString(1, "0");
                    pstInsertArea.setString(2, nombreArea);
                    pstInsertArea.executeUpdate();
                    
                    tableModelAreas.setRowCount(0);
                    verTablaAreas();
                    
                    JOptionPane.showMessageDialog(this, "Área añadida.");
                    
                    pp.comboBoxAreas.addItem(nombreArea);
                    
                    textNuevaArea.setText("");
                    
                    cn.close();
                    pstCheck.close();
                    pstInsertArea.close();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Ya existe esa área.");
                }
                rsCheck.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "No puede agregar un valor vacío.");
        }
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        String nombreArea = textAreaSeleccionada.getText().trim();
        if(!nombreArea.isEmpty() && !nombreAntiguo.isEmpty()){
            try {
                String id_area = new Areas(ipAddress).getIDArea(nombreArea);
                String delete = "DELETE FROM areas WHERE nombre_area = '" + nombreArea + "'";
                Connection cn = new Conexion().conectar();
                PreparedStatement pstDelete = cn.prepareStatement(delete);

                int yes_no = JOptionPane.showConfirmDialog(null,
                        "¿Seguro que quiere eliminar esta área?", "Alerta", JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (yes_no == 0) {
                    eliminarSimuladorArea(id_area);
                    pstDelete.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Área eliminada correctamente.");
                    tableModelAreas.setRowCount(0);
                    verTablaAreas();
                    DefaultTableModel model = (DefaultTableModel) pp.tablaSimuladores.getModel();
                    model.setRowCount(0);
                    pp.verTabla();
                    pp.comboBoxAreas.removeItem(nombreAntiguo);
                    
                    textAreaSeleccionada.setText("");
                    nombreAntiguo = "";

                    cn.close();
                    pstDelete.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
             JOptionPane.showMessageDialog(this, "Debe seleccionar alguno de los simuladores en la lista.");
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void eliminarSimuladorArea(String id_area){
        try{
            String delete = "DELETE FROM simuladorarea WHERE  id_area = '" + id_area + "'";
            Connection cn = new Conexion().conectar();
            PreparedStatement pstDelete = cn.prepareStatement(delete);
            
            pstDelete.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizarArea;
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField textAreaSeleccionada;
    private javax.swing.JTextField textNuevaArea;
    // End of variables declaration//GEN-END:variables
}
