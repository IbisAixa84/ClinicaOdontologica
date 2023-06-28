package com.example.clase23.service;

import com.example.clase23.entities.Turno;
import com.example.clase23.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;
    public List<Turno> listarTurnos(){
        return turnoRepository.findAll();
    }
    public Optional<Turno> buscarPorID(Long id){
        return turnoRepository.findById(id);
    }
    public Turno guardarTurno(Turno turno){
        return turnoRepository.save(turno);
    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);
    }
}
