package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.consultas.AgendamentoConsultaDTO;
import med.voll.api.dto.consultas.CancelamentoConsultaDTO;
import med.voll.api.dto.consultas.ListagemConsultasDTO;
import med.voll.api.dto.consultas.RelatorioConsultaMensalDTO;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.services.AgendaDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class CosultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @Autowired
    private ConsultaRepository consultaRepository;

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

    // Listagem de consultas
    @GetMapping
    public ResponseEntity<Page<ListagemConsultasDTO>> listarConsultas(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao) {
        var page = consultaRepository.findAllByStatusAgendado(paginacao);
        var dtoPage = page.map(ListagemConsultasDTO::new); // Mapeando para o DTO
        return ResponseEntity.ok(dtoPage);
    }

    // Relatorio mensal
    @GetMapping("/relatorio-mensal/{mes}")
    public ResponseEntity<List<RelatorioConsultaMensalDTO>> gerarRelatorioConsultaMensal(@PathVariable YearMonth mes) {
        LocalDateTime inicioMes = mes.atDay(1).atStartOfDay();
        LocalDateTime fimMes = mes.atEndOfMonth().atTime(23, 59, 59);

        List<RelatorioConsultaMensalDTO> relatorio = consultaRepository.gerarRelatorioConsultaMensal(inicioMes, fimMes);

        return ResponseEntity.ok(relatorio);
    }
}
