package med.voll.api.dto;

import med.voll.api.models.Paciente;

public record ListagemPacientesDTO(
        String nome,
        String email,
        String cpf
) {
    public ListagemPacientesDTO(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
