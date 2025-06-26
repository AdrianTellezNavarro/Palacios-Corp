
package gestion_usuarios;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.DriverManager;

    

public class Exámen extends javax.swing.JFrame {
    private String typeex;
    private int respuestascorr = 0;
    private int numpregunta = 0;
    private Connection conex;
    private int id;
    private String contraseña;
    private String nombre;
    private int id_Examen;
    private int preguntaActual = 0;
    private int puntaje = 0;
    private ArrayList<String[]> preguntasExamen;
    private ArrayList<Character> respuestasCorrectas;
    
    public Exámen(String typeex , Connection conex , int id, String contraseña, String nombre, int id_Examen) {
        initComponents();
        this.setLocationRelativeTo(this);
        this.typeex = typeex;
        this.id = id;
        this.conex = conex;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.id_Examen= id_Examen;
        
        
        Labels();
        cargarExamen(typeex);

        jButtonSaltar.setVisible(false);
        
                // En el constructor o método de inicialización de tu clase:
        jButton0.addActionListener(e -> verificarRespuesta('A'));
        jButton1.addActionListener(e -> verificarRespuesta('B'));
        jButton2.addActionListener(e -> verificarRespuesta('C'));
        jButton3.addActionListener(e -> verificarRespuesta('D'));

        // Botón para saltar pregunta (opcional)
        jButtonSaltar.addActionListener(e -> {
            if (preguntaActual < preguntasExamen.size() - 1) {
                preguntaActual++;
                mostrarPregunta(preguntaActual);
            }
        });
        
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton0 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButtonSaltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(542, 348));
        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Eras Demi ITC", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Exámen");
        jLabel3.setPreferredSize(new java.awt.Dimension(161, 28));
        jPanel1.add(jLabel3);
        jLabel3.setBounds(190, 20, 170, 25);

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(177, 40, 190, 16);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(50, 66, 440, 40);

        jButton0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton0ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton0);
        jButton0.setBounds(170, 110, 200, 40);

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(170, 150, 200, 40);
        jPanel1.add(jButton2);
        jButton2.setBounds(170, 190, 200, 40);
        jPanel1.add(jButton3);
        jButton3.setBounds(170, 230, 200, 40);

        jButtonSaltar.setText("Salir");
        jButtonSaltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaltarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSaltar);
        jButtonSaltar.setBounds(210, 290, 120, 23);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonSaltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaltarActionPerformed
    String sql = "INSERT INTO resultado_examen (ID_Usuario, ID_Examen, Puntaje_Obtenido) VALUES (?, ?, ?)";
    
    try (Connection conex = BD.conectar();
         PreparedStatement pstmt = conex.prepareStatement(sql)) {

        pstmt.setInt(1, id);
        pstmt.setInt(2,id_Examen );
        pstmt.setInt(3, puntaje);

        int cambio = pstmt.executeUpdate();

        if (cambio > 0) {
            System.out.println("Inserción exitosa");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
        
    MenuExamen obj = new MenuExamen( conex , id, contraseña , nombre );
    
    obj.setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_jButtonSaltarActionPerformed

    private void jButton0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton0ActionPerformed

    
    private void Labels(){
    
        jLabel1.setText("Tipo :" + typeex);
        
        String pregunta = "";
        String[] respuestas = new String[4];       
    }
    
    
    
    
    private void mostrarPregunta(int indice) {
    if (preguntasExamen == null || indice < 0 || indice >= preguntasExamen.size()) {
        return;
    }

    String[] pregunta = preguntasExamen.get(indice);
    
    jLabel2.setText(pregunta[0]);  // Pregunta
    jButton0.setText(pregunta[1]); // Opción A
    jButton1.setText(pregunta[2]); // Opción B
    jButton2.setText(pregunta[3]); // Opción C
    jButton3.setText(pregunta[4]); // Opción D
    
    
    
    preguntaActual = indice;
}
    
    

private int obtenerIdExamen(String typeex) {
    // Elimina espacios extras y normaliza el texto para comparación
    
    
    switch(typeex) {
        case "Historia de México":
            return 1;
        case "Geografia de México":
            return 2;
        case "Instituciones y gobierno":
            return 3;
        case "Cultura y tradiciones":
            return 4;
        case "Idioma español":
            return 5;
        case "Simbolos patrios":
            return 6;
        default:
            // Mensaje de depuración
            System.err.println("Tipo de examen no reconocido: " + typeex);
            return 0; // Retorna 0 si no coincide con ningún tipo
    }
}

public void cargarExamen(String typeex) {
    int idExamen = obtenerIdExamen(typeex);
    
    if (idExamen == 0) {
        JOptionPane.showMessageDialog(this, "Tipo de examen no reconocido", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        if (conex == null || conex.isClosed()) {
            conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/relacional", "root", "Admin!01");
        }

        String sql = "SELECT Texto_Pregunta, Opcion_A, Opcion_B, Opcion_C, Opcion_D, Respuesta_Correcta " +
                     "FROM pregunta WHERE ID_Examen = ? ORDER BY ID_Pregunta";
        
        PreparedStatement pst = conex.prepareStatement(sql);
        pst.setInt(1, idExamen);
        ResultSet rs = pst.executeQuery();

        preguntasExamen = new ArrayList<>();
        respuestasCorrectas = new ArrayList<>();

        while (rs.next()) {
            String[] pregunta = {
                rs.getString("Texto_Pregunta"),
                rs.getString("Opcion_A"),
                rs.getString("Opcion_B"),
                rs.getString("Opcion_C"),
                rs.getString("Opcion_D")
            };
            preguntasExamen.add(pregunta);
            
            String respuesta = rs.getString("Respuesta_Correcta");
            respuestasCorrectas.add(respuesta != null ? respuesta.charAt(0) : ' ');
        }

        if (preguntasExamen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay preguntas para este examen", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            mostrarPregunta(0);
        }

        rs.close();
        pst.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar preguntas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

// Método para verificar respuesta
public void verificarRespuesta(char opcionSeleccionada) {
    if (preguntaActual >= respuestasCorrectas.size()) return;
    
    char respuestaCorrecta = respuestasCorrectas.get(preguntaActual);
    boolean esCorrecta = Character.toUpperCase(opcionSeleccionada) == Character.toUpperCase(respuestaCorrecta);
    
    if (esCorrecta) {
        puntaje++;
        JOptionPane.showMessageDialog(this, "¡Correcto!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Incorrecto. La respuesta correcta es: " + respuestaCorrecta, 
                                    "Resultado", JOptionPane.WARNING_MESSAGE);
    }
    
    // Avanzar o terminar
    if (preguntaActual < preguntasExamen.size() - 1) {
        preguntaActual++;
        mostrarPregunta(preguntaActual);
    } else {
        mostrarResultadoFinal();
    }
}

private void mostrarResultadoFinal() {
    JOptionPane.showMessageDialog(this, 
        "Examen terminado!\nPuntaje: " + puntaje + "/" + preguntasExamen.size(),
        "Resultado Final", JOptionPane.INFORMATION_MESSAGE);
    
    jButtonSaltar.setVisible(true);
}
   
    
    
    
    
    
    
    
    private void verifi(int respuesta){
    
        boolean esCorrecta = false;
        int respcorr = 0;
        
        switch(typeex) {
            case "Historia de México":
                switch(numpregunta){ 
                    case 0:
                    if (respuesta == 3)respcorr = respcorr+1;
                    break;
                    case 1:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 2:
                    if (respuesta == 0)respcorr = respcorr+1;
                    break;
                    case 3:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 4:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 5:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 6:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 7:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 8:
                    if (respuesta == 3)respcorr = respcorr+1;
                    break;
                    case 9:
                    if (respuesta == 0)respcorr = respcorr+1;
                    break;}
                break;
            case "Geografia de México":
                switch(numpregunta){ 
                    case 0:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 1:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 2:
                    if (respuesta == 0)respcorr = respcorr+1;
                    break;
                    case 3:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 4:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 5:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 6:
                    if (respuesta == 3)respcorr = respcorr+1;
                    break;
                    case 7:
                    if (respuesta == 0)respcorr = respcorr+1;
                    break;
                    case 8:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 9:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;}
                break;
            case "Instituciones y gobierno":
                switch(numpregunta){ 
                    case 0:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 1:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 2:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 3:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 4:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break; 
                    case 5:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 6:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 7:
                    if (respuesta == 3)respcorr = respcorr+1;
                    break;
                    case 8:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 9:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;}
                break;
            case "Cultura y tradiciones":
                switch(numpregunta){ 
                    case 0:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 1:
                    if (respuesta == 3)respcorr = respcorr+1;
                    break;
                    case 2:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 3:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 4:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 5:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 6:
                    if (respuesta == 0)respcorr = respcorr+1;
                    break;
                    case 7:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 8:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 9:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;}
                break;
            case "Idioma español":
                switch(numpregunta){ 
                    case 0:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 1:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 2:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 3:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 4:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 5:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 6:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 7:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 8:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 9:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;}
                break;
            case "Simbolos patrios":
                switch(numpregunta){ 
                    case 0:
                    if (respuesta == 3)respcorr = respcorr+1;
                    break;
                    case 1:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 2:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 3:
                    if (respuesta == 1)respcorr = respcorr+1;
                    break;
                    case 4:
                    if (respuesta == 3)respcorr = respcorr+1;
                    break;
                    case 5:
                    if (respuesta == 2)respcorr = respcorr+1;
                    break;
                    case 6:
                    if (respuesta == 3)respcorr = respcorr+1;
                    break;
                    case 7:
                    if (respuesta == 3)respcorr = respcorr+1;
                    break;
                    case 8:
                    if (respuesta == 3)respcorr = respcorr+1;
                    break;
                    case 9:
                    if (respuesta == 0)respcorr = respcorr+1;
                    break;}
                break;}
        
        if(respcorr >= 1) {
            javax.swing.JOptionPane.showMessageDialog(this, "¡Correcto!");
            respuestascorr++;
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Incorrecto");
        }
        
        numpregunta++;
        Labels();
    
    }
    
    
    
    
    
    
     

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton0;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonSaltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
