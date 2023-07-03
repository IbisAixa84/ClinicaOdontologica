package com.digital.ClinicaOdontologica.repository;
import com.digital.ClinicaOdontologica.entities.security.AppUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUsuario,Long> {
    Optional<AppUsuario> findByEmail(String email);

}
