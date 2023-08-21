package cr.com.charly.modelo;

import java.time.LocalDate;

public class Reserva {
    Integer id;
    Integer huespedId;
    LocalDate fechaIngreso;
    LocalDate fechaEgreso;
    Double valorTotal;
    String metodoPago;
    Reserva() {}
    // Constructor para guardarlos
    public Reserva(Integer huespedId, LocalDate fechaIngreso, LocalDate fechaEgreso, String metodoPago) {
        int tarifaPorNoche = 100;

        this.huespedId = huespedId;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.valorTotal = (double) ((fechaEgreso.getDayOfYear() - fechaIngreso.getDayOfYear()) * tarifaPorNoche);
        this.metodoPago = metodoPago;
    }
    // COnstructor para cuando se recuperan de la base de datos

    public Reserva(Integer id, Integer huespedId, LocalDate fechaIngreso, LocalDate fechaEgreso, Double valorTotal, String metodoPago) {
        this.id = id;
        this.huespedId = huespedId;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.valorTotal = valorTotal;
        this.metodoPago = metodoPago;
    }
    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHuespedId() {
        return huespedId;
    }

    public void setHuespedId(Integer huespedId) {
        this.huespedId = huespedId;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(LocalDate fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        r.append("Reserva: ");
        r.append("id: ");
        r.append(id);
        r.append(", huespedId: ");
        r.append(huespedId);
        r.append(", fechaIngreso: ");
        r.append(fechaIngreso);
        r.append(", fechaEgreso: ");
        r.append(fechaEgreso);
        r.append(", valorTotal: ");
        r.append(valorTotal);
        r.append(", metodoPago: ");
        r.append(metodoPago);
        return r.toString();
    }
}
