package com.example.clase23.repository;

import com.example.clase23.model.Turno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TurnoDaoLista implements IDao<Turno> {
    private List<Turno> turnos;
    public TurnoDaoLista() { turnos=new ArrayList<>(); }

    @Override
    public Turno guardar(Turno turno) {
        PacienteDAOH2 pacienteDao= new PacienteDAOH2();
        OdontologoDAOH2 odontologoDao= new OdontologoDAOH2();
        turno.setPaciente(pacienteDao.buscar(turno.getPaciente().getId()));
        turno.setOdontologo((odontologoDao.buscar(turno.getOdontologo().getId())));
;       turnos.add(turno);
        return turno;
    }

    @Override
    public Turno buscar(Integer id) {
        Turno turnoBuscado= null;
        for (Turno turno:turnos){
            if(turno.getId() == id){
                turnoBuscado=turno;
            }
        }
        return turnoBuscado;
    }

    @Override
    public void actualizar(Turno turno) {
        eliminar (turno.getId());
        guardar(turno);
    }

    @Override
    public void eliminar(Integer id) {
        Turno turnoBuscado=buscar(id);
        turnos.remove(turnoBuscado);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnos;
    }

    @Override
    public Turno buscarXString(String valor) {
        return null;
    }
}
