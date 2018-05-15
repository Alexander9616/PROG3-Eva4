/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema1;

import Controlador.Conexion;
import Negocios.Logica;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lab2
 */
public class Notas extends javax.swing.JFrame {
    
    Logica logica;
    static DefaultTableModel modelo;
    DefaultComboBoxModel combo;
    //public static String carnetPublico;
    public Notas() {
        initComponents();
        logica = new Logica();
        Iniciar();
        modelo=new DefaultTableModel();
        combo=new DefaultComboBoxModel();
        String[] columnas={"Ciclo","Codigo","Materia","Nota 1","Nota 2","Nota 3","Promedio"};
        modelo.setColumnIdentifiers(columnas);
        tblDatos.setModel(modelo);
        ResultSet rs=Logica.consultarCiclos();
        try
        {
            combo.addElement("-Seleccione ciclo-");
            while(rs.next())
            {
              combo.addElement(rs.getString(1));
            }
            cmbCiclo.setModel(combo);
        }
        catch(Exception ex)
        {
            
        }
        this.setLocationRelativeTo(null);
    }
    public static void BuscarNotas(String carnet,String ciclo)
    {
        Conexion conn=new Conexion();
        String consulta="SELECT n.codCiclo as Ciclo,m.codMateria as Cod_Materia,m.nombre as Materia ";
                consulta +=",n.nota1,n.nota2,n.nota3,n.promedio ";
                consulta +="from tblnotas as n inner join tblmaterias as m ";
                consulta +="on n.codMateria=m.codMateria WHERE n.carnet='"+carnet+"' and n.codCiclo= '"+ciclo+"' ";
        ResultSet datos=null;
        try
        {
            modelo.setNumRows(0);
            datos=conn.consultarCarnet(consulta);
            while(datos.next())
            {
               modelo.addRow(new Object[] {datos.getString(1),datos.getString(2),datos.getString(3),
               datos.getString(4),datos.getString(5),datos.getString(6),datos.getString(7)});
            }
            
        }
        catch(Exception ex)
        {
            datos=null;
        }
    }
    
    public void BuscaRegistro(String carnetPublico, String ciclo)
    {
        //String carnet = carnetPublico;
        Iniciar();
        btnCancelar.setEnabled(true);
        modelo=new DefaultTableModel();
        modelo.setNumRows(0);
        String[] columnas={"Ciclo","Codigo","Materia","Nota 1","Nota 2","Nota 3","Promedio"};
        modelo.setColumnIdentifiers(columnas);
        if (logica.numRegistrosBuscar(carnetPublico,ciclo) > 0) {
            ResultSet rs = logica.consultarRegistros(carnetPublico,ciclo);
            try{
                while(rs.next()){
                    modelo.addRow(new Object[] {rs.getString("codCiclo"),rs.getString("codMateria"),rs.getString("nombreMateria"),rs.getString("nota1"),rs.getString("nota2"),rs.getString("nota3"),rs.getString("promedio")});
                }
                tblDatos.setModel(modelo);
                tblDatos.setVisible(true);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(rootPane, "Ocurrió un error mostrando los datos");
            }
            
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "No tiene registros para el ciclo "+ciclo);
        }
    }
    
   boolean validarMateria(String cod)
   {
       for (int i = 0; i < tblDatos.getRowCount(); i++) {
           if (tblDatos.getValueAt(i, 1).equals(cod)) {
               return true;
           }
       }
       return false;
   }
    
   
    void Iniciar()
    {
        txtCodMateria.setEnabled(false);
        txtNota1.setEnabled(false);
        txtNota2.setEnabled(false);
        txtNota3.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnAgregarMateria.setEnabled(false);
        tblDatos.setEnabled(false);
        btnBuscarMateria.setEnabled(false);
        cmbCiclo.setEnabled(false);
        txtNombreEstudiante.setText("");
        txtCarnet.setText("");
        txtCarnet.setEnabled(true);
        btnBuscarCarnet.setEnabled(true);
        txtCarnet.requestFocus();
        btnCancelar.setEnabled(false);
        btnBuscarCarnet.setEnabled(true);
    }
    
    void MateriaValidada()
    {
        txtNota1.setEnabled(true);
        txtNota2.setEnabled(true);
        txtNota3.setEnabled(true);
        btnAgregarMateria.setEnabled(true);
        txtCodMateria.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnBuscarMateria.setEnabled(false);
    }
    
    
    boolean validarCiclo(JComboBox combo)
    {
        int n = tblDatos.getRowCount()+1;
        switch (combo.getSelectedIndex())
        {
            case 1:
                return n != 5;
            case 2:
                return n != 5;
            case 3:
                return n != 2;
            default:
                return true;
        }
    }
    
    void CarnetValidado(){
        txtNota1.setEnabled(false);
        txtNota2.setEnabled(false);
        txtNota3.setEnabled(false);
        btnAgregarMateria.setEnabled(false);
        btnBuscarMateria.setEnabled(true);
        cmbCiclo.setEnabled(true);
        btnCancelar.setEnabled(true);
        
        txtCodMateria.setEnabled(true);
        txtNota1.setText("");
        txtNota2.setText("");
        txtNota3.setText("");
        txtNombreMateria.setText("");
        txtCodMateria.setText("");
        txtCarnet.setEnabled(false);
        txtNombreMateria.setText("");
        btnBuscarCarnet.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCarnet = new javax.swing.JTextField();
        btnBuscarCarnet = new javax.swing.JButton();
        txtNombreEstudiante = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNombreMateria = new javax.swing.JTextField();
        btnBuscarMateria = new javax.swing.JButton();
        txtCodMateria = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNota1 = new javax.swing.JTextField();
        txtNota3 = new javax.swing.JTextField();
        txtNota2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbCiclo = new javax.swing.JComboBox<>();
        btnAgregarMateria = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Carnet");

        txtCarnet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarnetKeyTyped(evt);
            }
        });

        btnBuscarCarnet.setText("Buscar");
        btnBuscarCarnet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCarnetActionPerformed(evt);
            }
        });

        txtNombreEstudiante.setEditable(false);

        jLabel2.setText("Nombre");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Registro de Notas"));

        jLabel3.setText("Codigo Materia");

        txtNombreMateria.setEditable(false);

        btnBuscarMateria.setText("Buscar");
        btnBuscarMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMateriaActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre");

        txtNota1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNota1KeyTyped(evt);
            }
        });

        txtNota3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNota3KeyTyped(evt);
            }
        });

        txtNota2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNota2KeyTyped(evt);
            }
        });

        jLabel5.setText("Nota 1 - 20%");

        jLabel6.setText("Nota 2 - 20%");

        jLabel7.setText("Nota 3 - 60%");

        cmbCiclo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccione-" }));
        cmbCiclo.setToolTipText("Seleccione un ciclo");

        btnAgregarMateria.setText("Agregar");
        btnAgregarMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMateriaActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setToolTipText("");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel8.setText("Ciclo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCodMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarMateria)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(cmbCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNota1)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNota2))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNota3))
                                .addGap(46, 46, 46)
                                .addComponent(btnCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregarMateria))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(126, 126, 126)
                                .addComponent(jLabel4)
                                .addGap(182, 182, 182)
                                .addComponent(jLabel8)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarMateria)
                    .addComponent(txtNombreMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCiclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNota3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAgregarMateria))
                .addGap(22, 22, 22))
        );

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblDatos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarCarnet)
                                .addGap(25, 25, 25)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar)
                            .addComponent(btnBuscar))
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCarnet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarCarnet)
                            .addComponent(txtNombreEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)))
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Buscar buscar = new Buscar();
        buscar.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnBuscarActionPerformed
    
    private void btnBuscarMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMateriaActionPerformed
        logica.setCodMateria(txtCodMateria.getText());
        String codigo = txtCodMateria.getText();
        if (logica.validarMateria()) {
            if (validarMateria(codigo)) {
                JOptionPane.showMessageDialog(rootPane, "La materia ya está añadida en la tabla");
                CarnetValidado();
            }
            else{
                if (cmbCiclo.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Seleccione un ciclo para inscribir las materias");
                    CarnetValidado();
                }
                else{
                   if (validarCiclo(cmbCiclo)) {
                       txtNombreMateria.setText(logica.consultarMateria().toString());
                       MateriaValidada();
                   }
                   else{
                       JOptionPane.showMessageDialog(rootPane, "No se pueden agregar más materias en el ciclo "+cmbCiclo.getSelectedItem().toString());
                       CarnetValidado();
                   }   
                    
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "El código de la materia no existe o está mal escrito");
        }
    }//GEN-LAST:event_btnBuscarMateriaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
       try{
      //consultaSQl = "insert into tblnotas(codCiclo,codMateria,nombreMateria,nota1,nota2,nota3,promedio,carnet) ";
            for (int i = 0; i < tblDatos.getRowCount(); i++) {
                
                logica.setCiclo(tblDatos.getValueAt(i, 0).toString());
                logica.setCodMateria(tblDatos.getValueAt(i, 1).toString());
                logica.setNombreMateria(tblDatos.getValueAt(i, 2).toString());
                logica.setNota1(Double.parseDouble(tblDatos.getValueAt(i, 3).toString()));
                logica.setNota2(Double.parseDouble(tblDatos.getValueAt(i, 4).toString()));
                logica.setNota3(Double.parseDouble(tblDatos.getValueAt(i, 5).toString()));
                logica.setPromedio(Double.parseDouble(tblDatos.getValueAt(i, 6).toString()));
                logica.setCarnet(txtCarnet.getText());
                logica.insertarMaterias();
            }
            JOptionPane.showMessageDialog(rootPane, "Registro Insertado Exitosamente");
            modelo.setNumRows(0);
            Iniciar();
            cmbCiclo.setSelectedIndex(0);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Error ingresando llos registros");
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    private void generarTabla()
    {
        //modelo.setNumRows(0);
        String ciclo,codMateria,materia;
        double nota1,nota2,nota3,promedio;
        ciclo=cmbCiclo.getSelectedItem().toString();
        codMateria=txtCodMateria.getText();
        materia=txtNombreMateria.getText();
        nota1=Double.valueOf(txtNota1.getText());
        nota2=Double.valueOf(txtNota2.getText());
        nota3=Double.valueOf(txtNota3.getText());
        promedio=((nota1*0.2)+(nota2*0.2)+(nota3*0.6));
        modelo.addRow(new Object[]{ciclo,codMateria,materia,nota1,nota2,nota3,promedio});
    }
    private void btnBuscarCarnetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCarnetActionPerformed
        // TODO add your handling code here:
        logica.setCarnet(txtCarnet.getText());
        if (logica.validarCarnet())
        {
            txtNombreEstudiante.setText(logica.consultarCarnet());
            CarnetValidado();
            
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"El carnet no existe en el registro");
            txtCarnet.setText("");
            txtCarnet.requestFocus();
        }
    }//GEN-LAST:event_btnBuscarCarnetActionPerformed

    private void btnAgregarMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMateriaActionPerformed
        String codigo = txtCodMateria.getText();
        if(Validar()==true){
            
        }
        else
        {
          
            generarTabla();
            LimpiarNotas();
            CarnetValidado();
            
        }
      
    }//GEN-LAST:event_btnAgregarMateriaActionPerformed

    private void txtCarnetKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarnetKeyTyped

        char c = evt.getKeyChar();
        if (c<'0' || c>'9') evt.consume();
    }//GEN-LAST:event_txtCarnetKeyTyped

    private void txtNota1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNota1KeyTyped
        if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.'){
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtNota1.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNota1KeyTyped

    private void txtNota2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNota2KeyTyped
        if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.'){
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtNota2.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNota2KeyTyped

    private void txtNota3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNota3KeyTyped
        if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.'){
            evt.consume();
        }
        if (evt.getKeyChar() == '.' && txtNota3.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNota3KeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Iniciar();
        modelo.setNumRows(0);
        txtNombreMateria.setText("");
        cmbCiclo.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    void LimpiarNotas(){
        txtNota1.setText("");
        txtNota2.setText("");
        txtNota3.setText("");
    }
    
    public boolean Validar(){
        String n1,n2,n3;
        n1=txtNota1.getText().toString();
        n2=txtNota2.getText().toString();
        n3=txtNota3.getText().toString();
        if(n1.equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Requiere primera nota");
            txtNota1.requestFocus();
            return true;
        }
        else if (n2.equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Requiere segunda nota");
            txtNota2.requestFocus();
            return true;
        }
        else if(n3.equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Requiere tercera nota");
            txtNota3.requestFocus();
            return true;
        }
        else
        {
            return false;
        }
    }
    
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
            java.util.logging.Logger.getLogger(Notas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Notas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Notas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Notas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Notas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarMateria;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarCarnet;
    private javax.swing.JButton btnBuscarMateria;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbCiclo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField txtCarnet;
    private javax.swing.JTextField txtCodMateria;
    private javax.swing.JTextField txtNombreEstudiante;
    private javax.swing.JTextField txtNombreMateria;
    private javax.swing.JTextField txtNota1;
    private javax.swing.JTextField txtNota2;
    private javax.swing.JTextField txtNota3;
    // End of variables declaration//GEN-END:variables
}
