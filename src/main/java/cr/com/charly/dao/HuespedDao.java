package cr.com.charly.dao;

import cr.com.charly.modelo.Huesped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void guardar(Huesped huesped) {
        // Comprobar si el huesped ya existe
        if (tieneDuplicados(huesped)) {
            // Imprimir en consola que el huesped ya existe (debug)
            System.out.println("El huésped ya existe.");
            // Retornar para no guardar el huesped
            // TO-DO mostrar un frame con un avisando que el huesped ya existe y que por ende no ende no es necesario guardarlo
            return;
        }

        // Imprimir en consola el huesped que se va a guardar (debug)
        System.out.println("Guardando el huesped " + huesped.toString());

        // Consulta SQL para insertar un huesped
        String query = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            // Setear los valores del huesped en la consulta SQL
            statement.setString(1, huesped.getNombre());
            statement.setString(2, huesped.getApellido());
            statement.setDate(3, java.sql.Date.valueOf(huesped.getFechaNacimiento()));
            statement.setString(4, huesped.getNacionalidad());
            statement.setString(5, huesped.getTelefono());

            // Ejectuar la consulta SQL y obtener el número de filas afectadas
            int rowsAffected = statement.executeUpdate();

            // Si se insertó el huesped, obtener el id generado
            if (rowsAffected == 1) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    // Si se obtuvo el id generado, setearlo en el huesped
                    while (resultSet.next()) {
                        // Setear el id generado en el huesped
                        huesped.setId(resultSet.getInt(1));
                        // Imprimir en consola el huesped que se insertó (debug)
                        System.out.println(String.format("Fue insertado el huesped: %s", huesped));
                    }
                }
            } else {
                // Imprimir en consola que no se pudo insertar el huesped (debug)
                System.out.println("No se pudo insertar el huésped.");
            }
        } catch (SQLException e) {
            // Lanzar una excepción en caso de que ocurra un error
            throw new RuntimeException(e);
        }
    }

    public List<Huesped> listar ()  {
        // TO-DO implementar el método
        List<Huesped> r = new ArrayList<>();

        // Consulta SQL para listar los huespedes
        String query = "SELECT * FROM huespedes";

        // try
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Ejecutar la consulta SQL y obtener el resultado
            try (ResultSet resultSet = statement.executeQuery()) {
                // Si se obtuvo un resultado, agregar los huespedes a la lista
                while (resultSet.next()) {
                    // Obtener los valores del huesped
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
                    String nacionalidad = resultSet.getString("nacionalidad");
                    String telefono = resultSet.getString("telefono");

                    // Crear un nuevo huesped con los valores obtenidos
                    Huesped huesped = new Huesped(id, nombre, apellido, fechaNacimiento.toLocalDate(), nacionalidad, telefono);

                    // Agregar el huesped a la lista
                    r.add(huesped);
                }
            }
        } catch (SQLException e) {
            // Lanzar una excepción en caso de que ocurra un error
            throw new RuntimeException(e);
        }

        // Retornar el resultado
        return r;
    }
    
    public int eliminar(Integer id) {
        // Consulta SQL para eliminar un huesped
        String query = "DELETE FROM huespedes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Setear los valores del huesped en la consulta SQL
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
