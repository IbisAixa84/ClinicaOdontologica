package com.example.clase23.repository;

import com.example.clase23.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {
    Optional<Odontologo> findByMatricula(String matricula);
}
