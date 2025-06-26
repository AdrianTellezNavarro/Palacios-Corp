package gestion_usuarios;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        
        Connection conex = BD.conectar();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Inicio_de_Sesión(conex).setVisible(true);
            }
        });
    }
}


     
