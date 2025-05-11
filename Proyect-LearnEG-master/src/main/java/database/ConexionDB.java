package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3307/learnEG"; //Estámos utilizando JDBC para conectarnos a MySQL
    private static final String USUARIO = "nicolas";
    private static final String CONTRASENA = "Nicolas2007!";

    public static Connection conectar() { //Conectar devuelve un objeto Conexion o null dependiendo de si se pudo o no hacer la conexión
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("✅ ¡Conexión exitosa a MySQL!");
            return conexion;
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con MySQL: " + e.getMessage());
            return null;
        }
    }
}
