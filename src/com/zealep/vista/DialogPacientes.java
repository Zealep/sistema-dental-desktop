/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.vista;

import com.zealep.modelo.Paciente;
import com.zealep.negocio.PacienteDAO;
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
 * @author user
 */
public class DialogPacientes extends javax.swing.JDialog {

    Paciente paciente = new Paciente();
    PacienteDAO pacienteDAO = new PacienteDAO();
    TableRowSorter trsFiltro;
    public static String llamado;

    public DialogPacientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mostrar();
    }

    public void mostrar() {
        List<Paciente> pacientes = new ArrayList<>();
        DefaultTableModel df = (DefaultTableModel) tablePaciente.getModel();
        pacientes = pacienteDAO.listar();
        Object[] obj = new Object[11];
        for (int i=0;i<pacientes.size();i++)
        {
                    obj[0]=pacientes.get(i).getIdPaciente();
                    obj[1]= pacientes.get(i).getApellidos();
                    obj[2]= pacientes.get(i).getNombres();
                    obj[3]= pacientes.get(i).getDni();
                    obj[4]= pacientes.get(i).getNroHistoria();
                    obj[5]= pacientes.get(i).getCelular();
                    obj[6]= pacientes.get(i).getTelefono();
                    obj[7]= pacientes.get(i).getDireccion();
                    obj[8]= pacientes.get(i).getFechaNacimiento();
                    obj[9]= pacientes.get(i).getLugarProcedencia();
                    obj[10]= pacientes.get(i).getEmail();
                    df.addRow(obj);
        }
        tablePaciente.setModel(df);

    }

    // BUSQUEDA FILTRO EN LA TABLA
    void busquedaFiltro() {

        {

            int opcionBuscar = 0;

            if (rbApellidos.isSelected()) {
                opcionBuscar = 1;
            } else if (rbNombres.isSelected()) {
                opcionBuscar = 2;
            } else if (rbDni.isSelected()) {
                opcionBuscar = 3;
            } else if (rbCelular.isSelected()) {
                opcionBuscar = 6;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBuscar = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rbApellidos = new javax.swing.JRadioButton();
        rbNombres = new javax.swing.JRadioButton();
        rbDni = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        rbCelular = new javax.swing.JRadioButton();
        btnSeleccionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePaciente = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Criterios de busqueda"));

        buttonGroup1.add(rbApellidos);
        rbApellidos.setText("Apellidos");

        buttonGroup1.add(rbNombres);
        rbNombres.setText("Nombres");

        buttonGroup1.add(rbDni);
        rbDni.setText("Dni");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        buttonGroup1.add(rbCelular);
        rbCelular.setText("Celular");

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Agregar_p1.png"))); // NOI18N
        btnSeleccionar.setText("Seleccionar Paciente");
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
                        .addComponent(rbApellidos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbNombres)
                        .addGap(14, 14, 14)
                        .addComponent(rbDni)
                        .addGap(10, 10, 10)
                        .addComponent(rbCelular)))
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
                            .addComponent(rbApellidos)
                            .addComponent(rbNombres)
                            .addComponent(rbDni)
                            .addComponent(rbCelular))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablePaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdTratamiento", "Apellidos", "Nombres", "Dni", "Nro Historia", "Celular", "Telefono", "Direccion", "F.Nacimiento", "L.Procedencia", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePaciente.setFocusable(false);
        tablePaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePacienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePaciente);
        if (tablePaciente.getColumnModel().getColumnCount() > 0) {
            tablePaciente.getColumnModel().getColumn(0).setMinWidth(0);
            tablePaciente.getColumnModel().getColumn(0).setMaxWidth(0);
        }

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
        jLabel1.setText("Listado de Pacientes");

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
                .addContainerGap(19, Short.MAX_VALUE))
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
        trsFiltro = new TableRowSorter(tablePaciente.getModel());
        tablePaciente.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void tablePacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePacienteMouseClicked


    }//GEN-LAST:event_tablePacienteMouseClicked

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed

        int row;
        row = tablePaciente.convertRowIndexToModel(tablePaciente.getSelectedRow());
        if (row == -1) {
            JOptionPane.showMessageDialog(rootPane, "Debes de seleccionar un paciente");
        } else {
            if (llamado.equals("citas")) {
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                int fila =  tablePaciente.convertRowIndexToModel(tablePaciente.getSelectedRow());
                defaultTableModel = (DefaultTableModel) tablePaciente.getModel();
                FrmCita.txtIdPaciente.setText(defaultTableModel.getValueAt(fila, 0).toString());
                FrmCita.txtApellidosPa.setText(defaultTableModel.getValueAt(fila, 1).toString());
                FrmCita.txtNombresPa.setText(defaultTableModel.getValueAt(fila, 2).toString());
                FrmCita.txtDniPa.setText(defaultTableModel.getValueAt(fila, 3).toString());
                FrmCita.txtNroHistoria.setText(defaultTableModel.getValueAt(fila,4).toString());
            } else if (llamado.equals("tratamiento")) {
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                int fila =  tablePaciente.convertRowIndexToModel(tablePaciente.getSelectedRow());
                defaultTableModel = (DefaultTableModel) tablePaciente.getModel();
                FrmTratamiento.txtIdPaciente.setText(defaultTableModel.getValueAt(fila, 0).toString());
                FrmTratamiento.txtApellidosPa.setText(defaultTableModel.getValueAt(fila, 1).toString());
                FrmTratamiento.txtNombresPa.setText(defaultTableModel.getValueAt(fila, 2).toString());
                FrmTratamiento.txtDniPa.setText(defaultTableModel.getValueAt(fila, 3).toString());
                FrmTratamiento.txtNroHistoria.setText(defaultTableModel.getValueAt(fila,4).toString());
            }
            
            else if (llamado.equals("examen")) {
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                int fila =  tablePaciente.convertRowIndexToModel(tablePaciente.getSelectedRow());
                defaultTableModel = (DefaultTableModel) tablePaciente.getModel();
                FrmExamen.txtIdPaciente.setText(defaultTableModel.getValueAt(fila, 0).toString());
                FrmExamen.txtApellidos.setText(defaultTableModel.getValueAt(fila, 1).toString());
                FrmExamen.txtNombres.setText(defaultTableModel.getValueAt(fila, 2).toString());
                FrmExamen.txtDni.setText(defaultTableModel.getValueAt(fila, 3).toString());
                FrmExamen.txtNroHistoria.setText(defaultTableModel.getValueAt(fila,4).toString());
            }
            else if (llamado.equals("modificar_tratamiento")) {
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                int fila =  tablePaciente.convertRowIndexToModel(tablePaciente.getSelectedRow());
                defaultTableModel = (DefaultTableModel) tablePaciente.getModel();
                FrmModificarTratamiento.txtIdPaciente.setText(defaultTableModel.getValueAt(fila, 0).toString());
                FrmModificarTratamiento.txtApellidosPa.setText(defaultTableModel.getValueAt(fila, 1).toString());
                FrmModificarTratamiento.txtNombresPa.setText(defaultTableModel.getValueAt(fila, 2).toString());
                FrmModificarTratamiento.txtDniPa.setText(defaultTableModel.getValueAt(fila, 3).toString());
                FrmModificarTratamiento.txtNroHistoria.setText(defaultTableModel.getValueAt(fila,4).toString());
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
            java.util.logging.Logger.getLogger(DialogPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogPacientes dialog = new DialogPacientes(new javax.swing.JFrame(), true);
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
    private javax.swing.JRadioButton rbApellidos;
    private javax.swing.JRadioButton rbCelular;
    private javax.swing.JRadioButton rbDni;
    private javax.swing.JRadioButton rbNombres;
    private javax.swing.JTable tablePaciente;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
