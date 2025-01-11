package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AgendamentoConsultaDTO;
import med.voll.api.dto.CancelamentoConsultaDTO;
import med.voll.api.dto.DetalhamentoConsultaDTO;
import med.voll.api.services.AgendaDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class CosultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid AgendamentoConsultaDTO dados){
        // Agendar consulta
        agendaDeConsultas.agendarConsulta(dados);
        return ResponseEntity.ok(new DetalhamentoConsultaDTO(null,null,null,null));
    }

    public ResponseEntity cancelarConsulta(@RequestBody @Valid CancelamentoConsultaDTO dados){
        agendaDeConsultas.cancelarConsulta(dados);
        return ResponseEntity.ok(new DetalhamentoConsultaDTO(null,null,null,null));
    }
}
