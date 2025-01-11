package med.voll.api.dto.paciente;

import med.voll.api.models.Endereco;
import med.voll.api.models.Paciente;

public record DetalhamentoPacienteDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        Endereco endereco,
        Boolean ativo
) {
    public DetalhamentoPacienteDTO(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco(), paciente.getAtivo());
    }
}