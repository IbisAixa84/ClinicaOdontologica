package com.example.clase23.dao;

import com.example.clase23.model.Odontologo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OdontologoDAOH2 implements IDao<Odontologo> {
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return null;
    }

    @Override
    public Odontologo buscar(Integer id) {
        return null;
    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Odontologo> listarTodos() {
        return null;
    }

    @Override
    public Odontologo buscarXString(String valor) {
        return null;
    }
}
