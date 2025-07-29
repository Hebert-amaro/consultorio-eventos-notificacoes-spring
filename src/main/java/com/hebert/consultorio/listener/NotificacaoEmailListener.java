package com.hebert.consultorio.listener;

import com.hebert.consultorio.event.ConsultaAgendadaEvent; // Ajuste este pacote conforme o seu
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoEmailListener {

    @EventListener
    public void handleConsultaAgendada(ConsultaAgendadaEvent event) {
        // Lógica para enviar e-mail.
        // Aqui estamos apenas simulando a impressão no console.
        System.out.println("----- Notificação por E-mail -----");
        System.out.printf("Para: %s%n", event.getConsulta().getPaciente().getNome());
        System.out.printf("Assunto: Confirmação de Consulta Agendada%n");
        System.out.printf("Conteúdo: Sua consulta de %s foi agendada para %s.%n",
                event.getConsulta().getDescricao(),
                event.getConsulta().getDataHora());
        System.out.println("----------------------------------");
    }
}