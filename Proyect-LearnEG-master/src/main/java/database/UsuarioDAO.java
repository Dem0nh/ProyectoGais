package database;

import model.Usuario;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

//Utilizamos PreparedEstatement para prevenir inyección SQL
//Utilizamos Try-With-resource para asegurar que las conexiones se cierren adecuadamente

public class UsuarioDAO {

    // Inserta un nuevo usuario
    public static void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nombre) VALUES (?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar usuario: " + e.getMessage());
        }
    }

    // Obtiene todos los usuarios
    public static List<Usuario> obtenerUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                lista.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener usuarios: " + e.getMessage());
        }

        return lista;
    }

    // Elimina un usuario por ID
    public static void eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar usuario: " + e.getMessage());
        }
    }

    // Busca un usuario por ID
    public static Usuario buscarUsuarioPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar usuario por ID: " + e.getMessage());
        }

        return usuario;
    }
}
