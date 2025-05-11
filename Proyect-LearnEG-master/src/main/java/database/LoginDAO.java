package database;

import model.User;
import model.Usuario;
import database.ConexionDB;

import java.sql.*;

public class LoginDAO {

    public boolean registrar(User user) {
        String sql = "INSERT INTO usuarios (usuario, contrasena) VALUES (?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsuario());  // Aquí 'user' es una instancia de 'User'
            stmt.setString(2, User.getContrasena());


            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean ingresar(String usuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // True si se encontró el usuario

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al iniciar sesión: " + e.getMessage());
            return false;
        }
    }
}
