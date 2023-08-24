# Simulación de Reserva de Hotel

Este es un proyecto sencillo que simula el proceso de reserva de un hotel, permitiendo a los usuarios registrar reservas, ver todas las reservas y huéspedes, así como realizar modificaciones y eliminaciones en los datos de reservas y huéspedes.

## Características

- Registrar una nueva reserva con información detallada, incluyendo fecha de entrada, fecha de salida y método de pago.
- Asociar una reserva con un huésped existente en la base de datos o crear un nuevo huésped si es necesario.
- Visualizar todas las reservas y huéspedes almacenados en la base de datos.
- Modificar los detalles de reservas y huéspedes según sea necesario.
- Eliminar reservas y huéspedes de la base de datos.

## Tecnologías Utilizadas

- Java con SDK Oracle OpenJDK versión 20.0.1.
- MySQL con MySQL Workbench para la gestión de la base de datos.

## Configuración de la Base de Datos

La base de datos `hotel` contiene dos tablas: `huespedes` y `reservas`.

### Tabla `huespedes`

- `id`: Identificador único del huésped (AUTO_INCREMENT).
- `nombre`: Nombre del huésped (No puede ser nulo).
- `apellido`: Apellido del huésped (No puede ser nulo).
- `fecha_nacimiento`: Fecha de nacimiento del huésped (No puede ser nulo).
- `nacionalidad`: Nacionalidad del huésped (No puede ser nulo).
- `telefono`: Número de teléfono del huésped (No puede ser nulo).

### Tabla `reservas`

- `id`: Identificador único de la reserva (AUTO_INCREMENT).
- `huesped_id`: ID del huésped asociado a la reserva (No puede ser nulo).
- `fecha_ingreso`: Fecha de entrada para la reserva (No puede ser nulo).
- `fecha_egreso`: Fecha de salida para la reserva (No puede ser nulo).
- `valor_total`: Costo total de la reserva (No puede ser nulo).
- `metodo_pago`: Método de pago utilizado para la reserva (No puede ser nulo).

## Uso

1. Clona este repositorio en tu máquina local.
2. Configura la conexión a la base de datos en tu aplicación.
3. Ejecuta la aplicación y comienza a simular el proceso de reserva de hotel.

¡Diviértete simulando el proceso de reserva de hotel con esta aplicación!

## Disclaimer

Este proyecto se proporciona "tal cual" y actualmente no ha sido sometido a pruebas exhaustivas. Es posible que el proyecto contenga errores o fallos inesperados.
