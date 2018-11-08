/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.vista;

import com.zealep.modelo.Control;
import com.zealep.negocio.ControlDAO;
import com.zealep.util.ControlesDTO;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author user
 */
public class DialogInfoControles extends javax.swing.JDialog {

     public static int IDTRATAMIENTO;
     ControlDAO controlDAO = new ControlDAO();
     
    public DialogInfoControles(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mostrarInformacion();
    }
    
    public void mostrarInformacion()
    {   
        List<Control> controles = new ArrayList<>();
        controles = controlDAO.listarControlesPorTratamiento(IDTRATAMIENTO);
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableControles.getModel();
        Object[] obj = new Object[2];
        for(int i=0;i<controles.size();i++)
        {    
            SimpleDateFormat sd = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
            Date fecha = controles.get(i).getFechaControl();
            obj[0] = sd.format(fecha);
            obj[1] = controles.get(i).getComentarios();
            defaultTableModel.addRow(obj);
        }
        tableControles.setModel(defaultTableModel);
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableControles = new javax.swing.JTable();
        btnReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(40, 96, 134));
        jLabel1.setText("Atención y Controles del Paciente");

        tableControles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha de Control", "Comentarios"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableControles.setFocusable(false);
        jScrollPane1.setViewportView(tableControles);
        if (tableControles.getColumnModel().getColumnCount() > 0) {
            tableControles.getColumnModel().getColumn(0).setMinWidth(180);
            tableControles.getColumnModel().getColumn(0).setMaxWidth(180);
        }

        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/printer.png"))); // NOI18N
        btnReporte.setText("Imprimir");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addComponent(btnReporte)
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnReporte))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        DefaultTableModel df = (DefaultTableModel) tableControles.getModel();
        if(df.getRowCount()<1)
        {
            JOptionPane.showMessageDialog(null,"El paciente no tiene controles realizados","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
        else
        {
        List<Control> controles = new ArrayList<>();
        List<ControlesDTO> controlesDTOs = new ArrayList<>();
        controles = controlDAO.listarControlesPorTratamiento(IDTRATAMIENTO);
        
        String apellidos="";
        String nombres="";
        String dni="";
        String nroHistoria="";
        
        for(int i=0;i<controles.size();i++)
        {
            ControlesDTO control = new ControlesDTO();
           SimpleDateFormat sd = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
            Date fecha = controles.get(i).getFechaControl();
            control.setFechaControl(sd.format(fecha));
            control.setComentario(controles.get(i).getComentarios());
            controlesDTOs.add(control);
            //enviar parametros de datos del paciente
            apellidos=controles.get(i).getTratamiento().getPaciente().getApellidos();
            nombres = controles.get(i).getTratamiento().getPaciente().getNombres();
            dni = controles.get(i).getTratamiento().getPaciente().getDni();
            nroHistoria = controles.get(i).getTratamiento().getPaciente().getNroHistoria();
            
        }
        
         Map p = new HashMap();
        String logo = "/imagenes/diamante.png";
        p.put("logo", this.getClass().getResourceAsStream(logo));
        p.put("apellidos",apellidos);
        p.put("nombres", nombres);
        p.put("dni",dni);
        p.put("nroHistoria", nroHistoria);
        
        JasperReport report;
        JasperPrint print;

        try {

            URL url = this.getClass().getResource("/reportes/RptInformacionControles.jasper");
            report = (JasperReport) JRLoader.loadObject(url);
            //report = JasperCompileManager.compileReport(new File("").getAbsolutePath()+"\\src\\reportes\\RptContratoTratamiento.jrxml");
            print = JasperFillManager.fillReport(report, p, new JRBeanCollectionDataSource(controlesDTOs));
            JasperViewer view = new JasperViewer(print, false);
            //jasperviewer como dialog
            JDialog dialog = new JDialog(this);//the owner
            dialog.setContentPane(view.getContentPane());
            dialog.setSize(view.getSize());
            dialog.setTitle("Controles del Paciente");
            dialog.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
        }
        }
    }//GEN-LAST:event_btnReporteActionPerformed

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
            java.util.logging.Logger.getLogger(DialogInfoControles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogInfoControles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogInfoControles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogInfoControles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogInfoControles dialog = new DialogInfoControles(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableControles;
    // End of variables declaration//GEN-END:variables
}
