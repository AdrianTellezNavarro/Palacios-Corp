/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gestion_usuarios;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


public class Datos_Usuario extends javax.swing.JFrame {

    private String contraseña;
    private int id;
    private String nombre;
    private Connection conex;
    
    public Datos_Usuario(String contraseña, String nombre, int id, Connection conex) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.contraseña = contraseña;
        this.id = id;
        this.nombre = nombre;
        this.conex = conex;
        Labels();
        
        SetImageLabel(jLabel6, "src/resources/Usuario.png");
    
    }
    
        
    private void Labels(){
        
        jLabel3.setText("Contraseña actual :" + contraseña );
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Nombre actual :" + nombre);
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
    
    }    

    
    @SuppressWarnings("unchecked")
    
    public void SetImageLabel(JLabel labelName , String root){
    ImageIcon image = new ImageIcon(root);
    Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
    labelName.setIcon(icon);
    this.repaint();
    
    
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPasswordFieldContraseña = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(542, 348));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Eras Demi ITC", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Datos Usuario");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(240, 10, 160, 25);
        jPanel1.add(jLabel6);
        jLabel6.setBounds(185, 6, 50, 50);

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Cambiar datos");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(240, 40, 160, 16);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(200, 80, 170, 20);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nuevo Nombre");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(200, 200, 170, 16);

        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldNombre);
        jTextFieldNombre.setBounds(200, 220, 170, 22);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(200, 170, 170, 16);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Nueva Contraseña");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(200, 110, 170, 16);

        jButton1.setBackground(new java.awt.Color(153, 0, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cambiar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(230, 270, 110, 20);

        jPasswordFieldContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldContraseñaActionPerformed(evt);
            }
        });
        jPanel1.add(jPasswordFieldContraseña);
        jPasswordFieldContraseña.setBounds(200, 130, 170, 22);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jButton2.setText("<Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(10, 10, 70, 21);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String newnombre = jTextFieldNombre.getText().trim();
String newcontraseña = new String(jPasswordFieldContraseña.getPassword()).trim();

if (newnombre.isEmpty() || newcontraseña.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Debe completar ambos campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
    return;
}

Connection conn = null;
PreparedStatement pstmt = null;
int filasAfectadas = 0;

try {
    conn = BD.conectar();
    
    String sql = "UPDATE usuario SET Nombre = ?, Contraseña = ? WHERE ID_Usuarios = ?";
    
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, newnombre);
    pstmt.setString(2, newcontraseña);
    pstmt.setInt(3, id);
    
    filasAfectadas = pstmt.executeUpdate();
    
    if (filasAfectadas > 0) {
        JOptionPane.showMessageDialog(this, "Cambio de datos realizado correctamente", "Cambio de datos", JOptionPane.INFORMATION_MESSAGE);
        
        Menu obj = new Menu(contraseña , nombre, id, conex);
        
        obj.setVisible(true);
        this.setVisible(false);
        
    } else {
        JOptionPane.showMessageDialog(this, "No se encontró el usuario o no se realizaron cambios", "Error", JOptionPane.ERROR_MESSAGE);
        
        // Limpiar campos para nuevo intento
        jPasswordFieldContraseña.setText("");
        jTextFieldNombre.requestFocus();
    }
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, "Error al actualizar datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
} finally {
    // Cerrar recursos
    try {
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al cerrar conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPasswordFieldContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldContraseñaActionPerformed
    
    }//GEN-LAST:event_jPasswordFieldContraseñaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    Menu obj = new Menu(contraseña , nombre, id, conex);
        
        obj.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFieldContraseña;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
