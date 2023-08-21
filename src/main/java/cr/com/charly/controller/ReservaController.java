package cr.com.charly.controller;

import cr.com.charly.dao.ReservaDao;
import cr.com.charly.factory.ConnectionFactory;
import cr.com.charly.modelo.Huesped;
import cr.com.charly.modelo.Reserva;

import java.time.LocalDate;
import java.util.List;

public class ReservaController {
    private final ReservaDao reservaDao;
    public ReservaController() {
        var factory = new ConnectionFactory();
        this.reservaDao = new ReservaDao(factory.recuperarConexion());
    }

    public int guardar(Reserva reserva) {
        return this.reservaDao.guardar(reserva);
    }

    public List<Reserva> listar() {
        return this.reservaDao.listar();
    }

    public int eliminar(Integer id) {
        return this.reservaDao.eliminar(id);
    }

    public List<Reserva> listarPorHuespedId(int huespedId) {
        return this.reservaDao.listarPorHuespedId(huespedId);
    }

    public int modificar(LocalDate fechaIngreso, LocalDate fechaEgreso, String formaDePago, int id) {
        return this.reservaDao.modificar(fechaIngreso, fechaEgreso, formaDePago, id);
    }
}
