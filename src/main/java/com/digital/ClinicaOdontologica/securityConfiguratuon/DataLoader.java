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

        String passAdmin="password";
        String passCifradoAdmin= passwordEncoder.encode(passAdmin);

        usuarioService.guardarUsuario(new AppUsuario("Pablo", "Sayago", "sayago@gmail", passCifradoAdmin, AppUsuarioRol.ROLE_ADMIN));
        System.out.println("La contraseña cifrada es: "+passCifradoAdmin);

        //ROL USUARIO
        String passUser="password";
        String passCifradoUser= passwordEncoder.encode(passUser);
        System.out.println("La contraseña cifrada es: "+passCifradoUser);
        usuarioService.guardarUsuario(new AppUsuario("Ibis","Fortunato","ibis@gmail",passCifradoUser, AppUsuarioRol.ROLE_USER));
        //ROL CEO
        String passCeo="password";
        String passCifradoCeo= passwordEncoder.encode(passUser);
        System.out.println("La contraseña cifrada es: "+passCifradoCeo);
        usuarioService.guardarUsuario(new AppUsuario("Lauta","Diosquez","lautag@gmail",passCifradoUser, AppUsuarioRol.ROLE_CEO));

    }
}
