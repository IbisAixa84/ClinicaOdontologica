package com.digital.ClinicaOdontologica.securityConfiguratuon;

import com.digital.ClinicaOdontologica.entities.security.AppUsuario;
import com.digital.ClinicaOdontologica.entities.security.AppUsuarioRol;
import com.digital.ClinicaOdontologica.entities.security.UsuarioService;
import com.digital.ClinicaOdontologica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass="password";
        String passCifrada= passwordEncoder.encode(pass);

        usuarioService.guardarUsuario(new AppUsuario("Pablo", "Sayago", "sayago@gmail", passCifrada, AppUsuarioRol.ROLE_USER));

    }
}
