
package GUI;

import aplicacion.DetalleVenta;
import aplicacion.Producto;
import aplicacion.Venta;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
public class menuPrincipal extends javax.swing.JFrame {

    public menuPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        String[] columnNamesProducto = {"Codigo", "Nombre", "Precio", "Stock"};
        tablemodelProductos = new DefaultTableModel(columnNamesProducto,0); 
        String[] columnNamesVenta = {"Codigo Prod", "Cantidad", "Producto", "Precio", "Subtotal"};
        tablemodelVenta = new DefaultTableModel(columnNamesVenta,0);
        jTableVenta.setModel(tablemodelVenta);
        jTableProductos.getTableHeader().setReorderingAllowed(false); 
        jTableVenta.setModel(tablemodelVenta);
        CargarTablaProductos();
        AddListeners();
    }
    
    public void CargarTablaProductos()
    {
        try {
            tablemodelProductos.setRowCount(0);
            Producto prod = new Producto();
            List<Producto> productos = prod.CargarProductos();
            for ( Producto productoActual : productos)
            {
                tablemodelProductos.addRow(new Object[]{productoActual.getId(),productoActual.getNombre()
                        ,productoActual.getPrecio(),productoActual.getStock()});
            }
            jTableProductos.setModel(tablemodelProductos);
            jTableProductos.clearSelection();
        } catch (ClassNotFoundException ex) {
            System.getLogger(GestionProductos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (SQLException ex) {
            System.getLogger(GestionProductos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    private void updateSubtotal() {
        String cantidadText = txtCantidad.getText();
        String precioText   = txtPrecio.getText();
        if (cantidadText.isEmpty() || precioText.isEmpty()) {
            txtSubtotal.setText("");
            btnAgregarAVenta.setEnabled(false);
        }
        else{
            float cantidad = Float.parseFloat(cantidadText);
            float precio   = Float.parseFloat(precioText);

            float subtotal = cantidad * precio;
            txtSubtotal.setText(String.valueOf(subtotal));
            btnAgregarAVenta.setEnabled(true);
        }
    }
    
    public void AddListeners(){
        //Listener tabla productos que carga los txt 
        jTableProductos.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && jTableProductos.getSelectedRow() != -1) {
                int selectedRow = jTableProductos.getSelectedRow();
                txtCodigo.setText(jTableProductos.getValueAt(selectedRow, 0).toString());
                txtProducto.setText(jTableProductos.getValueAt(selectedRow, 1).toString());
                txtPrecio.setText(jTableProductos.getValueAt(selectedRow, 2).toString());
                txtCantidad.setEnabled(true);
            }
            else
            {
                txtCantidad.setEnabled(false);
                btnAgregarAVenta.setEnabled(false);
            }    
        });
        //Listener textbox
        txtCantidad.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSubtotal();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSubtotal();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSubtotal(); 
            }
        });;
        //Listener tabla venta de selección (para activar el boton de quitar)
        jTableVenta.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && jTableVenta.getSelectedRow() != -1) {
                btnQuitarProd.setEnabled(true);
            }
            else
            {
                btnQuitarProd.setEnabled(false);
            }    
        });
        //Listener tabla venta si tiene filas (para el boton siguiente)
        tablemodelVenta.addTableModelListener(e -> {
        if (tablemodelVenta.getRowCount() > 0) {
            btnSiguiente.setEnabled(true);
        } else {
            btnSiguiente.setEnabled(false);
        }
        });
        
    }
      
    
    
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnAgregarAVenta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVenta = new javax.swing.JTable();
        btnQuitarProd = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemGestionProductos = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btnGestionClientes = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Menú Principal");

        jTableProductos = new javax.swing.JTable(){
            public boolean isCellEditable (int rowIndex,int colIndex)
            {
                return false;
            }
        };
        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableProductos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableProductos);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Lista de Productos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Precio:");

        jLabel3.setText("Cantidad:");

        jLabel4.setText("Subtotal:");

        txtProducto.setEnabled(false);

        txtPrecio.setEnabled(false);

        txtCantidad.setEnabled(false);

        txtSubtotal.setEnabled(false);

        jLabel1.setText("Producto:");

        btnAgregarAVenta.setText("Agregar a venta");
        btnAgregarAVenta.setEnabled(false);
        btnAgregarAVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAVentaActionPerformed(evt);
            }
        });

        jTableVenta = new javax.swing.JTable(){
            public boolean isCellEditable (int rowIndex,int colIndex)
            {
                return false;
            }
        };
        jTableVenta.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableVenta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableVenta.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableVenta);

        btnQuitarProd.setText("Quitar Producto Seleccionado");
        btnQuitarProd.setEnabled(false);
        btnQuitarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarProdActionPerformed(evt);
            }
        });

        btnSiguiente.setText("Siguiente");
        btnSiguiente.setEnabled(false);
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        txtTotal.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Total:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Registro de Venta");

        txtCodigo.setEnabled(false);

        jLabel9.setText("Codigo Producto:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnQuitarProd)
                        .addGap(147, 147, 147))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel5)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSiguiente)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(btnAgregarAVenta))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtProducto))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtSubtotal)
                                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAgregarAVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQuitarProd)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSiguiente)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(590, 590, 590)
                .addComponent(jLabel6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu3.setText("Productos");

        jMenuItemGestionProductos.setText("Gestion de Productos");
        jMenuItemGestionProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGestionProductosActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemGestionProductos);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Clientes");

        btnGestionClientes.setText("Gestion de Clientes");
        btnGestionClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionClientesActionPerformed(evt);
            }
        });
        jMenu4.add(btnGestionClientes);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Reportes");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGestionClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionClientesActionPerformed
        new GestionClientes().setVisible(true);
    }//GEN-LAST:event_btnGestionClientesActionPerformed

    private void jMenuItemGestionProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGestionProductosActionPerformed
                new GestionProductos().setVisible(true);
    }//GEN-LAST:event_jMenuItemGestionProductosActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        new ElegirCliente().setVisible(true);
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAgregarAVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAVentaActionPerformed
        tablemodelVenta.addRow(new Object[]{
            txtCodigo.getText(),
            txtCantidad.getText(),
            txtProducto.getText(),
            txtPrecio.getText(),
            txtSubtotal.getText()
        });

        jTableVenta.setModel(tablemodelVenta);
        jTableVenta.clearSelection();

        // limpiar los campos
        txtCodigo.setText("");
        txtProducto.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        txtSubtotal.setText("");
        jTableProductos.clearSelection();
        
        calcularTotal();   
    }//GEN-LAST:event_btnAgregarAVentaActionPerformed

    private void calcularTotal() {
        float total = 0f;

        int colSubtotal = jTableVenta.getColumnModel().getColumnIndex("Subtotal");

        for (int i = 0; i < tablemodelVenta.getRowCount(); i++) {
            Object valor = tablemodelVenta.getValueAt(i, colSubtotal);
            if (valor != null) {
                total += Float.parseFloat(valor.toString());
            }
        }

        txtTotal.setText(String.valueOf(total));
    }
    
    private void generarVenta() throws ClassNotFoundException, SQLException
    {
        
        List<DetalleVenta> detalles = new ArrayList<>();
        for (int i = 0; i < tablemodelVenta.getRowCount(); i++) {
            Producto productoActual =  new Producto();
            productoActual = productoActual.BuscarProducto((int) tablemodelVenta.getValueAt(i, 0));
            float cantidad = (float) tablemodelVenta.getValueAt(i,1);
            float precio = (float) tablemodelVenta.getValueAt(i,3);
            float subtotal = (float) tablemodelVenta.getValueAt(i,4);
            DetalleVenta detalleActual = new DetalleVenta(productoActual,precio,cantidad,subtotal);
            detalles.add(detalleActual);
        }
        Venta ventaActual = new Venta();
    }
    
    private void btnQuitarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarProdActionPerformed
        int fila = jTableVenta.getSelectedRow();
        if (fila != -1) {
            tablemodelVenta.removeRow(fila);
        }
        calcularTotal(); 
    }//GEN-LAST:event_btnQuitarProdActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new menuPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarAVenta;
    private javax.swing.JMenuItem btnGestionClientes;
    private javax.swing.JButton btnQuitarProd;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemGestionProductos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProductos;
    private javax.swing.JTable jTableVenta;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
    private javax.swing.table.DefaultTableModel tablemodelProductos;
    private javax.swing.table.DefaultTableModel tablemodelVenta;

    

    
}
