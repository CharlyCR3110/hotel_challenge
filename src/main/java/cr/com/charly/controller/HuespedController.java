package cr.com.charly.controller;

import cr.com.charly.dao.HuespedDao;
import cr.com.charly.factory.ConnectionFactory;
import cr.com.charly.modelo.Huesped;

import java.time.LocalDate;
import java.util.List;

public class HuespedController {
    private final HuespedDao huespedDao;
    public HuespedController() {
        var factory = new ConnectionFactory();
        this.huespedDao = new HuespedDao(factory.recuperarConexion());
    }

    public int guardar(Huesped huesped) {
        return this.huespedDao.guardar(huesped);
    }

    public List<Huesped> listar() {
        return this.huespedDao.listar();
    }

    public int eliminar(Integer id) {
        return this.huespedDao.eliminar(id);
    }

    public List<Huesped> listarPorApellido(String apellido) {
        return this.huespedDao.listarPorApellido(apellido);
    }

    public int modificar(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono, Integer id) {
        return this.huespedDao.modificar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, id);
    }

}
