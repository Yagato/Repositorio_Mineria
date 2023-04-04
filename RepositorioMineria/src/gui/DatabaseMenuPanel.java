package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import database.Areas;
import database.Conexion;
import database.Consultas;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 *
 * @author Carlos Alberto Gonzalez Guerrero
 */
public class DatabaseMenuPanel extends javax.swing.JPanel {

    private String headerSimuladores[] = {"Logo", "Nombre", "Costo (MXN)"};
    private DefaultTableModel tableModelSimuladores = new DefaultTableModel(headerSimuladores, 0);
    private MainScreenFrame myFrame;
    private String rolUsuario;
    private int hoveredRow = -1, hoveredColumn = -1;

    public DatabaseMenuPanel(MainScreenFrame frame, String rol) {
        initComponents();
        
        this.myFrame = frame;
        this.rolUsuario = rol;

        tablaSimuladores = verTabla();

        tablePanel.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tablaSimuladores);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
        
        tablaSimuladores.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(tablaSimuladores.getSelectedRow() == -1) return;
                
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    String nombreSimulador = tablaSimuladores.getValueAt(tablaSimuladores.getSelectedRow(), 1)
                            .toString();
                    new VerSimuladorFrame(myFrame, DatabaseMenuPanel.this, nombreSimulador, rolUsuario).setVisible(true);
                    tablaSimuladores.clearSelection();
                    frame.setEnabled(false);
                }
            }
        });

        searchButton.setBackground(new Color(48, 188, 99));
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));
        searchButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        searchText.setBackground(Color.WHITE);
        searchText.setFont(new Font("Arial", Font.BOLD, 20));
        searchText.setForeground(Color.BLACK);
        searchText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchText.getText().equals("Buscar...")) {
                    searchText.setText("");
                }
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchText.getText().equals("")) {
                    searchText.setText("Buscar...");
                }
                super.focusLost(e);
            }
        });

        searchButton.addActionListener((ActionEvent evt) -> {
            String nombre = searchText.getText().trim();

            if (nombre.equals("Buscar...")) {
                return;
            }

            nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
            tableModelSimuladores.setRowCount(0);
            buscarSimulador(nombre);
        });

        refreshButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        refreshButton.setBackground(new Color(227, 148, 199));

        try {
            Image img = ImageIO.read(getClass().getResource("/imagenes/refresh.png"));
            Image scaledImage = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            refreshButton.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            e.printStackTrace();
        }

        refreshButton.addActionListener((ActionEvent evt) -> {
            tableModelSimuladores.setRowCount(0);
            searchText.setText("");
            comboBoxAreas.setSelectedIndex(0);
            this.verTabla();
        });

        List areas = new Consultas().getListAreas();

        comboBoxAreas.setFont(new Font("Arial", Font.BOLD, 20));
        comboBoxAreas.setBackground(Color.WHITE);
        comboBoxAreas.setForeground(Color.BLACK);
        comboBoxAreas.removeAllItems();
        comboBoxAreas.addItem("-");

        for (int i = 0; i < areas.getItemCount(); i++) {
            comboBoxAreas.addItem(areas.getItem(i));
        }

        comboBoxAreas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreArea = comboBoxAreas.getSelectedItem().toString();
                if (!nombreArea.equals("-")) {
                    tableModelSimuladores.setRowCount(0);
                    filtrarPorAreas(nombreArea);
                    //comboBoxAreas.removeItem("-");
                }
            }
        });
    }

    public JTable renderTable() {
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
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(super.getPreferredSize().width,
                    getRowHeight() * getRowCount());
            }
        };
        
        TablaImagen imgRenderer = new TablaImagen();
        imgRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabla.setBorder(new LineBorder(Color.GRAY, 1));
        tabla.setDefaultRenderer(Object.class, imgRenderer);
        tabla.setRowHeight(80);
        tabla.setDefaultEditor(Object.class, null);
        tabla.setFillsViewportHeight(true);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setFont(new Font("Arial", Font.BOLD, 16));
        tabla.setBackground(Color.WHITE);
        tabla.setForeground(Color.BLACK);
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setBackground(new Color(37, 150, 190));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));

        return tabla;
    }

    public JTable verTabla() {
        JTable tabla = renderTable();

        String consulta = "SELECT logo, nombre_simulador, costo "
                + "FROM simuladores "
                + "GROUP BY simuladores.id_simulador";

        try {
            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];

                Image dimg = null;
                try {
                    BufferedImage im = ImageIO.read(rs.getBinaryStream("logo"));
                    dimg = im.getScaledInstance(110, 64, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(dimg);
                    fila[0] = new JLabel(icon);
                } catch (Exception e) {
                    fila[0] = "";
                }

                fila[1] = rs.getString("nombre_simulador");

                if (rs.getString("costo").equals("") || rs.getString("costo").equals("0")) {
                    fila[2] = "GRATIS";
                } else {
                    fila[2] = rs.getString("costo");
                }

                tableModelSimuladores.addRow(fila);
            }

            tabla.setModel(tableModelSimuladores);
            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tabla;
    }

    public JTable buscarSimulador(String nombre) {
        JTable tabla = renderTable();

        String consulta = "SELECT logo, nombre_simulador, costo "
                + "FROM simuladores "
                + "WHERE nombre_simulador LIKE '" + nombre + "' "
                + "GROUP BY simuladores.id_simulador";

        try {
            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];

                Image dimg = null;
                try {
                    BufferedImage im = ImageIO.read(rs.getBinaryStream("logo"));
                    dimg = im.getScaledInstance(110, 64, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(dimg);
                    fila[0] = new JLabel(icon);
                } catch (Exception e) {
                    fila[0] = "";
                }

                fila[1] = rs.getString("nombre_simulador");

                if (rs.getString("costo").equals("") || rs.getString("costo").equals("0")) {
                    fila[2] = "GRATIS";
                } else {
                    fila[2] = rs.getString("costo");
                }

                tableModelSimuladores.addRow(fila);
            }

            tabla.setModel(tableModelSimuladores);
            cn.close();
            pst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tabla;
    }

    public JTable filtrarPorAreas(String area) {
        JTable tabla = renderTable();

        String id_area = new Areas().getIDArea(area);

        String consulta = "SELECT logo, costo, nombre_simulador "
                + "FROM simuladores, areas, simuladorarea "
                + "WHERE simuladores.id_simulador = simuladorarea.id_simulador "
                + "AND simuladorarea.id_area = areas.id_area "
                + "AND simuladorarea.id_area = '" + id_area + "'";

        try {
            Connection cn = new Conexion().conectar();
            PreparedStatement pst = cn.prepareStatement(consulta);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];

                Image dimg = null;
                try {
                    BufferedImage im = ImageIO.read(rs.getBinaryStream("logo"));
                    dimg = im.getScaledInstance(110, 64, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(dimg);
                    fila[0] = new JLabel(icon);
                } catch (Exception e) {
                    fila[0] = "";
                }

                fila[1] = rs.getString("nombre_simulador");

                if (rs.getString("costo").equals("") || rs.getString("costo").equals("0")) {
                    fila[2] = "GRATIS";
                } else {
                    fila[2] = rs.getString("costo");
                }

                tableModelSimuladores.addRow(fila);
            }

            tabla.setModel(tableModelSimuladores);
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

        topBarPanel = new javax.swing.JPanel();
        searchButton = new javax.swing.JButton();
        searchText = new javax.swing.JTextField();
        comboBoxAreas = new javax.swing.JComboBox<>();
        refreshButton = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        tablaSimuladores = new javax.swing.JTable();

        setBackground(new java.awt.Color(245, 241, 216));
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(750, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topBarPanel.setBackground(new java.awt.Color(245, 241, 216));

        searchButton.setText("Buscar");

        searchText.setBackground(new java.awt.Color(255, 255, 255));
        searchText.setForeground(new java.awt.Color(0, 0, 0));

        comboBoxAreas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout topBarPanelLayout = new javax.swing.GroupLayout(topBarPanel);
        topBarPanel.setLayout(topBarPanelLayout);
        topBarPanelLayout.setHorizontalGroup(
            topBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(searchButton)
                .addGap(18, 18, 18)
                .addComponent(searchText, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(comboBoxAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(refreshButton)
                .addGap(22, 22, 22))
        );
        topBarPanelLayout.setVerticalGroup(
            topBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topBarPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(topBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchText, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(comboBoxAreas)
                    .addComponent(searchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        add(topBarPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, -1));

        tablePanel.setBackground(new java.awt.Color(245, 241, 216));
        tablePanel.setPreferredSize(new java.awt.Dimension(750, 280));
        tablePanel.setLayout(new java.awt.GridLayout());

        tablaSimuladores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablePanel.add(tablaSimuladores);

        add(tablePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 750, 560));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxAreas;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchText;
    public javax.swing.JTable tablaSimuladores;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JPanel topBarPanel;
    // End of variables declaration//GEN-END:variables
}
