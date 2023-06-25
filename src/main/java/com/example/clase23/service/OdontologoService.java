package com.example.clase23.service;

import com.example.clase23.dao.IDao;
import com.example.clase23.model.Odontologo;
import org.springframework.stereotype.Service;

@Service
public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }
}
