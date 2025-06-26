package gestion_usuarios;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;

public class Documentos extends javax.swing.JFrame {

    private int id;
    private String contraseña;
    private String nombre;
    private Connection conex;
    
    public Documentos(String contraseña, String nombre, int id , Connection conex) {
        initComponents();
        setLocationRelativeTo(this);
        this.id = id;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButtonUpload = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(542, 348));

        jLabel2.setFont(new java.awt.Font("Eras Demi ITC", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Documentos");

        jButtonUpload.setText("Subir");
        jButtonUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUploadActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonUpload)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(201, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(197, 197, 197))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addGap(72, 72, 72)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jButtonUpload)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUploadActionPerformed
                                                  
        JFileChooser fileChooser = new JFileChooser();
        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "Documentos e imágenes", "pdf", "doc", "docx", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filtro);
        
        int resultado = fileChooser.showOpenDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            
            try (Connection con = BD.conectar()) {
                if (con == null) {
                    JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String directorioDestino = "documentos_subidos/";
                File destino = new File(directorioDestino + archivoSeleccionado.getName());
                
                new File(directorioDestino).mkdirs();
                
                Files.copy(archivoSeleccionado.toPath(), destino.toPath(), 
                         StandardCopyOption.REPLACE_EXISTING);
                
                guardarEnBaseDatos(con, archivoSeleccionado, destino.getAbsolutePath());
                
                JOptionPane.showMessageDialog(this, "Documento subido exitosamente", 
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al subir el documento: " + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }                                             
    
   private void guardarEnBaseDatos(Connection con, File archivo, String rutaAlmacenamiento) 
    throws SQLException, IOException {  // Añade IOException aquí
    
    String sql = "INSERT INTO documentos (nombre, tipo, tamaño, ruta, ID_Usuario) " +
                 "VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, archivo.getName());
        
        String tipo = Files.probeContentType(archivo.toPath());
        stmt.setString(2, tipo != null ? tipo : "desconocido");
        
        stmt.setLong(3, archivo.length());
        stmt.setString(4, rutaAlmacenamiento);
        stmt.setInt(5, id);
        
        stmt.executeUpdate();
    }

    }//GEN-LAST:event_jButtonUploadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Menu obj = new Menu(contraseña, nombre, id, conex);
        
        this.setVisible(false);
        obj.setVisible(true);
        
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonUpload;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
