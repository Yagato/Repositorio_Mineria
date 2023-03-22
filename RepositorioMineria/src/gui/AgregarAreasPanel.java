package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import database.Areas;
import database.Conexion;

/**
 *
 * @author Lenovo
 */
public class AgregarAreasPanel extends javax.swing.JPanel {

    private String headerAreas[] = {"Areas"};
    private DefaultTableModel tableModelAreas = new DefaultTableModel(headerAreas, 0);
    private JTable tablaAreas;
    private String nombreAntiguo = "";

    public AgregarAreasPanel() {
        initComponents();

        tablaAreas = verTablaAreas();

        tablePanel.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tablaAreas);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();

        tablaAreas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textAreaSeleccionada.setText(tablaAreas.getValueAt(tablaAreas.getSelectedRow(), 0).toString());
                nombreAntiguo = textAreaSeleccionada.getText().trim();
            }
        });
    }

    public JTable verTablaAreas() {
        JTable tabla = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
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
        tabla.setRowHeight(30);
        tabla.setFont(new Font("Arial", Font.BOLD, 28));
        tabla.setBackground(new Color(74, 74, 80));
        tabla.setForeground(Color.WHITE);
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setBackground(new Color(114, 137, 218));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 32));

        String consulta = "SELECT nombre_area FROM areas";

        try {
            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object fila[] = new Object[1];

                fila[0] = rs.getString("nombre_area");

                tableModelAreas.addRow(fila);
            }
            tabla.setModel(tableModelAreas);
            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
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
        tablePanel = new javax.swing.JPanel();
        infoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textAreaSeleccionada = new javax.swing.JTextField();
        textNuevaArea = new javax.swing.JTextField();
        botonActualizarArea = new javax.swing.JButton();
        botonAgregar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(1, 1));

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        tablePanel.setBackground(new java.awt.Color(74, 75, 80));

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );

        jPanel1.add(tablePanel);

        infoPanel.setBackground(new java.awt.Color(74, 75, 80));
        infoPanel.setLayout(new java.awt.GridLayout(4, 2, 100, 10));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Área seleccionada");
        infoPanel.add(jLabel1);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nueva Área");
        infoPanel.add(jLabel2);

        textAreaSeleccionada.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        textAreaSeleccionada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        infoPanel.add(textAreaSeleccionada);

        textNuevaArea.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        textNuevaArea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        infoPanel.add(textNuevaArea);

        botonActualizarArea.setBackground(new java.awt.Color(51, 51, 255));
        botonActualizarArea.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        botonActualizarArea.setForeground(new java.awt.Color(255, 255, 255));
        botonActualizarArea.setText("Actualizar");
        botonActualizarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarAreaActionPerformed(evt);
            }
        });
        infoPanel.add(botonActualizarArea);

        botonAgregar.setBackground(new java.awt.Color(0, 204, 0));
        botonAgregar.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        botonAgregar.setForeground(new java.awt.Color(255, 255, 255));
        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });
        infoPanel.add(botonAgregar);

        botonEliminar.setBackground(new java.awt.Color(255, 0, 0));
        botonEliminar.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        botonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        infoPanel.add(botonEliminar);

        jPanel1.add(infoPanel);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void botonActualizarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarAreaActionPerformed
        String nombreArea = textAreaSeleccionada.getText().trim();
        if (!nombreArea.isEmpty() && !nombreAntiguo.isEmpty()) {
            try {
                String check = "SELECT * from areas WHERE BINARY nombre_area = '" + nombreArea + "'";
                String consulta = "UPDATE areas SET nombre_area = '" + nombreArea + "' "
                        + "WHERE nombre_area = '" + nombreAntiguo + "'";
                Connection cn = new Conexion().conectar();
                PreparedStatement pstUpdate = cn.prepareStatement(consulta);
                PreparedStatement pstCheck = cn.prepareStatement(check);

                ResultSet rsCheck = pstCheck.executeQuery();

                if (!rsCheck.next()) {
                    int yes_no = JOptionPane.showConfirmDialog(null,
                            "¿Seguro que quiere cambiar el nombre de esa área?", "Alerta", JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if (yes_no == 0) {
                        pstUpdate.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Área actualizada correctamente.");
                        tableModelAreas.setRowCount(0);
                        verTablaAreas();

                        textAreaSeleccionada.setText("");
                        nombreAntiguo = "";

                        cn.close();
                        pstUpdate.close();
                        pstCheck.close();
                        rsCheck.close();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ya existe esa área.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar alguno de los simuladores en la lista.");
        }
    }//GEN-LAST:event_botonActualizarAreaActionPerformed

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        String nombreArea = textNuevaArea.getText().trim();

        if (!nombreArea.isEmpty()) {
            try {
                String check = "SELECT * from areas WHERE nombre_area = '" + nombreArea + "'";
                String insertArea = "INSERT INTO areas values(?,?)";

                Connection cn = new Conexion().conectar();
                PreparedStatement pstCheck = cn.prepareStatement(check);
                PreparedStatement pstInsertArea = cn.prepareStatement(insertArea);

                ResultSet rsCheck = pstCheck.executeQuery();

                if (!rsCheck.next()) {
                    pstInsertArea.setString(1, "0");
                    pstInsertArea.setString(2, nombreArea);
                    pstInsertArea.executeUpdate();

                    tableModelAreas.setRowCount(0);
                    verTablaAreas();

                    JOptionPane.showMessageDialog(this, "Área añadida.");

                    textNuevaArea.setText("");

                    cn.close();
                    pstCheck.close();
                    pstInsertArea.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Ya existe esa área.");
                }
                rsCheck.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No puede agregar un valor vacío.");
        }
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        String nombreArea = textAreaSeleccionada.getText().trim();
        if (!nombreArea.isEmpty() && !nombreAntiguo.isEmpty()) {
            try {
                String id_area = new Areas().getIDArea(nombreArea);
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

                    textAreaSeleccionada.setText("");
                    nombreAntiguo = "";

                    cn.close();
                    pstDelete.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar alguno de los simuladores en la lista.");
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void eliminarSimuladorArea(String id_area) {
        try {
            String delete = "DELETE FROM simuladorarea WHERE  id_area = '" + id_area + "'";
            Connection cn = new Conexion().conectar();
            PreparedStatement pstDelete = cn.prepareStatement(delete);

            pstDelete.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizarArea;
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTextField textAreaSeleccionada;
    private javax.swing.JTextField textNuevaArea;
    // End of variables declaration//GEN-END:variables
}
