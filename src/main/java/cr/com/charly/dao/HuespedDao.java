package cr.com.charly.dao;

import cr.com.charly.modelo.Huesped;

import java.sql.*;

public class HuespedDao {
    private Connection connection;
    public HuespedDao(Connection connection) {
        this.connection = connection;
    }
    
    public boolean tieneDuplicados(Huesped huesped) {
        boolean r = false;
        // Consulta SQL para verificar si existe un huesped con el mismo nombre y apellido, count() retorna el número de filas que coinciden con el nombre y apellido del huesped
        String query = "SELECT COUNT(*) FROM huespedes WHERE nombre = ? AND apellido = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Setear los valores del huesped en la consulta SQL
            statement.setString(1, huesped.getNombre());
            statement.setString(2, huesped.getApellido());

            // Ejecutar la consulta SQL y obtener el resultado
            try (ResultSet resultSet = statement.executeQuery()) {
                // Si se obtuvo un resultado, verificar si existe un huesped con el mismo nombre y apellido
                if (resultSet.next()) {
                    // Obtener el número de filas que coinciden con el nombre y apellido del huesped
                    int rows = resultSet.getInt(1);

                    // Si existe un huesped con el mismo nombre y apellido, setear el resultado a true
                    if (rows > 0) {
                        r = true;
                    }
                }
            }
        } catch (SQLException e) {
            // Lanzar una excepción en caso de que ocurra un error
            throw new RuntimeException(e);
        }

        // Retornar el resultado
        return r;
    }
}
