package database;

import model.Progreso;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgresoDAO {

    public static void insertarProgreso(int usuarioId, int palabraId, boolean respuestaCorrecta) {
        String sql = "INSERT INTO progreso (usuarioId, palabraId, respuesta_correcta) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            stmt.setInt(2, palabraId);
            stmt.setBoolean(3, respuestaCorrecta);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar progreso: " + e.getMessage());
        }
    }

    public static List<Progreso> obtenerProgresoConDetalles() {
        List<Progreso> lista = new ArrayList<>();

        String sql = """
                SELECT p.palabraIngles, p.traduccionEspanol, pr.respuesta_correcta, pr.fecha
                FROM progreso pr
                JOIN palabras p ON pr.palabraId = p.id
                ORDER BY pr.fecha DESC
                """;

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String palabra = rs.getString("palabraIngles");
                String traduccion = rs.getString("traduccionEspanol");
                boolean correcta = rs.getBoolean("respuesta_correcta");
                Timestamp fecha = rs.getTimestamp("fecha");

                Progreso prog = new Progreso(
                        palabra,
                        traduccion,
                        correcta,
                        fecha.toString()
                );
                lista.add(prog);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener progreso: " + e.getMessage());
        }

        return lista;
    }
}
