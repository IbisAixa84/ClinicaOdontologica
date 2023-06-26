package com.example.clase23.service;

import com.example.clase23.repository.IDao;
import com.example.clase23.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    @Autowired
    private IDao<Turno> turnoIDao;


    public Turno guardarTurno(Turno turno){
        return turnoIDao.guardar(turno);
    }
    public Turno buscarTurno(Integer id){ return turnoIDao.buscar(id); }
    public void eliminarTurno(Integer id){ turnoIDao.eliminar(id); }
    public void actualizarTurno(Turno turno){ turnoIDao.actualizar(turno); }
    public List<Turno> listarTurnos(){
        // mucho código de control
        return turnoIDao.listarTodos();
    }
}
