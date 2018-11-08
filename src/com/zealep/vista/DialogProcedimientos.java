/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.vista;

import com.zealep.modelo.Paciente;
import com.zealep.modelo.Procedimiento;
import com.zealep.negocio.PacienteDAO;
import com.zealep.negocio.ProcedimientoDAO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author CRISTHIAN
 */
public class DialogProcedimientos extends javax.swing.JDialog {

    Procedimiento procedimiento = new Procedimiento();
    ProcedimientoDAO procedimientoDAO = new ProcedimientoDAO();
    TableRowSorter trsFiltro;
    public static String llamado;
    
    public DialogProcedimientos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mostrar();
    }
    
     public void mostrar() {
       List<Procedimiento> procedimientos = new ArrayList<>();
        procedimientos = procedimientoDAO.listar();
        DefaultTableModel modelo = new DefaultTableModel();
        String[] titulos = {"Id","Nombre","Costo","Descripcion"};
        modelo.setColumnIdentifiers(titulos);
        Object obj[] = new Object[modelo.getColumnCount()];
         for(int i=0;i<procedimientos.size();i++)
         {
             obj[0]=procedimientos.get(i).getIdProcedimiento();
             obj[1]=procedimientos.get(i).getNombre();
             obj[2]=procedimientos.get(i).getCosto();
             obj[3]=procedimientos.get(i).getDescripcion();
             modelo.addRow(obj);
         }
        tableProcedimientos.setModel(modelo);
        //lblCantidadRegistros.setText("La cantidad de pacientes es: " + String.valueOf(pacienteDAO.cantidadRegistros));

    }

    // BUSQUEDA FILTRO EN LA TABLA
    void busquedaFiltro() {


            int opcionBuscar = 0;

            if(rbNombre.isSelected()) {
                opcionBuscar = 1;
            } else if (rbCosto.isSelected()) {
                opcionBuscar = 2;
          
             }
            trsFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText(), opcionBuscar));
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBuscar = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rbNombre = new javax.swing.JRadioButton();
        rbCosto = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProcedimientos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Criterios de busqueda"));

        buttonGroup1.add(rbNombre);
        rbNombre.setText("Nombre");

        buttonGroup1.add(rbCosto);
        rbCosto.setText("Costo");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Agregar_p1.png"))); // NOI18N
        btnSeleccionar.setText("Seleccionar Procedimiento");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
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
                        .addComponent(rbNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbCosto)))
                .addGap(40, 40, 40)
                .addComponent(btnSeleccionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSeleccionar)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbNombre)
                            .addComponent(rbCosto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableProcedimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableProcedimientos.setFocusable(false);
        jScrollPane1.setViewportView(tableProcedimientos);

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelBuscarLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 397, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(40, 96, 134));
        jLabel1.setText("Listado de Procedimientos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
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
        trsFiltro = new TableRowSorter(tableProcedimientos.getModel());
        tableProcedimientos.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed

        int row;
        row = tableProcedimientos.convertRowIndexToModel(tableProcedimientos.getSelectedRow());
        if (row == -1) {
            JOptionPane.showMessageDialog(rootPane, "Debes de seleccionar un procedimiento");
        } else {
                if(llamado.equals("tratamiento"))
                {
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                int fila = tableProcedimientos.convertRowIndexToModel(tableProcedimientos.getSelectedRow());
                defaultTableModel = (DefaultTableModel) tableProcedimientos.getModel();
                FrmTratamiento.txtIdProcedimiento.setText(defaultTableModel.getValueAt(fila, 0).toString());
                FrmTratamiento.txtNombreProcedimiento.setText(defaultTableModel.getValueAt(fila, 1).toString());
                FrmTratamiento.txtPrecio.setText(defaultTableModel.getValueAt(fila, 2).toString());
                }
                
                else if(llamado.equals("modificar_tratamiento"))
                {
                 DefaultTableModel defaultTableModel = new DefaultTableModel();
                int fila = tableProcedimientos.convertRowIndexToModel(tableProcedimientos.getSelectedRow());
                defaultTableModel = (DefaultTableModel) tableProcedimientos.getModel();
                FrmModificarTratamiento.txtIdProcedimiento.setText(defaultTableModel.getValueAt(fila, 0).toString());
                FrmModificarTratamiento.txtNombreProcedimiento.setText(defaultTableModel.getValueAt(fila, 1).toString());
                FrmModificarTratamiento.txtPrecio.setText(defaultTableModel.getValueAt(fila, 2).toString());
                }
                
        dispose();
        }
         
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogProcedimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogProcedimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogProcedimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogProcedimientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogProcedimientos dialog = new DialogProcedimientos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JRadioButton rbCosto;
    private javax.swing.JRadioButton rbNombre;
    private javax.swing.JTable tableProcedimientos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}