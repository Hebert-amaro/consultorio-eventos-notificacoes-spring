package com.hebert.consultorio.service;

import com.hebert.consultorio.event.ConsultaAgendadaEvent; 
import com.hebert.consultorio.model.Consulta; 
import com.hebert.consultorio.model.ConsultaStatus; 
import com.hebert.consultorio.model.Paciente; 
import com.hebert.consultorio.repository.ConsultaRepository; 
import com.hebert.consultorio.repository.PacienteRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher; // Publicador de eventos do Spring

    public Consulta agendarConsulta(Long pacienteId, String descricao, LocalDateTime dataHora) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
        
        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setDescricao(descricao);
        consulta.setDataHora(dataHora);
        consulta.setStatus(ConsultaStatus.AGENDADA);

        Consulta novaConsulta = consultaRepository.save(consulta);

        // Publica o evento de consulta agendada
        eventPublisher.publishEvent(new ConsultaAgendadaEvent(this, novaConsulta));

        return novaConsulta;
    }

    // Método para simular cancelamento e publicar evento
    public Consulta cancelarConsulta(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada!"));

        if (consulta.getStatus() != ConsultaStatus.CANCELADA) {
            consulta.setStatus(ConsultaStatus.CANCELADA);
            Consulta consultaAtualizada = consultaRepository.save(consulta);
            // Poderíamos criar um novo evento, ex: ConsultaCanceladaEvent
            System.out.println("Consulta " + consultaId + " cancelada.");
            return consultaAtualizada;
        }
        return consulta;
    }
}
