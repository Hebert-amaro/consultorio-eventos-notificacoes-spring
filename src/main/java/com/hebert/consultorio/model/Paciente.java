package com.hebert.consultorio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; // Importe Lombok se estiver usando

@Entity
@Data // Anotação do Lombok para getters, setters, toString, equals e hashCode
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;

    // Construtores, getters e setters seriam gerados pelo Lombok (@Data)
    // Se não usar Lombok, você precisaria adicionar:
    // public Paciente() {}
    // public Long getId() { return id; }
    // public void setId(Long id) { this.id = id; }
    // ... e assim por diante para nome e telefone
}
