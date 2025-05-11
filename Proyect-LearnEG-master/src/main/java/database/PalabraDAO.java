package database;

import model.Palabra;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

//Utilizamos PreparedEstatement para prevenir inyección SQL
//Utilizamos Try-With-resource para asegurar que las conexiones se cierren adecuadamente


public class PalabraDAO {

    // Inserta una palabra en la base de datos
    public void insertarPalabra(Palabra palabra) {
        String sql = "INSERT INTO palabras (palabraIngles, traduccionEspanol, contexto, dificultad) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, palabra.getPalabraIngles());
            pstmt.setString(2, palabra.getTraduccionEspanol());
            pstmt.setString(3, palabra.getContexto());
            pstmt.setInt(4, palabra.getDificultad());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar palabra: " + e.getMessage());
        }
    }

    // Obtiene todas las palabras de la base de datos
    public List<Palabra> obtenerPalabras() {
        List<Palabra> lista = new ArrayList<>();
        String sql = "SELECT * FROM palabras";

        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Palabra palabra = new Palabra(
                        rs.getInt("id"),
                        rs.getString("palabraIngles"),
                        rs.getString("traduccionEspanol"),
                        rs.getString("contexto"),
                        rs.getInt("dificultad")
                );
                lista.add(palabra);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener palabras: " + e.getMessage());
        }

        return lista;
    }

    // Actualiza la dificultad de una palabra por su ID
    public void actualizarDificultad(Palabra palabra) {
        String sql = "UPDATE palabras SET dificultad = ? WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, palabra.getDificultad());
            pstmt.setInt(2, palabra.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar dificultad: " + e.getMessage());
        }
    }


    // Elimina una palabra por su ID
    public void eliminarPalabra(int id) {
        String sql = "DELETE FROM palabras WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar palabra: " + e.getMessage());
        }
    }

    public Palabra obtenerPalabraAleatoria() {
        String sql = "SELECT * FROM palabras ORDER BY RAND() LIMIT 1";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int id = rs.getInt("id");
                String palabraIngles = rs.getString("palabraIngles");
                String traduccionEspanol = rs.getString("traduccionEspanol");
                String contexto = rs.getString("contexto");
                int dificultad = rs.getInt("dificultad");

                return new Palabra(id, palabraIngles, traduccionEspanol, contexto, dificultad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Si no encuentra palabra o hay error
    }

}
