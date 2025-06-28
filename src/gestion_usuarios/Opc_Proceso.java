
package gestion_usuarios;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Opc_Proceso extends javax.swing.JFrame {
    
    
    
    
    
    private String proceso;
    private String contraseña;
    private String nombre;
    private int id;
    private Connection conex;
    
    
    public Opc_Proceso(String proceso, String contraseña, String nombre, int id , Connection conex) {
        initComponents();
    this.setLocationRelativeTo(this);
    this.proceso = proceso;
    this.id = id;
    this.conex = conex;

    // Obtener y asignar los pasos
    String[] pasos = obtenerPasosDesdeBD(proceso);
    asignarPasosABotones(pasos);
}

private String[] obtenerPasosDesdeBD(String proceso) {
    int idMetodo = obtenerIdMetodo(proceso);
    
    if (idMetodo == 0) {
        return new String[]{"Proceso no reconocido"};
    }

    try {
        String sql = "SELECT Titulo_Paso FROM paso WHERE ID_Metodo = ? ORDER BY orden";
        PreparedStatement pstmt = conex.prepareStatement(sql);
        pstmt.setInt(1, idMetodo);
        
        ResultSet rs = pstmt.executeQuery();
        
        ArrayList<String> pasosList = new ArrayList<>();
        
        while (rs.next()) {
            pasosList.add(rs.getString("Titulo_Paso"));
        }
        
        String[] pasos = pasosList.toArray(new String[0]);
        
        rs.close();
        pstmt.close();
        
        return pasos;
        
    } catch (SQLException ex) {
        ex.printStackTrace();
        return new String[]{"Error al cargar los pasos: " + ex.getMessage()};
    }
}

private int obtenerIdMetodo(String proceso) {
    switch(proceso) {
        case "Por Latinoamericano":
            return 1;
        case "Por Padres Mexicanos":
            return 2;
        case "Por Matrimonio con Mexican@":
            return 3;
        case "Por Hijos Mexicanos":
            return 4;
        default:
            return 0;
    }
}

private void asignarPasosABotones(String[] pasos) {
    if (pasos.length == 1 && (pasos[0].startsWith("Proceso no reconocido") || pasos[0].startsWith("Error"))) {
        jButton4.setText("<html><div style='text-align: center;'>" + pasos[0] + "</div></html>");
        return;
    }
    
    // Asignamos cada paso a su botón correspondiente
    if (pasos.length > 0) jButton4.setText("<html><div style='text-align: center;'>" + pasos[0] + "</div></html>");
    if (pasos.length > 1) jButton5.setText("<html><div style='text-align: center;'>" + pasos[1] + "</div></html>");
    if (pasos.length > 2) jButton6.setText("<html><div style='text-align: center;'>" + pasos[2] + "</div></html>");
    if (pasos.length > 3) jButton7.setText("<html><div style='text-align: center;'>" + pasos[3] + "</div></html>");
    if (pasos.length > 4) jButton8.setText("<html><div style='text-align: center;'>" + pasos[4] + "</div></html>");
    if (pasos.length > 5) jButton9.setText("<html><div style='width: 100px;'>" + pasos[5] + "</div></html>");
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(542, 348));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Eras Demi ITC", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Paso Proceso");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(210, 20, 130, 25);

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Selecciona en que paso de tu proceso te encuentras");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(130, 50, 330, 16);

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(40, 110, 150, 90);

        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);
        jButton6.setBounds(340, 110, 150, 90);

        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7);
        jButton7.setBounds(40, 200, 150, 90);

        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8);
        jButton8.setBounds(190, 200, 150, 90);

        jButton5.setActionCommand("Delegaciones\nForáneas\nde la SRE (Cita)");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);
        jButton5.setBounds(190, 110, 150, 90);

        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9);
        jButton9.setBounds(340, 200, 150, 90);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(10, 10, 72, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
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

    switch(proceso) {
        case "Por Latinoamericano":
            procesarCaso("Junta la documentación requerida");
            break;
            
        case "Por Padres Mexicanos":
            procesarCaso("Reúne los documentos requeridos");
            break;
            
        case "Por Matrimonio con Mexican@":
            procesarCaso("Reúne los documentos requeridos");
            break;
            
        case "Por Hijos Mexicanos":
            procesarCaso("Reúne los documentos necesarios");
            break;
    }

    
    dispose();
    
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
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

    switch(proceso) {
        case "Por Latinoamericano":
            procesarCaso("Espera la resolución y recoge tu Carta de Naturalización");
            break;
            
        case "Por Padres Mexicanos":
            procesarCaso("Recoge tu carta de naturalización y tramita tus nuevos documentos");
            break;
            
        case "Por Matrimonio con Mexican@":
            procesarCaso("Espera la resolución y recoge tu Carta de Naturalización");
            break;
            
        case "Por Hijos Mexicanos":
            procesarCaso("Espera la resolución y recoge tu Carta de Naturalización");
            break;
    }

    
    dispose();
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
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

    switch(proceso) {
        case "Por Latinoamericano":
            procesarCaso("Verifica que cumples con los requisitos generales");
            break;
            
        case "Por Padres Mexicanos":
            procesarCaso("Verifica que cumples con el requisito de ascendencia");
            break;
            
        case "Por Matrimonio con Mexican@":
            procesarCaso("Verifica que cumples con los requisitos básicos");
            break;
            
        case "Por Hijos Mexicanos":
            procesarCaso("Verifica que cumples con los requisitos");
            break;
    }
}

private void procesarCaso(String paso) {
    try {
        
        String checkSql = "SELECT 1 FROM usuario WHERE ID_Usuario = ? LIMIT 1";
        try (PreparedStatement checkStmt = conex.prepareStatement(checkSql)) {
            checkStmt.setInt(1, id);
            boolean existe = checkStmt.executeQuery().next();
            System.out.println(id);

            // Actualizar o insertar
            if (existe) {
                String updateSql = "UPDATE usuario SET Método_Actual = ?, Paso_Actual = ? WHERE ID_Usuario = ?";
                try (PreparedStatement updateStmt = conex.prepareStatement(updateSql)) {
                    updateStmt.setString(1, proceso);
                    updateStmt.setString(2, paso);
                    updateStmt.setInt(3, id);
                    updateStmt.executeUpdate();
                }
            } else {
                String insertSql = "INSERT INTO usuario (Método_Actual, Paso_Actual, ID_Usuario) VALUES (?, ?, ?)";
                try (PreparedStatement insertStmt = conex.prepareStatement(insertSql)) {
                    insertStmt.setString(1, proceso);
                    insertStmt.setString(2, paso);
                    insertStmt.setInt(3, id);
                    insertStmt.executeUpdate();
                }
            }
        }

        Pasos obj = new Pasos(paso, proceso, contraseña, nombre, id, conex);
        obj.setVisible(true);
        this.setVisible(false);

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, 
            "Error al guardar: " + ex.getMessage(), 
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

    }//GEN-LAST:event_jButton4ActionPerformed

    
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
   
    if (conex == null) {
        try {
            conex = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/relacional", 
                "root", 
                "Admin!01");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al conectar con la base de datos", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    String paso = "Realiza el pago de derechos";

    procesarCaso(paso);

    
    dispose();
    
    
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
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

    switch(proceso) {
        case "Por Latinoamericano":
            procesarCaso("Agenda cita y entrega tu expediente");
            break;
            
        case "Por Padres Mexicanos":
            procesarCaso("Llena y presenta la solicitud");
            break;
            
        case "Por Matrimonio con Mexican@":
            procesarCaso("Llena y entrega tu solicitud");
            break;
            
        case "Por Hijos Mexicanos":
            procesarCaso("Agenda una cita y entrega tu solicitud");
            break;
    }

        
dispose();

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
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

    switch(proceso) {
        case "Por Latinoamericano":
            procesarCaso("Presenta el examen de naturalización");
            break;
            
        case "Por Padres Mexicanos":
            procesarCaso("Espera la resolución del trámite");
            break;
            
        case "Por Matrimonio con Mexican@":
            procesarCaso("Presenta el examen de naturalización");
            break;
            
        case "Por Hijos Mexicanos":
            procesarCaso("Presenta el examen de naturalización");
            break;
    }

        
dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        ProcesoNaturalizacion obj = new ProcesoNaturalizacion(contraseña , nombre, id, conex);
    
        obj.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
