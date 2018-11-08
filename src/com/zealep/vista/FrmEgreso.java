/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.vista;

import com.zealep.modelo.Egreso;
import com.zealep.modelo.Procedimiento;
import com.zealep.negocio.EgresoDAO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author user
 */
public class FrmEgreso extends javax.swing.JInternalFrame {

     Egreso egreso = new Egreso();
    EgresoDAO egresoDAO = new EgresoDAO();
     String accion = "";
    TableRowSorter trsFiltro;
    public FrmEgreso() {
        initComponents();
        mostrar();
        inhabilitar();
        lblIdTratamiento.setVisible(false);
        txtIdEgreso.setVisible(false);
    }
    
      //METODOS 
    public void mostrar() {
        List<Egreso> egresos = new ArrayList<>();
        egresos = egresoDAO.listar();
        DefaultTableModel modelo = new DefaultTableModel();
        String[] titulos = {"Id","Fecha","Descripcion","Costo"};
        modelo.setColumnIdentifiers(titulos);
        Object obj[] = new Object[modelo.getColumnCount()];
         for(int i=0;i<egresos.size();i++)
         {
             obj[0]=egresos.get(i).getIdEgreso();
             obj[1]=egresos.get(i).getFechaEgreso();
             obj[2]=egresos.get(i).getDescripcion();
             obj[3]=egresos.get(i).getCosto();
             modelo.addRow(obj);
         }
        tableEgreso.setModel(modelo);
        
        lblCantidadRegistros.setText("La cantidad de egresos es: " + String.valueOf(egresoDAO.cantidadRegistros));

    }

    public void limpiar() {
        txtIdEgreso.setText("");
        dateFechaEgreso.setDate(null);
        txtDescripcion.setText("");
        txtCosto.setText("");
        txtBuscar.setText("");


    }

    public void inhabilitar() {
        btnNuevo.setEnabled(true);
        dateFechaEgreso.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtCosto.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(true);
        btnCancelar.setEnabled(false);

    }

    public void habilitar() {
        dateFechaEgreso.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtCosto.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnModificar.setEnabled(false);
        btnCancelar.setEnabled(true);

    }

    // BUSQUEDA FILTRO EN LA TABLA
    void busquedaFiltro() {

        {

            int opcionBuscar = 0;

            if (rbFecha.isSelected()) {
                opcionBuscar = 1;
            } else if (rbDescripcion.isSelected()) {
                opcionBuscar =2;
            } 

            trsFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText(), opcionBuscar));

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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemEliminar = new javax.swing.JMenuItem();
        tabEgreso = new javax.swing.JTabbedPane();
        panelBuscar = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rbFecha = new javax.swing.JRadioButton();
        rbDescripcion = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEgreso = new javax.swing.JTable();
        lblCantidadRegistros = new javax.swing.JLabel();
        panelNuevo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblIdTratamiento = new javax.swing.JLabel();
        txtIdEgreso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        dateFechaEgreso = new com.toedter.calendar.JDateChooser();
        jToolBar1 = new javax.swing.JToolBar();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        jMenuItemEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cancelar_p.png"))); // NOI18N
        jMenuItemEliminar.setText("Eliminar");
        jMenuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemEliminar);

        setTitle("Gestión de Gastos y Egresos");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Criterios de busqueda"));

        rbFecha.setText("Fecha");

        rbDescripcion.setText("Descripcion");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rbFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbDescripcion)))
                .addContainerGap(258, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbFecha)
                    .addComponent(rbDescripcion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableEgreso = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        tableEgreso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableEgreso.setComponentPopupMenu(jPopupMenu1);
        tableEgreso.setFocusable(false);
        tableEgreso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEgresoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableEgreso);

        lblCantidadRegistros.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCantidadRegistros.setText("lblCantidadRegistros");

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCantidadRegistros)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCantidadRegistros)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        tabEgreso.addTab("Buscar", panelBuscar);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos del tipo de tratamiento"));

        lblIdTratamiento.setText("Id Egreso");

        txtIdEgreso.setEnabled(false);

        jLabel2.setText("Fecha Egreso :");

        jLabel3.setText("Descripcion :");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        jLabel4.setText("Costo :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblIdTratamiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dateFechaEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCosto)))))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateFechaEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdTratamiento)
                    .addComponent(txtIdEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelNuevoLayout = new javax.swing.GroupLayout(panelNuevo);
        panelNuevo.setLayout(panelNuevoLayout);
        panelNuevoLayout.setHorizontalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        panelNuevoLayout.setVerticalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(342, Short.MAX_VALUE))
        );

        tabEgreso.addTab("Nuevo/Modificar", panelNuevo);

        jToolBar1.setRollover(true);

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/page_add.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setToolTipText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNuevo);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/page_save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGuardar);

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/page_edit.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setToolTipText("Editar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnModificar);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/page_delete.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setToolTipText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCancelar);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/principal.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setToolTipText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSalir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                String cadena = (txtBuscar.getText());
                txtBuscar.setText(cadena);
                repaint();
                busquedaFiltro();
            }
        });
        trsFiltro = new TableRowSorter(tableEgreso.getModel());
        tableEgreso.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void tableEgresoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEgresoMouseClicked

        int fila;
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        fila = tableEgreso.convertRowIndexToModel(tableEgreso.getSelectedRow());

        if (fila == -1){
            JOptionPane.showMessageDialog(null, "Se debe seleccionar un registro");
        }else{
            try {
                defaultTableModel = (DefaultTableModel)tableEgreso.getModel();
                txtIdEgreso.setText(defaultTableModel.getValueAt(fila, 0).toString());
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaEgreso = date.parse(defaultTableModel.getValueAt(fila, 1).toString());
                dateFechaEgreso.setDate(fechaEgreso);
                txtDescripcion.setText(defaultTableModel.getValueAt(fila, 2).toString());
                txtCosto.setText(defaultTableModel.getValueAt(fila, 3).toString());
            } catch (ParseException ex) {
                Logger.getLogger(FrmEgreso.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_tableEgresoMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion = "Nuevo";
        limpiar();
        habilitar();
        tabEgreso.setSelectedIndex(tabEgreso.indexOfComponent(panelNuevo));
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        egreso.setFechaEgreso(dateFechaEgreso.getDate());
        egreso.setCosto(Double.parseDouble(txtCosto.getText()));
        egreso.setDescripcion(txtDescripcion.getText());

        if (accion.equals("Nuevo")) {
            egresoDAO.insertar(egreso);
            JOptionPane.showMessageDialog(null, "El Egreso fue registrado correctamente");

        } else if (accion.equals("Modificar")) {
            egreso.setIdEgreso(Integer.parseInt(txtIdEgreso.getText()));
            egresoDAO.modificar(egreso);
            JOptionPane.showMessageDialog(null, "El Egreso fue actualizado correctamente");

        }
        mostrar();
        limpiar();
        inhabilitar();
        tabEgreso.setSelectedIndex(tabEgreso.indexOfComponent(panelBuscar));
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (tableEgreso.getSelectedRows().length > 0) {
            accion = "Modificar";
            habilitar();
            tabEgreso.setSelectedIndex(tabEgreso.indexOfComponent(panelNuevo));
        } else {
            JOptionPane.showMessageDialog(null, "¡Se debe seleccionar un registro!");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inhabilitar();
        limpiar();
        tabEgreso.setSelectedIndex(tabEgreso.indexOfComponent(panelBuscar));
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void jMenuItemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarActionPerformed
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableEgreso.getModel();
       int fila = tableEgreso.convertRowIndexToModel(tableEgreso.getSelectedRow());
       if(fila == -1)
       {
           JOptionPane.showMessageDialog(null,"Selecciona una fila para eliminar");
       }
       else
       {
        int idEgreso = Integer.parseInt(defaultTableModel.getValueAt(fila,0).toString());
        int confirmar = JOptionPane.showConfirmDialog(null,
                    "Esta seguro que desea eliminar el paciente? ");

            if (JOptionPane.OK_OPTION == confirmar) {
                boolean rpta=egresoDAO.eliminarEgreso(idEgreso);
                if(rpta)
                {
                    JOptionPane.showMessageDialog(null, "Egreso Eliminado");
                }
                else
                {
                  JOptionPane.showMessageDialog(null,"No se pudo eliminar el egreso");
                }
             
                mostrar();
            }
       }
    }//GEN-LAST:event_jMenuItemEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private com.toedter.calendar.JDateChooser dateFechaEgreso;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItemEliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCantidadRegistros;
    private javax.swing.JLabel lblIdTratamiento;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelNuevo;
    private javax.swing.JRadioButton rbDescripcion;
    private javax.swing.JRadioButton rbFecha;
    private javax.swing.JTabbedPane tabEgreso;
    private javax.swing.JTable tableEgreso;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtIdEgreso;
    // End of variables declaration//GEN-END:variables
}