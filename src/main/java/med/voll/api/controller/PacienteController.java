package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.CadastrarPacienteDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @PostMapping
    public void cadastrarPaciente(@RequestBody @Valid CadastrarPacienteDTO dados){
        System.out.println(dados);
    }
}
