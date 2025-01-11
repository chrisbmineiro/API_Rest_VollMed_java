package med.voll.api.controller;

import jakarta.transaction.Transactional;
import med.voll.api.dto.AgendamentoConsultaDTO;
import med.voll.api.dto.DetalhamentoConsultaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class CosultaController {

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody AgendamentoConsultaDTO dados){
        // Agendar consulta
        return ResponseEntity.ok(new DetalhamentoConsultaDTO(null,null,null,null));
    }
}
