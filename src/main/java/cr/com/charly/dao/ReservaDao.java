package cr.com.charly.dao;

import cr.com.charly.modelo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao {
    private Connection connection;
    public ReservaDao(Connection connection) {
        this.connection = connection;
    }
    public int guardar(Reserva reserva) {
        // Imprimir en la consola que se va a guardar la reserva (debug)
        System.out.println("Guardando la reserva");

        // Consulta SQL para guardar la reserva
        String query = "INSERT INTO reservas (huesped_id, fecha_ingreso, fecha_egreso, valor_total, metodo_pago) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            // Settear los valores de la reserva en la consulta SQL
            statement.setInt(1, reserva.getHuespedId());
            statement.setDate(2, java.sql.Date.valueOf(reserva.getFechaIngreso()));
            statement.setDate(3, java.sql.Date.valueOf(reserva.getFechaEgreso()));
            statement.setDouble(4, reserva.getValorTotal());
            statement.setString(5, reserva.getMetodoPago());

            // Ejecutar la consulta SQL y obtener el resultado
            int affectedRows = statement.executeUpdate();

            // Si se insertó la reserva, obtener el id generado
            if (affectedRows == 1) {
                try (ResultSet resultSet =statement.getGeneratedKeys()) {
                    // Si se obtuvo el id generado, setearlo en la reserva
                    while (resultSet.next()) {
                        // Setear el id generado en la reserva
                        reserva.setId(resultSet.getInt(1));
                        // Imprimir en consola la reserva que se insertó (debug)
                        System.out.println(String.format("Fue insertada la reserva: %s", reserva));
                    }
                }
            }
        } catch (SQLException e) {
            // Lanzar una excepción en caso de que ocurra un error
            throw new RuntimeException(e);
        }

        return reserva.getId();
    }

    public List<Reserva> listar() {
        List<Reserva> r = new ArrayList<>();

        // Consulta SQL para listar las reservas
        String query = "SELECT * FROM reservas";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // EJecutar la consulta SQL y obtener el resultado
            try (ResultSet resultSet = statement.executeQuery()) {
                // Si se obtuvo un resultado, agregar las reservas a la lista
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int huespedId = resultSet.getInt("huesped_id");
                    Date fechaIngreso = resultSet.getDate("fecha_ingreso");
                    Date fechaEgreso = resultSet.getDate("fecha_egreso");
                    Double valorTotal = resultSet.getDouble("valor_total");
                    String metodoPago = resultSet.getString("metodo_pago");

                    // Crear una nueva reserva con los valores obtenidos
                    Reserva reserva = new Reserva(id, huespedId, fechaIngreso.toLocalDate(), fechaEgreso.toLocalDate(), valorTotal, metodoPago);

                    // Agregar la reserva a la lista
                    r.add(reserva);
                }
            }
        } catch (SQLException e) {
            // Lanzar una excepción en caso de que ocurra un error
            throw new RuntimeException(e);
        }

        return r;
    }

    public int eliminar(int id) {
        // Consulta SQL para eliminar una reserva
        String query = "DELETE FROM reservas WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Setear los valores de la reserva en la consulta SQL
            statement.setInt(1, id);

            // Ejecutar la consulta SQL y obtener el número de filas afectadas
            int rowsAffected = statement.executeUpdate();

            // Imprimir en consola el número de filas afectadas (debug)
            System.out.println(String.format("Se eliminaron %d filas.", rowsAffected));

            // Retornar el número de filas afectadas
            return rowsAffected;
        } catch (SQLException e) {
            // Lanzar una excepción en caso de que ocurra un error
            throw new RuntimeException(e);
        }
    }

}
