package database;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;

public class DatabaseSetup {

    public static void crearTablas() {
        // Creamos la tabla palabras si no existe
        String crearTablaPalabras = """
                CREATE TABLE IF NOT EXISTS palabras (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    palabraIngles VARCHAR(100) NOT NULL,
                    traduccionEspanol VARCHAR(100) NOT NULL,
                    contexto VARCHAR(100) NOT NULL,
                    dificultad INT DEFAULT 1
                );
                """;

        // Creamos la tabla usuarios si no existe
        String crearTablaUsuario = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(100) NOT NULL
                );
                """;

        // Creamos la tabla progreso si no existe
        String crearTablaProgreso = """
        CREATE TABLE IF NOT EXISTS progreso (
            id INT AUTO_INCREMENT PRIMARY KEY,
            usuarioId INT,
            palabraId INT,
            respuesta_correcta BOOLEAN,
            fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
            FOREIGN KEY (usuarioId) REFERENCES usuarios(id),
            FOREIGN KEY (palabraId) REFERENCES palabras(id)
        );
        """;
        String crearTablaIntento = """
        CREATE TABLE intento (
                id INT AUTO_INCREMENT PRIMARY KEY,
                usuario_id INT,
                palabra_id INT,
                traduccion_usuario VARCHAR(255),
                es_correcta BOOLEAN,
                fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (usuario_id) REFERENCES usuario(id),
                FOREIGN KEY (palabra_id) REFERENCES palabra(id)
        );
        """;


        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(crearTablaPalabras);
            System.out.println("📦 Tabla 'palabras' verificada/creada correctamente.");

            stmt.execute(crearTablaUsuario);
            System.out.println("👤 Tabla 'usuarios' verificada/creada correctamente.");

            stmt.execute(crearTablaProgreso);
            System.out.println("📄 Tabla 'progreso' verificada/creada correctamente.");

            stmt.execute(crearTablaIntento);
            System.out.println("📶 Tabla 'progreso' verificada/creada correctamente.");

        } catch (SQLException e) {
            System.out.println("❌ Error al crear tablas: " + e.getMessage());
        }
    }
}
