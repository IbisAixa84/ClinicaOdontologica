package com.example.clase23.service;

import com.example.clase23.entities.Odontologo;
import com.example.clase23.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return (Odontologo) odontologoRepository.save(odontologo);
    }
    public Optional<Odontologo> buscarOdontologo(Long id){
        return odontologoRepository.findById(id);
    }
    public void actualizarOdontologo(Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo(Long id){
        odontologoRepository.deleteById(id);
    }
    public List<Odontologo> listarOdontologos(){
       return odontologoRepository.findAll();
    }
    public Optional<Odontologo> buscarPorMatricula(String matricula){
        return odontologoRepository.findByMatricula(matricula);
    }
}
