package database;

import model.Intento;
import database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IntentoDAO {

    public void guardarIntento(Intento intento) {
        String sql = "INSERT INTO intento (usuario_id, palabra_id, traduccion_usuario, es_correcta, fecha) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, intento.getUsuarioId());
            stmt.setInt(2, intento.getPalabraId());
            stmt.setString(3, intento.getTraduccionUsuario());
            stmt.setBoolean(4, intento.isEsCorrecta());
            stmt.setTimestamp(5, Timestamp.valueOf(intento.getFecha()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al guardar el intento: " + e.getMessage());
        }
    }

    public List<Intento> obtenerIntentosPorUsuario(int usuarioId) {
        List<Intento> intentos = new ArrayList<>();
        String sql = "SELECT * FROM intento WHERE usuario_id = ? ORDER BY fecha DESC";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Intento intento = new Intento(
                        rs.getInt("usuario_id"),
                        rs.getInt("palabra_id"),
                        rs.getString("traduccion_usuario"),
                        rs.getBoolean("es_correcta")
                );
                intento.setId(rs.getInt("id"));
                intento.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                intentos.add(intento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al obtener los intentos: " + e.getMessage());
        }

        return intentos;
    }

    public void eliminarIntentosPorUsuario(int usuarioId) {
        String sql = "DELETE FROM intento WHERE usuario_id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al eliminar intentos: " + e.getMessage());
        }
    }
}