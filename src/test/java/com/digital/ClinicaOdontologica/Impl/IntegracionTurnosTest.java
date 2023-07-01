/*package com.digital.ClinicaOdontologica.Impl;

import com.digital.ClinicaOdontologica.dto.TurnoDto;
import com.digital.ClinicaOdontologica.entities.Domicilio;
import com.digital.ClinicaOdontologica.entities.Odontologo;
import com.digital.ClinicaOdontologica.entities.Paciente;
import com.digital.ClinicaOdontologica.entities.Turno;
import com.digital.ClinicaOdontologica.service.OdontologoService;
import com.digital.ClinicaOdontologica.service.PacienteService;
import com.digital.ClinicaOdontologica.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

*//*
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    //necesito cargar los datos iniciales
    public void cargadorDeDatos(){
        Paciente pacienteAgregado= pacienteService.guardarPaciente(new Paciente("Jorgito","Pereyra","111111", LocalDate.of(2023,06,28),
                new Domicilio("Calle 1","1223","La Rioja","La Rioja"),"jorgito@digitalhouse.com"));
        Odontologo odontologoAgregado= odontologoService.guardarOdontologo(new Odontologo("Guillermo","Gardella","MP30"));
        TurnoDto turnoAgregado= turnoService.guardarTurno(new Turno(pacienteAgregado,odontologoAgregado,LocalDate.of(2023,07,20)));
    }
    @Test
    public void listarTurnosTest() throws Exception{
        cargadorDeDatos();
        MvcResult resultado=mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
       assertFalse(resultado.getResponse().getContentAsString().isEmpty());

    }
}*/
