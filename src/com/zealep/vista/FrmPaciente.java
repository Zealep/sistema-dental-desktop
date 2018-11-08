/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.vista;

import com.zealep.negocio.PacienteDAO;
import com.zealep.modelo.Paciente;
import com.zealep.modelo.Procedimiento;
import com.zealep.negocio.ProcedimientoDAO;
import com.zealep.util.Constantes;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author user
 */
public class FrmPaciente extends javax.swing.JInternalFrame {
    
    final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(FrmPrincipal.class);
    Paciente paciente = new Paciente();
    PacienteDAO pacienteDAO = new PacienteDAO();
    String accion = "";
    TableRowSorter trsFiltro;

    public FrmPaciente() {
        initComponents();
        mostrar();
        inhabilitar();
        txtFoto.setVisible(false);
        txtIdPaciente.setVisible(false);
        lblIdPaciente.setVisible(false);
    
        
    }
    
  
    //METODOS 
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
        
        lblCantidadRegistros.setText("La cantidad de pacientes es: " + String.valueOf(pacienteDAO.cantidadRegistros));

    }

    public void limpiar() {
        txtIdPaciente.setText("");
        txtApellidos.setText("");
        txtNombres.setText("");
        txtDni.setText("");
        txtNroHistoria.setText("");
        txtCelular.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtLugar.setText("");
        txtEmail.setText("");
        dateFechaNacimiento.setDate(null);
        lblFoto.setIcon(null);
        
        txtBuscar.setText("");


    }

    public void inhabilitar() {
        btnNuevo.setEnabled(true);
        txtApellidos.setEnabled(false);
        txtNombres.setEnabled(false);
        txtDni.setEnabled(false);
        txtNroHistoria.setEnabled(false);
        txtCelular.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtDireccion.setEnabled(false);
        dateFechaNacimiento.setEnabled(false);
        txtLugar.setEnabled(false);
        txtEmail.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(true);
        btnCancelar.setEnabled(false);

    }

    public void habilitar() {
        txtApellidos.setEnabled(true);
        txtNombres.setEnabled(true);
        txtDni.setEnabled(true);
        txtNroHistoria.setEnabled(true);
        txtCelular.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtDireccion.setEnabled(true);
        dateFechaNacimiento.setEnabled(true);
        txtLugar.setEnabled(true);
        txtEmail.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnModificar.setEnabled(false);
        btnCancelar.setEnabled(true);

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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        VerInformacion = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        tabPaciente = new javax.swing.JTabbedPane();
        panelBuscar = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rbApellidos = new javax.swing.JRadioButton();
        rbNombres = new javax.swing.JRadioButton();
        rbDni = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        rbCelular = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePaciente = new javax.swing.JTable();
        lblCantidadRegistros = new javax.swing.JLabel();
        panelNuevo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblIdPaciente = new javax.swing.JLabel();
        txtIdPaciente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtLugar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        dateFechaNacimiento = new com.toedter.calendar.JDateChooser();
        btnSeleccionar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        txtFoto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNroHistoria = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        VerInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscadetalle_p.png"))); // NOI18N
        VerInformacion.setText("Ver Informacion");
        VerInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerInformacionActionPerformed(evt);
            }
        });
        jPopupMenu1.add(VerInformacion);

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cancelar_p.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Eliminar);

        setTitle("Gestión de Pacientes");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Criterios de busqueda"));

        buttonGroup2.add(rbApellidos);
        rbApellidos.setText("Apellidos");

        buttonGroup2.add(rbNombres);
        rbNombres.setText("Nombres");

        buttonGroup2.add(rbDni);
        rbDni.setText("Dni");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        buttonGroup2.add(rbCelular);
        rbCelular.setText("Celular");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbApellidos)
                    .addComponent(rbNombres)
                    .addComponent(rbDni)
                    .addComponent(rbCelular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablePaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdPaciente", "Apellidos", "Nombres", "Dni", "Nro Historia", "Celular", "Telefono", "Direccion", "F.Nacimiento", "L.Procedencia", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePaciente.setComponentPopupMenu(jPopupMenu1);
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

        lblCantidadRegistros.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCantidadRegistros.setText("lblCantidadRegistros");

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                    .addComponent(lblCantidadRegistros)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(56, Short.MAX_VALUE))
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
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tabPaciente.addTab("Buscar", panelBuscar);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos del paciente"));

        lblIdPaciente.setText("Id Paciente :");

        txtIdPaciente.setEnabled(false);

        jLabel2.setText("Apellidos :");

        jLabel3.setText("Nombres :");

        jLabel4.setText("Dni :");

        jLabel5.setText("Telefono :");

        jLabel6.setText("F. Nacimiento :");

        jLabel7.setText("Celular :");

        jLabel8.setText("Direccion :");

        jLabel9.setText("Lugar Procedencia :");

        jLabel10.setText("Email :");

        dateFechaNacimiento.setPreferredSize(new java.awt.Dimension(87, 19));

        btnSeleccionar.setText("Seleccionar Foto");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel12.setText("Nro Historia :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                                                .addComponent(txtDni, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtApellidos, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtNombres, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtNroHistoria, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(dateFechaNacimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(lblIdPaciente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 65, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtNroHistoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(dateFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(25, 25, 25))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addGap(27, 27, 27)
                .addComponent(txtFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdPaciente)
                    .addComponent(txtIdPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout panelNuevoLayout = new javax.swing.GroupLayout(panelNuevo);
        panelNuevo.setLayout(panelNuevoLayout);
        panelNuevoLayout.setHorizontalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );
        panelNuevoLayout.setVerticalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        tabPaciente.addTab("Nuevo/Modificar", panelNuevo);

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
                .addComponent(tabPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 106, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
       txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                String cadena = (txtBuscar.getText());
                txtBuscar.setText(cadena);
                //repaint();
                busquedaFiltro();
            }
        });
        trsFiltro = new TableRowSorter(tablePaciente.getModel());
        tablePaciente.setRowSorter(trsFiltro);
        
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion = "Nuevo";
        limpiar();
        habilitar();
        tabPaciente.setSelectedIndex(tabPaciente.indexOfComponent(panelNuevo));
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(dateFechaNacimiento.getDate()==null)
        {
         JOptionPane.showMessageDialog(this, "Debes de ingresar la fecha de cumpleaños");
        }
        else
        {
        paciente.setApellidos(txtApellidos.getText());
        paciente.setNombres(txtNombres.getText());
        paciente.setDni(txtDni.getText());
        paciente.setNroHistoria(txtNroHistoria.getText());
        paciente.setFechaNacimiento(dateFechaNacimiento.getDate());
        paciente.setTelefono(txtTelefono.getText());
        paciente.setCelular(txtCelular.getText());
        paciente.setDireccion(txtDireccion.getText());
        paciente.setLugarProcedencia(txtLugar.getText());
        paciente.setEmail(txtEmail.getText());
        paciente.setFoto(txtFoto.getText());
        paciente.setEstado(Constantes.ESTADO_REGISTRO_ACTIVO);
       

        if (accion.equals("Nuevo")) {
            pacienteDAO.insertar(paciente);
            JOptionPane.showMessageDialog(null, "El paciente fue registrado correctamente");

        } else if (accion.equals("Modificar")) {
            paciente.setIdPaciente(Integer.parseInt(txtIdPaciente.getText()));
            pacienteDAO.modificar(paciente);
            JOptionPane.showMessageDialog(null, "El paciente fue actualizado correctamente");

        }
        limpiarTabla(tablePaciente);
        mostrar();
        limpiar();
        inhabilitar();
        tabPaciente.setSelectedIndex(tabPaciente.indexOfComponent(panelBuscar));
        }
       
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (tablePaciente.getSelectedRows().length > 0) {
            accion = "Modificar";
            habilitar();
            tabPaciente.setSelectedIndex(tabPaciente.indexOfComponent(panelNuevo));
        } else {
            JOptionPane.showMessageDialog(null, "¡Se debe seleccionar un registro!");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inhabilitar();
        tabPaciente.setSelectedIndex(tabPaciente.indexOfComponent(panelBuscar));
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tablePacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePacienteMouseClicked
      
        int fila;
        DefaultTableModel defaultTableModel = new DefaultTableModel();
         fila = tablePaciente.convertRowIndexToModel(tablePaciente.getSelectedRow());
        
        if (fila == -1){
            JOptionPane.showMessageDialog(null, "Se debe seleccionar un registro");
        }else{
            try {
                
                defaultTableModel = (DefaultTableModel)tablePaciente.getModel();
                txtIdPaciente.setText(defaultTableModel.getValueAt(fila, 0).toString());
                txtApellidos.setText(defaultTableModel.getValueAt(fila, 1).toString());
                txtNombres.setText(defaultTableModel.getValueAt(fila, 2).toString());
                txtDni.setText(defaultTableModel.getValueAt(fila, 3).toString());
                txtNroHistoria.setText(defaultTableModel.getValueAt(fila, 4).toString());
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNac = date.parse(defaultTableModel.getValueAt(fila, 8).toString());
                dateFechaNacimiento.setDate(fechaNac);
                txtTelefono.setText(defaultTableModel.getValueAt(fila, 6).toString());
                txtCelular.setText(defaultTableModel.getValueAt(fila, 5).toString());
                txtDireccion.setText(defaultTableModel.getValueAt(fila, 7).toString());
                txtLugar.setText(defaultTableModel.getValueAt(fila, 9).toString());
                txtEmail.setText(defaultTableModel.getValueAt(fila, 10).toString());
                
                //mostrar foto
        String ruta = pacienteDAO.obtenerRutaFoto(Integer.parseInt(defaultTableModel.getValueAt(fila, 0).toString()));
        ImageIcon img = new ImageIcon(ruta);
        
        Image imagen = img.getImage();
        BufferedImage resizedImg = new BufferedImage(lblFoto.getWidth(),lblFoto.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(imagen, 0, 0, lblFoto.getWidth(),lblFoto.getHeight(), null);
        g2.dispose();
        ImageIcon imagenRediseñada = new ImageIcon(resizedImg);     
        lblFoto.setIcon(imagenRediseñada);
        txtFoto.setText(ruta);
               
              
            } catch (Exception e) {
                
                log.error("Ocurrio un error: "+e);
            }
        }
        
        
    }//GEN-LAST:event_tablePacienteMouseClicked

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed

        JFileChooser file=new JFileChooser();
        file.showOpenDialog(this);
        File abre=file.getSelectedFile();
        String ruta =abre.getAbsolutePath();
        ImageIcon img = new ImageIcon(ruta);
        Image imagen = img.getImage();
        BufferedImage resizedImg = new BufferedImage(lblFoto.getWidth(),lblFoto.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(imagen, 0, 0, lblFoto.getWidth(),lblFoto.getHeight(), null);
        g2.dispose();
        ImageIcon imagenRediseñada = new ImageIcon(resizedImg);
        
        
        lblFoto.setIcon(imagenRediseñada);
        txtFoto.setText(ruta);

    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void VerInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerInformacionActionPerformed
       
      
       DefaultTableModel defaultTableModel = (DefaultTableModel) tablePaciente.getModel();
       int fila = tablePaciente.convertRowIndexToModel(tablePaciente.getSelectedRow());
       int idPaciente = Integer.parseInt(defaultTableModel.getValueAt(fila,0).toString());
       DialogInfoPaciente.ID = idPaciente;
       DialogInfoPaciente dialogInfoPaciente = new DialogInfoPaciente(null, closable);
       dialogInfoPaciente.setLocationRelativeTo(null);
       dialogInfoPaciente.show();
       
    }//GEN-LAST:event_VerInformacionActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
      DefaultTableModel defaultTableModel = (DefaultTableModel) tablePaciente.getModel();
       int fila = tablePaciente.convertRowIndexToModel(tablePaciente.getSelectedRow());
       if(fila == -1)
       {
           JOptionPane.showMessageDialog(null,"Selecciona una fila para eliminar");
       }
       else
       {
        int idPaciente = Integer.parseInt(defaultTableModel.getValueAt(fila,0).toString());
        int confirmar = JOptionPane.showConfirmDialog(null,
                    "Esta seguro que desea eliminar el paciente? ");

            if (JOptionPane.OK_OPTION == confirmar) {
                boolean rpta=pacienteDAO.eliminarPaciente(idPaciente);
                if(rpta)
                {
                    JOptionPane.showMessageDialog(null, "Paciente Eliminado");
                }
                else
                {
                  JOptionPane.showMessageDialog(null,"No se pudo eliminar el paciente");
                }
                limpiarTabla(tablePaciente);
                mostrar();
            }
       }
       
    }//GEN-LAST:event_EliminarActionPerformed

  public void limpiarTabla(JTable tabla) {
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        int a = tabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JMenuItem VerInformacion;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dateFechaNacimiento;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCantidadRegistros;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblIdPaciente;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelNuevo;
    private javax.swing.JRadioButton rbApellidos;
    private javax.swing.JRadioButton rbCelular;
    private javax.swing.JRadioButton rbDni;
    private javax.swing.JRadioButton rbNombres;
    private javax.swing.JTabbedPane tabPaciente;
    private javax.swing.JTable tablePaciente;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFoto;
    private javax.swing.JTextField txtIdPaciente;
    private javax.swing.JTextField txtLugar;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNroHistoria;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
