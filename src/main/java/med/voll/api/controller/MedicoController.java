package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AtualizarMedicoDTO;
import med.voll.api.dto.CadastroMedicoDTO;
import med.voll.api.dto.ListagemMedicosDTO;
import med.voll.api.models.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid CadastroMedicoDTO dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<ListagemMedicosDTO> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(ListagemMedicosDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizarMedico(@RequestBody @Valid AtualizarMedicoDTO dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    // mudar o atributo ativo para false
    @DeleteMapping("/{id}")
    @Transactional
    public void excluirMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
