package com.digital.ClinicaOdontologica.repository;

import com.digital.ClinicaOdontologica.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    Optional<Odontologo> findBynombreLike(String nombre);

    Optional<Odontologo> findByMatricula(String matricula);
}