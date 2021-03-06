/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.vista;

import com.zealep.modelo.Tratamiento;
import com.zealep.modelo.TratamientoDetalle;
import com.zealep.negocio.TratamiendoDetalleDAO;
import com.zealep.negocio.TratamientoDAO;
import com.zealep.util.TratamientoDetalleDTO;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JDialog;
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
 * @author CRISTHIAN
 */
public class DialogInfoTratamiento extends javax.swing.JDialog {

    public static int IDTRATAMIENTO;
    TratamientoDAO tratamientoDAO = new TratamientoDAO();
    TratamiendoDetalleDAO tratamiendoDetalleDAO = new TratamiendoDetalleDAO();
    List<TratamientoDetalle> tratamientoDetalles = new ArrayList<>();

    public DialogInfoTratamiento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mostrarInformacion();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void mostrarInformacion() {
        Tratamiento t = new Tratamiento();
        t = tratamientoDAO.listarTratamientoPorId(IDTRATAMIENTO);
        lblApellidosDoc.setText(t.getDoctor().getApellidos());
        lblApellidosPa.setText(t.getPaciente().getApellidos());
        lblCop.setText(t.getDoctor().getNroCop());
        lblCostoTotal.setText(String.valueOf(t.getMontoTotal()));
        lblDescuento.setText(String.valueOf(t.getDescuento()));
        lblCostoConDescuento.setText(String.valueOf(t.getMontoTotalConDescuento()));
        lblDiaPago.setText(String.valueOf(t.getDiaPagar()));
        lblDniDoc.setText(t.getDoctor().getDni());
        lblDniPa.setText(t.getPaciente().getDni());
        SimpleDateFormat sd = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        String fbrackets ;
                if(t.getFechaInstBrackets()!=null)
                fbrackets=sd.format(t.getFechaInstBrackets());
                else{
                fbrackets="";
                }
        lblFechaBrackets.setText(fbrackets);
        String fcontent;
        if(t.getFechaInstContencion()!=null)
                fcontent=sd.format(t.getFechaInstContencion());
                else{
                fcontent="";
                }
        lblFechaContencion.setText(fcontent);
        
         String fcontentinf;
        if(t.getFechaInstContencionInferior()!=null)
                fcontentinf=sd.format(t.getFechaInstContencionInferior());
                else{
                fcontentinf="";
                }
        lblFechaContencionInferior.setText(fcontentinf);
        
        
        String fRegistro = sd.format(t.getFechaRegistro());
        lblFechaRegistro.setText(fRegistro);
        String fInicioPago;
        if(t.getFechaInicioPago()!=null)
                fInicioPago=sd.format(t.getFechaInicioPago());
                else{
                fInicioPago="";
                }
        
        lblFechaPago.setText(fInicioPago);
        lblMesesTrata.setText(String.valueOf(t.getMesesTratamiento()));
        lblNombresDoc.setText(t.getDoctor().getNombres());
        lblNombresPa.setText(t.getPaciente().getNombres());
        lblNroHistoria.setText(t.getPaciente().getNroHistoria());
        String fOrtodo;
        if(t.getFechaInstAparatologia()!=null)
                fOrtodo=sd.format(t.getFechaInstAparatologia());
                else{
                fOrtodo="";
                }
        lblOrtodontica.setText(fOrtodo);
        lblPagoMensual.setText(String.valueOf(t.getPagoMensual()));
        txtComentarios.setText(t.getComentarios());

        //detalle de tratamientos
        tratamientoDetalles = tratamiendoDetalleDAO.listarTratamientosDetallePorId(IDTRATAMIENTO);
        Object[] o = new Object[5];
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableProcedimientos.getModel();

        for (int i = 0; i < tratamientoDetalles.size(); i++) {
            o[0] = tratamientoDetalles.get(i).getProcedimiento().getNombre();
            o[1] = tratamientoDetalles.get(i).getPiezas();
            o[2] = tratamientoDetalles.get(i).getCantidad();
            o[3] = tratamientoDetalles.get(i).getPrecio();
            o[4] = Double.parseDouble(tratamientoDetalles.get(i).getCantidad())*tratamientoDetalles.get(i).getPrecio();
            defaultTableModel.addRow(o);
        }

        tableProcedimientos.setModel(defaultTableModel);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblApellidosPa = new javax.swing.JLabel();
        lblNombresPa = new javax.swing.JLabel();
        lblDniPa = new javax.swing.JLabel();
        lblNroHistoria = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblApellidosDoc = new javax.swing.JLabel();
        lblNombresDoc = new javax.swing.JLabel();
        lblDniDoc = new javax.swing.JLabel();
        lblCop = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblFechaRegistro = new javax.swing.JLabel();
        lblCostoTotal = new javax.swing.JLabel();
        lblPagoMensual = new javax.swing.JLabel();
        lblMesesTrata = new javax.swing.JLabel();
        lblDiaPago = new javax.swing.JLabel();
        lblFechaBrackets = new javax.swing.JLabel();
        lblFechaContencion = new javax.swing.JLabel();
        lblOrtodontica = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtComentarios = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        lblFechaPago = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        lblCostoConDescuento = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblFechaContencionInferior = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProcedimientos = new javax.swing.JTable();
        btnContrato = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(40, 96, 134));
        jLabel1.setText("Datos del Tratamiento del Paciente");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 96, 134)), "Datos paciente"));

        jLabel2.setText("Apellidos :");

        jLabel3.setText("Nombres :");

        jLabel4.setText("Dni :");

        jLabel5.setText("N° Historia :");

        lblApellidosPa.setText("jLabel16");

        lblNombresPa.setText("jLabel16");

        lblDniPa.setText("jLabel16");

        lblNroHistoria.setText("jLabel16");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblApellidosPa)
                    .addComponent(lblNombresPa)
                    .addComponent(lblNroHistoria)
                    .addComponent(lblDniPa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblApellidosPa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNombresPa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblDniPa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblNroHistoria))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 96, 134)), "Datos doctor"));

        jLabel6.setText("Apellidos :");

        jLabel7.setText("Nombres :");

        jLabel8.setText("Dni :");

        jLabel9.setText("COP :");

        lblApellidosDoc.setText("jLabel16");

        lblNombresDoc.setText("jLabel16");

        lblDniDoc.setText("jLabel16");

        lblCop.setText("jLabel16");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblApellidosDoc)
                    .addComponent(lblCop)
                    .addComponent(lblDniDoc)
                    .addComponent(lblNombresDoc))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblApellidosDoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblNombresDoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblDniDoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblCop))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 96, 134)), "Datos tratamiento"));

        jLabel10.setText("Fecha Contrato :");

        jLabel11.setText("Costo Total :");

        jLabel12.setText("Meses Tratamiento :");

        jLabel13.setText("Pago Mensual :");

        jLabel14.setText("Dia pago mensual :");

        jLabel17.setText("F. Inst. Brackets :");

        jLabel19.setText("F. Inst. Contencion Superior :");

        jLabel20.setText("F. Inst. Apart. Ortondica : ");

        jLabel15.setText("Comentarios :");

        lblFechaRegistro.setText("jLabel27");

        lblCostoTotal.setText("jLabel27");

        lblPagoMensual.setText("jLabel27");

        lblMesesTrata.setText("jLabel27");

        lblDiaPago.setText("jLabel27");

        txtComentarios.setEditable(false);
        txtComentarios.setColumns(20);
        txtComentarios.setRows(5);
        txtComentarios.setEnabled(false);
        jScrollPane2.setViewportView(txtComentarios);

        jLabel16.setText("Fecha Inicio Pago :");

        lblFechaPago.setText("jLabel18");

        jLabel18.setText("Descuento :");

        jLabel21.setText("Costo Total con Descuento :");

        lblDescuento.setText("jLabel22");

        lblCostoConDescuento.setText("jLabel23");

        jLabel22.setText("F. Inst. Contencion Inferior :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaRegistro)
                            .addComponent(lblCostoTotal)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescuento)
                            .addComponent(lblCostoConDescuento))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblPagoMensual)
                        .addComponent(lblMesesTrata)
                        .addComponent(lblDiaPago)
                        .addComponent(lblFechaBrackets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFechaContencion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblOrtodontica, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addComponent(lblFechaPago)
                        .addComponent(lblFechaContencionInferior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblFechaRegistro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCostoTotal)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblDescuento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lblCostoConDescuento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblPagoMensual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblMesesTrata))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblDiaPago))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblFechaPago))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(lblFechaBrackets, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFechaContencion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(lblFechaContencionInferior, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(lblOrtodontica, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 96, 134)), "Datos Procedimientos"));

        tableProcedimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tratamiento", "Pieza", "Cantidad", "Precio", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableProcedimientos);
        if (tableProcedimientos.getColumnModel().getColumnCount() > 0) {
            tableProcedimientos.getColumnModel().getColumn(0).setMinWidth(300);
            tableProcedimientos.getColumnModel().getColumn(0).setMaxWidth(300);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnContrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/printer.png"))); // NOI18N
        btnContrato.setText("Contrato");
        btnContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContratoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnContrato)
                        .addGap(140, 140, 140)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnContrato)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContratoActionPerformed

        List<TratamientoDetalle> tratamientoDetalles = new ArrayList<>();
        Tratamiento tratamiento = new Tratamiento();
        tratamiento = tratamientoDAO.listarTratamientoPorId(IDTRATAMIENTO);
        tratamientoDetalles = tratamiendoDetalleDAO.listarTratamientosDetallePorId(IDTRATAMIENTO);

        double total = tratamiento.getMontoTotal();
        //obtener tototal de precios
        //for (int i = 0; i < tratamientoDetalles.size(); i++) {
        //    total = total + tratamientoDetalles.get(i).getPrecio();
       // }

        // wrapper a dto de detalle de tratamiento
        List<TratamientoDetalleDTO> tratamientoDetalleDTOs = new ArrayList<>();
        for (TratamientoDetalle t : tratamientoDetalles) {
            TratamientoDetalleDTO dto = new TratamientoDetalleDTO();
            dto.setProcedimiento(t.getProcedimiento().getNombre());
            dto.setCantidad(t.getCantidad());
            dto.setPiezas(t.getPiezas());
            dto.setPrecio(String.valueOf(t.getPrecio()));
            double cantidad = Double.parseDouble(t.getCantidad());
            double precio = t.getPrecio();
            double precioFinal = cantidad * precio;
            dto.setPrecioFinal(String.valueOf(precioFinal));
            tratamientoDetalleDTOs.add(dto);

        }

        SimpleDateFormat sd = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        String fecha = sd.format(tratamiento.getFechaRegistro());

        Map p = new HashMap();
        p.put("apellidos", tratamiento.getPaciente().getApellidos());
        p.put("nombres", tratamiento.getPaciente().getNombres());
        p.put("dni", tratamiento.getPaciente().getDni());
        p.put("apellidosDoc", tratamiento.getDoctor().getApellidos());
        p.put("nombresDoc", tratamiento.getDoctor().getNombres());
        p.put("cop", tratamiento.getDoctor().getNroCop());
        p.put("fecha", fecha);
        p.put("total", String.valueOf(total));
        p.put("descuento",String.valueOf(tratamiento.getDescuento()));
        p.put("montoFinal",String.valueOf(tratamiento.getMontoTotalConDescuento()));
        p.put("comentarios",tratamiento.getComentarios());

        String logo = "/imagenes/diamante.png";
        p.put("logo", this.getClass().getResourceAsStream(logo));
        JasperReport report;
        JasperPrint print;

        try {

            URL url = this.getClass().getResource("/reportes/RptContratoTratamiento.jasper");
            report = (JasperReport) JRLoader.loadObject(url);
            //report = JasperCompileManager.compileReport(new File("").getAbsolutePath()+"\\src\\reportes\\RptContratoTratamiento.jrxml");
            print = JasperFillManager.fillReport(report, p, new JRBeanCollectionDataSource(tratamientoDetalleDTOs));
            JasperViewer view = new JasperViewer(print, false);
            //jasperviewer como dialog
            JDialog dialog = new JDialog(this);//the owner
            dialog.setContentPane(view.getContentPane());
            dialog.setSize(view.getSize());
            dialog.setTitle("Contrato de Tratamiento");
            dialog.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnContratoActionPerformed

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
            java.util.logging.Logger.getLogger(DialogInfoTratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogInfoTratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogInfoTratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogInfoTratamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogInfoTratamiento dialog = new DialogInfoTratamiento(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnContrato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblApellidosDoc;
    private javax.swing.JLabel lblApellidosPa;
    private javax.swing.JLabel lblCop;
    private javax.swing.JLabel lblCostoConDescuento;
    private javax.swing.JLabel lblCostoTotal;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblDiaPago;
    private javax.swing.JLabel lblDniDoc;
    private javax.swing.JLabel lblDniPa;
    private javax.swing.JLabel lblFechaBrackets;
    private javax.swing.JLabel lblFechaContencion;
    private javax.swing.JLabel lblFechaContencionInferior;
    private javax.swing.JLabel lblFechaPago;
    private javax.swing.JLabel lblFechaRegistro;
    private javax.swing.JLabel lblMesesTrata;
    private javax.swing.JLabel lblNombresDoc;
    private javax.swing.JLabel lblNombresPa;
    private javax.swing.JLabel lblNroHistoria;
    private javax.swing.JLabel lblOrtodontica;
    private javax.swing.JLabel lblPagoMensual;
    private javax.swing.JTable tableProcedimientos;
    private javax.swing.JTextArea txtComentarios;
    // End of variables declaration//GEN-END:variables
}
