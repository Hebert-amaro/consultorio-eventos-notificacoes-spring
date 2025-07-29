package com.hebert.consultorio.repository;

import com.hebert.consultorio.model.Consulta; // Ajuste este pacote conforme o seu
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}