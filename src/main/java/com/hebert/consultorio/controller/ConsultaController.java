package com.hebert.consultorio.controller;

import com.hebert.consultorio.model.Consulta;
import com.hebert.consultorio.model.Paciente;
import com.hebert.consultorio.repository.PacienteRepository;
import com.hebert.consultorio.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation; // Importe esta
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag; // Importe esta
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/consultas")
@Tag(name = "Consultas", description = "API para gerenciamento de consultas odontológicas") // Anotação na classe
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Operation(summary = "Agenda uma nova consulta") // Anotação no método
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta agendada com sucesso",
                    content = @Content(schema = @Schema(implementation = Consulta.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/agendar")
    public ResponseEntity<Consulta> agendarConsulta(@RequestBody Map<String, Object> payload) {
        // ... seu código existente ...
        Long pacienteId = ((Number)payload.get("pacienteId")).longValue();
        String descricao = (String) payload.get("descricao");
        LocalDateTime dataHora = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0);

        if (!pacienteRepository.existsById(pacienteId)) {
            Paciente p = new Paciente();
            p.setNome("Paciente Teste " + pacienteId);
            p.setTelefone("99999-999" + pacienteId);
            pacienteRepository.save(p);
        }

        Consulta novaConsulta = consultaService.agendarConsulta(pacienteId, descricao, dataHora);
        return ResponseEntity.ok(novaConsulta);
    }

    @Operation(summary = "Cancela uma consulta existente") // Exemplo para o endpoint de cancelar
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta cancelada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    })

    // Endpoint para testar o cancelamento (sem evento por enquanto, mas pode ser adicionado)
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<Consulta> cancelarConsulta(@PathVariable Long id) {
        Consulta consultaCancelada = consultaService.cancelarConsulta(id);
        return ResponseEntity.ok(consultaCancelada);
    }
}
