package com.hebert.consultorio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data; // Importe Lombok se estiver usando

import java.time.LocalDateTime;

@Entity
@Data // Anotação do Lombok
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Paciente paciente;

    private LocalDateTime dataHora;
    private String descricao;
    private ConsultaStatus status; // Usaremos um enum para o status

    // Construtores, getters e setters seriam gerados pelo Lombok
}
