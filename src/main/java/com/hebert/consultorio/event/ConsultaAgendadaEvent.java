package com.hebert.consultorio.event;

import com.hebert.consultorio.model.Consulta; // Ajuste este pacote conforme o seu
import org.springframework.context.ApplicationEvent;

public class ConsultaAgendadaEvent extends ApplicationEvent {

    private final Consulta consulta;

    public ConsultaAgendadaEvent(Object source, Consulta consulta) {
        super(source);
        this.consulta = consulta;
    }

    public Consulta getConsulta() {
        return consulta;
    }
}