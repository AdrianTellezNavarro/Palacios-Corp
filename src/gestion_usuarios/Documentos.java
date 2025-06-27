package gestion_usuarios;
import java.awt.Desktop;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileSystemView;

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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ver");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(201, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(197, 197, 197))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButtonUpload))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addGap(88, 88, 88)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonUpload)
                .addContainerGap(141, Short.MAX_VALUE))
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
                "PDF", "pdf");
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
    throws SQLException, IOException {
    
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    String ruta = "C:\\Users\\adrtl\\OneDrive\\Documentos\\NetBeansProjects\\Gestion_usuarios\\documentos_subidos";
    File folder = new File(ruta);

    

    JFileChooser fileChooser = new JFileChooser(folder);
    
    fileChooser.setDialogTitle("Selecciona un documento para abrir");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setAcceptAllFileFilterUsed(false);
    
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Documentos (PDF)", "pdf");
    fileChooser.setFileFilter(filter);

    int result = fileChooser.showOpenDialog(this);
    
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        try {
            Desktop.getDesktop().open(selectedFile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, 
                "No se pudo abrir el documento:\n" + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }  
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonUpload;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
