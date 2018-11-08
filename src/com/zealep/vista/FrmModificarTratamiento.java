/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.vista;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.zealep.modelo.Doctor;
import com.zealep.modelo.Paciente;
import com.zealep.modelo.Procedimiento;
import com.zealep.modelo.Tratamiento;
import com.zealep.modelo.TratamientoDetalle;
import com.zealep.negocio.TratamiendoDetalleDAO;
import com.zealep.negocio.TratamientoDAO;
import com.zealep.util.TratamientoDetalleDTO;
import static com.zealep.vista.DialogInfoTratamiento.IDTRATAMIENTO;
import static com.zealep.vista.FrmTratamiento.txtApellidosDoc;
import static com.zealep.vista.FrmTratamiento.txtApellidosPa;
import static com.zealep.vista.FrmTratamiento.txtCop;
import static com.zealep.vista.FrmTratamiento.txtDniPa;
import static com.zealep.vista.FrmTratamiento.txtIdDoctor;
import static com.zealep.vista.FrmTratamiento.txtIdPaciente;
import static com.zealep.vista.FrmTratamiento.txtIdProcedimiento;
import static com.zealep.vista.FrmTratamiento.txtNombreProcedimiento;
import static com.zealep.vista.FrmTratamiento.txtNombresDoc;
import static com.zealep.vista.FrmTratamiento.txtNombresPa;
import static com.zealep.vista.FrmTratamiento.txtNroHistoria;
import static com.zealep.vista.FrmTratamiento.txtPrecio;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Proysym01
 */
public class FrmModificarTratamiento extends javax.swing.JInternalFrame {

    TratamientoDetalle tratamientoDetalle = new TratamientoDetalle();
    List<TratamientoDetalle> procedimientos = new ArrayList<>();
    TratamientoDAO tratamientoDAO = new TratamientoDAO();
    Tratamiento tratamiento = new Tratamiento();
    TratamiendoDetalleDAO tratamiendoDetalleDAO = new TratamiendoDetalleDAO();
    String accion = "";
    TableRowSorter trsFiltro;
    int ultimoId = 0;
    double costoTotal = 0;
    public FrmModificarTratamiento() {
        initComponents();
        mostrar();
        inhabilitar();
        lblIdDoctor.setVisible(false);
        lblIdPaciente.setVisible(false);
        lblIdTratamiento.setVisible(false);
        txtIdDoctor.setVisible(false);
        txtIdPaciente.setVisible(false);
        txtIdTratamiento.setVisible(false);
        txtIdProcedimiento.setVisible(false);
    }
    
    public void mostrar() {

        List<Tratamiento> tratamientos = new ArrayList<>();
        tratamientos = tratamientoDAO.listarTratamientos();
        DefaultTableModel modelo = new DefaultTableModel();
        String[] titulos = {"Id", "Apellidos", "Nombres", "Dni", "Nro Historia", "A.Doctor", "N.Doctor","F.Registro","N° Meses", "Pago Mensual", "Costo Total", "Dia Pago", "Comentarios", "Estado"};
        modelo.setColumnIdentifiers(titulos);
        Object obj[] = new Object[modelo.getColumnCount()];

        for (int i = 0; i < tratamientos.size(); i++) {
            obj[0] = tratamientos.get(i).getIdTratamiento();
            obj[1] = tratamientos.get(i).getPaciente().getApellidos();
            obj[2] = tratamientos.get(i).getPaciente().getNombres();
            obj[3] = tratamientos.get(i).getPaciente().getDni();
            obj[4] = tratamientos.get(i).getPaciente().getNroHistoria();
            obj[5] = tratamientos.get(i).getDoctor().getApellidos();
            obj[6] = tratamientos.get(i).getDoctor().getNombres();
            obj[7] = tratamientos.get(i).getFechaRegistro();
            obj[8] = tratamientos.get(i).getMesesTratamiento();
            obj[9] = tratamientos.get(i).getPagoMensual();
            obj[10] = tratamientos.get(i).getMontoTotal();
            obj[11] = tratamientos.get(i).getDiaPagar();
            obj[12] = tratamientos.get(i).getComentarios();
            obj[13] = tratamientos.get(i).getEstado();

            modelo.addRow(obj);

        }

        tableTratamiento.setModel(modelo);
        // lblCantidadRegistros.setText("La cantidad de pacientes es: " + String.valueOf(tratamientoDAO.cantidadRegistros));

    }
    void busquedaFiltro() {

        int opcionBuscar = 0;

        if (rbApellidos.isSelected()) {
            opcionBuscar = 1;
        } else if (rbNombres.isSelected()) {
            opcionBuscar = 2;
        } else if (rbDni.isSelected()) {
            opcionBuscar = 3;
        }
        else if (rbFechaRegistro.isSelected()) {
            opcionBuscar = 7;
        }

        trsFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText(), opcionBuscar));

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
        dateFechaRegistro.setDate(null);
        dateFechaPago.setDate(null);
        dateFechaContencionInferior.setDate(null);

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
       
        txtNroHistoria.setEnabled(false);
        spnMesesTrata.setEnabled(false);
        txtPagoMensual.setEnabled(false);
        txtCostoTotal.setEnabled(false);
        spnDiaPago.setEnabled(false);
        txtComentarios.setEnabled(false);
        dateFechaAparatologia.setEnabled(false);
        dateFechaBrackets.setEnabled(false);
        dateFechaContencion.setEnabled(false);
        dateFechaContencionInferior.setEnabled(false);
        dateFechaRegistro.setEnabled(false);
        dateFechaPago.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtCantidad.setEnabled(false);
        txtPiezas.setEnabled(false);
        txtObservacion.setEnabled(false);
        btnModificar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnBuscarDoctor.setEnabled(false);
        btnBuscarPaciente.setEnabled(false);
        btnBuscarProcedimiento.setEnabled(false);
        btnAgregar.setEnabled(false);
        btnQuitar.setEnabled(false);
        txtDescuento.setEnabled(false);
        txtCostoConDescuento.setEnabled(false);

    }

    public void habilitar() {
        txtPrecio.setEnabled(true);
        txtCantidad.setEnabled(true);
        txtPiezas.setEnabled(true);
        txtObservacion.setEnabled(true);
        spnMesesTrata.setEnabled(true);
        txtPagoMensual.setEnabled(true);
        txtCostoTotal.setEnabled(true);
        spnDiaPago.setEnabled(true);
        dateFechaAparatologia.setEnabled(true);
        dateFechaBrackets.setEnabled(true);
        dateFechaContencion.setEnabled(true);
        dateFechaContencionInferior.setEnabled(true);
        dateFechaRegistro.setEnabled(true);
        dateFechaPago.setEnabled(true);
        txtComentarios.setEnabled(true);
        btnModificar.setEnabled(true);
        btnCancelar.setEnabled(true);
         btnBuscarDoctor.setEnabled(true);
        btnBuscarPaciente.setEnabled(true);
        btnBuscarProcedimiento.setEnabled(true);
        btnAgregar.setEnabled(true);
        btnQuitar.setEnabled(true);
        txtDescuento.setEnabled(true);
        txtCostoConDescuento.setEnabled(true);

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
        VerDetalle = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        tabModificarTrata = new javax.swing.JTabbedPane();
        panelListar = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        rbApellidos = new javax.swing.JRadioButton();
        rbNombres = new javax.swing.JRadioButton();
        rbDni = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        rbFechaRegistro = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTratamiento = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();
        panelModificar = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        panelNuevo = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnBuscarPaciente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtApellidosPa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombresPa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDniPa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNroHistoria = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btnBuscarDoctor = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtApellidosDoc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNombresDoc = new javax.swing.JTextField();
        txtCop = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
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
        jPanel8 = new javax.swing.JPanel();
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
        dateFechaContencionInferior = new com.toedter.calendar.JDateChooser();

        VerDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscadetalle_p.png"))); // NOI18N
        VerDetalle.setText("Ver Detalle");
        VerDetalle.setToolTipText("");
        VerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerDetalleActionPerformed(evt);
            }
        });
        jPopupMenu1.add(VerDetalle);

        setClosable(true);
        setTitle("Actualización y Modificación del Tratamiento del Paciente");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Criterios de busqueda"));

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

        buttonGroup1.add(rbFechaRegistro);
        rbFechaRegistro.setText("Fecha Contrato");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbApellidos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbNombres)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbDni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbFechaRegistro)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbApellidos)
                    .addComponent(rbNombres)
                    .addComponent(rbDni)
                    .addComponent(rbFechaRegistro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableTratamiento = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        tableTratamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableTratamiento.setComponentPopupMenu(jPopupMenu1);
        tableTratamiento.setFocusable(false);
        tableTratamiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTratamientoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableTratamiento);

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/page_edit.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelListarLayout = new javax.swing.GroupLayout(panelListar);
        panelListar.setLayout(panelListarLayout);
        panelListarLayout.setHorizontalGroup(
            panelListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelListarLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 785, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        panelListarLayout.setVerticalGroup(
            panelListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListarLayout.createSequentialGroup()
                .addGroup(panelListarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelListarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelListarLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        tabModificarTrata.addTab("Listado", panelListar);

        jToolBar1.setRollover(true);

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/page_edit.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setFocusable(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnModificar);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/page_delete.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setFocusable(false);
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCancelar);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/principal.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setFocusable(false);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSalir);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos del tratamiento"));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Asignar Paciente :"));

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDniPa, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                            .addComponent(txtApellidosPa))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombresPa, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                            .addComponent(txtNroHistoria)))
                    .addComponent(btnBuscarPaciente))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btnBuscarPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidosPa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombresPa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(txtNroHistoria)
                    .addComponent(txtDniPa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))), "Asignar Doctor :"));

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnBuscarDoctor)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
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
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnBuscarDoctor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtApellidosDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtNombresDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Asignar Procedimiento :"));

        jLabel2.setText("Nombre Procedimiento(*):");

        txtNombreProcedimiento.setEditable(false);
        txtNombreProcedimiento.setEnabled(false);

        jLabel6.setText("Precio(*):");

        btnBuscarProcedimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscar_p.png"))); // NOI18N
        btnBuscarProcedimiento.setText("Buscar Procedimiento");
        btnBuscarProcedimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProcedimientoActionPerformed(evt);
            }
        });

        jLabel10.setText("Cantidad(*):");

        jLabel16.setText("Piezas:");

        tableProcedimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Deta", "Id Pro", "Nombre", "Precio", "Cantidad", "Piezas", "Observ."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableProcedimientos);
        if (tableProcedimientos.getColumnModel().getColumnCount() > 0) {
            tableProcedimientos.getColumnModel().getColumn(0).setMinWidth(0);
            tableProcedimientos.getColumnModel().getColumn(0).setMaxWidth(0);
            tableProcedimientos.getColumnModel().getColumn(1).setMinWidth(0);
            tableProcedimientos.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Agregar_p1.png"))); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Remove.png"))); // NOI18N
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        jLabel18.setText("Observacion :");

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarProcedimiento)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNombreProcedimiento)
                                            .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPiezas))))
                                    .addComponent(txtObservacion))
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBuscarProcedimiento)
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnQuitar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos del tratamiento"));

        jLabel17.setText("F. Inst. Brackets :");

        jLabel19.setText("F. Inst. Contencion Superior :");

        jLabel20.setText("F. Inst. Aparat.Ortodontica : ");

        jLabel11.setText("N° Meses Tratamiento :");

        jLabel12.setText("Pago Mensual S/. :");

        jLabel13.setText("Costo Total Tratamiento :");

        jLabel14.setText("Dia de pago :");

        jLabel15.setText("Comentarios :");

        txtComentarios.setColumns(20);
        txtComentarios.setRows(5);
        jScrollPane2.setViewportView(txtComentarios);

        lblIdPaciente.setText("Pa :");

        txtIdPaciente.setEnabled(false);

        lblIdDoctor.setText("Doc :");

        txtIdDoctor.setEnabled(false);

        lblIdTratamiento.setText("Id Tratamiento :");

        txtIdTratamiento.setEnabled(false);

        jLabel22.setText("F. Registro Contrato :");

        jLabel23.setText("F. Inicio Pago :");

        jLabel24.setText("Descuento :");

        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyPressed(evt);
            }
        });

        jLabel25.setText("Costo total Con Descuento :");

        jLabel9.setText("F. Inst. Contencion Inferior :");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
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
                        .addComponent(txtIdProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCostoConDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(txtDescuento)))
                            .addComponent(jLabel9))
                        .addContainerGap(113, Short.MAX_VALUE))))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel22)
                        .addComponent(jLabel11)
                        .addComponent(jLabel14)
                        .addComponent(jLabel12)
                        .addComponent(jLabel13)
                        .addComponent(jLabel23)
                        .addComponent(jLabel17)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtPagoMensual, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spnDiaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spnMesesTrata, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dateFechaRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                        .addComponent(dateFechaPago, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                        .addComponent(dateFechaBrackets, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                        .addComponent(dateFechaContencion, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                        .addComponent(dateFechaContencionInferior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateFechaAparatologia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtCostoConDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPagoMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnDiaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(spnMesesTrata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(9, 9, 9)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(dateFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(dateFechaBrackets, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(dateFechaContencion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(dateFechaContencionInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateFechaAparatologia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelNuevoLayout.setVerticalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panelModificarLayout = new javax.swing.GroupLayout(panelModificar);
        panelModificar.setLayout(panelModificarLayout);
        panelModificarLayout.setHorizontalGroup(
            panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelModificarLayout.setVerticalGroup(
            panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelModificarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabModificarTrata.addTab("Modificacion", panelModificar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabModificarTrata)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabModificarTrata)
                .addGap(66, 66, 66))
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
        trsFiltro = new TableRowSorter(tableTratamiento.getModel());
        tableTratamiento.setRowSorter(trsFiltro);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void tableTratamientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTratamientoMouseClicked
       
    }//GEN-LAST:event_tableTratamientoMouseClicked

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed
        DialogPacientes.llamado = "modificar_tratamiento";
        DialogPacientes dialogPacientes = new DialogPacientes(null, closable);
        dialogPacientes.show();
    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnBuscarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDoctorActionPerformed
        DialogDoctores.llamado="modificar_tratamiento";
        DialogDoctores dialogDoctores = new DialogDoctores(null, closable);
        dialogDoctores.show();

    }//GEN-LAST:event_btnBuscarDoctorActionPerformed

    private void btnBuscarProcedimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProcedimientoActionPerformed
        DialogProcedimientos.llamado="modificar_tratamiento";
        DialogProcedimientos dialogProcedimientos = new DialogProcedimientos(null, closable);
        dialogProcedimientos.show();
    }//GEN-LAST:event_btnBuscarProcedimientoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        //if(txtNombreProcedimiento.getText().equals("") || txtPrecio.getText().equals("") || txtPiezas.getText().equals("") || txtCantidad.getText().equals(""))
        if (txtNombreProcedimiento.getText().equals("") || txtPrecio.getText().equals("") || txtCantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debes rellenar todos los campos(*)requeridos ", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);

        } else {
            
            //agregar detalle a base de datos
            TratamientoDetalle t = new TratamientoDetalle();
            Tratamiento tra = new Tratamiento();
            tra.setIdTratamiento(Integer.parseInt(txtIdTratamiento.getText()));
            t.setTratamiento(tra);
            Procedimiento p = new Procedimiento();
            p.setIdProcedimiento(Integer.parseInt(txtIdProcedimiento.getText()));
            t.setProcedimiento(p);
            t.setPrecio(Double.parseDouble(txtPrecio.getText()));
            t.setCantidad(txtCantidad.getText());
            t.setPiezas(txtPiezas.getText());
            t.setObservacion(txtObservacion.getText());
            tratamiendoDetalleDAO.insertarTratamientoDetalle(t);
            
          //listar detalles
          actualizarTablaProcedimientos();
          limpiarProcedimiento();
          actualizarCostoTotal();

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
                int idDetalle =Integer.parseInt(model.getValueAt(fila,0).toString());
                tratamiendoDetalleDAO.eliminarTratamientoDetalle(idDetalle);
                model.removeRow(fila);  
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                actualizarCostoTotal();
            }
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void VerDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerDetalleActionPerformed
       DefaultTableModel defaultTableModel = (DefaultTableModel) tableTratamiento.getModel();
       int fila = tableTratamiento.convertRowIndexToModel(tableTratamiento.getSelectedRow());
       int idTratamiento = Integer.parseInt(defaultTableModel.getValueAt(fila,0).toString());
       DialogInfoTratamiento.IDTRATAMIENTO = idTratamiento;
       DialogInfoTratamiento dialogInfoTratamiento = new DialogInfoTratamiento(null, closable);
       dialogInfoTratamiento.setLocationRelativeTo(null);
       dialogInfoTratamiento.show();
    }//GEN-LAST:event_VerDetalleActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
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
        tratamiento.setIdTratamiento(Integer.parseInt(txtIdTratamiento.getText()));
        tratamiento.setDoctor(d);
        tratamiento.setMesesTratamiento((int)spnMesesTrata.getValue());
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
        tratamiento.setFechaInstAparatologia(dateFechaAparatologia.getDate());
        tratamiento.setFechaRegistro(dateFechaRegistro.getDate());
        tratamiento.setFechaInicioPago(dateFechaPago.getDate());
        tratamiento.setFechaInstContencionInferior(dateFechaContencionInferior.getDate());
       
        tratamientoDAO.modificar(tratamiento);
        //ultimoId = tratamientoDAO.ultimoIdRegistrado();

            //registrar detalle
            List<TratamientoDetalle> tratamientoDetalles = obtenerProcedimientos();
            for (int i = 0; i < tratamientoDetalles.size(); i++) {
                TratamientoDetalle t = new TratamientoDetalle();
                Tratamiento ultimo = new Tratamiento();
                ultimo.setIdTratamiento(Integer.parseInt(txtIdTratamiento.getText()));
                t.setTratamiento(ultimo);
                t.setIdTratamientoDetalle(tratamientoDetalles.get(i).getIdTratamientoDetalle());
                t.setProcedimiento(tratamientoDetalles.get(i).getProcedimiento());
                t.setPrecio(tratamientoDetalles.get(i).getPrecio());
                t.setCantidad(tratamientoDetalles.get(i).getCantidad());
                t.setPiezas(tratamientoDetalles.get(i).getPiezas());
                t.setObservacion(tratamientoDetalles.get(i).getObservacion());
                tratamiendoDetalleDAO.modificarTratamientoDetalle(t);
            }
            
           
            limpiar();
            limpiarTabla(tableProcedimientos);
            mostrar();
             
            JOptionPane.showMessageDialog(null, "El tratamiento del paciente fue modificado correctamente");
          
           tabModificarTrata.setSelectedIndex(tabModificarTrata.indexOfComponent(panelListar));
      

       
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        inhabilitar();
        limpiarTabla(tableProcedimientos);
        tabModificarTrata.setSelectedIndex(tabModificarTrata.indexOfComponent(panelListar));
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        int fila = tableTratamiento.convertRowIndexToModel(tableTratamiento.getSelectedRow());
        if(fila == -1)
        {
            JOptionPane.showMessageDialog(null, "Debes de seleccionar una fila para modificar");
        }
        else
        {
          
            
          DefaultTableModel defaultTableModel = (DefaultTableModel) tableTratamiento.getModel();
          Tratamiento t = new Tratamiento();
          int id = Integer.parseInt(defaultTableModel.getValueAt(fila,0).toString());
          t = tratamientoDAO.listarTratamientoPorId(id);
          txtIdTratamiento.setText(String.valueOf(id));
          txtIdPaciente.setText(t.getPaciente().getIdPaciente().toString());
          txtApellidosPa.setText(t.getPaciente().getApellidos());
          txtNombresPa.setText(t.getPaciente().getNombres());
          txtDniPa.setText(t.getPaciente().getDni());
          txtNroHistoria.setText(t.getPaciente().getNroHistoria());
          txtIdDoctor.setText(t.getDoctor().getIdDoctor().toString());
          txtApellidosDoc.setText(t.getDoctor().getApellidos());
          txtNombresDoc.setText(t.getDoctor().getNombres());
          txtCop.setText(t.getDoctor().getNroCop());
          
          //llenado de los datos del tratamiento
          //txtCostoTotal.setText(String.valueOf(t.getMontoTotal()));
          txtPagoMensual.setText(String.valueOf(t.getPagoMensual()));
          spnDiaPago.setValue(t.getDiaPagar());
          spnMesesTrata.setValue(t.getMesesTratamiento());
          dateFechaRegistro.setDate(t.getFechaRegistro());
          dateFechaAparatologia.setDate(t.getFechaInstAparatologia());
          dateFechaBrackets.setDate(t.getFechaInstBrackets());
          dateFechaContencion.setDate(t.getFechaInstContencion());
          dateFechaContencionInferior.setDate(t.getFechaInstContencionInferior());
          dateFechaPago.setDate(t.getFechaInicioPago());
          txtComentarios.setText(t.getComentarios());
          txtCostoTotal.setText(String.valueOf(t.getMontoTotal()));
          txtDescuento.setText(String.valueOf(t.getDescuento()));
          txtCostoConDescuento.setText(String.valueOf(t.getMontoTotalConDescuento()));
         
          //llenado de tabla de detalle tratamiento
          
          //detalle de tratamientos

        List<TratamientoDetalle> tratamientoDetalles = new ArrayList<>();
        int idTratamiento = Integer.parseInt(defaultTableModel.getValueAt(fila,0).toString());
        tratamientoDetalles = tratamiendoDetalleDAO.listarTratamientosDetallePorId(idTratamiento);
      
        
        Object[] o = new Object[7];
       
        DefaultTableModel defaultProcedimientos = (DefaultTableModel) tableProcedimientos.getModel();
        for (int i = 0; i < tratamientoDetalles.size(); i++) {
            o[0] = tratamientoDetalles.get(i).getIdTratamientoDetalle();
            o[1]= tratamientoDetalles.get(i).getProcedimiento().getIdProcedimiento();
            o[2] = tratamientoDetalles.get(i).getProcedimiento().getNombre();
            o[3] = tratamientoDetalles.get(i).getPrecio();
            o[4] = tratamientoDetalles.get(i).getCantidad();
            o[5] =  tratamientoDetalles.get(i).getPiezas();
            o[6] = tratamientoDetalles.get(i).getObservacion();
            //[4] = Double.parseDouble(tratamientoDetalles.get(i).getCantidad())*tratamientoDetalles.get(i).getPrecio();
            defaultProcedimientos.addRow(o);
        }
        tableProcedimientos.setModel(defaultProcedimientos);
           habilitar();
            tabModificarTrata.setSelectedIndex(tabModificarTrata.indexOfComponent(panelModificar));
            //costo total sumando los datos de la tabla
            //actualizarCostoTotal();
        }
      
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int fila = tableProcedimientos.getSelectedRow();
        if(fila == -1)
        {
        JOptionPane.showMessageDialog(null,"Seleccione fila del procedimiento para actualizar");
        }
        else
        {   
            DefaultTableModel df = (DefaultTableModel) tableProcedimientos.getModel();
            int idTratamiento = Integer.parseInt(txtIdTratamiento.getText());
            int idDetalle = Integer.parseInt(df.getValueAt(fila, 0).toString());
            int idProcedimiento = Integer.parseInt(df.getValueAt(fila,1).toString());
            double precio = Double.parseDouble(df.getValueAt(fila, 3).toString());
            String cantidad = df.getValueAt(fila, 4).toString();
            String piezas = df.getValueAt(fila, 5).toString();
            String observacion = df.getValueAt(fila, 6).toString();
            
            TratamientoDetalle td = new TratamientoDetalle();
            td.setIdTratamientoDetalle(idDetalle);
            Tratamiento t = new Tratamiento();
            t.setIdTratamiento(idTratamiento);
            td.setTratamiento(t);
            Procedimiento p = new Procedimiento();
            p.setIdProcedimiento(idProcedimiento);
            td.setProcedimiento(p);
            td.setPrecio(precio);
            td.setCantidad(cantidad);
            td.setPiezas(piezas);
            td.setObservacion(observacion);
            
            boolean respuesta = tratamiendoDetalleDAO.modificarTratamientoDetalle(td);
            if(respuesta)
            {
               JOptionPane.showMessageDialog(null,"Modificado");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No se pudo modificar","Error", JOptionPane.ERROR_MESSAGE);
            }
            actualizarTablaProcedimientos();
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
            td.setIdTratamientoDetalle(Integer.parseInt(defaultTableModel.getValueAt(i, 0).toString()));
            Tratamiento t = new Tratamiento();
            td.setTratamiento(tratamiento);
            Procedimiento p = new Procedimiento();
            
            p.setIdProcedimiento(Integer.parseInt(defaultTableModel.getValueAt(i, 1).toString()));
            p.setNombre(defaultTableModel.getValueAt(i, 2).toString());
            td.setProcedimiento(p);
            td.setPrecio(Double.parseDouble(defaultTableModel.getValueAt(i, 3).toString()));
            td.setCantidad(defaultTableModel.getValueAt(i, 4).toString());
            td.setPiezas(defaultTableModel.getValueAt(i, 5).toString());
            td.setObservacion(defaultTableModel.getValueAt(i, 6).toString());
            procedimientos.add(td);
        }

        return procedimientos;
    }

    public void actualizarCostoTotal()
    {   double total = 0;
        for(int i=0;i < tableProcedimientos.getRowCount(); i++)
        {   double precio =  Double.parseDouble(tableProcedimientos.getValueAt(i,3).toString());
            double cantidad = Double.parseDouble(tableProcedimientos.getValueAt(i, 4).toString());
            double subtotal =  precio * cantidad;
            total = total + subtotal;
       
        }
        
        txtCostoTotal.setText(String.valueOf(total));
    }
    
    public void actualizarTablaProcedimientos()
    {
          //listar todos los detalles
            List<TratamientoDetalle> detalles = new ArrayList<>();
            detalles = tratamiendoDetalleDAO.listarTratamientosDetallePorId(Integer.parseInt(txtIdTratamiento.getText()));
            
            limpiarTabla(tableProcedimientos);
            DefaultTableModel modelo = (DefaultTableModel) tableProcedimientos.getModel();
            Object[] fila = new Object[7];
            
            for(int i=0;i<detalles.size();i++)
            {
            fila[0] = detalles.get(i).getIdTratamientoDetalle();
            fila[1] = detalles.get(i).getProcedimiento().getIdProcedimiento();
            fila[2] = detalles.get(i).getProcedimiento().getNombre();
            fila[3] = detalles.get(i).getPrecio();
            fila[4] = detalles.get(i).getCantidad();
            fila[5] = detalles.get(i).getPiezas();
            fila[6] = detalles.get(i).getObservacion();
              modelo.addRow(fila);
            }
           
           
            tableProcedimientos.setModel(modelo);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem VerDetalle;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarDoctor;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarProcedimiento;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dateFechaAparatologia;
    private com.toedter.calendar.JDateChooser dateFechaBrackets;
    private com.toedter.calendar.JDateChooser dateFechaContencion;
    private com.toedter.calendar.JDateChooser dateFechaContencionInferior;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblIdDoctor;
    private javax.swing.JLabel lblIdPaciente;
    private javax.swing.JLabel lblIdTratamiento;
    private javax.swing.JPanel panelListar;
    private javax.swing.JPanel panelModificar;
    private javax.swing.JPanel panelNuevo;
    private javax.swing.JRadioButton rbApellidos;
    private javax.swing.JRadioButton rbDni;
    private javax.swing.JRadioButton rbFechaRegistro;
    private javax.swing.JRadioButton rbNombres;
    private javax.swing.JSpinner spnDiaPago;
    private javax.swing.JSpinner spnMesesTrata;
    private javax.swing.JTabbedPane tabModificarTrata;
    private javax.swing.JTable tableProcedimientos;
    private javax.swing.JTable tableTratamiento;
    public static javax.swing.JTextField txtApellidosDoc;
    public static javax.swing.JTextField txtApellidosPa;
    private javax.swing.JTextField txtBuscar;
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
