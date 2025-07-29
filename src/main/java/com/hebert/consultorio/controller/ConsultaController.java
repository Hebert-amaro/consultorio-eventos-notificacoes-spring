package com.hebert.consultorio.controller;

import com.hebert.consultorio.model.Consulta; 
import com.hebert.consultorio.model.Paciente; 
import com.hebert.consultorio.repository.PacienteRepository; 
import com.hebert.consultorio.service.ConsultaService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private PacienteRepository pacienteRepository; // Para criar um paciente de teste rapidamente

    @PostMapping("/agendar")
    public ResponseEntity<Consulta> agendarConsulta(@RequestBody Map<String, Object> payload) {
        // Simplesmente para testar, vamos criar um paciente se não existir.
        // Em um cenário real, o paciente viria de um ID existente ou seria criado em outro endpoint.
        Long pacienteId = ((Number)payload.get("pacienteId")).longValue();
        String descricao = (String) payload.get("descricao");
        // Para testar, vamos assumir que a data e hora vêm como string ou são geradas automaticamente
        LocalDateTime dataHora = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0); // Exemplo: amanhã às 10h

        // Se o pacienteId não existir, crie um de teste
        if (!pacienteRepository.existsById(pacienteId)) {
            Paciente p = new Paciente();
            p.setNome("Paciente Teste " + pacienteId);
            p.setTelefone("99999-999" + pacienteId);
            pacienteRepository.save(p);
        }

        Consulta novaConsulta = consultaService.agendarConsulta(pacienteId, descricao, dataHora);
        return ResponseEntity.ok(novaConsulta);
    }

    // Endpoint para testar o cancelamento (sem evento por enquanto, mas pode ser adicionado)
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Consulta> cancelarConsulta(@PathVariable Long id) {
        Consulta consultaCancelada = consultaService.cancelarConsulta(id);
        return ResponseEntity.ok(consultaCancelada);
    }
}
