package com.example.clase23.service;

import com.example.clase23.dao.IDao;
import com.example.clase23.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }
    public Odontologo buscarOdontologo(Integer id){
        return odontologoIDao.buscar(id);
    }
    public void actualizarOdontologo(Odontologo odontologo){
        odontologoIDao.actualizar(odontologo);
    }
    public void eliminarOdontologo(Integer id) { odontologoIDao.eliminar(id); }
    public List<Odontologo> listadoOdontologos() { return odontologoIDao.listarTodos(); }
    public Odontologo buscarXString(String valor) { return odontologoIDao.buscarXString(valor); }
}
























