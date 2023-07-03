package com.digital.ClinicaOdontologica;

import com.digital.ClinicaOdontologica.entities.Odontologo;
import com.digital.ClinicaOdontologica.repository.OdontologoRepository;
import com.digital.ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Clase23Application {
	@Autowired
	private OdontologoRepository odontologoRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	public static void main(String[] args) {

		SpringApplication.run(Clase23Application.class, args);



		Odontologo odontologo=new Odontologo("ML365","Guillermina","Lopez");
	}

}
