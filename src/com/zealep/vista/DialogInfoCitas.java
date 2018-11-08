/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.vista;

import com.zealep.modelo.Cita;
import com.zealep.negocio.CitaDAO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author user
 */
public class DialogInfoCitas extends javax.swing.JDialog {

    CitaDAO citaDAO = new CitaDAO();
    TableRowSorter trsFiltro;
    
    public DialogInfoCitas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mostrar();
    }
    
    public void mostrar() {

        List<Cita> citas = new ArrayList<>();
        citas = citaDAO.listarCitas();
        DefaultTableModel modelo = new DefaultTableModel();
        String[] titulos = {"Id","Idpaciente", "Apellidos", "Nombres", "Dni", "Nro Hist.","Celular","Fecha Cita", "Hora Cita", "Asunto","Estado"};
        modelo.setColumnIdentifiers(titulos);
        Object obj[] = new Object[modelo.getColumnCount()];

        for (int i = 0; i < citas.size(); i++) {
            obj[0] = citas.get(i).getIdCita();
            obj[1] = citas.get(i).getPaciente().getIdPaciente();
            obj[2] = citas.get(i).getPaciente().getApellidos();
            obj[3] = citas.get(i).getPaciente().getNombres();
            obj[4] = citas.get(i).getPaciente().getDni();
            obj[5] = citas.get(i).getPaciente().getNroHistoria();
            obj[6] = citas.get(i).getPaciente().getCelular();
            obj[7] = citas.get(i).getFechaCita();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm a");
            LocalTime l = citas.get(i).getHoraCita();
            obj[8] = l.format(dtf);
            obj[9] = citas.get(i).getAsunto();
            obj[10] = citas.get(i).getEstado();

            modelo.addRow(obj);

        }

        tableCitaPacientes.setModel(modelo);
        tableCitaPacientes.getColumnModel().getColumn(0).setMinWidth(0);
        tableCitaPacientes.getColumnModel().getColumn(0).setMaxWidth(0);
        tableCitaPacientes.getColumnModel().getColumn(1).setMinWidth(0);
        tableCitaPacientes.getColumnModel().getColumn(1).setMaxWidth(0);
        tableCitaPacientes.getColumnModel().getColumn(2).setPreferredWidth(150);
        tableCitaPacientes.getColumnModel().getColumn(3).setPreferredWidth(150);
        tableCitaPacientes.getColumnModel().getColumn(9).setPreferredWidth(350);

    }
    
    
         // BUSQUEDA FILTRO EN LA TABLA
    void busquedaFiltro() {

        

            int opcionBuscar = 0;

            if (rbApellidos.isSelected()) {
                opcionBuscar = 2;
            } else if (rbNombres.isSelected()) {
                opcionBuscar = 3;
            } else if (rbFechaCita.isSelected()) {
                opcionBuscar = 7;
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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemEliminar = new javax.swing.JMenuItem();
        jMenuItemAtentida = new javax.swing.JMenuItem();
        jMenuItemPendiente = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCitaPacientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        rbApellidos = new javax.swing.JRadioButton();
        rbNombres = new javax.swing.JRadioButton();
        rbFechaCita = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();

        jMenuItemEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cancel.png"))); // NOI18N
        jMenuItemEliminar.setText("Eliminar");
        jMenuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemEliminar);

        jMenuItemAtentida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Valid.png"))); // NOI18N
        jMenuItemAtentida.setText("Atendida");
        jMenuItemAtentida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAtentidaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemAtentida);

        jMenuItemPendiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/horario_m.png"))); // NOI18N
        jMenuItemPendiente.setText("Pendiente");
        jMenuItemPendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPendienteActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemPendiente);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Citas de los Pacientes");

        tableCitaPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableCitaPacientes.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tableCitaPacientes);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Busqueda :"));

        buttonGroup1.add(rbApellidos);
        rbApellidos.setText("Apellidos");

        buttonGroup1.add(rbNombres);
        rbNombres.setText("Nombres");

        buttonGroup1.add(rbFechaCita);
        rbFechaCita.setText("Fecha Cita");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbApellidos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbNombres)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbFechaCita)
                        .addGap(0, 84, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbApellidos)
                    .addComponent(rbNombres)
                    .addComponent(rbFechaCita))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        trsFiltro = new TableRowSorter(tableCitaPacientes.getModel());
        tableCitaPacientes.setRowSorter(trsFiltro);
        
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void jMenuItemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEliminarActionPerformed
        int fila = tableCitaPacientes.convertRowIndexToModel(tableCitaPacientes.getSelectedRow());
        DefaultTableModel df = (DefaultTableModel) tableCitaPacientes.getModel();
        if(fila == -1 )
        {
            JOptionPane.showMessageDialog(null,"Debes de seleccionar una cita para eliminarla","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            int confirmar = JOptionPane.showConfirmDialog(null,
                "Esta seguro que desea Eliminar el registro? ");

            if (JOptionPane.OK_OPTION == confirmar) {
                int idCita = Integer.parseInt(df.getValueAt(fila,0).toString()) ;
                citaDAO.eliminarCita(idCita);
                mostrar();

            }
        }

    }//GEN-LAST:event_jMenuItemEliminarActionPerformed

    private void jMenuItemAtentidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAtentidaActionPerformed
        int fila = tableCitaPacientes.convertRowIndexToModel(tableCitaPacientes.getSelectedRow());
        DefaultTableModel df = (DefaultTableModel) tableCitaPacientes.getModel();
        if(fila == -1 )
        {
            JOptionPane.showMessageDialog(null,"Debes de seleccionar una cita para cambiar su estado","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
        else
        {      int idCita = Integer.parseInt(df.getValueAt(fila,0).toString()) ;
            citaDAO.cambiarEstadoCita(idCita,"ATENDIDA");
            mostrar();
        }

    }//GEN-LAST:event_jMenuItemAtentidaActionPerformed

    private void jMenuItemPendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPendienteActionPerformed

        int fila = tableCitaPacientes.convertRowIndexToModel(tableCitaPacientes.getSelectedRow());
        DefaultTableModel df = (DefaultTableModel) tableCitaPacientes.getModel();
        if(fila == -1 )
        {
            JOptionPane.showMessageDialog(null,"Debes de seleccionar una cita para cambiar su estado","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
        else
        {  int idCita = Integer.parseInt(df.getValueAt(fila,0).toString()) ;
            citaDAO.cambiarEstadoCita(idCita,"PENDIENTE");
            mostrar();
        }
    }//GEN-LAST:event_jMenuItemPendienteActionPerformed

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
            java.util.logging.Logger.getLogger(DialogInfoCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogInfoCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogInfoCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogInfoCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogInfoCitas dialog = new DialogInfoCitas(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenuItem jMenuItemAtentida;
    private javax.swing.JMenuItem jMenuItemEliminar;
    private javax.swing.JMenuItem jMenuItemPendiente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbApellidos;
    private javax.swing.JRadioButton rbFechaCita;
    private javax.swing.JRadioButton rbNombres;
    private javax.swing.JTable tableCitaPacientes;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}