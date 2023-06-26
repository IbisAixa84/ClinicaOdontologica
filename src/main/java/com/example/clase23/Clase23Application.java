package com.example.clase23;

import com.example.clase23.repository.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Clase23Application {

	public static void main(String[] args) {
		BD.crearTablas();
		SpringApplication.run(Clase23Application.class, args);
	}

}
