
package gestion_usuarios;

import java.awt.Image;
import javax.swing.*;
import java.sql.*;

public class Menu extends javax.swing.JFrame {
    private String contraseña;
    private String nombre;
    private int id;
    private Connection conex;

    public Menu(String contraseña, String nombre, int id , Connection conex) {
        initComponents();
        setLocationRelativeTo(this);
        this.contraseña = contraseña;
        this.id = id;
        this.nombre = nombre;
          
        
        SetImageLabel(jLabel3, "src/resources/Usuario.png");
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

        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(542, 348));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Eras Demi ITC", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Menu");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(230, 40, 70, 25);

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("¿Que deseas hacer?");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(210, 70, 150, 16);

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3);
        jLabel3.setBounds(500, 10, 30, 30);

        jButton2.setText("+ Estadisticas de Exámen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(110, 230, 320, 40);

        jButton7.setText("+ Proceso de Naturalización");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);
        jButton7.setBounds(110, 110, 320, 40);

        jButton8.setText("+ Continuar Proceso");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8);
        jButton8.setBounds(110, 150, 320, 40);

        jButton9.setText("+ Exámen");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9);
        jButton9.setBounds(110, 190, 320, 40);

        jButton1.setText("+Documentos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(110, 270, 320, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Datos_Usuario obj = new Datos_Usuario(contraseña , nombre , id, conex);
            this.setVisible(false);
            obj.setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    statsexam obj = new statsexam( id , conex, contraseña , nombre);
    
    obj.setVisible(true);
    this.setVisible(false);
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    ProcesoNaturalizacion obj = new ProcesoNaturalizacion(contraseña , nombre, id, conex);
    
    obj.setVisible(true);
    this.setVisible(false);
    
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    String proceso = null;
    String paso = null;
    
    if (conex == null) {
        try {
            conex = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/relacional", 
                "root", 
                "Admin!01");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al conectar con la BD", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
        
    try {
        String checkSql = "SELECT Método_Actual, Paso_Actual FROM usuario WHERE ID_Usuario = ? LIMIT 1";
        try (PreparedStatement checkStmt = conex.prepareStatement(checkSql)) {
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                proceso = rs.getString("Método_Actual");
                paso = rs.getString("Paso_Actual");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Usted todavía no ha realizado ningún proceso de naturalización",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        
        Pasos obj = new Pasos(paso, proceso, contraseña, nombre, id, conex);
        obj.setVisible(true);
        this.setVisible(false);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, 
            "Error al consultar la BD: " + ex.getMessage(), 
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}

@Override
public void dispose() {
    if (conex != null) {
        try {
            if (!conex.isClosed()) {
                conex.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException ex) {
            System.err.println("Error al cerrar la conexión:");
            ex.printStackTrace();
        }
    }
    super.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    MenuExamen obj = new MenuExamen(conex, id , contraseña , nombre);
    
    obj.setVisible(true);
    this.setVisible(false);
    
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    
        Documentos obj = new Documentos (contraseña, nombre, id, conex);
        
        obj.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton1ActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
