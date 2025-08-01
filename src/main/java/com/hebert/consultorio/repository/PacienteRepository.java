package com.hebert.consultorio.repository;

import com.hebert.consultorio.model.Paciente; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
