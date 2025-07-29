package com.hebert.consultorio.listener;

import com.hebert.consultorio.event.ConsultaAgendadaEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoSmsListener {

    @EventListener
    public void handleConsultaAgendada(ConsultaAgendadaEvent event) {
        // Lógica para enviar SMS.
        // Aqui estamos apenas simulando a impressão no console.
        System.out.println("----- Notificação por SMS -----");
        System.out.printf("Para: %s%n", event.getConsulta().getPaciente().getTelefone());
        System.out.printf("Conteúdo: Consulta de %s agendada para %s. Consultorio Odontologico.%n",
                event.getConsulta().getDescricao(),
                event.getConsulta().getDataHora());
        System.out.println("-------------------------------");
    }
}