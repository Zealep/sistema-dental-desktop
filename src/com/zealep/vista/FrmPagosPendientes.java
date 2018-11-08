/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.vista;

import com.zealep.negocio.PagoDAO;
import com.zealep.util.DefaultTableCellHeaderRenderer;
import com.zealep.util.PagoUltimoDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author user
 */
public class FrmPagosPendientes extends javax.swing.JInternalFrame {

     TableRowSorter trsFiltro;
    
    public FrmPagosPendientes() {
        initComponents();
        mostrar();
        colorearPagoPendiente();
    }
    
    
    public void mostrar()
    {
        PagoDAO pagoDAO = new PagoDAO();
        List<PagoUltimoDTO> pagos = new ArrayList<>();
        pagos = pagoDAO.listarUltimosPagosPendientePorTratamiento();
        DefaultTableModel df = (DefaultTableModel) tablePagosPendientes.getModel();
        Object[] obj = new Object[15];
        SimpleDateFormat sd = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        for(int i=0;i<pagos.size();i++)
        {           Date fecha = pagos.get(i).getFechaUltimoPago();
                     Date fechaInicio = pagos.get(i).getFechaInicioPago();
                    String fechaUltimoPago = sd.format(fecha);
                    String fechaInicioPago = sd.format(fechaInicio);
                    obj[0]= pagos.get(i).getIdTratamiento();
                    obj[1]= fechaUltimoPago;
                    obj[2]= pagos.get(i).getNroCuota();
                    obj[3]= pagos.get(i).getMontoPago();
                    obj[4]= pagos.get(i).getApellidos();
                    obj[5]= pagos.get(i).getNombres();
                    obj[6]= pagos.get(i).getNroHistoria();
                    obj[7]= pagos.get(i).getCelular();
                    obj[8]= pagos.get(i).getMesesTratamiento();
                    obj[9]= pagos.get(i).getDiaPagar();
                    obj[10]= pagos.get(i).getPagoMensual();
                    obj[11]= fechaInicioPago;
                    obj[12]= pagos.get(i).getMontoTotal();
                    obj[13]= pagos.get(i).getComentarios();
                    obj[14]= "CONFORME";
                    df.addRow(obj);
                  
        }
        
        tablePagosPendientes.setModel(df);
        
    }
    
     // BUSQUEDA FILTRO EN LA TABLA
    void busquedaFiltro() {

        {

            int opcionBuscar = 0;

            if (rbApellidos.isSelected()) {
                opcionBuscar = 4;
            } else if (rbNombres.isSelected()) {
                opcionBuscar = 5;
            } else if (rbCelular.isSelected()) {
                opcionBuscar = 7;
            } else if (rbNroHistoria.isSelected()) {
                opcionBuscar = 6;
            }
            
            else if (rbEstado.isSelected()) {
                opcionBuscar = 14;
            }

            trsFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText(), opcionBuscar));

        }
    }
    
    public void colorearPagoPendiente()
    
    {
        tablePagosPendientes.setDefaultRenderer(Object.class,new DefaultTableCellHeaderRenderer(){
        
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column)
                {
                    JLabel jLabel = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    
                     SimpleDateFormat sf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    Date fechaUltimoPago = null;
                    Date fechaInicioPago=null;
                try {
                    fechaUltimoPago = sf.parse(tablePagosPendientes.getValueAt(row, 1).toString());
                    fechaInicioPago = sf.parse(tablePagosPendientes.getValueAt(row, 11).toString());
                } catch (ParseException ex) {
                    Logger.getLogger(FrmPagosPendientes.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    // Calendar calendar = Calendar.getInstance();
                    // calendar.setTime(fechaUltimoPago);
                    // calendar.add(Calendar.MONTH,1);
                    // Date fechaPagar = calendar.getTime();
                    int diaPagar = Integer.parseInt(table.getValueAt(row,9).toString());
                    int nroCuota = Integer.parseInt(table.getValueAt(row, 2).toString());
                    int mesesTratamiento = Integer.parseInt(table.getValueAt(row, 8).toString());
                  boolean pendiente =transformarFecha(diaPagar, fechaInicioPago,nroCuota,fechaUltimoPago,mesesTratamiento);                  
                    if(pendiente)
                    {
                      Color c = Color.RED;
                      jLabel.setForeground(c);
                      table.setValueAt("PENDIENTE", row, 14);
                    }
                return jLabel;
                }
        });
    }
    
    public boolean transformarFecha(int diaPagar,Date fechaInicioPago,int nroCuota,Date fechaUltimoPago,int mesesTratamiento)
    {      
            boolean pendiente = false; 
           Date fechaTransformada = null;
           Date fechaHoy = new Date();
           int cuotaPendiente = 0;
           
           try {
            
            SimpleDateFormat mes = new SimpleDateFormat("MM");
            SimpleDateFormat año = new SimpleDateFormat("yyyy");
            SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
            
//             Date pruebaFecha = sf.parse("23/10/2018");
//             fechaHoy = pruebaFecha;
//           
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            startCalendar.setTime(fechaInicioPago);
            endCalendar.setTime(fechaHoy);
            int startMes = (startCalendar.get(Calendar.YEAR) * 12) + startCalendar.get(Calendar.MONTH);
            int endMes = (endCalendar.get(Calendar.YEAR) * 12) + endCalendar.get(Calendar.MONTH);
            int diffMonth = (endMes - startMes)+1;
            if(diffMonth < 0){
                diffMonth = 0;
            }
            
            cuotaPendiente = diffMonth;
            
      
            String mesFecha = mes.format(fechaHoy);
            String añoFecha = año.format(fechaHoy);
            String dia = String.valueOf(diaPagar);
            String fechaPagarString = dia+"/"+mesFecha+"/"+añoFecha;           
            Date fechaPagar = sf.parse(fechaPagarString);
            
           
            
            if(fechaHoy.compareTo(fechaPagar) == 1 && fechaUltimoPago.before(fechaPagar) && nroCuota < cuotaPendiente && nroCuota!=mesesTratamiento)
            {
                pendiente = true;
            }
            else
            { pendiente = false;
            
            }
             



             
     } catch (Exception e) {
          JOptionPane.showMessageDialog(this, e);
        }
              
              return pendiente;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePagosPendientes = new javax.swing.JTable();
        rbApellidos = new javax.swing.JRadioButton();
        rbNombres = new javax.swing.JRadioButton();
        rbCelular = new javax.swing.JRadioButton();
        rbNroHistoria = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        rbEstado = new javax.swing.JRadioButton();

        setClosable(true);

        tablePagosPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Tratamiento", "Ultimo Pago realizado", "Nro Cuota", "Monto Pago", "Apellidos", "Nombre", "Nro Historia", "Celular", "Meses Tratamiento", "Dia de Pago", "Pago Mensual", "Fecha Inicio Pago", "Monto Total", "Comentarios Tratamiento", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablePagosPendientes);
        if (tablePagosPendientes.getColumnModel().getColumnCount() > 0) {
            tablePagosPendientes.getColumnModel().getColumn(0).setMinWidth(0);
            tablePagosPendientes.getColumnModel().getColumn(0).setMaxWidth(0);
            tablePagosPendientes.getColumnModel().getColumn(1).setMinWidth(170);
            tablePagosPendientes.getColumnModel().getColumn(1).setMaxWidth(170);
            tablePagosPendientes.getColumnModel().getColumn(4).setMinWidth(170);
            tablePagosPendientes.getColumnModel().getColumn(4).setMaxWidth(170);
            tablePagosPendientes.getColumnModel().getColumn(5).setMinWidth(170);
            tablePagosPendientes.getColumnModel().getColumn(5).setMaxWidth(170);
            tablePagosPendientes.getColumnModel().getColumn(11).setMinWidth(170);
            tablePagosPendientes.getColumnModel().getColumn(11).setMaxWidth(170);
            tablePagosPendientes.getColumnModel().getColumn(13).setMinWidth(200);
            tablePagosPendientes.getColumnModel().getColumn(13).setMaxWidth(200);
        }

        buttonGroup1.add(rbApellidos);
        rbApellidos.setText("Apellidos");

        buttonGroup1.add(rbNombres);
        rbNombres.setText("Nombres");

        buttonGroup1.add(rbCelular);
        rbCelular.setText("Celular");

        buttonGroup1.add(rbNroHistoria);
        rbNroHistoria.setText("Nro Historia");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(40, 96, 134));
        jLabel1.setText("Ultimos Pagos Realizados por Tratamiento");

        buttonGroup1.add(rbEstado);
        rbEstado.setText("Estado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbApellidos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbNombres)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbCelular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbNroHistoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbEstado))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1710, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(366, 366, 366)
                        .addComponent(jLabel1)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbApellidos)
                    .addComponent(rbNombres)
                    .addComponent(rbCelular)
                    .addComponent(rbNroHistoria)
                    .addComponent(rbEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
     txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                String cadena = (txtBuscar.getText());
                txtBuscar.setText(cadena);
                repaint();
                busquedaFiltro();
            }
        });
        trsFiltro = new TableRowSorter(tablePagosPendientes.getModel());
        tablePagosPendientes.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtBuscarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbApellidos;
    private javax.swing.JRadioButton rbCelular;
    private javax.swing.JRadioButton rbEstado;
    private javax.swing.JRadioButton rbNombres;
    private javax.swing.JRadioButton rbNroHistoria;
    private javax.swing.JTable tablePagosPendientes;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
