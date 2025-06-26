
package gestion_usuarios;
import java.sql.*;

public class BD {
        static final String URL = "jdbc:mysql://localhost:3306/relacional";
    static final String USERNAME = "root";
    static final String PASSWORD = "Admin!01";
    
    
    
    
    public static Connection conectar() {
    Connection con = null;
    try{
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Conexión a la BDD exitosa");
    } catch(SQLException e) {
        System.out.println(e);
    }
    return con;
}
}
