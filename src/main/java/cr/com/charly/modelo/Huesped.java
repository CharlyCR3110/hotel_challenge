package cr.com.charly.modelo;

import java.time.LocalDate;

public class Huesped {
    private Integer id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    public Huesped() {}
    // Constructor para guardarlos
    public Huesped (String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
    }
    // COnstructor para cuando se recuperan de la base de datos
    public Huesped (Integer id, String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        r.append("Huesped: ");
        r.append(this.nombre);
        r.append(" ");
        r.append(this.apellido);
        r.append(" ");
        r.append(this.fechaNacimiento);
        r.append(" ");
        r.append(this.nacionalidad);
        r.append(" ");
        r.append(this.telefono);
        return r.toString();
    }
}
