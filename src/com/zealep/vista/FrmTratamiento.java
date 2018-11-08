/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.vista;


import com.zealep.modelo.Doctor;
import com.zealep.modelo.Paciente;
import com.zealep.modelo.Procedimiento;
import com.zealep.modelo.Tratamiento;
import com.zealep.modelo.TratamientoDetalle;
import com.zealep.negocio.TratamiendoDetalleDAO;
import com.zealep.negocio.TratamientoDAO;
import com.zealep.util.TratamientoDetalleDTO;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
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
public class FrmTratamiento extends javax.swing.JInternalFrame {
    
     
    TratamientoDetalle tratamientoDetalle = new TratamientoDetalle();
    List<TratamientoDetalle> procedimientos = new ArrayList<>();
    TratamientoDAO tratamientoDAO = new TratamientoDAO();
    Tratamiento tratamiento = new Tratamiento();
    TratamiendoDetalleDAO tratamiendoDetalleDAO = new TratamiendoDetalleDAO();
    String accion = "";
    TableRowSorter trsFiltro;
    int ultimoId = 0;
    double costoTotal = 0;

    public FrmTratamiento() {
        initComponents();
        inhabilitar();
        lblIdDoctor.setVisible(false);
        lblIdPaciente.setVisible(false);
        lblIdTratamiento.setVisible(false);
        txtIdDoctor.setVisible(false);
        txtIdPaciente.setVisible(false);
        txtIdTratamiento.setVisible(false);
        txtIdProcedimiento.setVisible(false);
        dateFechaRegistro.setDate(new Date());
    }


    public void limpiar() {
        txtIdTratamiento.setText("");
        txtIdPaciente.setText("");
        txtApellidosPa.setText("");
        txtNombresPa.setText("");
        txtDniPa.setText("");
        txtIdDoctor.setText("");
        txtApellidosDoc.setText("");
        txtNombresDoc.setText("");
        spnMesesTrata.setValue((int) 0);
        txtPagoMensual.setText("");
        txtCostoTotal.setText("");
        txtDescuento.setText("");
        txtCostoConDescuento.setText("");
        txtCop.setText("");
        spnDiaPago.setValue((int) 0);
        txtComentarios.setText("");
        txtNroHistoria.setText("");
        txtNombreProcedimiento.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtPiezas.setText("");
        txtObservacion.setText("");
        costoTotal = 0;
        dateFechaAparatologia.setDate(null);
        dateFechaBrackets.setDate(null);
        dateFechaContencion.setDate(null);
        dateFechaContencionInf.setDate(null);
        
        dateFechaPago.setDate(null);

    }

    public void limpiarTabla(JTable tabla) {
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        int a = tabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }

    public void limpiarProcedimiento() {
        txtNombreProcedimiento.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtPiezas.setText("");
        txtObservacion.setText("");
    }

    public void inhabilitar() {
        btnNuevo.setEnabled(true);
        txtNroHistoria.setEnabled(false);
        spnMesesTrata.setEnabled(false);
        txtPagoMensual.setEnabled(false);
        txtCostoTotal.setEnabled(false);
        txtDescuento.setEnabled(false);
        txtCostoConDescuento.setEnabled(false);
        spnDiaPago.setEnabled(false);
        txtComentarios.setEnabled(false);
        dateFechaAparatologia.setEnabled(false);
        dateFechaBrackets.setEnabled(false);
        dateFechaContencion.setEnabled(false);
        dateFechaRegistro.setEnabled(false);
        dateFechaPago.setEnabled(false);
        dateFechaContencionInf.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtCantidad.setEnabled(false);
        txtPiezas.setEnabled(false);
        txtObservacion.setEnabled(false);
        btnRegistrar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnBuscarDoctor.setEnabled(false);
        btnBuscarPaciente.setEnabled(false);
        btnBuscarProcedimiento.setEnabled(false);
        btnAgregar.setEnabled(false);
        btnQuitar.setEnabled(false);
        btnUpdate.setEnabled(false);

    }

    public void habilitar() {
        txtPrecio.setEnabled(true);
        txtCantidad.setEnabled(true);
        txtPiezas.setEnabled(true);
        txtObservacion.setEnabled(true);
        spnMesesTrata.setEnabled(true);
        txtPagoMensual.setEnabled(true);
        txtCostoTotal.setEnabled(true);
         txtDescuento.setEnabled(true);
        txtCostoConDescuento.setEnabled(true);
        spnDiaPago.setEnabled(true);
        dateFechaAparatologia.setEnabled(true);
        dateFechaBrackets.setEnabled(true);
        dateFechaContencion.setEnabled(true);
        dateFechaContencionInf.setEnabled(true);
        dateFechaRegistro.setEnabled(true);
        dateFechaPago.setEnabled(true);
        txtComentarios.setEnabled(true);
        btnRegistrar.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnCancelar.setEnabled(true);
         btnBuscarDoctor.setEnabled(true);
        btnBuscarPaciente.setEnabled(true);
        btnBuscarProcedimiento.setEnabled(true);
        btnAgregar.setEnabled(true);
        btnQuitar.setEnabled(true);
        btnUpdate.setEnabled(true);

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
        buttonGroup2 = new javax.swing.ButtonGroup();
        panelNuevo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnBuscarPaciente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtApellidosPa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombresPa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDniPa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNroHistoria = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnBuscarDoctor = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtApellidosDoc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNombresDoc = new javax.swing.JTextField();
        txtCop = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreProcedimiento = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnBuscarProcedimiento = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtPiezas = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableProcedimientos = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtObservacion = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        dateFechaBrackets = new com.toedter.calendar.JDateChooser();
        dateFechaContencion = new com.toedter.calendar.JDateChooser();
        dateFechaAparatologia = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        spnMesesTrata = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        txtPagoMensual = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCostoTotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        spnDiaPago = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtComentarios = new javax.swing.JTextArea();
        lblIdPaciente = new javax.swing.JLabel();
        txtIdPaciente = new javax.swing.JTextField();
        lblIdDoctor = new javax.swing.JLabel();
        txtIdDoctor = new javax.swing.JTextField();
        txtIdProcedimiento = new javax.swing.JTextField();
        lblIdTratamiento = new javax.swing.JLabel();
        txtIdTratamiento = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        dateFechaRegistro = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        dateFechaPago = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtCostoConDescuento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dateFechaContencionInf = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setClosable(true);
        setTitle("Registro del Tratamiento Integral del Paciente");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos del tratamiento"));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Asignar Paciente :"));

        btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscar_p.png"))); // NOI18N
        btnBuscarPaciente.setText("Buscar Paciente");
        btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPacienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellidos :");

        txtApellidosPa.setEnabled(false);

        jLabel4.setText("Nombres :");

        txtNombresPa.setEnabled(false);

        jLabel5.setText("Dni :");

        txtDniPa.setEnabled(false);

        jLabel1.setText("Nro Historia :");

        txtNroHistoria.setEditable(false);
        txtNroHistoria.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDniPa, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                            .addComponent(txtApellidosPa))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombresPa, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                            .addComponent(txtNroHistoria)))
                    .addComponent(btnBuscarPaciente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnBuscarPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidosPa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombresPa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(txtNroHistoria)
                    .addComponent(txtDniPa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))), "Asignar Doctor :"));

        btnBuscarDoctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscar_p.png"))); // NOI18N
        btnBuscarDoctor.setText("Buscar Doctor");
        btnBuscarDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDoctorActionPerformed(evt);
            }
        });

        jLabel7.setText("Apellidos :");

        txtApellidosDoc.setEnabled(false);

        jLabel8.setText("Nombres :");

        txtNombresDoc.setEnabled(false);

        txtCop.setEditable(false);
        txtCop.setEnabled(false);

        jLabel21.setText("COP:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnBuscarDoctor)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellidosDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombresDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCop, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnBuscarDoctor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtApellidosDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtNombresDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Asignar Procedimiento :"));

        jLabel2.setText("Nombre Procedimiento(*):");

        txtNombreProcedimiento.setEditable(false);
        txtNombreProcedimiento.setEnabled(false);

        jLabel6.setText("Precio(*):");

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        btnBuscarProcedimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscar_p.png"))); // NOI18N
        btnBuscarProcedimiento.setText("Buscar Procedimiento");
        btnBuscarProcedimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProcedimientoActionPerformed(evt);
            }
        });

        jLabel10.setText("Cantidad(*):");

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel16.setText("Piezas:");

        tableProcedimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Precio", "Cantidad", "Piezas", "Observ.", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProcedimientos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableProcedimientosKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tableProcedimientos);
        if (tableProcedimientos.getColumnModel().getColumnCount() > 0) {
            tableProcedimientos.getColumnModel().getColumn(0).setMinWidth(0);
            tableProcedimientos.getColumnModel().getColumn(0).setMaxWidth(0);
            tableProcedimientos.getColumnModel().getColumn(5).setMinWidth(0);
            tableProcedimientos.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Agregar_p1.png"))); // NOI18N
        btnAgregar.setToolTipText("Agregar ");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Remove.png"))); // NOI18N
        btnQuitar.setToolTipText("Eliminar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        jLabel18.setText("Observacion :");

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        btnUpdate.setToolTipText("Actualizar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarProcedimiento)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNombreProcedimiento)
                                            .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPiezas))))
                                    .addComponent(txtObservacion))
                                .addGap(32, 32, 32)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBuscarProcedimiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnQuitar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombreProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(txtPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos del tratamiento"));

        jLabel17.setText("F. Inst. Brackets :");

        jLabel19.setText("F. Inst. Contencion Superior:");

        jLabel20.setText("F. Inst. Aparat. Ortodontica : ");

        jLabel11.setText("N° Cuotas:");

        jLabel12.setText("Pago Mensual S/. :");

        jLabel13.setText("Costo Total Tratamiento :");

        jLabel14.setText("Dia de pago :");

        jLabel15.setText("Comentarios :");

        txtComentarios.setColumns(20);
        txtComentarios.setRows(5);
        jScrollPane2.setViewportView(txtComentarios);

        lblIdPaciente.setText("ID :");

        txtIdPaciente.setEnabled(false);

        lblIdDoctor.setText("ID :");

        txtIdDoctor.setEnabled(false);

        lblIdTratamiento.setText("Id Tratamiento :");

        txtIdTratamiento.setEnabled(false);

        jLabel22.setText("F. Registro Contrato :");

        jLabel23.setText("F. Inicio Pago(*) :");

        jLabel24.setText("Descuento(*) :");

        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyPressed(evt);
            }
        });

        jLabel25.setText("Costo total Con Descuento :");

        jLabel9.setText("F. Inst. Contencion Inferior :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblIdPaciente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdDoctor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCostoTotal, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescuento, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateFechaRegistro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCostoConDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addComponent(jLabel17)
                                        .addGap(1, 1, 1))
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19)
                                        .addComponent(jLabel9)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(dateFechaContencion, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(dateFechaBrackets, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(dateFechaAparatologia, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(dateFechaContencionInf, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtPagoMensual, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(spnMesesTrata, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spnDiaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dateFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(dateFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtCostoConDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtPagoMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(spnDiaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(spnMesesTrata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dateFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFechaBrackets, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFechaContencion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(dateFechaContencionInf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(dateFechaAparatologia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdDoctor)
                    .addComponent(txtIdDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdTratamiento)
                    .addComponent(txtIdTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdPaciente)
                    .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelNuevoLayout = new javax.swing.GroupLayout(panelNuevo);
        panelNuevo.setLayout(panelNuevoLayout);
        panelNuevoLayout.setHorizontalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelNuevoLayout.setVerticalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Se debe de ingresar los campos obligatorios señalados (*)");

        jToolBar1.setRollover(true);

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/page_add.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNuevo);

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/page_save.png"))); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnRegistrar);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/page_delete.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCancelar);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/principal.png"))); // NOI18N
        btnSalir.setText("Salir");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addGap(52, 52, 52))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed
        DialogPacientes.llamado = "tratamiento";
        DialogPacientes dialogPacientes = new DialogPacientes(null, closable);
        dialogPacientes.show();

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnBuscarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDoctorActionPerformed
        DialogDoctores.llamado="tratamiento";
        DialogDoctores dialogDoctores = new DialogDoctores(null, closable);
        dialogDoctores.show();


    }//GEN-LAST:event_btnBuscarDoctorActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        //if(txtNombreProcedimiento.getText().equals("") || txtPrecio.getText().equals("") || txtPiezas.getText().equals("") || txtCantidad.getText().equals(""))
        if (txtNombreProcedimiento.getText().equals("") || txtPrecio.getText().equals("") || txtCantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debes rellenar todos los campos(*)requeridos ", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);

        } else {
            double precio = Double.parseDouble(txtPrecio.getText());
            double cantidad = Double.parseDouble(txtCantidad.getText());
            double total = precio*cantidad;
            
            DefaultTableModel modelo = (DefaultTableModel) tableProcedimientos.getModel();
            Object[] fila = new Object[7];
            fila[0] = txtIdProcedimiento.getText();
            fila[1] = txtNombreProcedimiento.getText();
            fila[2] = txtPrecio.getText();
            fila[3] = txtCantidad.getText();
            fila[4] = txtPiezas.getText();
            fila[5] = txtObservacion.getText();
            fila[6] = String.valueOf(total);
            modelo.addRow(fila);
            tableProcedimientos.setModel(modelo);
            actualizarCostoTotal();
            
            limpiarProcedimiento();

        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableProcedimientos.getModel();
        int fila = tableProcedimientos.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar una fila de la tabla", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        } else {

            int confirmar = JOptionPane.showConfirmDialog(null,
                    "Esta seguro que desea Eliminar el registro? ");

            if (JOptionPane.OK_OPTION == confirmar) {
                model.removeRow(fila);
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                actualizarCostoTotal();
            }
        }

    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(txtDescuento.getText().equals("") || dateFechaPago.getDate()==null)
        {
            JOptionPane.showMessageDialog(null,"Se deben de ingresar los campos obligatorios(*)");
        }
        else
        {
        Paciente p = new Paciente();
        p.setIdPaciente(Integer.parseInt(txtIdPaciente.getText()));
        p.setApellidos(txtApellidosPa.getText());
        p.setNombres(txtNombresPa.getText());
        p.setDni(txtDniPa.getText());
        tratamiento.setPaciente(p);
        Doctor d = new Doctor();
        d.setIdDoctor(Integer.parseInt(txtIdDoctor.getText()));
        d.setApellidos(txtApellidosDoc.getText());
        d.setNombres(txtNombresDoc.getText());
        d.setNroCop(txtCop.getText());
        tratamiento.setDoctor(d);
        tratamiento.setMesesTratamiento((int) spnMesesTrata.getValue());
        tratamiento.setComentarios(txtComentarios.getText());
        tratamiento.setDiaPagar((int) (spnDiaPago.getValue()));
        tratamiento.setMontoTotal(Double.parseDouble(txtCostoTotal.getText()));
          if (txtDescuento.getText().equals("")) {
            tratamiento.setDescuento(0);
        } else {
             tratamiento.setDescuento(Double.parseDouble(txtDescuento.getText()));
        }
      
        tratamiento.setMontoTotalConDescuento(Double.parseDouble(txtCostoConDescuento.getText()));
        
        if (txtPagoMensual.getText().equals("")) {
            tratamiento.setPagoMensual(0);
        } else {
            tratamiento.setPagoMensual(Double.parseDouble(txtPagoMensual.getText()));
        }
        tratamiento.setFechaInstBrackets(dateFechaBrackets.getDate());
        tratamiento.setFechaInstContencion(dateFechaContencion.getDate());
        tratamiento.setFechaInstContencionInferior(dateFechaContencionInf.getDate());
        tratamiento.setFechaInstAparatologia(dateFechaAparatologia.getDate());
        tratamiento.setFechaRegistro(dateFechaRegistro.getDate());
        tratamiento.setFechaInicioPago(dateFechaPago.getDate());
        if (accion.equals("Nuevo")) {
            boolean rpta = tratamientoDAO.insertar(tratamiento);
            if(rpta)
            {
            ultimoId = tratamientoDAO.ultimoIdRegistrado();

            //registrar detalle
            List<TratamientoDetalle> tratamientoDetalles = obtenerProcedimientos();
            for (int i = 0; i < tratamientoDetalles.size(); i++) {
                TratamientoDetalle t = new TratamientoDetalle();
                Tratamiento ultimo = new Tratamiento();
                ultimo.setIdTratamiento(ultimoId);
                t.setTratamiento(ultimo);
                t.setProcedimiento(tratamientoDetalles.get(i).getProcedimiento());
                t.setPrecio(tratamientoDetalles.get(i).getPrecio());
                t.setCantidad(tratamientoDetalles.get(i).getCantidad());
                t.setPiezas(tratamientoDetalles.get(i).getPiezas());
                t.setObservacion(tratamientoDetalles.get(i).getObservacion());
                tratamiendoDetalleDAO.insertarTratamientoDetalle(t);
            }
            
            List<TratamientoDetalleDTO> tratamientoDetalleDTOs = new ArrayList<>();
            for(TratamientoDetalle t:tratamientoDetalles)
            {
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
            
            limpiar();
            limpiarTabla(tableProcedimientos);
             
                 JOptionPane.showMessageDialog(null, "El tratamiento del paciente fue registrado correctamente");
            
            int result = JOptionPane.showConfirmDialog(this, "¿Desea Generar contrato?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                emitirContrato(tratamiento,tratamientoDetalleDTOs,String.valueOf(tratamiento.getMontoTotal()));
            }
           
            }
            else
            {
                JOptionPane.showMessageDialog(null, "El tratamiento del paciente fue registrado correctamente","ERROR",JOptionPane.ERROR_MESSAGE);
            }

        }
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion = "Nuevo";
        limpiar();
        habilitar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarProcedimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProcedimientoActionPerformed
        DialogProcedimientos.llamado="tratamiento";
        DialogProcedimientos dialogProcedimientos = new DialogProcedimientos(null, closable);
        dialogProcedimientos.show();
    }//GEN-LAST:event_btnBuscarProcedimientoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        inhabilitar();
        limpiarTabla(tableProcedimientos);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tableProcedimientosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableProcedimientosKeyPressed
           if(evt.getKeyCode()==evt.VK_ENTER)
            {
            int fila = tableProcedimientos.getSelectedRow();
            actualizarTotalFila(fila);
            actualizarCostoTotal();
           
            }
    }//GEN-LAST:event_tableProcedimientosKeyPressed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char validar = evt.getKeyChar();
        if(Character.isLetter(validar))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane,"Ingrese solo numeros");
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        char validar = evt.getKeyChar();
        if(Character.isLetter(validar))
        {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane,"Ingrese solo numeros");
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
          int fila = tableProcedimientos.getSelectedRow();
           if(fila == -1)
           {
           JOptionPane.showMessageDialog(rootPane,"Seleccione la fila del procedimiento que actualizara");
           }
           else{
            actualizarTotalFila(fila);
            actualizarCostoTotal();
           }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtDescuentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyPressed
        txtDescuento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                double montoTotal;
                double descuento;
                if (txtCostoTotal.getText().equals("") || txtCostoTotal.getText() == null) {
                    montoTotal = 0;
                } else {
                    montoTotal = Double.parseDouble(txtCostoTotal.getText());
                }
                if (txtDescuento.getText().equals("") || txtDescuento.getText() == null) {
                    descuento = 0;
                }
                else
                {
                descuento = Double.parseDouble(txtDescuento.getText());
                }

                txtCostoConDescuento.setText(String.valueOf(montoTotal - descuento));
            }

        });
    }//GEN-LAST:event_txtDescuentoKeyPressed

    private List<TratamientoDetalle> obtenerProcedimientos() {
        List<TratamientoDetalle> procedimientos = new ArrayList<>();
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableProcedimientos.getModel();
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            TratamientoDetalle td = new TratamientoDetalle();
            Tratamiento t = new Tratamiento();
            td.setTratamiento(tratamiento);
            Procedimiento p = new Procedimiento();
            p.setIdProcedimiento(Integer.parseInt(defaultTableModel.getValueAt(i, 0).toString()));
            p.setNombre(defaultTableModel.getValueAt(i, 1).toString());
            td.setProcedimiento(p);
            td.setPrecio(Double.parseDouble(defaultTableModel.getValueAt(i, 2).toString()));
            td.setCantidad(defaultTableModel.getValueAt(i, 3).toString());
            td.setPiezas(defaultTableModel.getValueAt(i, 4).toString());
            td.setObservacion(defaultTableModel.getValueAt(i, 5).toString());
            procedimientos.add(td);
        }

        return procedimientos;
    }
        
     public void actualizarCostoTotal()
    {   double total = 0;
        for(int i=0;i < tableProcedimientos.getRowCount(); i++)
        {   double precio =  Double.parseDouble(tableProcedimientos.getValueAt(i,2).toString());
            double cantidad = Double.parseDouble(tableProcedimientos.getValueAt(i, 3).toString());
            double subtotal =  precio * cantidad;
            total = total + subtotal;
       
        }
        
        txtCostoTotal.setText(String.valueOf(total));
    }
    
       public void emitirContrato(Tratamiento tratamiento,List<TratamientoDetalleDTO> procedimientos,String total) {
        
        SimpleDateFormat sd = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
           String fecha=sd.format(tratamiento.getFechaRegistro());   
           
        Map p = new HashMap();
        p.put("apellidos", tratamiento.getPaciente().getApellidos());
        p.put("nombres", tratamiento.getPaciente().getNombres());
        p.put("dni", tratamiento.getPaciente().getDni());
        p.put("apellidosDoc", tratamiento.getDoctor().getApellidos());
        p.put("nombresDoc",tratamiento.getDoctor().getNombres());
        p.put("cop",tratamiento.getDoctor().getNroCop());
        p.put("fecha",fecha);
        p.put("total",total);
        p.put("descuento",String.valueOf(tratamiento.getDescuento()));
        p.put("montoFinal",String.valueOf(tratamiento.getMontoTotalConDescuento()));
        p.put("comentarios",tratamiento.getComentarios());

        String logo="/imagenes/diamante.png";
        p.put("logo", this.getClass().getResourceAsStream(logo)); 
        JasperReport report;
        JasperPrint print;

        try {
            
            URL url = this.getClass().getResource("/reportes/RptContratoTratamiento.jasper");
            report = (JasperReport) JRLoader.loadObject(url);
            //report = JasperCompileManager.compileReport(new File("").getAbsolutePath()+"\\src\\reportes\\RptContratoTratamiento.jrxml");
            print = JasperFillManager.fillReport(report, p, new JRBeanCollectionDataSource(procedimientos));
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("CONTRATO DE TRATAMIENTO");
            view.setVisible(true);
            
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
       
       public void actualizarTotalFila(int fila)
       {
           DefaultTableModel df = (DefaultTableModel) tableProcedimientos.getModel();
           double precio = Double.parseDouble(df.getValueAt(fila, 2).toString());
           double cantidad = Double.parseDouble(df.getValueAt(fila, 3).toString());
           double total = precio * cantidad;
           df.setValueAt(String.valueOf(total), fila, 6);
       }
       
     
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarDoctor;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarProcedimiento;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dateFechaAparatologia;
    private com.toedter.calendar.JDateChooser dateFechaBrackets;
    private com.toedter.calendar.JDateChooser dateFechaContencion;
    private com.toedter.calendar.JDateChooser dateFechaContencionInf;
    private com.toedter.calendar.JDateChooser dateFechaPago;
    private com.toedter.calendar.JDateChooser dateFechaRegistro;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblIdDoctor;
    private javax.swing.JLabel lblIdPaciente;
    private javax.swing.JLabel lblIdTratamiento;
    private javax.swing.JPanel panelNuevo;
    private javax.swing.JSpinner spnDiaPago;
    private javax.swing.JSpinner spnMesesTrata;
    private javax.swing.JTable tableProcedimientos;
    public static javax.swing.JTextField txtApellidosDoc;
    public static javax.swing.JTextField txtApellidosPa;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextArea txtComentarios;
    public static javax.swing.JTextField txtCop;
    private javax.swing.JTextField txtCostoConDescuento;
    private javax.swing.JTextField txtCostoTotal;
    private javax.swing.JTextField txtDescuento;
    public static javax.swing.JTextField txtDniPa;
    public static javax.swing.JTextField txtIdDoctor;
    public static javax.swing.JTextField txtIdPaciente;
    public static javax.swing.JTextField txtIdProcedimiento;
    private javax.swing.JTextField txtIdTratamiento;
    public static javax.swing.JTextField txtNombreProcedimiento;
    public static javax.swing.JTextField txtNombresDoc;
    public static javax.swing.JTextField txtNombresPa;
    public static javax.swing.JTextField txtNroHistoria;
    private javax.swing.JTextField txtObservacion;
    private javax.swing.JTextField txtPagoMensual;
    private javax.swing.JTextField txtPiezas;
    public static javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
