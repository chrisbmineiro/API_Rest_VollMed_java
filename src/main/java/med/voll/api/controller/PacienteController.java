package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AtualizarPacienteDTO;
import med.voll.api.dto.CadastroPacienteDTO;
import med.voll.api.dto.ListagemMedicosDTO;
import med.voll.api.dto.ListagemPacientesDTO;
import med.voll.api.models.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid CadastroPacienteDTO dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<ListagemPacientesDTO> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(ListagemPacientesDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizarPaciente(@RequestBody @Valid AtualizarPacienteDTO dados){
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    // mudar o atributo ativo para false
    @DeleteMapping("/{id}")
    @Transactional
    public void excluirPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }
}
