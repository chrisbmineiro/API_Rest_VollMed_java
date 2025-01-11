package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.consultas.AgendamentoConsultaDTO;
import med.voll.api.dto.consultas.CancelamentoConsultaDTO;
import med.voll.api.dto.consultas.DetalhamentoConsultaDTO;
import med.voll.api.services.AgendaDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class CosultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid AgendamentoConsultaDTO dados){
        // Agendar consulta
        var dto = agendaDeConsultas.agendarConsulta(dados);
        return ResponseEntity.ok(dto);
    }

    // Cancelar consulta
    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarConsulta(@RequestBody @Valid CancelamentoConsultaDTO dados) {
        agendaDeConsultas.cancelarConsulta(dados);
        return ResponseEntity.noContent().build();
    }
}
