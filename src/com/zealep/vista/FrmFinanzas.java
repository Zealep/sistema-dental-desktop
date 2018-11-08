/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.vista;

import com.zealep.modelo.Egreso;
import com.zealep.modelo.Pago;
import com.zealep.negocio.EgresoDAO;
import com.zealep.negocio.PagoDAO;
import static com.zealep.vista.FrmHistorialPago.txtIdTratamiento;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class FrmFinanzas extends javax.swing.JInternalFrame {

    PagoDAO pagoDAO = new PagoDAO();
    EgresoDAO egresoDAO = new EgresoDAO();
    
    public FrmFinanzas() {
        initComponents();
        lblTotalIngresos.setText("S/. 0.0");
        lblTotalGastos.setText("S/. 0.0");
        lblDiferencia.setText("S/. 0.0");
    }
    
   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableEgresos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cboMes = new com.toedter.calendar.JMonthChooser();
        spnAnho = new com.toedter.calendar.JYearChooser();
        btnBuscarPorAñoYmes = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableIngresos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTotalIngresos = new javax.swing.JLabel();
        lblTotalGastos = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblDiferencia = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        dateFechaInicio = new com.toedter.calendar.JDateChooser();
        dateFechaFin = new com.toedter.calendar.JDateChooser();
        btnBuscarPorInicioYFin = new javax.swing.JButton();

        setClosable(true);
        setTitle("Control Financiero de Ingresos y Egresos");

        tableEgresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableEgresos);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar Por Mes y Año"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboMes.setPreferredSize(new java.awt.Dimension(160, 80));
        jPanel1.add(cboMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, 20));
        jPanel1.add(spnAnho, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 95, 20));

        btnBuscarPorAñoYmes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscar_p.png"))); // NOI18N
        btnBuscarPorAñoYmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPorAñoYmesActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscarPorAñoYmes, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 70, 20));

        tableIngresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tableIngresos);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 144, 73));
        jLabel1.setText("TOTAL INGRESOS :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("TOTAL GASTOS :");

        lblTotalIngresos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTotalIngresos.setForeground(new java.awt.Color(0, 144, 73));
        lblTotalIngresos.setText("jLabel3");

        lblTotalGastos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTotalGastos.setForeground(new java.awt.Color(255, 0, 51));
        lblTotalGastos.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("DIFERENCIA :");

        lblDiferencia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDiferencia.setText("jLabel6");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotalGastos, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(lblTotalIngresos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDiferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotalIngresos))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTotalGastos))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblDiferencia))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("GASTOS");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("INGRESOS");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Buscar Por Fecha Inicio y Fin"));

        btnBuscarPorInicioYFin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscar_p.png"))); // NOI18N
        btnBuscarPorInicioYFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPorInicioYFinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(dateFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnBuscarPorInicioYFin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dateFechaFin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateFechaInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscarPorInicioYFin, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(163, 163, 163)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarPorAñoYmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPorAñoYmesActionPerformed
        buscarIngresosPorAñoyMes();
        buscarEgresosPorAñoyMes();
        double ingreso = totalIngresos();
        double egreso = totalEgresos();
        double diferencia = ingreso - egreso;
        if(diferencia<0)
        {
            lblDiferencia.setText("S/. "+String.valueOf(diferencia));
            lblDiferencia.setForeground(Color.RED);
        }
        else
        {
         lblDiferencia.setText("S/. "+String.valueOf(diferencia));
          lblDiferencia.setForeground(new Color(0, 144, 73));
        }
    }//GEN-LAST:event_btnBuscarPorAñoYmesActionPerformed

    private void btnBuscarPorInicioYFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPorInicioYFinActionPerformed
        buscarIngresosPorInicioyFin();
        buscarEgresosPorInicioyFin();
        double ingreso = totalIngresos();
        double egreso = totalEgresos();
        double diferencia = ingreso - egreso;
        if(diferencia<0)
        {
            lblDiferencia.setText("S/. "+String.valueOf(diferencia));
            lblDiferencia.setForeground(Color.RED);
        }
        else
        {
         lblDiferencia.setText("S/. "+String.valueOf(diferencia));
            lblDiferencia.setForeground(new Color(0, 144, 73));
        }
    }//GEN-LAST:event_btnBuscarPorInicioYFinActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarPorAñoYmes;
    private javax.swing.JButton btnBuscarPorInicioYFin;
    private com.toedter.calendar.JMonthChooser cboMes;
    private com.toedter.calendar.JDateChooser dateFechaFin;
    private com.toedter.calendar.JDateChooser dateFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDiferencia;
    private javax.swing.JLabel lblTotalGastos;
    private javax.swing.JLabel lblTotalIngresos;
    private com.toedter.calendar.JYearChooser spnAnho;
    private javax.swing.JTable tableEgresos;
    private javax.swing.JTable tableIngresos;
    // End of variables declaration//GEN-END:variables

     public void buscarIngresosPorAñoyMes()
    {
        DefaultTableModel modelo = new DefaultTableModel();
        List<Pago> pagos = new ArrayList<Pago>();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        pagos = pagoDAO.buscarPagosPorAñoyMes(spnAnho.getYear(),cboMes.getMonth()+1);
        String[] titulos = {"Fecha de Pago","Apellidos","Nombres","Descripcion","Monto Total"};
        modelo.setColumnIdentifiers(titulos);
        Object obj[] = new Object[modelo.getColumnCount()];

        for (int i = 0; i < pagos.size(); i++) {
            obj[0] = formateador.format(pagos.get(i).getFechaPago());
            obj[1] = pagos.get(i).getTratamiento().getPaciente().getApellidos();
            obj[2] = pagos.get(i).getTratamiento().getPaciente().getNombres();
            obj[3] = pagos.get(i).getComentarios();
            obj[4] = pagos.get(i).getMonto();
            modelo.addRow(obj);
            
        }
         tableIngresos.setModel(modelo);
        }
    
    
    public void buscarEgresosPorAñoyMes()
    {
    DefaultTableModel modelo = new DefaultTableModel();
        List<Egreso> egresos = new ArrayList<Egreso>();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        egresos = egresoDAO.buscarEgresosPorMesyAño(spnAnho.getYear(),cboMes.getMonth()+1);
        String[] titulos = {"Fecha de Egreso","Descripcion","Monto Total"};
        modelo.setColumnIdentifiers(titulos);
        Object obj[] = new Object[modelo.getColumnCount()];

        for (int i = 0; i < egresos.size(); i++) {
            obj[0] = formateador.format(egresos.get(i).getFechaEgreso());
            obj[1] = egresos.get(i).getDescripcion();
            obj[2] = egresos.get(i).getCosto();
            modelo.addRow(obj);
            
        }
         tableEgresos.setModel(modelo);
      
        }
    
     public void buscarIngresosPorInicioyFin()
    {
        DefaultTableModel modelo = new DefaultTableModel();
        List<Pago> pagos = new ArrayList<Pago>();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        pagos = pagoDAO.buscarPagosPorFechaInicioyFin(dateFechaInicio.getDate(), dateFechaFin.getDate());
        String[] titulos = {"Fecha de Pago","Apellidos","Nombres","Descripcion","Monto Total"};
        modelo.setColumnIdentifiers(titulos);
        Object obj[] = new Object[modelo.getColumnCount()];

        for (int i = 0; i < pagos.size(); i++) {
            obj[0] = formateador.format(pagos.get(i).getFechaPago());
            obj[1] = pagos.get(i).getTratamiento().getPaciente().getApellidos();
            obj[2] = pagos.get(i).getTratamiento().getPaciente().getNombres();
            obj[3] = pagos.get(i).getComentarios();
            obj[4] = pagos.get(i).getMonto();
            modelo.addRow(obj);
            
        }
         tableIngresos.setModel(modelo);
        }
    
    
    public void buscarEgresosPorInicioyFin()
    {
    DefaultTableModel modelo = new DefaultTableModel();
        List<Egreso> egresos = new ArrayList<Egreso>();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        egresos = egresoDAO.buscarEgresosPorFechaInicioyFin(dateFechaInicio.getDate(), dateFechaFin.getDate());
        String[] titulos = {"Fecha de Egreso","Descripcion","Monto Total"};
        modelo.setColumnIdentifiers(titulos);
        Object obj[] = new Object[modelo.getColumnCount()];

        for (int i = 0; i < egresos.size(); i++) {
            obj[0] = formateador.format(egresos.get(i).getFechaEgreso());
            obj[1] = egresos.get(i).getDescripcion();
            obj[2] = egresos.get(i).getCosto();
            modelo.addRow(obj);
            
        }
         tableEgresos.setModel(modelo);
      
        }
    
    public double totalIngresos()
    {   
        double total = 0;
        DefaultTableModel df = (DefaultTableModel) tableIngresos.getModel();
        
        for(int i=0;i<df.getRowCount();i++)
        {   double monto = Double.parseDouble(df.getValueAt(i,4).toString());
            total = total + monto;
        }
        
        lblTotalIngresos.setText("S/. "+String.valueOf(total));
        return total;
    }
    
    public double totalEgresos()
    {
     double total = 0;
        DefaultTableModel df = (DefaultTableModel) tableEgresos.getModel();
        
        for(int i=0;i<df.getRowCount();i++)
        {   double monto = Double.parseDouble(df.getValueAt(i,2).toString());
            total = total + monto;
        }
        
        lblTotalGastos.setText("S/. "+String.valueOf(total));
        return total;
    }
    


}
